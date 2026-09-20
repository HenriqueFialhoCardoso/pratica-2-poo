import java.util.ArrayList;
import java.util.List;

public class Fatura {
    private List<Item> itens;

    public Fatura() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(Produto produto, int quantidade) {
        for (Item item : itens) {
            if (item.getProduto().getCodigo() == produto.getCodigo()) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }

        itens.add(new Item(produto, quantidade));
    }

    public void excluirItem(int codigo) {
        itens.removeIf(item -> item.getProduto().getCodigo() == codigo);
    }

    public void alterarItem(int codigo, int novaQuantidade) {
        for (Item item : itens) {
            if (item.getProduto().getCodigo() == codigo) {
                item.setQuantidade(novaQuantidade);
                return;
            }
        }
    }

    public void exibirFatura() {
        if (itens.isEmpty()) {
            System.out.println("\nFatura vazia.");
            return;
        }

        System.out.println("\n===== FATURA =====");

        for (Item item : itens) {
            System.out.println(item);
        }

        System.out.printf("Valor total: R$ %.2f%n", getValorTotal());
    }

    public double getValorTotal() {
        double total = 0;

        for (Item item : itens) {
            total += item.getValorTotal();
        }

        return total;
    }

    public boolean possuiItens() {
        return !itens.isEmpty();
    }
}