package br.com.controller;

import javax.faces.bean.ManagedBean;

import br.com.dao.FidelidadeDAO;
import br.com.model.Pessoa;

@ManagedBean(name="pessoaBean")
public class PessoaBean {

	FidelidadeDAO fidelidadedao = new FidelidadeDAO();
	Pessoa pessoa = new Pessoa();
	
	public void verificar(){
		fidelidadedao.verificarpontos(pessoa);
	}
	
	public FidelidadeDAO getFidelidadedao() {
		return fidelidadedao;
	}

	public void setFidelidadedao(FidelidadeDAO fidelidadedao) {
		this.fidelidadedao = fidelidadedao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	
}
