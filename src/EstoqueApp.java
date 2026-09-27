public class EstoqueApp {

    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        System.out.println("=== Cadastro de produtos ===");
        try {
            estoque.adicionarProduto(new ProdutoComum("Arroz 5kg", 25.90, 10));
            estoque.adicionarProduto(new ProdutoComum("Detergente", 2.50, 40));
            estoque.adicionarProduto(new ProdutoPerecivel("Leite 1L", 5.00, 20, 10));
            estoque.adicionarProduto(new ProdutoPerecivel("Iogurte", 4.00, 15, 2));
            System.out.println("Produtos cadastrados com sucesso.");
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== Tentativa de cadastro inválido ===");
        try {
            estoque.adicionarProduto(new ProdutoComum("Feijão 1kg", 8.00, -5));
        } catch (QuantidadeInvalidaException e) {
            System.out.println("QuantidadeInvalidaException capturada: " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== Produtos em estoque ===");
        estoque.listarProdutos();

        System.out.println();
        System.out.println("=== Sobrecarga de aplicarDesconto ===");
        Product arroz = estoque.getProduto(0);
        arroz.aplicarDesconto(10);
        System.out.printf("Arroz com 10%% de desconto: R$ %.2f%n", arroz.getPreco());
        Product leite = estoque.getProduto(2);
        leite.aplicarDesconto(50, 1.00);
        System.out.printf("Leite com 50%% de desconto (máximo R$ 1,00): R$ %.2f%n", leite.getPreco());

        System.out.println();
        System.out.println("=== Vendas ===");
        try {
            realizarOperacoes(estoque);
        } catch (QuantidadeInvalidaException e) {
            System.out.println("QuantidadeInvalidaException capturada: " + e.getMessage());
        } catch (ProdutoIndisponivelException e) {
            System.out.println("ProdutoIndisponivelException capturada: " + e.getMessage());
        } catch (EstoqueException e) {
            // O catch mais genérico precisa vir por último
            System.out.println("EstoqueException capturada: " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== Estoque final ===");
        estoque.listarProdutos();
        System.out.printf("%nValor total do estoque: R$ %.2f%n", estoque.calcularValorTotalEstoque());
    }

    // Declara a exceção base: pode lançar qualquer EstoqueException
    private static void realizarOperacoes(Estoque estoque) throws EstoqueException {
        estoque.adicionarProduto(new ProdutoComum("Sabonete", 3.20, 30));
        System.out.println("Produto \"Sabonete\" cadastrado.");

        estoque.venderProduto(1, 15);
        System.out.println("Venda realizada: 15 unidade(s) de Detergente. Restam "
                + estoque.getProduto(1).getQuantidade() + ".");

        estoque.venderProduto(3, 100);
        System.out.println("Esta linha não deve ser impressa.");
    }
}
