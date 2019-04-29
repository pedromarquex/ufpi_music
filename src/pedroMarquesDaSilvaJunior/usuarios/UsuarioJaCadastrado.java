package pedroMarquesDaSilvaJunior.usuarios;

@SuppressWarnings("serial")
public class UsuarioJaCadastrado extends Exception {

	public UsuarioJaCadastrado() {
		super("Usuário já cadastrado");
	}
}