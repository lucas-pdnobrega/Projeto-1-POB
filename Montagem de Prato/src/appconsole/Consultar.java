/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import modelo.Acompanhamento;
import modelo.Prato;
import regras_negocio.Fachada;

public class Consultar {

	public Consultar() {
		try {
			Fachada.inicializar();

			System.out.println("consultas...");
			System.out.println("acompanhentos que custam 3.68R$");
			for(Acompanhamento a : Fachada.acompanhamentosPreco(3.68))
				System.out.println(a);

			
			System.out.println("\npratos com acompanhamento Arroz");
			for(Prato p : Fachada.acompanhamentoPrato("Arroz")) {
				System.out.println(p);
			}
			
			System.out.println("\npratos com carne Rabo");
			for(Prato p : Fachada.carnePrato("Rabo")) {
				System.out.println(p);
			}
			
			System.out.println("\npratos com mais de 0 acompanhamentos");
			for(Prato p : Fachada.pratosNAcompanhamentos(0)) {
				System.out.println(p);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}

