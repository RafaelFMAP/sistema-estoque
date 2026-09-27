public class ProdutoPerecivel extends Product {
    private static final double DESCONTO_VENCIMENTO = 0.20;

    private int diasParaVencer;

    public ProdutoPerecivel(String nome, double preco, int quantidade, int diasParaVencer)
            throws QuantidadeInvalidaException {
        super(nome, preco, quantidade);
        this.diasParaVencer = diasParaVencer;
    }

    @Override
    public double calcularValorTotal() {
        double total = getPreco() * getQuantidade();
        if (diasParaVencer <= 3) {
            total -= total * DESCONTO_VENCIMENTO;
        }
        return total;
    }

    @Override
    public String getDescricao() {
        String validade = "Vence em: " + diasParaVencer + " dia(s)";
        if (diasParaVencer <= 3) {
            validade += " (20% de desconto)";
        }
        return super.getDescricao() + " | " + validade;
    }

    public int getDiasParaVencer() {
        return diasParaVencer;
    }
}
