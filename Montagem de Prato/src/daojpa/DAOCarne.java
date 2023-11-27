/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import modelo.Carne;

public class DAOCarne extends DAO<Carne>{

	public Carne read(Object chave) {
        String nome = (String) chave; 
        TypedQuery<Carne> query = manager.createQuery(
            "SELECT c FROM Carne c WHERE c.nome = :nome", Carne.class);
        query.setParameter("nome", nome);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

	//--------------------------------------------
	//  consulta - Fausto pediu para aguardar
	//--------------------------------------------
	
	
	
}

