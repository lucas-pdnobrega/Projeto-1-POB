package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
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
	
	
	//=================================================
		public static void main(String[] args) {
			new Consultar();
		}

}
