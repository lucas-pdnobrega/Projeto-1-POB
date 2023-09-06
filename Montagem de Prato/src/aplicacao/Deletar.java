package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Prato;
import modelo.Carne;
import modelo.Acompanhamento;

public class Deletar {
	protected ObjectContainer manager;

	public Deletar() {
		manager = Util.conectarBanco();
		apagar();
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void apagar() {
		Query q = manager.query();
		q.constrain(Carne.class);
		q.descend("nome").constrain("Lombinho");
		List<Carne> resultados = q.execute(); // select p from Carne p where p.nome="Lombinho"

		if (resultados.size() > 0) {
			//apagar Lombinho
			Carne c = resultados.get(0);
			manager.delete(c);
			manager.commit();
			System.out.println("apagou Lombinho");
		} else
			System.out.println("carne inexistente");
	}

	// =================================================
	public static void main(String[] args) {
		new Deletar();
	}
}