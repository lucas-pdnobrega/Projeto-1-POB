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

			System.out.println("consultas... \n");
			System.out.println("\nacompanhentos que custam 3.68R$");
			for(Acompanhamento a : Fachada.acompanhamentosPreco(3.68))
				System.out.println(a);


			System.out.println("\npratos com acompanhamento Purê de Batata");
			for(Prato p : Fachada.acompanhamentoPrato("Purê de Batata")) {
				System.out.println(p);
			}
		
			System.out.println("\npratos com carne Rabo");
			for(Prato p : Fachada.carnePrato("Rabo")) {
				System.out.println(p);
			}
			System.out.println("\npratos com mais de 2 acompanhamentos");
			for(Prato p : Fachada.pratosNAcompanhamentos(2)) {
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

