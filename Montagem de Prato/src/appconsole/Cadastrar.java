/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import modelo.Acompanhamento;
import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.inicializar();
			System.out.println("cadastrando acompanhamento...");
			Fachada.cadastrarAcompanhamento("Batata Frita", 3.68);
			Fachada.cadastrarAcompanhamento("Salada Árabe", 5.68);
			Fachada.cadastrarAcompanhamento("Purê de Batata", 3.88);
			Fachada.cadastrarAcompanhamento("Arroz", 4.20);
			Fachada.cadastrarAcompanhamento("Macarrão", 2.95);
			Fachada.cadastrarAcompanhamento("Salada Ceaser", 3.30);
			Fachada.cadastrarAcompanhamento("Batata Sauteé", 3.30);
			Fachada.cadastrarAcompanhamento("Ovo Cozido", 2.00);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("cadastrando carne...");
			Fachada.cadastrarCarne("Lombinho", 24.99);
			Fachada.cadastrarCarne("Bife", 14.99);
			Fachada.cadastrarCarne("Frango", 11.88);
			Fachada.cadastrarCarne("Picanha", 24.3);
			Fachada.cadastrarCarne("Rabo", 23.7);
			Fachada.cadastrarCarne("Linguiça Toscana", 14.99);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("cadastrando prato...");
			Fachada.cadastrarPrato("Lombinho a Palmeira", Fachada.localizarCarne("Lombinho"));
			Fachada.cadastrarPrato("Rabo a la Bezerra", Fachada.localizarCarne("Rabo"));
			Fachada.cadastrarPrato("Linguiça Estrela", Fachada.localizarCarne("Linguiça Toscana"));
			Fachada.cadastrarPrato("Linguiça Completa Estrela", Fachada.localizarCarne("Linguiça Toscana"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("adicionando acompanhamentos...");
			Fachada.localizarPrato("Lombinho a Palmeira").adicionar(Fachada.localizarAcompanhamento("Salada Árabe"));
			Fachada.localizarPrato("Rabo a la Bezerra").adicionar(Fachada.localizarAcompanhamento("Salada Árabe"));
			Fachada.localizarPrato("Linguiça Estrela").adicionar(Fachada.localizarAcompanhamento("Arroz"));
			Fachada.localizarPrato("Linguiça Completa Estrela").adicionar(Fachada.localizarAcompanhamento("Arroz"));
			Fachada.localizarPrato("Linguiça Completa Estrela").adicionar(Fachada.localizarAcompanhamento("Ovo Cozido"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}


	public static void main(String[] args) {
		new Cadastrar();
	}
}
