package dominio;

public class ArvoreBinaria {
	private NoArvore raiz;

	public ArvoreBinaria() {
		super();
		raiz = null;

	}

	public boolean arvoreVazia() {
		if (raiz == null)
			return true;
		return false;

	}

	public void inserirNaArvore(int valor) {
		inserirNaArvore(raiz, valor);
	}

	private void inserirNaArvore(NoArvore node, int valor) {

		if (arvoreVazia()) {
			raiz = new NoArvore(valor);
			System.out.println("Valor inserido com sucesso");
		} else {

			if (valor < node.getValor()) {
				if (node.getEsquerdo() != null) {

					inserirNaArvore(node.getEsquerdo(), valor);
				} else {
					System.out.println("  Inserindo " + valor + " a esquerda de " + raiz.getValor());
					node.setEsquerdo(new NoArvore(valor));

				}
			} else if (valor > node.getValor()) {
				if (node.getDireito() != null) {
					inserirNaArvore(node.getDireito(), valor);

				} else {
					System.out.println("  Inserindo " + valor + " a direita de " + raiz.getValor());
					node.setDireito(new NoArvore(valor));

				}
			}

		}

	}

}
