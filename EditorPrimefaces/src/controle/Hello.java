package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class Hello {

	private String texto;

	public String getMessage() {
		return "Editor Do Primefaces!!";
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String exibirTexto() {

		return "Pagina";
	}

}