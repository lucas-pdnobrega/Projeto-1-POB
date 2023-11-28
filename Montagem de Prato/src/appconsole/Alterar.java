/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package appconsole;

import regras_negocio.Fachada;

public class Alterar {
	
	public Alterar() {
		try {
			Fachada.inicializar();
			Fachada.removerAcompanhamentoPrato("Salada Árabe", "Rabo a la Bezerra");
			System.out.println("tirou Batata Sauteé de Rabo a la Bezerra");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Alterar();
	}
}
