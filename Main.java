import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto("Teclado", 1, 120.00));
        produtos.add(new Produto("Mouse", 2, 80.00));
        produtos.add(new Produto("Headset", 3, 200.00));

        Fatura fatura = new Fatura();

        int opcao;

        do {
            System.out.println("\n===== LOJA =====");
            System.out.println("1 - Comprar");
            System.out.println("2 - Ver Fatura");
            System.out.println("3 - Excluir item");
            System.out.println("4 - Alterar item");
            System.out.println("5 - Finalizar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.println("\n===== PRODUTOS =====");

                    for (Produto produto : produtos) {
                        System.out.println(produto);
                    }

                    System.out.println("0 - Voltar");
                    System.out.print("Digite o código do produto: ");
                    int codigoCompra = scanner.nextInt();

                    if (codigoCompra == 0) {
                        break;
                    }

                    Produto produtoEscolhido = null;

                    for (Produto produto : produtos) {
                        if (produto.getCodigo() == codigoCompra) {
                            produtoEscolhido = produto;
                            break;
                        }
                    }

                    if (produtoEscolhido == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    System.out.print("Digite a quantidade: ");
                    int quantidade = scanner.nextInt();

                    if (quantidade <= 0) {
                        System.out.println("Quantidade inválida.");
                        break;
                    }

                    fatura.adicionarItem(produtoEscolhido, quantidade);
                    System.out.println("Produto adicionado à fatura.");

                    break;

                case 2:
                    fatura.exibirFatura();
                    System.out.println("Digite 0 para voltar.");
                    scanner.nextInt();
                    break;

                case 3:
                    if (!fatura.possuiItens()) {
                        System.out.println("\nFatura vazia.");
                        break;
                    }

                    fatura.exibirFatura();

                    System.out.println("0 - Voltar");
                    System.out.print("Digite o código do item para excluir: ");
                    int codigoExcluir = scanner.nextInt();

                    if (codigoExcluir == 0) {
                        break;
                    }

                    fatura.excluirItem(codigoExcluir);
                    System.out.println("Item excluído.");

                    break;

                case 4:
                    if (!fatura.possuiItens()) {
                        System.out.println("\nFatura vazia.");
                        break;
                    }

                    fatura.exibirFatura();

                    System.out.println("0 - Voltar");
                    System.out.print("Digite o código do item: ");
                    int codigoAlterar = scanner.nextInt();

                    if (codigoAlterar == 0) {
                        break;
                    }

                    System.out.print("Digite a nova quantidade: ");
                    int novaQuantidade = scanner.nextInt();

                    if (novaQuantidade <= 0) {
                        System.out.println("Quantidade inválida.");
                        break;
                    }

                    fatura.alterarItem(codigoAlterar, novaQuantidade);
                    System.out.println("Item alterado.");

                    break;

                case 5:
                    System.out.println("\n===== COMPRA FINALIZADA =====");
                    fatura.exibirFatura();
                    System.out.printf("Valor final: R$ %.2f%n",
                            fatura.getValorTotal());

                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 5);

        scanner.close();
    }
}