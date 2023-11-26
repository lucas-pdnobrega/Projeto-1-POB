/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Prato;

import java.util.List;

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
	
	
}
