package controle;

import java.util.Collection;

import dominio.DAO;
import dominio.IDAO;
import dominio.DadosException;
import dominio.Prova;
import face.JanelaAlterarProva;
import face.JanelaElaborarProva;
import face.JanelaIncluirQuestao;
import face.JanelaIncluirQuestaoAlteracao;

public class CtrlAlterarProva {
	//
	// ATRIBUTOS
	//
	public enum Status {
		EmAlteracao, Pronta;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if (anterior == null && novo == EmAlteracao || anterior == EmAlteracao && novo == Pronta
					|| anterior == Pronta)
				return;
			throw new ControleException(new ErroDeControle(1, "N�o se pode sair do estado "
					+ (anterior == null ? "NULO" : anterior) + " e ir para o estado " + novo));
		}
	}

	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private final CtrlPrograma ctrlPrg;

	/**
	 * Refer�ncia para a janela alterar Prova
	 */
	private JanelaAlterarProva jAlterarProva;

	/**
	 * Refer�ncia para a janela incluir Quest�o
	 */
	private JanelaIncluirQuestaoAlteracao jIncluirQuestao;

	/**
	 * Refer�ncia para o objeto Prova sendo manipulado
	 */
	private Prova atual;

	/**
	 * Refer�ncia para o objeto DaoProva
	 */
	private IDAO<Prova> dao = (IDAO<Prova>) DAO.getDAO(Prova.class);

	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Status status;

	//
	// M�TODOS
	//

	/**
	 * Construtor da classe CtrlElaborarProva
	 */

	public CtrlAlterarProva(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException {
		// Recupero os objetos Prova do DAO
		IDAO<Prova> dao = (IDAO<Prova>) DAO.getDAO(Prova.class);
		Collection<Prova> provas = dao.getListaObjs();
		// Crio e abro a janela de altera��o da prova
		this.jAlterarProva = new JanelaAlterarProva(this, provas);

		// Informo que o controlador de caso de uso est� alterando
		this.setStatus(Status.EmAlteracao);
	}

	public void incluirQuestao() throws ControleException, DadosException {
		// Crio e abro a janela de inclus�o de quest�o
		this.jIncluirQuestao = new JanelaIncluirQuestaoAlteracao(this);
	}

	public void terminar() throws ControleException {
		if (this.status == Status.Pronta)
			return;
		// N�o h� Prova em manipula��o
		this.atual = null;
		// Fecho a janela
		this.jAlterarProva.setVisible(false);
		// Informo que o controlador est� encerrado
		this.setStatus(Status.Pronta);
	}

	public void terminarQuestao() throws ControleException {
		// if (this.status == Status.Pronta)
		// return;
		// N�o h� Prova em manipula��o
		// this.atual = null;
		// Fecho a janela
		this.jIncluirQuestao.setVisible(false);
		// Informo que o controlador est� encerrado
		// this.setStatus(Status.Pronta);
	}

	public Status getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param novo
	 * @throws ControleException
	 */
	public void setStatus(Status novo) throws ControleException {
		Status.validarTransicaoStatus(this.status, novo);
		this.status = novo;
	}
}