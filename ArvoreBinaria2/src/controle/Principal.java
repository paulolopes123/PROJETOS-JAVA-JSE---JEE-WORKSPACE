package controle;

import java.util.Scanner;

import dominio.ArvoreBinaria;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		ArvoreBinaria arvore = new ArvoreBinaria();
		
		for(int i=0;i<5;i++){
			System.out.println("Digite um valor inteiro:");
			int valor = entrada.nextInt();
			arvore.inserirNaArvore(valor);
		}

	}

}
