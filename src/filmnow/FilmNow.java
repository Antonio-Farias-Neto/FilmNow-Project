package filmnow;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes. 
 * 
 * @author Antonio Neto
 *
 */
public class FilmNow {
	
	private static final int TAMANHO = 100;
	
	private Filme[] filmes; //uma representacao simploria da lista de filmes
	private Filme[] hotList;// uma representação simplória da HotList de filmes

	/**
	 * Cria o FilmNow.
	 */
	public FilmNow() {
		this.filmes = new Filme[TAMANHO];
		this.hotList = new Filme[10];
	}
	
	/**
	 * Recebe um filme e o coloca na HotList.
	 * @param filme é o filme recebido
	 * @param posicao é a posição que ele entrará na hotlist.
	 * @return valor booleano confirmando se foi adicionado ou não.
	 */
	public String atribuirHot(Filme filme, int posicao) {
		if (posicao <1 || posicao > 10) {
			return "POSIÇÃO INVÁLIDA";
		}
		
		if (filme == null) {
			return "FILME INVÁLIDO, POSIÇÃO VAZIA";
		}
		if (filme.isNaHotList()) {
			return "FILME JÁ ADICIONADO";
		}
		if(this.hotList[posicao-1] != null) {
			this.hotList[posicao-1].setNaHotList(false);
			this.hotList[posicao-1] = filme;
			filme.setNaHotList(true);
			return "FILME ADICIONADO";
		}
		this.hotList[posicao-1] = filme;
		filme.setNaHotList(true);
		return "FILME ADICIONADO";
	}
	
	/**
	 * Remove um filme da HotList
	 * @param posicao a posicão do filme na hotlist que será removido.
	 * @return valor booleano confirmando ou não a remoção.
	 */
	public boolean removerHot(int posicao) {
		if (posicao < 1 || posicao > 10) {
			return false;
		}
		
		if (this.hotList[posicao - 1] == null) {
			return false;
		}
		
		this.hotList[posicao - 1].setNaHotList(false);
		this.hotList[posicao - 1] = null;
		return true;
		
	}
	
	/**
	 * procura os filmes e suas posições na hotlist e os concatena.
	 * @return concatenação de Strings e filmes , mostrando de forma organizada os filmes que estão
	 * na hotlist com seus respectivos nomes, anos e posições na lista.
	 */
	public String exibirHotList() { 
		String out = "";
		int contador = 1;
		for (Filme fil: this.hotList) {
			if (fil != null) {
				if (fil.getAno() == 0) {
					out += contador + " - " + fil.getNome() + "\n";
				} else {
					out += contador + " - " + fil.getNome() + ", " + fil.getAno() + "\n"; 		
				}
			}
			contador++;
		}
		return out;
	}
	
	/**
	 * Acessa a lista de filmes mantida.
	 * @return O array de filmes.
	 */
	public Filme[] getFilmes() {
		return this.filmes.clone();
	}

	/**
	 * Acessa os dados de um filme específico.
	 * @param posicao Posição do filme no sistema.
	 * @return Dados do filme. Null se não há filme na posição.
	 */
	public Filme getFilme(int posicao) {
		return filmes[posicao-1];
	}
	
	/**
	 * remove um filme da lista de filmes e da hotlist caso ele esteja lá.
	 * @param posicao a posição do filme que será removido na lista de filmes.
	 * @return
	 */
	public boolean removerFilme(int posicao) {
		if (posicao > 100 || posicao < 1) {
			return false;
		}
		if (this.filmes[posicao-1] == null) {
				return false;
		}
		for (int i =0 ; i < this.hotList.length; i++) {
			if (this.filmes[posicao-1].equals(this.hotList[i])) {
				this.hotList[i] = null;
			}
		}
		this.filmes[posicao-1] = null;
		return true;
	}
	
	/**
	 * concatena todos os nomes dos filmes iguais ao nome recebido, com suas respectivas posições.
	 * @param nome O nome recebido para ser procurado pelo array de filmes.
	 * @return concatennação de nomes de filmes e suas posições.
	 */
	public String mostrarTodosPeloNome(String nome) {
		String out = "";
		for (int i = 0; i < this.filmes.length; i++) {
			if (this.filmes[i] != null) {
				if (this.filmes[i].getNome().equals(nome)) {
					out += (i+1) + " - " + this.filmes[i].getNome() + "\n";
				}
			}
		}
		return out;
	}
	
	/**
	 * concatena os nomes dos filmes que possuem o mesmo ano de lançamento com suas respectivas posições
	 * @param ano O ano recebido que será comparado ao ano de cada filme do array de filmes.
	 * @return concatenação de nomes de filmes que possuem o mesmo ano com suas respectivas posições.
	 */
	public String mostrarTodosPeloAno(int ano) {
		String out = "";
		for (int i = 0; i < this.filmes.length; i++) {
			if (this.filmes[i] != null) {
				if (this.filmes[i].getAno() == ano) {
					out += (i+1) + " - " + this.filmes[i].getNome() + "\n";
				}
			}
		}
		return out;
	}

	/**
	 * Adiciona um filme em uma posição. Se já existir filme na posição, sobrescreve o anterior. 
	 * @param posicao Posição do filme.
	 * @param nome Nome do filme.
	 * @param ano Ano de lançamento do filme.
	 * @param local Local onde o filme pode ser assitido.
	 */
	public String cadastraFilme(int posicao, String nome, String ano, String local) {
		if ("".equals(ano.trim())) {
			ano = "0";
		}
		Filme filme = new Filme(nome,Integer.parseInt(ano),local);
		if (posicao < 1 || posicao > 100) {
			return "POSIÇÃO INVÁLIDA";
		}
		
		if ("".equals(local.trim()) || "".equals(nome.trim())) {
			return "FILME INVÁLIDO";
		}
				
		for (Filme fil: this.filmes) {
			if (filme.equals(fil)) {
				return "FILME JÁ ADICIONADO";
			}
		}
		this.filmes[posicao-1] = filme;
		return "FILME ADICIONADO";
		
	}

}
