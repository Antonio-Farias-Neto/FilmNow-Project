package filmnow;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilmNowTest {
	
	private FilmNow fn;
	
	@BeforeEach
	void preparaFilme() {
		fn = new FilmNow();
	}
	
	@Test
	void testarCadastrarFilmePosicaoVazia() {
		assertEquals(fn.cadastraFilme(5, "Avatar", "2009", "Disney+") , "FILME ADICIONADO");
	}
	
	@Test
	void testarCadastrarFilmePosicaoExistente() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		assertEquals(fn.cadastraFilme(5, "20 dias em Mariupol", "2023", "Cinema") , "FILME ADICIONADO");
	}
	
	@Test
	void testarCadastrarFilmesIguais() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		assertEquals(fn.cadastraFilme(7, "Avatar", "2009", "Disney+") , "FILME JÁ ADICIONADO");
	}
	
	@Test
	void testarCadastrarFilmesLocalDiferente() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		assertEquals(fn.cadastraFilme(7, "Avatar", "2009", "Popcornflix") , "FILME JÁ ADICIONADO");
	}
	
	@Test
	void testarCadastrarFilmePosicaoLimite() {
		assertEquals(fn.cadastraFilme(100, "Avatar", "2009", "Disney+") , "FILME ADICIONADO");
	}
	
	@Test
	void testarCadastrarFilmeAcimaDoLimite() {
		assertEquals(fn.cadastraFilme(101, "Avatar", "2009", "Disney+") , "POSIÇÃO INVÁLIDA");
	}
	
	@Test
	void testarCadastrarFilmeAbaixoDoLimite() {
		assertEquals(fn.cadastraFilme(0, "Avatar", "2009", "Disney+") , "POSIÇÃO INVÁLIDA");
	}
	
	@Test
	void testarCadastrarFilmeComLocalVazio() {
		assertEquals(fn.cadastraFilme(5, "20 dias em Mariupol", "2023", "") , "FILME INVÁLIDO");
	}
	
	@Test 
	void testarCadastrarFilmeComAnoVazio() {
		assertEquals(fn.cadastraFilme(5, "20 dias em Mariupol","", "Cinema") , "FILME ADICIONADO");
	}
	
	@Test
	void testarCadastrarFilmeComNomeVazio() {
		assertEquals(fn.cadastraFilme(5, "", "2023", "Cinema") , "FILME INVÁLIDO");
	}
	
	@Test 
	void testarAdicionarNaHotList() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		assertEquals(fn.atribuirHot(fn.getFilme(5), 1), "FILME ADICIONADO");
	}
	
	@Test
	void testarAdicionarFilmeJaExistenteNaHotList() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		Filme filme1 = fn.getFilme(5);
		Filme filme2 = fn.getFilme(5);
		fn.atribuirHot(filme1, 1);
		assertEquals(fn.atribuirHot(filme2, 2), "FILME JÁ ADICIONADO");
	}
	
	@Test
	void testarAdicionarNaHotListAbaixoDoLimite() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		assertEquals(fn.atribuirHot(fn.getFilme(5), 0), "POSIÇÃO INVÁLIDA");
	}
	
	@Test
	void testarAdicionarNaHotListAcimaDoLimite() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		assertEquals(fn.atribuirHot(fn.getFilme(5), 11), "POSIÇÃO INVÁLIDA");
	}
	
	@Test
	void testarAdicionarPosicaoVaziaNaHotList() {
		assertEquals(fn.atribuirHot(fn.getFilme(80), 1), "FILME INVÁLIDO, POSIÇÃO VAZIA");
	}
	
	@Test
	void testarRemoverDaHotList() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		fn.atribuirHot(fn.getFilme(5), 1);
		assertEquals(fn.removerHot(1), true);
	}
	
	@Test
	void testarRemoverDeUmaPosicaoNullNaHotList() {
		assertEquals(fn.removerHot(1), false);
	}
	
	@Test
	void testarRemoverAbaixoDoLimiteDaHotList() {
		assertEquals(fn.removerHot(0), false);
	}
	
	@Test
	void testarRemoverAcimaDoLimiteDaHotList() {
		assertEquals(fn.removerHot(11), false);
	}
	
	@Test
	void testarExibirHotList() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		fn.cadastraFilme(6, "20 dias em Mariupol","2023", "Cinema");
		Filme filme = fn.getFilme(5);
		Filme filme2 = fn.getFilme(6);
		fn.atribuirHot(filme, 2);
		fn.atribuirHot(filme2, 6);
		assertEquals(fn.exibirHotList(), 2 + " - " + filme.getNome() + ", " + filme.getAno() + "\n"
				+ 6 + " - " + filme2.getNome() + ", " + filme2.getAno() + "\n");
	}
	
	@Test 
	void testarRemoverFilme() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		assertEquals(fn.removerFilme(5), true);
	}
	
	@Test 
	void testarRemoverFilmeEmPosicaoVazia() {
		assertEquals(fn.removerFilme(50), false);
	}
	
	@Test 
	void testarRemoverFilmeAbaixoDoLimite() {
		assertEquals(fn.removerFilme(0), false);
	}
	
	@Test 
	void testarRemoverFilmeAcimaDoLimite() {
		assertEquals(fn.removerFilme(101), false);
	}
	
	@Test
	void testarRemoverFilmeTambemDaHotList() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		fn.atribuirHot(fn.getFilme(5), 1);
		assertEquals(fn.removerFilme(5), true);
		assertEquals(fn.exibirHotList(), "");
	}
	
	@Test
	void testarMostrarTodosPeloNome() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		fn.cadastraFilme(8, "Avatar", "2020", "netflix");
		fn.cadastraFilme(60, "Avatar", "2000", "prime");
		assertEquals(fn.mostrarTodosPeloNome("Avatar"), 5 + " - " + "Avatar" + "\n" +
		8 + " - " + "Avatar" + "\n" + 60 + " - " + "Avatar" + "\n");
	}
	
	@Test
	void testarMostrarTodosPeloNomeInexistente() {
		assertEquals(fn.mostrarTodosPeloNome("Shrek 3"), "");
	}
	
	@Test
	void testarMostrarTodosPeloAno() {
		fn.cadastraFilme(5, "Avatar", "2009", "Disney+");
		fn.cadastraFilme(7, "Shrek 3", "2009", "netflix");
		fn.cadastraFilme(30, "Os Incríveis", "2009", "prime");
		assertEquals(fn.mostrarTodosPeloAno(2009), 5 + " - " + "Avatar" + "\n" +
				7 + " - " + "Shrek 3" + "\n" + 30 + " - " + "Os Incríveis" + "\n");
	}
	
	@Test
	void testarMostrarTodosPeloAnoInexistente() {
		assertEquals(fn.mostrarTodosPeloAno(50), "");
		assertEquals(fn.mostrarTodosPeloAno(-30), "");
	}
}
