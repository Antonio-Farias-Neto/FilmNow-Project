package filmnow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular o sistema FilmNow.
 * 
 * @author eliane e Antonio
 *
 */
public class MainFilmNow {

	public static void main(String[] args) {
		FilmNow fn = new FilmNow();

		System.out.println("Carregando filmes ...");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaFilmes("filmes_inicial.csv", fn);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, fn, scanner);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário/a.
	 * @return O comando escolhido.
	 */
	
	private static String menu(Scanner scanner) {
		System.out.println(
				"\n---\nMENU\n" + 
						"(A)Adicionar filme\n" +
						"(F)Remover Filme\n" +
						"(M)Mostrar todos\n" + 
						"(MN)Mostrar Todos Pelo Nome\n" +
						"(MA)Mostrar Todos Pelo Ano\n" +
						"(D)Detalhar filme\n" +
						"(E)Exibir HotList\n" +
						"(H)Atribuir Hot\n" +
						"(R)Remover Hot\n" +
						"(L)Adicionarlocal\n" +
						"(O)Remover Local\n" +
						"(S)air\n" + 
						"\n" + 
						"Opção> ");
		return scanner.next().toUpperCase();
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param fn  O sistema FilmNow que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, FilmNow fn, Scanner scanner) {
		switch (opcao) {
		case "A":
			adicionaFilme(fn, scanner);
			break;
		case "M":
			mostrarFilmes(fn);
			break;
		case "MN":
			mostrarFilmesPeloNome(fn,scanner);
			break;
		case "MA":
			mostrarFilmesPeloAno(fn,scanner);
			break;
		case "D":
			detalharFilme(fn, scanner);
			break;
		case "E":
			exibirHotList(fn);
			break;
		case "H":
			atribuirHot(fn, scanner);
			break;
		case "R":
			removerHot(fn,scanner);
			break;
		case "F":
			removerFilme(fn,scanner);
			break;
		case "L":
			adicionaLocal(fn,scanner);
			break;
		case "O":
			removerLocal(fn,scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}

	/**
	 * Imprime lista de filmes.
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 */
	private static void mostrarFilmes(FilmNow fn) {

		Filme[] filmes = fn.getFilmes();
		for (int i = 0; i < filmes.length; i++) {
			if (filmes[i] != null) {
				System.out.println(formataFilme(i, filmes[i]));
			}
		}
	}
	
	/**
	 * imprime a lista de filmes que possuem o mesmo nome, o qual será definido pelo usuário.
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Receberá a entrada do nome do filme.
	 */
	private static void mostrarFilmesPeloNome(FilmNow fn, Scanner scanner) {
		System.out.println("Nome>");
		scanner.nextLine();
		String nome = scanner.nextLine();
		System.out.println(fn.mostrarTodosPeloNome(nome));
	}
	
	/**
	 * imprime a lista de filmes que possuem o mesmo ano de lançamento, o qual será definido pelo usuário.
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Receberá a entrada do ano do filme.
	 */
	private static void mostrarFilmesPeloAno(FilmNow fn, Scanner scanner) {
		System.out.println("Ano>");
		int ano = scanner.nextInt();
		scanner.nextLine();
		System.out.println(fn.mostrarTodosPeloAno(ano));
	}
	
	/**
	 * imprime de forma organizada os filmes presentes na hotlist.
	 * @param fn O sistema FilmNow a ser manipulado.
	 */
	private static void exibirHotList(FilmNow fn) {
		System.out.println(fn.exibirHotList());
	}
	
	/**
	 * recebe um filme do sistema FilmNow e o coloca na hotlist na posicão escolhida pelo usuário.
	 * @param fn o Sistema FilmNow a ser manipulado.
	 * @param scanner sistema que receberá a posição como entrada do usuário	
	 */
	private static void atribuirHot(FilmNow fn, Scanner scanner) {
		System.out.println("Filme> ");
		int posicaoFilme = scanner.nextInt();
		System.out.println("posicao> ");
		int posicaoHot = scanner.nextInt();
		Filme filme = fn.getFilme(posicaoFilme);
		System.out.println(fn.atribuirHot(filme, posicaoHot));
	}
	
	/**
	 * remove um filme da hotlist pela sua posição nela.
	 * @param fn O sistema FilmNow a ser Manipulado.
	 * @param scanner O sistema que receberá da entrada a posição do filme na hotlist a ser removido.
	 */
	private static void removerHot(FilmNow fn,Scanner scanner) {
		System.out.println("Posicao> ");
		int posicao = scanner.nextInt(); 
		fn.removerHot(posicao);
	}
	
	/**
	 * Remove um filme da lista de filmes do FilmNow.
	 * @param fn é o sistema FilmNow a ser usado.
	 * @param scanner é o scanner usado para receber do usuário a posição do filme que será removido.
	 */
	private static void removerFilme(FilmNow fn, Scanner scanner) {
		System.out.println("Posicao> ");
		int posicao = scanner.nextInt(); 
		fn.removerFilme(posicao);
	}
	
	
	/**
	 * Imprime os detalhes de um dos filmes. 
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void detalharFilme(FilmNow fn, Scanner scanner) {
		System.out.print("\nPosição> ");
		int posicao = scanner.nextInt();
		Filme filme = fn.getFilme(posicao);
		DetalharFilme df = new DetalharFilme(filme,posicao);
		System.out.println(df.toString());
	}
		

	/**
	 * Formata um filme para impressão. 
	 * 
	 * @param posicao A posição do filme (que é exibida)/
	 * @param filme O filme a ser impresso.
	 * @return A String formatada.
	 */
	private static String formataFilme(int posicao, Filme filme) {
		return posicao+1 + " - " + filme;
	}

	/**
	 * Cadastra um filme no sistema. 
	 * 
	 * @param fn O sistema FilmNow a ser manipulado.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void adicionaFilme(FilmNow fn, Scanner scanner) {
		System.out.print("\nPosição no sistema> ");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		System.out.print("\nNome> ");
		String nome = scanner.nextLine();
		System.out.print("\nAno> ");
		String ano = scanner.nextLine();
		System.out.print("\nLocal> ");
		String local = scanner.nextLine();
		System.out.println(fn.cadastraFilme(posicao, nome, ano, local));
		
	}
	
	/**
	 * adiciona local de exibição a filmes escolhidos pelo usuário.
	 * @param fn o sistema FilmNow a ser usado.
	 * @param scanner o scanner pelo qual o usuário informará o local a ser adicionado e em quais filmes.
	 */
	private static void adicionaLocal(FilmNow fn,Scanner scanner) {
		System.out.println("Local>");
		scanner.nextLine();
		String local = scanner.nextLine();
		System.out.println("Filme(s) a receber esse local separados por vírgula>");
		String []posicoes = scanner.nextLine().split(",");
		
		for (String posicao:posicoes) {
			int pos = Integer.parseInt(posicao);
			if (pos > 100 || pos < 1 || fn.getFilme(pos) == null) {
				System.out.println("POSIÇÃO " + pos + " INVÁLIDA");
			} else {
				Filme filme = fn.getFilme(pos);
				filme.adicionarLocal(local);
			}
		}
	}
	
	/**
	 * Remove um local de exibição de um filme escolhido pelo usuário.
	 * @param fn Sistema FilmNow a ser manipulado.
	 * @param scanner O scanner que receberá a posição do filme a ter o local removido e
	 * o nome do local a ser removido.
	 */
	private static void removerLocal(FilmNow fn,Scanner scanner) {
		System.out.println("Posição do filme a ter o local removido>");
		int pos = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Local>");
		String local = scanner.nextLine();
		if (pos > 100 || pos < 1 || fn.getFilme(pos) == null) {
			System.out.println("POSIÇÃO INVÁLIDA");
		} else {
			fn.getFilme(pos).removerLocal(local);
		}
	}

	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * Lê carga de filmes de um arquivo csv. 
	 * 
	 * @param arquivoFilmes O caminho para o arquivo.
	 * @param fn O sistema FilmNow a ser populado com os dados.
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaFilmes(String arquivoFilmes, FilmNow fn) throws FileNotFoundException, IOException {
		LeitorFilmNow leitor = new LeitorFilmNow();
		
		int carregados =  leitor.carregaContatos(arquivoFilmes, fn);
		System.out.println("Carregamos " + carregados + " registros.");
	}
}
