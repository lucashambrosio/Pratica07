package Pratica07;

import javax.swing.JOptionPane;
import java.sql.*;

public class LivrariaUniversitaria {

    // Método para fazer a conexão com o banco de dados
    public static Connection conectar() {
        String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
        String user = "postgres";
        String password = "123456";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            JOptionPane.showMessageDialog(null, "Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }

        return connection;
    }

    // Classe para inserir registros na tabela "Livros"
    public static void inserirLivro(String titulo, double preco) {
        Connection connection = conectar();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Livros (titulo, vl_preco) VALUES (?, ?)");
            statement.setString(1, titulo);
            statement.setDouble(2, preco);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Livro inserido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir livro: " + e.getMessage());
        } finally {
            fecharConexao(connection);
        }
    }

    private static void fecharConexao(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                JOptionPane.showMessageDialog(null, "Conexão com o banco de dados fechada com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
        }
    }

    // Classe para excluir o livro pelo id
    public static void excluirLivro(int id) {
        Connection connection = conectar();

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Livros WHERE id = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Livro excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum livro encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir livro: " + e.getMessage());
        } finally {
            fecharConexao(connection);
        }
    }

    // Classe para buscar um livro pelo título
    public static void buscarLivroPorTitulo(String titulo) {
        Connection connection = conectar();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Livros WHERE titulo LIKE ?");
            statement.setString(1, titulo + "%");
            ResultSet resultSet = statement.executeQuery();

            StringBuilder resultMessage = new StringBuilder();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String livroTitulo = resultSet.getString("titulo");
                double preco = resultSet.getDouble("vl_preco");

                resultMessage.append("ID: ").append(id).append(", Título: ").append(livroTitulo).append(", Preço: ").append(preco).append("\n");
            }

            if (resultMessage.length() > 0) {
                JOptionPane.showMessageDialog(null, "Livros encontrados:\n" + resultMessage.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum livro encontrado com o título fornecido.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar livro por título: " + e.getMessage());
        } finally {
            fecharConexao(connection);
        }
    }

    // Classe para buscar um livro pelo valor do preço
    public static void buscarLivroPorPreco(double preco) {
        Connection connection = conectar();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Livros WHERE vl_preco >= ?");
            statement.setDouble(1, preco);
            ResultSet resultSet = statement.executeQuery();

            StringBuilder resultMessage = new StringBuilder();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String livroTitulo = resultSet.getString("titulo");
                double livroPreco = resultSet.getDouble("vl_preco");

                resultMessage.append("ID: ").append(id).append(", Título: ").append(livroTitulo).append(", Preço: ").append(livroPreco).append("\n");
            }

            if (resultMessage.length() > 0) {
                JOptionPane.showMessageDialog(null, "Livros encontrados:\n" + resultMessage.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum livro encontrado com o preço fornecido.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar livro por preço: " + e.getMessage());
        } finally {
            fecharConexao(connection);
        }
    }
}

