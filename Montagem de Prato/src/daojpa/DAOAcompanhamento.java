/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import modelo.Acompanhamento;

public class DAOAcompanhamento extends DAO<Acompanhamento>{

		
	public Acompanhamento read(Object chave) {
        String nome = (String) chave;
        TypedQuery<Acompanhamento> q = manager.createQuery(
            "SELECT a FROM Acompanhamento a WHERE a.nome = :nome", Acompanhamento.class);
        q.setParameter("nome", nome);

        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

	//--------------------------------------------
	//  consulta - Fausto pediu para aguardar
	//--------------------------------------------
	
	public List<Acompanhamento> consultarPrecoAcompanhamento(double preco) {
        
        TypedQuery<Acompanhamento> q = manager.createQuery(
            "SELECT a FROM Acompanhamento a WHERE a.preco = :preco", Acompanhamento.class);
        q.setParameter("preco", preco);

        return q.getResultList();
    }
	
}

