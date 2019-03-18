package produto;

/**
 * Classe que representa um repositório de produtos usando arrays como estrutura
 * sobrejacente. Alguns métodos (atualizar, remover e procurar) ou executam com
 * sucesso ou retornam um erro. Para o caso desde exercício, o erro será
 * representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 * 
 * Obs: Note que você deve utilizar a estrutura de dados array e não
 * implementações de array prontas da API Collections de Java (como ArrayList,
 * por exemplo).
 * 
 * @author Adalberto
 *
 */
public class RepositorioProdutoNaoPerecivelArray {
	/**
	 * A estrutura (array) onde os produtos sao mantidos.
	 */
	private ProdutoNaoPerecivel[] produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutoNaoPerecivelArray(int size) {
		super();
		this.produtos = new ProdutoNaoPerecivel[size];
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {
		int indice = -1;

		for (int i = 0; i < this.produtos.length; i++) {
			if (this.produtos[i].getCodigo() == codigo) {
				indice = i;
				break;
			}
		}

		return indice;
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existe(int codigo) {
		boolean existe = false;
		int indice = this.procurarIndice(codigo);

		if (indice != -1) {
			existe = true;
		}

		return existe;
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(ProdutoNaoPerecivel produto) {
		if (produto == null) {
			throw new NullPointerException();
		}

		this.index += 1;

		try {
			this.produtos[this.index] = produto;
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Implement desired exception
			throw new UnsupportedOperationException("ArrayIOOB");
		}
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(ProdutoNaoPerecivel produto) {
		if (produto == null) {
			throw new NullPointerException();
		}

		int indice = this.procurarIndice(produto.getCodigo());

		if (indice == -1) {
			// TODO Implement desired exception
			throw new UnsupportedOperationException("Inexistente");
		}

		this.produtos[indice] = produto;
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo
	 */
	public void remover(int codigo) {
		int indice = this.procurarIndice(codigo);

		if (indice == -1) {
			// TODO Implement desired exception
			throw new UnsupportedOperationException("Inexistente");
		}

		this.produtos[indice] = null;

		while(indice < this.produtos.length && this.produtos[indice + 1] != null) {
		  this.produtos[indice] = this.produtos[indice + 1];

		  indice += 1;
		}
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public ProdutoNaoPerecivel procurar(int codigo) {
		int indice = this.procurarIndice(codigo);

		if (indice == -1) {
			// TODO Implement desired exception
			throw new UnsupportedOperationException("Inexistente");
		}

		return this.produtos[indice];
	}
}
