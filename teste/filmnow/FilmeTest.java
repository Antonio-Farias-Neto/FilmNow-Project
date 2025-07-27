package filmnow;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FilmeTest {
	
	@Test
	void testarAdicionarLocal() {
		Filme filme = new Filme("Avatar",2009,"Disney+");
		assertEquals(filme.adicionarLocal("netflix"), true);
	}
	
	@Test
	void testarAdicionarLocalComListaLotada() {
		Filme filme = new Filme("Avatar",2009,"Disney+");
		filme.adicionarLocal("Amazon Prime Video");
		filme.adicionarLocal("GloboPlay");
		filme.adicionarLocal("HBO");
		filme.adicionarLocal("Crunchyroll");
		assertEquals(filme.adicionarLocal("netflix"), false);
	}
	
	@Test
	void testarAdicionarLocalComMesmoNome() {
		Filme filme = new Filme("Avatar",2009,"Disney+");
		assertEquals(filme.adicionarLocal("Disney+"), false);
	}
	
	@Test
	void testarRemoverLocal() {
		Filme filme = new Filme("Avatar",2009,"Disney+");
		filme.adicionarLocal("GloboPlay");
		assertEquals(filme.removerLocal("Disney+"), true);
	}
	
	@Test
	void testarRemoverLocalComNomeErrado() {
		Filme filme = new Filme("Avatar",2009,"Disney+");
		filme.adicionarLocal("GloboPlay");
		assertEquals(filme.removerLocal("Disiney+"), false);
	}
	
	@Test
	void testarRemoverLocalComApenasUmLocalNaLista() {
		Filme filme = new Filme("Avatar",2009,"Disney+");
		assertEquals(filme.removerLocal("Disney+"), false);
	}
	
	@Test
	void testarQuantidadeDeLocais() {
		Filme filme = new Filme("Avatar",2009,"Disney+");
		filme.adicionarLocal("Amazon Prime Video");
		filme.adicionarLocal("Netflix");
		assertEquals(filme.quantidadeDeLocais(), 3);
	}

	
	@Test
	void testarToString() {
		Filme filme = new Filme("Avatar",2009,"Disney+");
		assertEquals(filme.toString(), filme.getNome());
	} 
	
	@Test
	public void testeNomeNull() {
		try {
			Filme filme = new Filme(null,2009,"Disney+");
			if (filme.getNome() == null) {
				throw new NullPointerException("FILME NULO");
			}
		} catch (NullPointerException npe) {
			assertEquals("FILME NULO", npe.getMessage());
		}

	}
	
	@Test
	public void testeNomeVazio(){
		try {
			Filme filme = new Filme("",2009,"Disney+");
			if ("".equals(filme.getNome().trim())) {
				throw new IllegalArgumentException("FILME SEM NOME");
			}
		} catch (IllegalArgumentException iae) {
			assertEquals("FILME SEM NOME", iae.getMessage());
		}
	}
}


