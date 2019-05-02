package pedroMarquesDaSilvaJunior;

import java.util.ArrayList;
import java.util.Date;

import pedroMarquesDaSilvaJunior.estilos.EstiloJaCadastrado;
import pedroMarquesDaSilvaJunior.estilos.EstiloNaoCadastrado;
import pedroMarquesDaSilvaJunior.estilos.ListaEstilos;
import pedroMarquesDaSilvaJunior.musicas.ListaMusicas;
import pedroMarquesDaSilvaJunior.musicas.Musica;
import pedroMarquesDaSilvaJunior.musicas.MusicaJaCadastrada;
import pedroMarquesDaSilvaJunior.musicas.MusicaNaoCadastrada;
import pedroMarquesDaSilvaJunior.playlists.ListaPlaylits;
import pedroMarquesDaSilvaJunior.playlists.Playlist;
import pedroMarquesDaSilvaJunior.playlists.PlaylistExistente;
import pedroMarquesDaSilvaJunior.playlists.PlaylistNaoExistente;
import pedroMarquesDaSilvaJunior.usuarios.Artista;
import pedroMarquesDaSilvaJunior.usuarios.ListaUsuarios;
import pedroMarquesDaSilvaJunior.usuarios.Usuario;
import pedroMarquesDaSilvaJunior.usuarios.UsuarioJaCadastrado;
import pedroMarquesDaSilvaJunior.usuarios.UsuarioNaoCadastrado;

public class UFPIMusic implements InterfaceStreaming {
	ListaEstilos estilos = new ListaEstilos();
	ListaUsuarios usuarios = new ListaUsuarios();
	ListaMusicas acervo = new ListaMusicas();
	ListaPlaylits lists = new ListaPlaylits();

	@Override
	public void cadastrarEstilo(String nome) throws ValorInvalido, EstiloJaCadastrado {
		if (nome == null || nome.equals("")) {
			throw new ValorInvalido();
		}
		try {
			estilos.adiciona(nome.toLowerCase());
		} catch (ValorInvalido | EstiloJaCadastrado e) {
			throw e;
		}

	}

	@Override
	public ArrayList<Musica> pesquisarPorEstilo(String nome) {
		ArrayList<Musica> todas = acervo.getMusicas();
		ArrayList<Musica> retorno = new ArrayList<Musica>();
		if (todas.isEmpty()) {
			return retorno;
		} else {
			for(Musica mus: todas) {
				if(mus.getEstilo().equals(nome.toLowerCase())) {
					retorno.add(mus);
				}
			}
			return retorno;
		}
	}

	@Override
	public ArrayList<Musica> pesquisarPorData(Date inicial) {
		ArrayList<Musica> todas = acervo.getMusicas();
		ArrayList<Musica> retorno = new ArrayList<Musica>();
		if (todas.isEmpty()) {
			return retorno;
		} else {
			for(Musica mus: todas) {
				if(mus.getLancamento().after(inicial)) {
					retorno.add(mus);
				}
			}
			return retorno;
		}
	}

	@Override
	public ArrayList<Musica> pesquisarPorArtista(String nome) {
		ArrayList<Musica> todas = acervo.getMusicas();
		ArrayList<Musica> retorno = new ArrayList<Musica>();
		if (todas.isEmpty()) {
			return retorno;
		} else {
			for(Musica mus: todas) {
				if(mus.getArtista().getIdUsuario().equals(nome)) {
					retorno.add(mus);
				}
			}
			return retorno;
		}
	}

	@Override
	public void cadastrarUsuario(Usuario usuario) throws ValorInvalido, UsuarioJaCadastrado {
		try {
			usuarios.adiciona(usuario);
		} catch (ValorInvalido | UsuarioJaCadastrado e) {
			throw e;
		}
	}

	@Override
	public void adicionarMusica(String idUsu, String nomeMusica, String estilo, String link, int duracao,
			Date lancamento) throws ValorInvalido, UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado {
		if (idUsu == null || idUsu.equals("")) {
			throw new ValorInvalido();
		} else if (nomeMusica == null || nomeMusica.equals("")) {
			throw new ValorInvalido();
		} else if (estilo == null || estilo.equals("")) {
			throw new ValorInvalido();
		} else if (link == null || link.equals("")) {
			throw new ValorInvalido();
		} else if (duracao < 1) {
			throw new ValorInvalido();
		} else if (lancamento.equals(new Date(0)) || lancamento.after(new Date())) {
			throw new ValorInvalido();
		}
		try {
			Artista artista = usuarios.getArtista(idUsu);
			Musica mus = new Musica(artista, nomeMusica, estilo.toLowerCase(), link, duracao, lancamento);
			estilos.procura(estilo.toLowerCase());
			acervo.adiciona(mus);
		} catch (ValorInvalido | UsuarioNaoCadastrado | MusicaJaCadastrada | EstiloNaoCadastrado e) {
			throw e;
		}
	}

	@Override
	public void criarPlaylist(String idUsu, String nomeLista)
			throws ValorInvalido, UsuarioNaoCadastrado, PlaylistExistente {
		try {
			usuarios.procura(idUsu);
			lists.adiciona(idUsu, nomeLista);
		} catch (ValorInvalido | UsuarioNaoCadastrado | PlaylistExistente e) {
			throw e;
		}
	}

	@Override
	public void adicionarMusicaPlaylist(String idUsu, String nomeLista, String idAutor, String nomeMusica)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente, MusicaNaoCadastrada, MusicaJaCadastrada {
		try {
			usuarios.procura(idUsu);
			Musica mus = acervo.procura(nomeMusica, idAutor);
			Playlist list = lists.procura(idUsu, nomeLista);
			list.getMusicas().adiciona(mus);
		} catch (UsuarioNaoCadastrado | PlaylistNaoExistente | MusicaNaoCadastrada | MusicaJaCadastrada e) {
			throw e;
		}
	}

	@Override
	public Playlist pesquisaPlaylistUsuario(String idUsu, String nomeLista)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente {
		try {
			usuarios.procura(idUsu);
			Playlist list = lists.procura(idUsu, nomeLista);
			return list;
		} catch (UsuarioNaoCadastrado | PlaylistNaoExistente e) {
			throw e;
		}
	}

	@Override
	public ArrayList<Playlist> pesquisaPlaylistEstilo(String idUsu, String estilo)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente {
		ArrayList<Playlist> retorno = new ArrayList<Playlist>();
		ArrayList<Playlist> todas = lists.getPlaylists();
		if (todas.isEmpty()) {
			return retorno;
		} else {
			try {
				usuarios.procura(idUsu);
				for(Playlist playlist: todas) {
					if (playlist.getEstilos().getEstilos().contains(estilo.toLowerCase())) {
						retorno.add(playlist);
					}
				}
				if (retorno.size() == 0) {
					throw new PlaylistNaoExistente();
				}
				return retorno;
			} catch ( UsuarioNaoCadastrado e ) {
				throw e;
			}
		}
	}

}
