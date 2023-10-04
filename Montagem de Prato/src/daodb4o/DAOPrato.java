/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daodb4o;

import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;
import modelo.Carne;
import modelo.Prato;

public class DAOPrato extends DAO<Prato>{

	public Prato read (Object chave){
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Prato.class);
		q.descend("nome").constrain(nome);
		List<Prato> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	//--------------------------------------------
	// create id
	//--------------------------------------------
		public void create(Prato obj){
			int novoid = super.gerarId();  	//gerar novo id da classe
			obj.setId(novoid);				//atualizar id do objeto antes de grava-lo no banco
			manager.store(obj);
		}
	

	//--------------------------------------------
	//  consultas
	//--------------------------------------------
		public List<Prato> consultarCarneNome(String nome) {
			//quais os pratos contendo carne de nome X
			Query q = manager.query();
			q.constrain(Prato.class);
			q.descend("carne").descend("nome").constrain(nome);
			List<Prato> r = q.execute();
			
			return r;
		}
		
		public List<Prato> consultarAcompanhamentoNome(String nome) {
			//quais os pratos contendo acompanhamento de nome X
			Query q = manager.query();
			q.constrain(Prato.class);
			q.descend("acompanhamentos").descend("nome").constrain(nome);
			List<Prato> r = q.execute();
			
			return r;
		}

		public List<Prato> consultarPratosComMaisDeNAcompanhamentos(int quantidade) {
			//quais pratos possuem mais de dois acompanhamentos
			Query q = manager.query();
			q.constrain(Prato.class);  
			q.constrain(new Filtro(quantidade));
			List<Prato >r = q.execute();
			
			return r;
		}
		
		
	
}

class Filtro implements Evaluation {
	private int quantidade;
			public Filtro(int quantidade) {
				this.quantidade = quantidade;
		// TODO Auto-generated constructor stub
	}

			public void evaluate(Candidate candidate) {
				//destacar o objeto que esta sendo consultado no banco
				Prato prato = (Prato) candidate.getObject();
				
				if(prato.getAcompanhamentos().size()>quantidade) 
					candidate.include(true); 	//incluir objeto no resultado da consulta
				else		
					candidate.include(false);	//excluir objeto do resultado da consulta
			}
		}