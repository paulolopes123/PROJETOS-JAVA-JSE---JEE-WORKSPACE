package controle;

import controle.CtrlElaborarProva.Status;
import dominio.DadosException;
import dominio.Prova;
import dominio.Questao;
import face.JanelaElaborarProva;
import face.JanelaElaborarQuestao;

public class CtrlElaborarQuestao {

	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private final CtrlPrograma ctrlPrg;

	/**
	 * Refer�ncia para a janela elaborar Quest�o
	 */
	private JanelaElaborarQuestao jElaborarQuestao;

	/**
	 * Refer�ncia para o objeto quest�o sendo manipulado
	 */
	private Questao atual;

	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Status status;

	private enum Status {
		EmElaboracao, Disponivel, EmAplicacao;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if (anterior == null && novo == Disponivel || anterior == EmElaboracao && novo == Disponivel
					|| anterior == EmElaboracao && novo == EmAplicacao || anterior == Disponivel && novo == EmElaboracao
					|| anterior == Disponivel && novo == EmElaboracao)
				return;

		}

	};

	//
	// M�TODOS
	//

	/**
	 * Construtor da classe CtrlElaborarQuest�o
	 */

	public CtrlElaborarQuestao(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de elabora��o da quest�o
		this.jElaborarQuestao = new JanelaElaborarQuestao(this);
		// N�o h� Prova em manipula��o
		this.atual = null;
		// Informo que o controlador de caso de uso est� dispon�vel
		this.setStatus(Status.EmElaboracao);

	}

	public void terminar() throws ControleException {

		// N�o h� Prova em manipula��o
		this.atual = null;
		// Fecho a janela
		this.jElaborarQuestao.setVisible(false);
		// Informo que o controlador est� encerrado
		this.setStatus(Status.Disponivel);
		// Notifico ao controlador do programa o t�rmino do caso de uso
	}

	public Status getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param novo
	 * @throws ControleException
	 * @throws DadosException
	 */
	public void setStatus(Status novo) throws ControleException {
		Status.validarTransicaoStatus(this.status, novo);
		this.status = novo;
	}

}