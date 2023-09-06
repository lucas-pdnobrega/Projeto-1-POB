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
		//Alterar Acompanhamentos contidos no Prato Rabo a la Bezerra
		Query q = manager.query();
		q.constrain(Prato.class);
		q.descend("nome").constrain("Rabo a la Bezerra");
		List<Prato> resultados = q.execute();
		
		if(resultados.size()>0) {
			Prato p =  resultados.get(0);

			//adicionar novo Acompanhamento no Prato
			p.adicionar(new Acompanhamento("Palmito", 1.22));

			//remover Acompanhamento do Prato
			Acompanhamento a = p.localizar("Batata Sauteé");
			p.remover(a.getNome());  

			manager.store(p);
			manager.store(a);
			manager.commit();
			System.out.println("adicionou Palmito e tirou Batata Sauteé de Rabo a la Bezerra");
		}
		else
			System.out.println("inexistente");
	}



	//=================================================
	public static void main(String[] args) {
		new Alterar();
	}
}
