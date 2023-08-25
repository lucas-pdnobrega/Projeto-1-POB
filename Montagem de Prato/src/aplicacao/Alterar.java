package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Prato;
import modelo.Carne;
import modelo.Acompanhamento;




public class Alterar {
	protected ObjectContainer manager;

	public Alterar(){
		manager = Util.conectarBanco();
		atualizar();
		Util.desconectar();

		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void atualizar(){
		//localizar pessoa com nome joao
		Query q = manager.query();
		q.constrain(Carne.class);  				
		q.descend("nome").constrain("Bife");		 
		List<Carne> resultados = q.execute(); // select p from Carne p where p.nome="Bife"
		
	/**	if(resultados.size()>0) {
			Pessoa p =  resultados.get(0);
			p.setNome("joana");
			
			//adicionar novo telefone
			p.adicionar(new Telefone("99999999"));
			
			//remover telefone existente
			Telefone t = p.localizar("88881100");
			p.remover(t);  
			t.setPessoa(null);	 	//este objeto pode ficar� orfao no banco 

			manager.store(p);
			manager.store(t);	
			manager.delete(t);	//deletar objeto orfao no banco 
			manager.commit();
			System.out.println("alterou nome e telefones de joao");
		}
		else
			System.out.println("joao inexistente"); **/
	}



	//=================================================
	public static void main(String[] args) {
		new Alterar();
	}
}
