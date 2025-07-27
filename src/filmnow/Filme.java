package filmnow;

import java.util.Objects;

/**
 * A representação de um filme, com nome, ano de lançamento, locais de exibição, e se está em uma HotList.
 * @author Antonio Neto
 */
public class Filme {
	private String nome;
	private int ano;
	private String[] locais;
	private boolean NaHotList;
	
	/**
	 * Cria um filme
	 * @param nome é o nome do filme.
	 * @param ano é o ano de lançamento do filme.
	 * @param local é o local onde o filme é exibido.
	 */
	public Filme (String nome, int ano, String local) {
		this.nome = nome;
		this.ano = ano;
		this.locais = new String[5];
		this.locais[0] = local;
		this.NaHotList = false;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public int getAno() {
		return this.ano;
	}
	
	public String[] getLocais() {
		return this.locais;
	}
	
	public boolean isNaHotList() {
		return this.NaHotList;
	}
	
	public void setNaHotList(boolean valor) {
		this.NaHotList = valor;
	}
	
	/**
	 * sobrescreve o hashCode para retornar os inteiros correspndentes apenas ao ano e nome do filme.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(ano, nome);
	}
	
	/**
	 * sobrescreve o equals para fazer a comparação de filmes apenas pelo nome e ano.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return ano == other.ano && Objects.equals(nome, other.nome);
	}
	
	/**
	 * conta quantos locais o filme tem e retorna essa quantidade;
	 * @return quantidade de locais que o filme possui.
	 */
	public int quantidadeDeLocais() {
		int contador = 0;
		for (String local: this.locais) {
			if (local != null) {
				contador++;
			}
		}
		return contador;
	}
	
	/**
	 * Adiciona um local ao filme caso o filme tenha menos de 5 locais ou que o filme ainda não tenha esse local.
	 * @param local é o local recebido para ser adicionado ao filme.
	 * @return valor booleano confirmando ou não a adicão de um local ao filme.
	 */
	public boolean adicionarLocal(String local) {
		if (quantidadeDeLocais() == 5) {
			return false;
		}
		for (String loc: this.locais) {
			if (local.equals(loc)) {
				return false;
			}
		}
		this.locais[quantidadeDeLocais()] = local;
		return true;
	}
	
	/**
	 * remove um local de exibição da lista de locais do filme.
	 * @param local é a string local a ser recebida para ser removida.
	 * @return valor booleano confirmando ou não a remoção.
	 */
	public boolean removerLocal(String local) {
		if (quantidadeDeLocais() == 1) {
			return false;
		}
		for (int i = 0; i < this.locais.length; i++) {
			if(local.equals(this.locais[i])) {
				this.locais[i] = null;
				return true;
			}
		}
		return false;
	}

	/**
	 * sobreescreve o toString para retornar apenas o nome do filme.
	 */
	@Override
	public String toString() {
		return this.getNome();
	}
}
