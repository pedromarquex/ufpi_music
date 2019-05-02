package pedroMarquesDaSilvaJunior.playlists;

import java.util.ArrayList;

import pedroMarquesDaSilvaJunior.ValorInvalido;
import pedroMarquesDaSilvaJunior.musicas.Musica;
import pedroMarquesDaSilvaJunior.musicas.MusicaJaCadastrada;
import pedroMarquesDaSilvaJunior.musicas.MusicaNaoCadastrada;

public class ListaPlaylits {
	private ArrayList<Playlist> lists = new ArrayList<Playlist>();

	public void adiciona(String idUsu, String nome) throws PlaylistExistente, ValorInvalido {
		if(nome == null || nome.equals("")) {
			throw new ValorInvalido();
		}
		try {
			procura(idUsu, nome);
			throw new PlaylistExistente();
		} catch (PlaylistNaoExistente e) {
			Playlist list = new Playlist(idUsu, nome);
			lists.add(list);
		}
	}

	public void adicionaMusicaPlaylist(String idUsu, String nomeLista, Musica musica)
			throws PlaylistNaoExistente, MusicaJaCadastrada {
		try {
			Playlist list = procura(idUsu, nomeLista);
			list.getMusicas().procura(musica.getNome(), musica.getArtista().getNome());
		} catch (MusicaNaoCadastrada e) {
			Playlist list = procura(idUsu, nomeLista);
			list.getMusicas().adiciona(musica);
		} catch (PlaylistNaoExistente e) {
			throw e;
		}
	}

	public Playlist procura(String idUsu, String nome) throws PlaylistNaoExistente {
		if (lists.isEmpty()) {
			throw new PlaylistNaoExistente();
		} else {
			for (Playlist playlist : lists) {
				if (playlist.getIdUsuario().equals(idUsu) && playlist.getNome().equals(nome)) {
					return playlist;
				}
			}
			throw new PlaylistNaoExistente();
		}
	}

	public ArrayList<Playlist> getPlaylists() {
		return this.lists;
	}
}
