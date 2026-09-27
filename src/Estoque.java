import java.util.ArrayList;
import java.util.List;

public class Estoque {
    // Composição: Estoque TEM UMA lista de Product
    private List<Product> produtos = new ArrayList<>();

    public void adicionarProduto(Product p) {
        produtos.add(p);
    }

    public void venderProduto(int indice, int quantidade) throws ProdutoIndisponivelException {
        if (indice < 0 || indice >= produtos.size()) {
            throw new ProdutoIndisponivelException("Produto de índice " + indice + " não existe no estoque.");
        }
        produtos.get(indice).vender(quantidade);
    }

    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Product p : produtos) {
            total += p.calcularValorTotal(); // cada subtipo calcula do seu jeito
        }
        return total;
    }

    public Product getProduto(int indice) {
        return produtos.get(indice);
    }

    public void listarProdutos() {
        for (int i = 0; i < produtos.size(); i++) {
            Product p = produtos.get(i);
            System.out.printf("[%d] %s | Total: R$ %.2f%n", i, p.getDescricao(), p.calcularValorTotal());
        }
    }
}
