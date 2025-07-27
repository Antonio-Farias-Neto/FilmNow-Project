package filmnow;

/**
 * Representação de um detalhamento de um filme.
 * @author Antonio Neto
 */
public class DetalharFilme {
	private Filme filme;
	private int posicao;
	/**
	 * cria um detalhar filme
	 * @param filme é o filme recebido para ser atribuído ao atributo filme.
	 */
	public DetalharFilme(Filme filme, int posicao) {
		this.filme = filme;
		this.posicao = posicao;
	}
	
	/**
	 * pega os locais onde o filme é exibido e os organiza separados por vírgula em uma String.
	 * @return String com os locais de exibição do filme.
	 */
	public String locaisDeExibicao() {
		String out = "";
		for (String local: this.filme.getLocais()) {
			if (local != null) {
				out += local + ",";
			}
		}
		out = out.substring(0,out.length()-1);
		return out;
	}
	
	/**
	 * sobrescreve o toString para retornar uma concatenação de dados do filme como ,nome, ano, local(is) e um
	 * "fogo" caso esse filme esteja na hotlist.
	 */
	@Override
	public String toString() {
		if (this.posicao > 100 || this.posicao < 1) {
			return "POSIÇÃO INVÁLIDA";
		}
		if (this.filme == null) {
			return "";
		}
		
		if (this.filme.isNaHotList() && this.filme.getAno() == 0) {
			return "\n" + "🔥" + this.filme.getNome() + "\n" + locaisDeExibicao();
		}
		if (this.filme.isNaHotList()) {
			return "\n" + "🔥" + this.filme.getNome() + ", " + this.filme.getAno() + "\n" + locaisDeExibicao();
		}
		if (this.filme.getAno() == 0) {
			return "\n" + this.filme.getNome() + "\n" + locaisDeExibicao();
		}
	
		return "\n" + this.filme.getNome() + ", " + this.filme.getAno() + "\n" + locaisDeExibicao();
		
	}
}
