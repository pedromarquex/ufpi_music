package pedroMarquesDaSilvaJunior.testes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import pedroMarquesDaSilvaJunior.InterfaceStreaming;
import pedroMarquesDaSilvaJunior.UFPIMusic;
import pedroMarquesDaSilvaJunior.ValorInvalido;
import pedroMarquesDaSilvaJunior.estilos.EstiloJaCadastrado;
import pedroMarquesDaSilvaJunior.estilos.EstiloNaoCadastrado;
import pedroMarquesDaSilvaJunior.musicas.Musica;
import pedroMarquesDaSilvaJunior.musicas.MusicaJaCadastrada;
import pedroMarquesDaSilvaJunior.musicas.MusicaNaoCadastrada;
import pedroMarquesDaSilvaJunior.playlists.Playlist;
import pedroMarquesDaSilvaJunior.playlists.PlaylistExistente;
import pedroMarquesDaSilvaJunior.playlists.PlaylistNaoExistente;
import pedroMarquesDaSilvaJunior.usuarios.Artista;
import pedroMarquesDaSilvaJunior.usuarios.Assinante;
import pedroMarquesDaSilvaJunior.usuarios.Banda;
import pedroMarquesDaSilvaJunior.usuarios.Usuario;
import pedroMarquesDaSilvaJunior.usuarios.UsuarioJaCadastrado;
import pedroMarquesDaSilvaJunior.usuarios.UsuarioNaoCadastrado;

public class TesteProva1Resumido {

	@Test
	public void testeCadastroUsuario() {
		InterfaceStreaming ufpi = new UFPIMusic();
		Usuario assinante = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", "testando");
		try { // Cadastrando assinante
			ufpi.cadastrarUsuario(assinante);
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
