package com.unigranrio.projetofinal.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.unigranrio.projetofinal.controller.ITabelavel;


/**
 * Implementa a classe Quest�o que tem o "implements Serializable" para realizar
 * o processo de serializa��o e o "implements Tabelavel" para informar que os
 * objetos poder�o ser exibidos em uma tabela de interface
 * 
 *
 */

@Entity
public class Questao implements IDados, ITabelavel, Serializable {
	private static final long serialVersionIUD = 1L;
	private enum Status {
		EmElaboracao, Disponivel, EmAplicacao;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws DadosException {
			if (anterior == null && novo == Disponivel || anterior == EmElaboracao && novo == Disponivel
					|| anterior == EmElaboracao && novo == EmAplicacao || anterior == Disponivel && novo == EmElaboracao
					|| anterior == Disponivel && novo == EmElaboracao)
				return;
		}

	};

	// Atributos
	@Id
	@GeneratedValue
	private Long id;
	private Status status;
	private String textoHtml;
	@OneToMany(mappedBy = "questao")
	private Set<Peso> peso = new HashSet<Peso>();
	@OneToMany(mappedBy = "questao")
	private Set<Responde> responde = new HashSet<Responde>();
	@OneToMany(mappedBy = "questao")
	private Set<Opcao> opcao = new HashSet<Opcao>();
	//@OneToMany(mappedBy = "questao")
	private Set<Foto> foto = new HashSet<Foto>();
	@ManyToOne
	private Professor professor;

	// M�todos
	public Questao(Long id, Status status, String textoHtml, Professor professor) throws DadosException {
		super();
		this.id = id;
		this.setStatus(status.EmElaboracao);// estado inicial
		this.setTextoHtml(textoHtml);
		this.setProfessor(professor);

	}

	public Questao() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status novo) throws DadosException {
		status.validarTransicaoStatus(this.status, novo);
		this.status = novo;
	}

	public String getTextoHtml() {
		return textoHtml;
	}

	public void setTextoHtml(String textoHtml) throws DadosException {
		validarTexto(textoHtml);
		this.textoHtml = textoHtml;
	}

	public Set<Peso> getPeso() {
		return peso;
	}

	// metodo adiciona Peso do atributo de relacionamento n-�rio
	public void adicionaPeso(Peso peso) throws DadosException {
		if (this.peso.contains(peso))
			return;
		this.peso.add(peso);
		peso.setQuestao(this);

	}

	// metodo remove Peso do atributo de relacionamento n-�rio

	public void removePeso(Peso peso) throws DadosException {
		if (!this.peso.contains(peso))
			return;
		this.peso.remove(peso);
		peso.setQuestao(null);

	}

	public Set<Responde> getResponde() {
		return responde;
	}

	// metodo adiciona resposta do atributo de relacionamento n-�rio
	public void adicionaResposta(Responde responde) throws DadosException {
		if (this.responde.contains(responde))
			return;
		this.responde.add(responde);
		responde.setQuestao(this);

	}

	// metodo remove resposta do atributo de relacionamento n-�rio
	public void removeResposta(Responde responde) throws DadosException {
		if (!this.responde.contains(responde))
			return;
		this.responde.remove(responde);
		responde.setQuestao(null);

	}

	public Set<Opcao> getOpcao() {
		return opcao;
	}

	// metodo adiciona Op��o do atributo de relacionamento n-�rio
	public void adicionaOpcao(Opcao opcao) throws DadosException {
		if (this.opcao.contains(opcao))
			return;
		this.opcao.add(opcao);
		opcao.setQuestao(this);

	}

	// metodo remove Op��o do atributo de relacionamento n-�rio
	public void removeOpcao(Opcao opcao) throws DadosException {
		if (!this.opcao.contains(opcao))
			return;
		this.opcao.remove(opcao);
		opcao.setQuestao(null);

	}

	// metodo adiciona Foto do atributo de relacionamento n-�rio
	public void adicionaFoto(Foto foto) throws DadosException {
		if (this.foto.contains(foto))
			return;
		this.foto.add(foto);
		foto.setQuestao(this);

	}

	// metodo remove Foto do atributo de relacionamento n-�rio
	public void removeFoto(Foto foto) throws DadosException {
		if (!this.foto.contains(foto))
			return;
		this.foto.remove(foto);
		foto.setQuestao(null);

	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) throws DadosException {
		if (this.professor == professor)
			return;
		if (this.professor == null) {
			this.setStatus(Status.EmElaboracao);
			Professor antigo = this.professor;
			this.professor = null;
			antigo.removeQuestao(this);

		} else {
			this.setStatus(Status.Disponivel);
			if (this.professor != null) {

				this.professor.removeQuestao(this);
				this.professor = professor;
				professor.adicionaQuestao(this);

			}

		}

	}

	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
	 * objeto Quest�o
	 */
	public String toString() {
		return "Quest�o [textoHtml=" + textoHtml + "]";
	}

	// valida��o dos atributos
	@RegraDeDominio
	public void validarTexto(String t) throws DadosException {
		if (t == null || t.length() == 0)
			throw new DadosException(new ErroDeDominio(1, "O Texto n�o pode ser nulo"));
	}

	@RegraDeDominio
	public void validarPeso(Peso peso) throws DadosException {
		if (peso == null)
			throw new DadosException(new ErroDeDominio(2, "O Peso n�o pode ser nulo"));
	}

	@RegraDeDominio
	public void validarResponde(Responde responde) throws DadosException {
		// N�o h� regras de valida��o
	}

	@RegraDeDominio
	public void validarOpcao(Opcao opcao) throws DadosException {
		// N�o h� regras de valida��o
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		return new Object[] { this.textoHtml };
	}

	public Object getChave() {
		return id;
	}

}
