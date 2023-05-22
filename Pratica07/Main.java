package Pratica07;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("<1> Cadastrar Livro");
            System.out.println("<2> Pesquisar Livro por Preço");
            System.out.println("<3> Pesquisar Livro por Título");
            System.out.println("<4> Excluir Livro");
            System.out.println("<5> Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção:"));

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    pesquisarLivroPorPreco();
                    break;
                case 3:
                    pesquisarLivroPorTitulo();
                    break;
                case 4:
                    excluirLivro();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public static void cadastrarLivro() {
        String titulo = JOptionPane.showInputDialog("Digite o título do livro:");
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do livro:"));

        LivrariaUniversitaria.inserirLivro(titulo, preco);
    }

    public static void pesquisarLivroPorPreco() {
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço mínimo do livro:"));

        LivrariaUniversitaria.buscarLivroPorPreco(preco);
    }

    public static void pesquisarLivroPorTitulo() {
        String titulo = JOptionPane.showInputDialog("Digite o título do livro:");

        LivrariaUniversitaria.buscarLivroPorTitulo(titulo);
    }

    public static void excluirLivro() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do livro a ser excluído:"));

        LivrariaUniversitaria.excluirLivro(id);
    }
}
