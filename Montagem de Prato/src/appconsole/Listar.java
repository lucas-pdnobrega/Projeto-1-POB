/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import modelo.Acompanhamento;
import modelo.Carne;
import modelo.Prato;
import modelo.Usuario;
import regras_negocio.Fachada;

public class Listar {

	public Listar() {
		try {
			Fachada.inicializar();
			System.out.println("\n---listagem de carnes:");
			for(Carne c: Fachada.listarCarnes())
				System.out.println(c);

			System.out.println("\n---listagem de acompanhamentos:");
			for(Acompanhamento a: Fachada.listarAcompanhamentos())
				System.out.println(a);
			
			System.out.println("\n---listagem de prato:");
			for(Prato p: Fachada.listarPratos())
				System.out.println(p);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Listar();
	}
}
