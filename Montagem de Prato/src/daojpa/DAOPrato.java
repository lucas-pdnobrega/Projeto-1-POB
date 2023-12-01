/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Acompanhamento;
import modelo.Prato;

import java.util.List;

import com.db4o.query.Query;



public class DAOPrato extends DAO<Prato>{

	public Prato read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Prato> q = manager.createQuery("select p from Prato p where p.nome = :n ",Prato.class);
			q.setParameter("n", nome);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	
	public List<Prato> consultarCarneNome(String nome) {
		//quais os pratos contendo carne de nome X
		TypedQuery<Prato> q = manager.createQuery("SELECT p FROM Prato p "
      	      + "JOIN FETCH p.carne c "
      	      + "WHERE c.nome = :nome",
      	        Prato.class);
      q.setParameter("nome", nome);
      return q.getResultList();
	}
		
	public List<Prato> consultarAcompanhamentoNome(String nome) {
        // quais os pratos com acompanhamentos de nome x
        TypedQuery<Prato> q = manager.createQuery("SELECT p FROM Prato p "
        	      + "JOIN FETCH p.acompanhamentos a "
        	      + "WHERE a.nome = :nome",
        	        Prato.class);
        q.setParameter("nome", nome);
        return q.getResultList();
        
        /*  select p.nome, a.nome from prato_acompanhamento pa 
			join prato p on p.id = pa.prato_id_fk 
			join acompanhamento a on a.id = pa.acompanhamento_id_fk 
			where a.nome = 'Arroz'; 
         */
    }
	
	public List<Prato> consultarPratosComMaisDeNAcompanhamentos(int quantidade) {
		//quais pratos possuem mais de dois acompanhamentos
		TypedQuery<Prato> q = manager.createQuery("SELECT p FROM Prato p "
				 + "WHERE (SELECT COUNT(a) FROM p.acompanhamentos a) > 2",
		        Prato.class);

		    return q.getResultList();
	}
}
