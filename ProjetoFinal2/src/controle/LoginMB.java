package controle;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import dominio.Usuario;
import dominio.dao.UsuarioDAO;

@Named("bean")
@SessionScoped
public class LoginMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private String login;
	private String senha;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * 
	 */
	public String acaoAutenticar() throws Exception {
		UsuarioDAO usuario = new UsuarioDAO();
		usuario.save(this.getLogin());
		
		return "";

	}

	/**
	 * 
	 */
	public String acaoLogout() {

		return "login";
	}

}
