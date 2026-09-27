public abstract class Product implements Vendavel {
    private String nome;
    private double preco;
    private int quantidade;

    public Product(String nome, double preco, int quantidade) throws QuantidadeInvalidaException {
        if (preco < 0) {
            throw new QuantidadeInvalidaException("Preço inválido para \"" + nome + "\": " + preco);
        }
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException("Quantidade inválida para \"" + nome + "\": " + quantidade);
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Cada subclasse decide como calcular o valor total
    public abstract double calcularValorTotal();

    public String getDescricao() {
        return String.format("%-20s | Preço: R$ %8.2f | Quantidade: %4d", nome, preco, quantidade);
    }

    @Override
    public void vender(int quantidadeDesejada) throws ProdutoIndisponivelException {
        if (quantidadeDesejada <= 0) {
            throw new IllegalArgumentException("A quantidade a vender deve ser positiva.");
        }
        if (quantidadeDesejada > quantidade) {
            throw new ProdutoIndisponivelException("Estoque insuficiente de \"" + nome + "\": solicitado "
                    + quantidadeDesejada + ", disponível " + quantidade + ".");
        }
        quantidade -= quantidadeDesejada;
    }

    // Sobrecarga (polimorfismo estático): aplica um desconto percentual sobre o preço
    public void aplicarDesconto(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("Percentual deve estar entre 0 e 100.");
        }
        preco -= preco * percentual / 100;
    }

    // Sobrecarga: aplica o desconto percentual, limitado a um valor máximo em reais
    public void aplicarDesconto(double percentual, double descontoMaximo) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("Percentual deve estar entre 0 e 100.");
        }
        if (descontoMaximo < 0) {
            throw new IllegalArgumentException("Desconto máximo não pode ser negativo.");
        }
        double desconto = Math.min(preco * percentual / 100, descontoMaximo);
        preco -= desconto;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
