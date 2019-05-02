package pedroMarquesDaSilvaJunior;

import static org.junit.Assert.*;
import pedroMarquesDaSilvaJunior.estilos.*;
import pedroMarquesDaSilvaJunior.musicas.*;
import pedroMarquesDaSilvaJunior.playlists.*;
import pedroMarquesDaSilvaJunior.usuarios.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class TesteProva1 {

	@Test
	public void testeCadastroUsuario() {
		InterfaceStreaming ufpi = new UFPIMusic();
		Usuario assinante = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", "testando");
		try { // Cadastrando assinante
			ufpi.cadastrarUsuario(assinante);
		} catch (ValorInvalido | UsuarioJaCadastrado e) {
			fail("Nao era esperada nenhuma excecao.");
		}

		Usuario artista = new Artista("MarquinhosSantana", "Marquinhos Santana", "contato@ms.com", "mark14");
		try { // Cadastrando artista
			ufpi.cadastrarUsuario(artista);
		} catch (ValorInvalido | UsuarioJaCadastrado e) {
			fail("Nao era esperada nenhuma excecao.");
		}

		Artista artista2 = new Artista("JoseSoares", "Jose Soares", "contato@js.com", "jj90");
		ArrayList<Artista> artistas = new ArrayList<Artista>();
		artistas.add(artista2);
		Usuario banda = new Banda("ForroBoys", "Forro Boys", "contato@fb.com", "forrozinho", artistas);

		try { // Cadastrando banda
			ufpi.cadastrarUsuario(banda);
		} catch (ValorInvalido | UsuarioJaCadastrado e) {
			fail("Nao era esperada nenhuma excecao.");
		}
	}

	@Test
	public void testeCadastroUsuarioJaCadastrado() throws ValorInvalido, UsuarioJaCadastrado {
		InterfaceStreaming ufpi = new UFPIMusic();
		Usuario assinante = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", "testando");
		ufpi.cadastrarUsuario(assinante);
		try {
			ufpi.cadastrarUsuario(assinante);
			fail("Excecao UsuarioJaCadastrado esperada.");
		} catch (UsuarioJaCadastrado e) {
			// Ok, era pra dar erro mesmo.
		}
	}

	@Test
	public void testeCadastroUsuarioStringIdentificadorInvalido() throws UsuarioJaCadastrado {
		InterfaceStreaming ufpi = new UFPIMusic();
		Usuario assinante = new Assinante(null, "Francisco", "francisco12@gmail.com", "testando");
		try { // Testando para identificador nulo
			ufpi.cadastrarUsuario(assinante);
			fail("Excecao ValorInvalido esperada.");
		} catch (ValorInvalido e) {
			// Ok, era pra dar erro mesmo.
		}

		Usuario assinante2 = new Assinante("", "Francisco", "francisco12@gmail.com", "testando");
		try { // Testando para identificador vazio
			ufpi.cadastrarUsuario(assinante2);
			fail("Excecao ValorInvalido esperada.");
		} catch (ValorInvalido e) {
			// Ok, era pra dar erro mesmo.
		}
	}

	@Test
	public void testeCadastroUsuarioStringNomeInvalido() throws UsuarioJaCadastrado {
		InterfaceStreaming ufpi = new UFPIMusic();
		Usuario assinante = new Assinante("Chiquinho12", null, "francisco12@gmail.com", "testando");
		try { // Testando para nome nulo
			ufpi.cadastrarUsuario(assinante);
			fail("Excecao ValorInvalido esperada.");
		} catch (ValorInvalido e) {
			// Ok, era pra dar erro mesmo.
		}

		Usuario assinante2 = new Assinante("Chiquinho12", "", "francisco12@gmail.com", "testando");
		try {// Testando para nome vazio
			ufpi.cadastrarUsuario(assinante2);
			fail("Excecao ValorInvalido esperada.");
		} catch (ValorInvalido e) {
			// Ok, era pra dar erro mesmo.
		}
	}

	@Test
	public void testeCadastroUsuarioStringEmailInvalido() throws UsuarioJaCadastrado {
		InterfaceStreaming ufpi = new UFPIMusic();
		Usuario assinante = new Assinante("Chiquinho12", "Francisco", null, "testando");
		try { // Testando para email nulo
			ufpi.cadastrarUsuario(assinante);
			fail("Excecao ValorInvalido esperada.");
		} catch (ValorInvalido e) {
			// Ok, era pra dar erro mesmo.
		}

		Usuario assinante2 = new Assinante("Chiquinho12", "Francisco", "", "testando");
		try {// Testando para email vazio
			ufpi.cadastrarUsuario(assinante2);
			fail("Excecao ValorInvalido esperada.");
		} catch (ValorInvalido e) {
			// Ok, era pra dar erro mesmo.
		}
	}

	@Test
	public void testeCadastroUsuarioStringSenhaInvalida() throws UsuarioJaCadastrado {
		InterfaceStreaming ufpi = new UFPIMusic();
		Usuario assinante = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", null);
		try { // Testando para senha nula
			ufpi.cadastrarUsuario(assinante);
			fail("Excecao ValorInvalido esperada.");
		} catch (ValorInvalido e) {
			// Ok, era pra dar erro mesmo.
		}

		Usuario assinante2 = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", "");
		try { // Testando para senha vazia
			ufpi.cadastrarUsuario(assinante2);
			fail("Excecao ValorInvalido esperada.");
		} catch (ValorInvalido e) {
			// Ok, era pra dar erro mesmo.
		}
	}
	
	@Test
	// Deixaram cadastrar bandas sem artistas
	public void testeCadastroBandaComArtistaInvalido() {
		InterfaceStreaming ufpi = new UFPIMusic();
		ArrayList<Artista> artistas = new ArrayList<Artista>();
		Usuario banda = new Banda("ForroBoys", "Forro Boys", "contato@fb.com", "forrozinho", null);
		Usuario banda2 = new Banda("OsTrakinas", "Os trakinas", "contato@otks.com", "trakinas55", artistas);

		try { // Cadastrando banda com o arraylist nulo
			ufpi.cadastrarUsuario(banda);
			fail("Era esperada a excecao ValorInvalido.");
		} catch (UsuarioJaCadastrado e) {
			fail("Era esperada a excecao ValorInvalido.");
		} catch(ValorInvalido e) {
			//Ok, era pra dar erro mesmo.
		}
		
		try { // Cadastrando banda com o arraylist sem artistas
			ufpi.cadastrarUsuario(banda2);
			fail("Era esperada a excecao ValorInvalido.");
		} catch (UsuarioJaCadastrado e) {
			fail("Era esperada a excecao ValorInvalido.");
		} catch(ValorInvalido e) {
			//Ok, era pra dar erro mesmo.
		}	
	}

	@Test
	public void testeCadastroEstilo() {
		InterfaceStreaming ufpi = new UFPIMusic();
		String estilo = "Rock";
		try {
			ufpi.cadastrarEstilo(estilo);
		} catch (ValorInvalido | EstiloJaCadastrado e) {
			fail("Nenhuma excecao era esperada.");
		}
	}

	@Test
	// Retirar teste de senhas com ignore case
	public void testeCadastroEstiloJaCadastrado() throws ValorInvalido, EstiloJaCadastrado {
		InterfaceStreaming ufpi = new UFPIMusic();
		String estilo = "Rock";
		String estilo2 = "rOck";
		ufpi.cadastrarEstilo(estilo);
		try {
			ufpi.cadastrarEstilo(estilo2);
			fail("Exececao EstiloJaCadastrado era esperada.");
		} catch (ValorInvalido e) {
			fail("Exececao EstiloJaCadastrado era esperada.");
		} catch (EstiloJaCadastrado e) {
			// Ok, era pra dar erro mesmo.
		}
	}

	@Test
	public void testeCadastroEstiloInvalido() {
		InterfaceStreaming ufpi = new UFPIMusic();
		String estilo = null;
		String estilo2 = "";

		try { // Testando estilo nulo
			ufpi.cadastrarEstilo(estilo);
			fail("Exececao ValorInvalido era esperada.");
		} catch (ValorInvalido e) {
			// Ok, era pra dar erro mesmo.
		} catch (EstiloJaCadastrado e) {
			fail("Exececao ValorInvalido era esperada.");
		}

		try { // Testando estilo vazio.
			ufpi.cadastrarEstilo(estilo2);
			fail("Exececao ValorInvalido era esperada.");
		} catch (ValorInvalido e) {
			// Ok, era pra dar erro mesmo.
		} catch (EstiloJaCadastrado e) {
			fail("Exececao ValorInvalido era esperada.");
		}
	}

	@Test
	public void testeCadastroPlayList() throws ValorInvalido, UsuarioJaCadastrado {
		InterfaceStreaming ufpi = new UFPIMusic();
		String nomePlayList = "Os desesperados";
		Usuario assinante = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", "testando");
		ufpi.cadastrarUsuario(assinante);
		try {
			ufpi.criarPlaylist("Chiquinho12", nomePlayList);
		} catch (UsuarioNaoCadastrado | PlaylistExistente e) {
			fail("Nao era esperada nenhuma excecao");
		}

	}

	@Test
	public void testeCadastroPlayListNomeInvalido() throws ValorInvalido, UsuarioJaCadastrado {
		InterfaceStreaming ufpi = new UFPIMusic();
		String nomePlayList = null;
		String nomePlayList2 = "";
		Usuario assinante = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", "testando");
		ufpi.cadastrarUsuario(assinante);
		try { // Testando para string nula
			ufpi.criarPlaylist("Chiquinho12", nomePlayList);
			fail("Era esperada a excecao ValorInvalido");
		} catch (UsuarioNaoCadastrado | PlaylistExistente e) {
			fail("Era esperada a excecao ValorInvalido");
		} catch (ValorInvalido e) {
			// Ok,era pra dar erro mesmo.
		}

		try { // Testando para string vazia
			ufpi.criarPlaylist("Chiquinho12", nomePlayList2);
			fail("Era esperada a excecao ValorInvalido");
		} catch (UsuarioNaoCadastrado | PlaylistExistente e) {
			fail("Era esperada a excecao ValorInvalido");
		} catch (ValorInvalido e) {
			// Ok,era pra dar erro mesmo.
		}

	}

	@Test
	public void testeCadastroPlayListJaExistente()
			throws ValorInvalido, UsuarioJaCadastrado, UsuarioNaoCadastrado, PlaylistExistente {
		InterfaceStreaming ufpi = new UFPIMusic();
		String nomePlayList = "Os desesperados";
		Usuario assinante = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", "testando");
		ufpi.cadastrarUsuario(assinante);
		ufpi.criarPlaylist("Chiquinho12", nomePlayList);
		try {
			ufpi.criarPlaylist("Chiquinho12", nomePlayList);
			fail("Excecao PlayListExistente esperada");
		} catch (UsuarioNaoCadastrado | ValorInvalido e) {
			fail("Excecao PlayListExistente esperada");
		} catch (PlaylistExistente e) {
			// Ok,era pra dar erro mesmo.
		}

	}

	@Test
	public void testeCadastroPlayListemUsuarioNaoCadastrado() {
		InterfaceStreaming ufpi = new UFPIMusic();
		String nomePlayList = "Os desesperados";
		try {
			ufpi.criarPlaylist("Chiquinho12", nomePlayList);
			fail("Excecao UsuarioNaoCadastrado era esperada");
		} catch (ValorInvalido | PlaylistExistente e) {
			fail("Excecao UsuarioNaoCadastrado era esperada");
		} catch (UsuarioNaoCadastrado e) {
			// Ok, era pra dar erro mesmo.
		}
	}

	@Test
	public void testeAdicionarMusicaEstiloNaoCadastrado() throws ValorInvalido, UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloJaCadastrado, UsuarioJaCadastrado {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		
		ufpiMusic.cadastrarUsuario(artista);
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);		
		
		try {
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			fail("Erro: era esperada a excecao EstiloNaoCadastrado.");
		}
		catch (EstiloNaoCadastrado e) {
			//A excecao EstiloNaoCadastrado realmente era esperada, nenhum estilo foi cadastrado ainda.
		}
		
		ufpiMusic.cadastrarEstilo("MPB");
		
		try {
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
		}
		catch(EstiloNaoCadastrado e) {
			fail("Nenhuma excecao era esperada.");
		}
	}
	
	@Test
	public void testeAdicionarMusicaAssinante() throws ValorInvalido, MusicaJaCadastrada, EstiloJaCadastrado, EstiloNaoCadastrado, UsuarioJaCadastrado {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario assinante = new Assinante("antonio", "Antonio", "antionio@mail.com", "senha1234");
		Usuario artista = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Artista artista2 = new Artista("JoseSoares", "Jose Soares", "contato@js.com", "jj90");
		ArrayList<Artista> artistas = new ArrayList<Artista>();
		artistas.add(artista2);
		Usuario banda = new Banda("ForroBoys", "Forro Boys", "contato@fb.com", "forrozinho", artistas);
		
		ufpiMusic.cadastrarUsuario(banda);
		ufpiMusic.cadastrarUsuario(assinante);
		ufpiMusic.cadastrarUsuario(artista);
		ufpiMusic.cadastrarEstilo("MPB");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);		
		
		try {
			ufpiMusic.adicionarMusica("antonio", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			fail("Erro: era esperada a excecao UsuarioNaoCadastrado.");
		}
		catch (UsuarioNaoCadastrado e) {
			//A excecao UsuarioNaoCadastrado realmente era esperada, um assinante nao pode adicionar musicas ao UFPI Music.
		}
		
		try {
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
		}
		catch(UsuarioNaoCadastrado e) {
			fail("Nenhuma excecao era esperada.");
		}
		
		try {
			ufpiMusic.adicionarMusica("ForroBoys", "Revanche", "MPB", "https://www.ufpimusic/ForroBoys/revanche.mp3", 426, lancamento);
		}
		catch(UsuarioNaoCadastrado e) {
			fail("Nenhuma excecao era esperada.");
		}
	}
	
	@Test
	public void testeAdicionarMusicaArtistaInexistente() throws ValorInvalido, MusicaJaCadastrada, EstiloJaCadastrado, EstiloNaoCadastrado, UsuarioJaCadastrado {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		
		ufpiMusic.cadastrarUsuario(artista);
		ufpiMusic.cadastrarEstilo("MPB");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);		
		
		try {
			ufpiMusic.adicionarMusica("alceu", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			fail("Erro: era esperada a excecao UsuarioNaoCadastrado.");
		}
		catch (UsuarioNaoCadastrado e) {
			//A excecao UsuarioNaoCadastrado realmente era esperada, esse artista nao esta cadastrado no UFPI Music.
		}
		
		try {
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
		}
		catch(UsuarioNaoCadastrado e) {
			fail("Nenhuma excecao era esperada.");
		}
	}
	
	@Test
	//Pesquisar usuario pode gerar excecoes de usuario nao cadastrado ao inves de valor invalido
	public void testeAdicionarMusicaValoresInvalidos() throws UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloJaCadastrado, UsuarioJaCadastrado, EstiloNaoCadastrado {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		
		try {
			ufpiMusic.cadastrarUsuario(artista);
			ufpiMusic.cadastrarEstilo("MPB");
		} 
		catch (ValorInvalido e) {
		}
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);		
		
		try {
			ufpiMusic.adicionarMusica("", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			fail("Erro: era esperada a excecao ValorInvalido.");
		}
		catch (ValorInvalido e) {
			//A excecao ValorInvalido realmente era esperada, o nome do artista esta vazio.
		}
		
		try {
			ufpiMusic.adicionarMusica("zeca", "", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			fail("Erro: era esperada a excecao ValorInvalido.");
		}
		catch(ValorInvalido e) {
			//A excecao ValorInvalido realmente era esperada, o nome da masica esta vazio.
		}
		
		try {
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", null, 426, lancamento);
			fail("Erro: era esperada a excecao ValorInvalido.");
		}
		catch(ValorInvalido e) {
			//A excecao ValorInvalido realmente era esperada, o link da masica nao pode ser nulo.
		}
		
		try {
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 0, lancamento);
			fail("Erro: era esperada a excecao ValorInvalido.");
		}
		catch (ValorInvalido e) {
			//A excecao ValorInvalido realmente era esperada, uma musica nao pode ter duracao menor que 1.
		}
		
		try {
			Date lanc = new Date(0);
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lanc);
			fail("Erro: era esperada a excecao ValorInvalido.");
		}
		catch (ValorInvalido e) {
			//A excecao ValorInvalido realmente era esperada, data de lancamento invalida.
		}
		
		try {
			gregorianCalendar.set(2019, 06, 20);
			long milisegundos2 = gregorianCalendar.getTimeInMillis();
			Date lanc = new Date(milisegundos2);
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lanc);
			fail("Erro: era esperada a excecao ValorInvalido.");
		}
		catch (ValorInvalido e) {
			//A excecao ValorInvalido realmente era esperada, data de lancamento invalida.
		}
	}
	
	@Test
	public void testeAdicionarMusicaJaCadastrada() throws ValorInvalido, UsuarioNaoCadastrado, EstiloJaCadastrado, UsuarioJaCadastrado, EstiloNaoCadastrado {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Usuario artista2 = new Artista("alceu", "Alceu Valenca", "alceu@mail.com", "seualceu");
		
		ufpiMusic.cadastrarUsuario(artista1);
		ufpiMusic.cadastrarUsuario(artista2);
		ufpiMusic.cadastrarEstilo("MPB");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);		
		
		try {
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			fail("Erro: era esperada a excecao MusicaJaCadastrada.");
		}
		catch (MusicaJaCadastrada e) {
			//A excecao MusicaJaCadastrada realmente era esperada, a musica ja havia sido cadastrada.
		}
		
		try {
			ufpiMusic.adicionarMusica("alceu", "Telegrama", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 320, lancamento);
			ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);
		}
		catch (MusicaJaCadastrada e) {
			fail("Nenhuma excecao era esperada.");
		}
	}

	@Test
	public void testePesquisarPorEstilo() throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado, UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Usuario artista2 = new Artista("alceu", "Alceu Valenca", "alceu@mail.com", "seualceu");
		
		ufpiMusic.cadastrarUsuario(artista1);
		ufpiMusic.cadastrarUsuario(artista2);
		ufpiMusic.cadastrarEstilo("MPB");
		ufpiMusic.cadastrarEstilo("ROCK");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);	
		
		ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
		ufpiMusic.adicionarMusica("alceu", "Coracao Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412, lancamento);
		ufpiMusic.adicionarMusica("zeca", "Babylon", "ROCK", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);
		
		ArrayList<Musica> musicas = new ArrayList<Musica>();
		
		musicas = ufpiMusic.pesquisarPorEstilo("");
		if(musicas.size() > 0) {
			fail("Nao esperada nenhuma musica ao buscar um estilo com nome vazio.");
		}
		
		musicas = ufpiMusic.pesquisarPorEstilo("MPB");
		if(musicas.size() != 2) {
			fail("Deveria retornar exatamente 2 musicas.");
		}
	}
	
	@Test
	public void testePesquisarPorData() throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado, UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Usuario artista2 = new Artista("alceu", "Alceu Valenca", "alceu@mail.com", "seualceu");
		
		ufpiMusic.cadastrarUsuario(artista1);
		ufpiMusic.cadastrarUsuario(artista2);
		ufpiMusic.cadastrarEstilo("MPB");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento1 = new Date(milisegundos);	
		
		gregorianCalendar.set(2018, 06, 20);
		milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento2 = new Date(milisegundos);	
		
		gregorianCalendar.set(2017, 06, 20);
		milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento3 = new Date(milisegundos);	
		
		ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento1);
		ufpiMusic.adicionarMusica("alceu", "Coracao Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412, lancamento1);
		ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento2);
		ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3", 430, lancamento3);
		
		gregorianCalendar.set(2016, 06, 20);
		milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);	
		
		ArrayList<Musica> musicas = new ArrayList<Musica>();
		
		musicas = ufpiMusic.pesquisarPorData(lancamento);
		if(musicas.size() != 4) {
			fail("Deveria retornar exatamente 4 musicas, ja que todas foram lancadas depois dessa data.");
		}
		
		musicas = ufpiMusic.pesquisarPorData(lancamento3);
		if(musicas.size() != 3) {
			fail("Deveria retornar exatamente 3 musicas.");
		}
		
		musicas = ufpiMusic.pesquisarPorData(lancamento2);
		if(musicas.size() != 2) {
			fail("Deveria retornar exatamente 2 musicas.");
		}
		
		musicas = ufpiMusic.pesquisarPorData(lancamento1);
		if(musicas.size() != 0) {
			fail("Deveria retornar exatamente 0 musicas, ja que nenhuma musica foi lancada apos essa data.");
		}
	}
	
	@Test
	public void testePesquisarPorArtista() throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado, UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Usuario artista2 = new Artista("alceu", "Alceu Valenca", "alceu@mail.com", "seualceu");
		
		ufpiMusic.cadastrarUsuario(artista1);
		ufpiMusic.cadastrarUsuario(artista2);
		ufpiMusic.cadastrarEstilo("MPB");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);	
		
		ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
		ufpiMusic.adicionarMusica("alceu", "Coracao Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412, lancamento);
		ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);
		ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3", 430, lancamento);
		ufpiMusic.adicionarMusica("alceu", "Outra de Jour", "MPB", "https://www.ufpimusic/alceu/outraBelleDeJour.mp3", 430, lancamento);
		
		ArrayList<Musica> musicas = new ArrayList<Musica>();
		
		musicas = ufpiMusic.pesquisarPorArtista("");
		if(musicas.size() > 0) {
			fail("Nao deveria retornar nenhuma musica para artista com nome vazio.");
		}
		
		musicas = ufpiMusic.pesquisarPorArtista("zeca");
		if(musicas.size() != 2) {
			fail("Deveria retornar exatamente 2 musicas para zeca.");
		}
		
		musicas = ufpiMusic.pesquisarPorArtista("alceu");
		if(musicas.size() != 3) {
			fail("Deveria retornar exatamente 3 musicas para alceu.");
		}
	}
	
	@Test
	public void testeAdicionarMusicaPlaylistUsuarioNaoCadastrado() throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado, PlaylistExistente, PlaylistNaoExistente, MusicaNaoCadastrada {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Usuario artista2 = new Artista("alceu", "Alceu Valenca", "alceu@mail.com", "seualceu");
		Usuario assinante = new Assinante("carlos", "Carlos Campelo", "carlos@mail.com", "Carlitos");
		
		ufpiMusic.cadastrarUsuario(artista1);
		ufpiMusic.cadastrarUsuario(artista2);
		ufpiMusic.cadastrarUsuario(assinante);
		ufpiMusic.cadastrarEstilo("MPB");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);	
		
		try {
			ufpiMusic.criarPlaylist("carlos", "Classicos");
			
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			ufpiMusic.adicionarMusica("alceu", "Coracao Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412, lancamento);
			ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);
			ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3", 430, lancamento);
			
			ufpiMusic.adicionarMusicaPlaylist("joao", "Classicos", "zeca", "Telegrama");
			fail("Deveria ter gerado a excecao UsuarioNaoCadastrado.");
		} catch (UsuarioNaoCadastrado e) {
			//A excecao UsuarioNaoCadastrado era esperada.
		}
		
		try {
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Telegrama");
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "La Belle de Jour");
		} catch (UsuarioNaoCadastrado e) {
			fail("Nenhum excecao era esperada.");
		}
	}

	@Test
	public void testeAdicionarMusicaPlaylistNaoExistente() throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado, UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado, PlaylistExistente, MusicaNaoCadastrada {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Usuario artista2 = new Artista("alceu", "Alceu Valenca", "alceu@mail.com", "seualceu");
		Usuario assinante = new Assinante("carlos", "Carlos Campelo", "carlos@mail.com", "Carlitos");
		
		ufpiMusic.cadastrarUsuario(artista1);
		ufpiMusic.cadastrarUsuario(artista2);
		ufpiMusic.cadastrarUsuario(assinante);
		ufpiMusic.cadastrarEstilo("MPB");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);	
		
		try {
			ufpiMusic.criarPlaylist("carlos", "Classicos");
			
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			ufpiMusic.adicionarMusica("alceu", "Coracao Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412, lancamento);
			ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);
			ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3", 430, lancamento);
			
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Coletanea MPB", "zeca", "Telegrama");
			fail("Deveria ter gerado a excecao PlaylistNaoExistente.");
		} catch (PlaylistNaoExistente e) {
			//A excecao PlaylistNaoExistente era esperada.
		}
		
		try {
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Telegrama");
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "La Belle de Jour");
		} catch (PlaylistNaoExistente e) {
			fail("Nenhum excecao era esperada.");
		}
	}

	@Test
	public void testeAdicionarMusicaNaoCadastradaPlaylist() throws ValorInvalido, UsuarioJaCadastrado, UsuarioNaoCadastrado, PlaylistExistente, PlaylistNaoExistente, MusicaJaCadastrada, EstiloNaoCadastrado, EstiloJaCadastrado {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Usuario artista2 = new Artista("alceu", "Alceu Valenca", "alceu@mail.com", "seualceu");
		Usuario assinante = new Assinante("carlos", "Carlos Campelo", "carlos@mail.com", "Carlitos");
		
		ufpiMusic.cadastrarUsuario(artista1);
		ufpiMusic.cadastrarUsuario(artista2);
		ufpiMusic.cadastrarUsuario(assinante);
		ufpiMusic.cadastrarEstilo("MPB");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);	
		
		try {
			ufpiMusic.criarPlaylist("carlos", "Classicos");
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Telegrama");
			fail("Deveria ter gerado a excecao MusicaNaoCadastrada.");
		} catch (MusicaNaoCadastrada e) {
			//A excecao MusicaNaoCadastrada era esperada.
		}
		
		try {
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3", 430, lancamento);
			
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Telegrama");
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "La Belle de Jour");
			
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Coracao Bobo");
			fail("Deveria ter gerado a excecao MusicaNaoCadastrada, ja que o id do cantor esta errado.");
		} catch (MusicaNaoCadastrada e) {
			//A excecao MusicaNaoCadastrada era esperada.
		}
	}

	@Test
	public void testeAdicionarMusicaJaCadastradaPlaylist() throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado, UsuarioNaoCadastrado, EstiloNaoCadastrado, PlaylistExistente, PlaylistNaoExistente, MusicaNaoCadastrada {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Usuario artista2 = new Artista("alceu", "Alceu Valenca", "alceu@mail.com", "seualceu");
		Usuario assinante = new Assinante("carlos", "Carlos Campelo", "carlos@mail.com", "Carlitos");
		
		ufpiMusic.cadastrarUsuario(artista1);
		ufpiMusic.cadastrarUsuario(artista2);
		ufpiMusic.cadastrarUsuario(assinante);
		ufpiMusic.cadastrarEstilo("MPB");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);	
		
		try {
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			ufpiMusic.adicionarMusica("alceu", "Coracao Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412, lancamento);
			ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);
			ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3", 430, lancamento);
		
			ufpiMusic.criarPlaylist("carlos", "Classicos");
			
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Telegrama");
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "La Belle de Jour");
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Telegrama");
			fail("Deveria ter gerado a excecao MusicaJaCadastrada.");
		} catch (MusicaJaCadastrada e) {
			//A excecao MusicaJaCadastrada era esperada.
		}
		
		try {
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Babylon");
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "Coracao Bobo");
		} catch (MusicaJaCadastrada e) {
			fail("Nenhuma excecao era esperada.");
		}
	}
	
	@Test
	public void testeAdicionarMusicaPlaylist() throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado, UsuarioNaoCadastrado, EstiloNaoCadastrado, PlaylistExistente, PlaylistNaoExistente, MusicaNaoCadastrada, MusicaJaCadastrada {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Usuario artista2 = new Artista("alceu", "Alceu Valenca", "alceu@mail.com", "seualceu");
		Usuario assinante = new Assinante("carlos", "Carlos Campelo", "carlos@mail.com", "Carlitos");
		
		ufpiMusic.cadastrarUsuario(artista1);
		ufpiMusic.cadastrarUsuario(artista2);
		ufpiMusic.cadastrarUsuario(assinante);
		ufpiMusic.cadastrarEstilo("MPB");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);	
		
		ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
		ufpiMusic.adicionarMusica("alceu", "Coracao Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412, lancamento);
		ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);
		ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3", 430, lancamento);
		
		ufpiMusic.criarPlaylist("carlos", "Classicos");
		
		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Telegrama");
		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "La Belle de Jour");
			
		Playlist playlist = ufpiMusic.pesquisaPlaylistUsuario("carlos", "Classicos");
			
		assertEquals(2, playlist.getMusicas().getMusicas().size());
		/*if(playlist.getMusicas().getMusicas().size() != 2) {
			fail("A quantidade de musicas na playlist deveria ser exatamente 2.");
		}*/
			
		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Babylon");
		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "Coracao Bobo");
		
		playlist = ufpiMusic.pesquisaPlaylistUsuario("carlos", "Classicos");
		
		assertEquals(4, playlist.getMusicas().getMusicas().size());
	}
	
	@Test
	public void testePesquisaPlaylistUsuarioNaoCadastrado() throws ValorInvalido, UsuarioJaCadastrado, PlaylistExistente, PlaylistNaoExistente {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario assinante = new Assinante("carlos", "Carlos Campelo", "carlos@mail.com", "Carlitos");
		
		ufpiMusic.cadastrarUsuario(assinante);
		
		try {
			ufpiMusic.criarPlaylist("carlos", "Classicos");
			
			ufpiMusic.pesquisaPlaylistUsuario("joao", "Classicos");
			fail("Deveria ter gerado a excecao UsuarioNaoCadastrado.");
		} catch (UsuarioNaoCadastrado e) {
			//A excecao UsuarioNaoCadastrado era esperada.
		}
		
		try {
			ufpiMusic.pesquisaPlaylistUsuario("carlos", "Classicos");
		} catch (UsuarioNaoCadastrado e) {
			fail("Nenhuma excecao era esperada.");
		}
	}

	@Test
	public void testePesquisaPlaylistNaoExistente() throws ValorInvalido, UsuarioJaCadastrado, UsuarioNaoCadastrado, PlaylistExistente {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario assinante = new Assinante("carlos", "Carlos Campelo", "carlos@mail.com", "Carlitos");
		
		ufpiMusic.cadastrarUsuario(assinante);
		
		try {
			ufpiMusic.criarPlaylist("carlos", "Classicos");
			
			ufpiMusic.pesquisaPlaylistUsuario("carlos", "Coletanea MPB");
			fail("Deveria ter gerado a excecao PlaylistNaoExistente.");
		} catch (PlaylistNaoExistente e) {
			//A excecao PlaylistNaoExistente era esperada.
		}
		
		try {
			ufpiMusic.pesquisaPlaylistUsuario("carlos", "Classicos");
			
			ufpiMusic.criarPlaylist("carlos", "Coletanea MPB");
			ufpiMusic.pesquisaPlaylistUsuario("carlos", "Coletanea MPB");
		} catch (PlaylistNaoExistente e) {
			fail("Nenhuma excecao era esperada.");
		}
	}

	@Test
	public void testePesquisaPlaylistEstiloUsuarioNaoCadastrado() throws PlaylistNaoExistente, MusicaNaoCadastrada, MusicaJaCadastrada, ValorInvalido, UsuarioJaCadastrado, EstiloNaoCadastrado, EstiloJaCadastrado, PlaylistExistente {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Usuario artista2 = new Artista("alceu", "Alceu Valenca", "alceu@mail.com", "seualceu");
		Usuario artista3 = new Artista("mabel", "Mabel", "mabel@mail.com", "cookies");
		Usuario assinante = new Assinante("carlos", "Carlos Campelo", "carlos@mail.com", "Carlitos");
		
		ufpiMusic.cadastrarUsuario(artista1);
		ufpiMusic.cadastrarUsuario(artista2);
		ufpiMusic.cadastrarUsuario(artista3);
		ufpiMusic.cadastrarUsuario(assinante);
		ufpiMusic.cadastrarEstilo("MPB");
		ufpiMusic.cadastrarEstilo("Pop");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);	
		
		try {
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			ufpiMusic.adicionarMusica("alceu", "Coracao Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412, lancamento);
			ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);
			ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3", 430, lancamento);
			ufpiMusic.adicionarMusica("mabel", "Don't Call Me Up", "Pop", "https://www.ufpimusic/mabel/dontCallMeUp.mp3", 240, lancamento);
			
			ufpiMusic.criarPlaylist("carlos", "Classicos");
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Telegrama");
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "La Belle de Jour");
			
			ufpiMusic.pesquisaPlaylistEstilo("joao", "MPB");
			fail("Deveria ter gerado a excecao UsuarioNaoCadastrado.");
		} catch (UsuarioNaoCadastrado e) {
			//A excecao UsuarioNaoCadastrado era esperada.
		}
		
		try {
			ArrayList<Playlist> playlist = ufpiMusic.pesquisaPlaylistEstilo("carlos", "MPB");
			if(playlist.size() != 1) {
				fail("Deveria haver 1 playlist.");
			}
			
			ufpiMusic.criarPlaylist("carlos", "Variados");
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Variados", "mabel", "Don't Call Me Up");
			ufpiMusic.adicionarMusicaPlaylist("carlos", "Variados", "alceu", "Coracao Bobo");
			
			playlist = ufpiMusic.pesquisaPlaylistEstilo("carlos", "Pop");
			if(playlist.size() != 1) {
				fail("Deveria haver 1 playlist.");
			}
			
			playlist = ufpiMusic.pesquisaPlaylistEstilo("carlos", "MPB");
			if(playlist.size() != 2) {
				fail("Deveria haver 2 playlist.");
			}			
		} catch (UsuarioNaoCadastrado e) {
			fail("Nenhuma excecao era esperada.");
		}
	}	
	
	@Test
	public void testePesquisaPlaylistNaoExistenteEstilo() throws ValorInvalido, UsuarioJaCadastrado, UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado, PlaylistExistente, EstiloJaCadastrado {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Usuario artista2 = new Artista("alceu", "Alceu Valenca", "alceu@mail.com", "seualceu");
		Usuario assinante = new Assinante("carlos", "Carlos Campelo", "carlos@mail.com", "Carlitos");
		
		ufpiMusic.cadastrarUsuario(artista1);
		ufpiMusic.cadastrarUsuario(artista2);
		ufpiMusic.cadastrarUsuario(assinante);
		ufpiMusic.cadastrarEstilo("MPB");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);	
		
		try {
			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
			ufpiMusic.adicionarMusica("alceu", "Coracao Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412, lancamento);
			
			ufpiMusic.criarPlaylist("carlos", "Classicos");
			ufpiMusic.pesquisaPlaylistEstilo("carlos", "MPB");
			
			ufpiMusic.pesquisaPlaylistEstilo("carlos", "Pop");
			fail("Deveria ter gerado a excecao PlaylistNaoExistente.");
		} catch (PlaylistNaoExistente e) {
			//A excecao PlaylistNaoExistente era esperada.
		}
	}
	
	@Test
	public void testeTempoTotalPlaylist() throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado, UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado, PlaylistExistente, PlaylistNaoExistente, MusicaNaoCadastrada {
		InterfaceStreaming ufpiMusic = new UFPIMusic();
		
		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");
		Usuario artista2 = new Artista("alceu", "Alceu Valenca", "alceu@mail.com", "seualceu");
		Usuario assinante = new Assinante("carlos", "Carlos Campelo", "carlos@mail.com", "Carlitos");
		
		ufpiMusic.cadastrarUsuario(artista1);
		ufpiMusic.cadastrarUsuario(artista2);
		ufpiMusic.cadastrarUsuario(assinante);
		ufpiMusic.cadastrarEstilo("MPB");
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(2019, 02, 20);
		long milisegundos = gregorianCalendar.getTimeInMillis();
		Date lancamento = new Date(milisegundos);	
		
		ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426, lancamento);
		ufpiMusic.adicionarMusica("alceu", "Coracao Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412, lancamento);
		ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);
		ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3", 430, lancamento);
		
		ufpiMusic.criarPlaylist("carlos", "Classicos");
		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Telegrama");
		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "La Belle de Jour");
		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "Coracao Bobo");
		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Babylon");		
		
		Playlist playlist = ufpiMusic.pesquisaPlaylistUsuario("carlos", "Classicos");
		assertEquals(1708, playlist.getDuracaoTotal());
	}
}
