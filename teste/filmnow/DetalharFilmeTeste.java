package filmnow;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DetalharFilmeTeste {
	
	private FilmNow fn;
	
	@BeforeEach
	void preparaDetalharFilme() {
		fn = new FilmNow();
	}

	@Test
	void testarDetalharFilmeComTodosOsDados() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		Filme filme = fn.getFilme(5);
		DetalharFilme df = new DetalharFilme(filme,5);
		assertEquals(df.toString(), "\n" + filme.getNome() + ", " + filme.getAno() + "\n" + df.locaisDeExibicao());
	}
	
	@Test
	void testarDetalharFilmeComAnoVazio() {
		fn.cadastraFilme(5, "20 dias em Mariupol", "", "Cinema");
		Filme filme = fn.getFilme(5);
		DetalharFilme df = new DetalharFilme(filme,5);
		assertEquals(df.toString(), "\n" + filme.getNome() + "\n" + df.locaisDeExibicao());
	}
	
	@Test
	void testarDetalharFilmeEmUmaPosicaoSemFilme() {
		DetalharFilme df = new DetalharFilme(null,5);
		assertEquals(df.toString(),"");
	}
	
	@Test 
	void testarDetalharFilmePosicaoAbaixoDoLimite() {
		DetalharFilme df = new DetalharFilme(null,0);
		assertEquals(df.toString(), "POSIÇÃO INVÁLIDA");
	}
	
	@Test 
	void testarDetalharFilmePosicaoAcimaDoLimite() {
		DetalharFilme df = new DetalharFilme(null,101);
		assertEquals(df.toString(), "POSIÇÃO INVÁLIDA");
	}
	
	@Test 
	void testarDetalharFilmeDaHotList() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		Filme filme = fn.getFilme(5);
		fn.atribuirHot(filme, 1);
		DetalharFilme df = new DetalharFilme(filme,5);
		assertEquals(df.toString(), "\n" + "🔥" + filme.getNome() + ", " + filme.getAno() + "\n" + df.locaisDeExibicao());
	}
	
	@Test 
	void testarDetalharFilmeDaHotListSemOAno() {
		fn.cadastraFilme(5, "Avatar", "", "Disney+");
		Filme filme = fn.getFilme(5);
		fn.atribuirHot(filme, 1);
		DetalharFilme df = new DetalharFilme(filme,5);
		assertEquals(df.toString(), "\n" + "🔥" + filme.getNome() + "\n" + df.locaisDeExibicao());
	}

}
