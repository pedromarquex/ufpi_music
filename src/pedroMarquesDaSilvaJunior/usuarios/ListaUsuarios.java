package pedroMarquesDaSilvaJunior.usuarios;

import java.util.ArrayList;

import pedroMarquesDaSilvaJunior.ValorInvalido;

public class ListaUsuarios {
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

	public void adiciona(Usuario usuario) throws ValorInvalido, UsuarioJaCadastrado {
		if (usuario.getIdUsuario() == null || usuario.getIdUsuario().equals("")) {
			throw new ValorInvalido();
		} else if (usuario.getNome() == null || usuario.getNome().equals("")) {
			throw new ValorInvalido();
		} else if (usuario.getSenha() == null || usuario.getSenha().equals("")) {
			throw new ValorInvalido();
		}
		try {
			this.procura(usuario.getIdUsuario());
			throw new UsuarioJaCadastrado();
		} catch (UsuarioNaoCadastrado e) {
			usuarios.add(usuario);
		}

	}

	public Usuario procura(String idUsuario) throws UsuarioNaoCadastrado {
		if (usuarios.isEmpty()) {
			throw new UsuarioNaoCadastrado();
		} else {
			for (Usuario usuario : usuarios) {
				if (usuario.getIdUsuario() == idUsuario) {
					return usuario;
				}
			}
			throw new UsuarioNaoCadastrado();
		}
	}

	public Artista getArtista(String idUsuario) throws UsuarioNaoCadastrado, ValorInvalido {
		if (idUsuario == null || idUsuario.equals("")) {
			throw new ValorInvalido();
		} else if (usuarios.isEmpty()) {
			throw new UsuarioNaoCadastrado();
		} else {
			for (Usuario usuario : usuarios) {
				if (usuario.getIdUsuario().equals(idUsuario) && usuario instanceof Artista) {
					return (Artista) usuario;
				}
			}
			throw new UsuarioNaoCadastrado();
		}
	}

	public Assinante getAssinante(String idUsuario) throws UsuarioNaoCadastrado, ValorInvalido {
		if (idUsuario == null || idUsuario.equals("")) {
			throw new ValorInvalido();
		} else if (usuarios.isEmpty()) {
			throw new UsuarioNaoCadastrado();
		} else {
			for (Usuario usuario : usuarios) {
				if (usuario.getIdUsuario().equals(idUsuario) && usuario instanceof Assinante) {
					return (Assinante) usuario;
				}
			}
			throw new UsuarioNaoCadastrado();
		}
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
}
