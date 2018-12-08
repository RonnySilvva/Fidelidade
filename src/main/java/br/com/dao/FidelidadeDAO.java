package br.com.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.model.Pessoa;

public class FidelidadeDAO {
	
	public void verificarpontos(Pessoa pessoa){
		
		//Sessão
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		//Fim Sessão

		//Query que faz a contagem de dados no banco
		String cpfverificado = pessoa.getCpf();
		Query query = session.createSQLQuery("select count(cpf) from pessoa where cpf = '" + cpfverificado +"'");


		List<?> result = query.getResultList();		//Variavel result pega o resultado da query(no caso a quantidade de dados)
		String resultadoconv = result.toString(); //resultado convertido para String

		System.out.println(resultadoconv);

		//Estrutura de Condição que verifica se tem a quantidade para apagar, se não ele adiciona no banco
		if(resultadoconv.equals("[9]")){
			System.out.println("Apagar");
			Query deletar = session.createSQLQuery("delete from pessoa where cpf ='" +cpfverificado+"'" );
			deletar.executeUpdate();
			msg();
			session.getTransaction().commit();
			
			session.close();
			factory.close();
		}else{
			pessoa.setNome(pessoa.getNome());
			pessoa.setCpf(pessoa.getCpf());
			
			session.save(pessoa);
			session.getTransaction().commit();
			System.out.println("Gravado!");
			msgadicionar();
			
			session.close();
			factory.close();
		}
		//Fim Estrutura

	}
	
	public void msg(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("AVISE AO CLIENTE","PARABÉNS, VOCÊ GANHOU UMA PIZZA GRÁTIS"));
	}
	
	public void msgadicionar(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("ADICIONADO COM SUCESSO"));
	}
	
	}
