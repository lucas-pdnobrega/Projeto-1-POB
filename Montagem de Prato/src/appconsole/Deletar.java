/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package appconsole;

import regras_negocio.Fachada;

public class Deletar {
	
	public Deletar() {
		try {
			Fachada.inicializar();
			Fachada.excluirAcompanhamento("Ovo Cozido");
			System.out.println("Excluiu Ovo Cozido");
			Fachada.excluirCarne("Linguiça Toscana");
			System.out.println("Excluiu Linguiça Toscana");
			Fachada.excluirPrato("Lombinho a Palmeira");
			System.out.println("Excluiu Lombinho a Palmeira");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Deletar();
	}
}
