package dominio;

/*
 * Escreva uma aplica��o Java que contenha uma �rvore bin�ria de busca cujos n�s guardar�o,
 *  al�m das refer�ncias para o filho esquerdo e o filho direito, apenas um valor inteiro.
 *   Forne�a um m�todo inserir() que permitir� inserir os valores na �rvore. Em seguida forne�a
 *    um m�todo recursivo que permitir� fazer a travessia in-order da �rvore.
 * 
 * 
 * 
 */
public class ArvoreBinariaBusca {
	private NoArvore raiz;// refer�ncia para o n� raiz da arvor�

	public ArvoreBinariaBusca() {
		super();
		raiz = null;// inicializando o n� raiz como null
	}

	// m�todo usado para inserir um novo n� na �rvore
	// retorna true se o n� for inserido com sucesso e false se o elemento
	// n�o puder ser inserido (no caso de j� existir um elemento igual)
	public boolean inserir(int valor) {

		// A �rvore ainda est� vazia?
		if (raiz == null) {
			// vamos criar o primeiro n� e defini-lo como a raiz da �rvore
			raiz = new NoArvore(valor);// cria um novo n�
		} else {
			// localiza o n� Pai
			NoArvore pai = null;
			NoArvore noAtual = raiz;// come�a a busca pela raiz
			// enquanto o n� atual for diferente de null
			while (noAtual != null) {
				if (valor < noAtual.getValor()) {

					pai = noAtual;
					noAtual = noAtual.getEsquerdo();
				} else if (valor > noAtual.getValor()) {

					pai = noAtual;
					noAtual = noAtual.getDireito();
				} else {
					return false;// Um n� com este valor j� existe

				}
			}

			// cria o novo n� e o adiciona ao n� pai
			if (valor < pai.getValor()) {
				pai.setEsquerdo(new NoArvore(valor));

			} else {

				pai.setDireito(new NoArvore(valor));
			}

		}
		return true; // retorna true para indicar que o novo n� foi inserido
	}

	// M�todo que permite disparar a travessia em-ordem
	public void emOrdem() {

		emOrdem(raiz);

	}
	// sobrecarga do m�todo emOrdem com um par�metro (esta � a vers�o recursiva
	// do m�todo)

	private void emOrdem(NoArvore raiz) {

		if (raiz == null) {// condi��o de parada
			return;
		}

		// visita a sub-�rvore da esquerdo
		emOrdem(raiz.getEsquerdo());
		// visita o n� atual
		System.out.println(raiz.getValor());
		// visita a sub-�rvore da direita
		emOrdem(raiz.getDireito());

	}

	// M�todo que permite disparar a travessia pre-ordem
	public void preOrdem() {

		preOrdem(raiz);

	}
	// sobrecarga do m�todo preOrdem com um par�metro (esta � a vers�o recursiva
	// do m�todo)

	private void preOrdem(NoArvore raiz) {

		if (raiz == null) {// condi��o de parada
			return;
		}
		// visita o n� atual
		System.out.println(raiz.getValor());
		// visita a sub-�rvore da esquerdo
		emOrdem(raiz.getEsquerdo());

		// visita a sub-�rvore da direita
		emOrdem(raiz.getDireito());

	}

	// M�todo que permite disparar a travessia pos-ordem
	public void posOrdem() {

		posOrdem(raiz);

	}
	// sobrecarga do m�todo posOrdem com um par�metro (esta � a vers�o recursiva
	// do m�todo)

	private void posOrdem(NoArvore raiz) {

		if (raiz == null) {// condi��o de parada
			return;
		}

		// visita a sub-�rvore da esquerdo
		emOrdem(raiz.getEsquerdo());
		// visita a sub-�rvore da direita
		emOrdem(raiz.getDireito());
		// visita o n� atual
		System.out.println(raiz.getValor());

	}

}
