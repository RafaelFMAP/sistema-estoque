# Sistema de Estoque de Produtos

Exercício de Java: classes abstratas, herança, interfaces, polimorfismo, composição e exceções.

## Estrutura

| Arquivo | Descrição |
|---|---|
| `EstoqueException` | Exceção base (checked) |
| `QuantidadeInvalidaException` | Preço ou quantidade negativos no cadastro |
| `ProdutoIndisponivelException` | Venda maior que o estoque disponível |
| `Vendavel` | Interface com `vender(int)` |
| `Product` | Classe abstrata; implementa `Vendavel`; sobrecarga de `aplicarDesconto` |
| `ProdutoComum` | `preco × quantidade` |
| `ProdutoPerecivel` | 20% de desconto quando `diasParaVencer <= 3` |
| `Estoque` | Composição: contém uma lista de `Product` |
| `EstoqueApp` | Programa principal (`main`) |

## Como compilar e executar

```bash
javac -d out src/*.java
java -cp out EstoqueApp
```

## Saída do programa

Veja [`saida.txt`](saida.txt).
