/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Acompanhamento;
import modelo.Prato;

public class DAOAcompanhamento extends DAO<Acompanhamento>{

	public Acompanhamento read (Object chave){
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Acompanhamento.class);
		q.descend("nome").constrain(nome);
		List<Acompanhamento> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	
	public List<Acompanhamento> consultarPrecoAcompanhamento(double preco) {
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
		
		return r;
	}
	
}

