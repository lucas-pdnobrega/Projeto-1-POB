package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Acompanhamento;
import modelo.Prato;

public class Consultar {
	protected ObjectContainer manager;
	
	public Consultar(){
		manager = Util.conectarBanco();
		consultarPrecoAcompanhamento(3.68);
		consultarNomeAcompanhamento("Purê de Batata");
		consultarCarneNome("Rabo");
		consultarPratosComMaisDeDoisAcompanhamentos();
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}
	
	public void consultarPrecoAcompanhamento(double preco) {
		//quais os acompanhamentos com preço X
		Query q = manager.query();
		q.constrain(Acompanhamento.class);
		q.descend("preco").constrain(preco);
		List<Acompanhamento> r = q.execute();
		
		System.out.println("Acompanhamentos com preço " + preco);
		for (Acompanhamento a : r) {
			System.out.println(a);
		}
		System.out.println("FIM");
	}
	
	public void consultarNomeAcompanhamento(String nome) {
		//quais os pratos contendo acompanhamento de nome X
		Query q = manager.query();
		q.constrain(Prato.class);
		q.descend("acompanhamentos").descend("nome").constrain(nome);
		List<Prato> r = q.execute();
		System.out.println("Pratos com acompanhamento de nome " + nome);
		for (Prato p : r) {
			System.out.println(p);
		}
		System.out.println("FIM");
	}
	
	public void consultarCarneNome(String nome) {
		//quais os pratos contendo carne de nome X
		Query q = manager.query();
		q.constrain(Prato.class);
		q.descend("carne").descend("nome").constrain(nome);
		List<Prato> r = q.execute();
		System.out.println("Pratos com carne de nome " + nome);
		for (Prato p : r) {
			System.out.println(p);
		}
		System.out.println("FIM");
	}
	
	public void consultarPratosComMaisDeDoisAcompanhamentos() {
		System.out.println("\n---Pratos com mais de Dois Acompanhamentos");
		Query p = manager.query();
		p.constrain(Prato.class);  
		p.constrain(new Filtro());
		List<Prato >r = p.execute();
		
		for(Prato livro: r) {
			System.out.println(livro);
		}
		System.out.println("FIM");
	}
	
	//=================================================
		public static void main(String[] args) {
			new Consultar();
		}

}

//classe interna 
class Filtro implements Evaluation {

	public void evaluate(Candidate candidate) {
		//destacar o objeto que esta sendo consultado no banco
		Prato prato = (Prato) candidate.getObject();
		
		if(prato.getAcompanhamentos().size()>2) 
			candidate.include(true); 	//incluir objeto no resultado da consulta
		else		
			candidate.include(false);	//excluir objeto do resultado da consulta
	}
}

