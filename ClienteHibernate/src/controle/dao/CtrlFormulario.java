package controle.dao;

import face.JanelaFormulario;

public class CtrlFormulario {

	private CriaTabelas ctrlTabela;
	private JanelaFormulario jFormulario;

	public CtrlFormulario(CriaTabelas c) {
		this.ctrlTabela = c;
		this.iniciar();

	}

	public void iniciar() {
		this.jFormulario = new JanelaFormulario(this);
	}

}