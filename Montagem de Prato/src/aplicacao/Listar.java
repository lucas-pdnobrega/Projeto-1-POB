package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Prato;
import modelo.Carne;
import modelo.Acompanhamento;


public class Listar {
	protected ObjectContainer manager;

	public Listar(){
		manager = Util.conectarBanco();
		listarPrato();
		listarCarne();
		listarAcompanhamento();
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void listarPrato(){
		System.out.println("\n---listagem dos Pratos:");
		
		Query q = manager.query();
		q.constrain(Prato.class);  				
		List<Prato> resultados = q.execute();
		for(Prato p: resultados)
			System.out.println(p);
	}
	public void listarCarne(){
		System.out.println("\n---listagem das Carnes:");
		
		Query q = manager.query();
		q.constrain(Carne.class);  				
		List<Carne> resultados = q.execute();
		for(Carne a: resultados)
			System.out.println(a);
	}

	public void listarAcompanhamento(){
		System.out.println("\n---listagem dos Acompanhamentos:");
		
		Query q = manager.query();
		q.constrain(Acompanhamento.class);  				
		List<Acompanhamento> resultados = q.execute();
		for(Acompanhamento p: resultados)
			System.out.println(p);
	}



	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}

