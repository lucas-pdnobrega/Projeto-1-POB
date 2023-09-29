package aplicacao;

import com.db4o.ObjectContainer;

import modelo.Prato;
import modelo.Carne;
import modelo.Acompanhamento;

public class Cadastrar {
	protected ObjectContainer manager;

	public Cadastrar(){
		manager = Util.conectarBanco();
		cadastrar();
		Util.desconectar();
		
		System.out.println("fim da aplicacao");
	}

	
	public void cadastrar(){
		
		System.out.println("cadastrando...");
		
		//Cadastrar Carnes
		Carne c1;
		c1 = new Carne("Lombinho", 24.99);
		manager.store(c1);
		manager.commit();
	
		Carne c2;
		c2 = new Carne("Bife", 14.99);
		manager.store(c2);
		manager.commit();
		
		Carne c3;
		c3 = new Carne("Frango", 11.88);
		manager.store(c3);
		manager.commit();
		
		Carne c4;
		c4 = new Carne("Picanha", 24.3);
		manager.store(c4);
		manager.commit();
		
		Carne c5;
		c5 = new Carne("Rabo", 23.7);
		manager.store(c5);
		manager.commit();
		
		Carne c6;
		c6 = new Carne("Linguiça Toscana", 14.99);
		manager.store(c6);
		manager.commit();
			
		
		//Cadastrar Acompanhamentos
		Acompanhamento a1;
		a1 = new Acompanhamento("Batata Frita", 3.68);
		manager.store(a1);
		manager.commit();
		
		Acompanhamento a2;
		a2 = new Acompanhamento("Salada Árabe", 5.68);
		manager.store(a2);
		manager.commit();
		
		Acompanhamento a3;
		a3 = new Acompanhamento("Purê de Batata", 3.68);
		manager.store(a3);
		manager.commit();

		Acompanhamento a4;
		a4 = new Acompanhamento("Arroz", 4.20);
		manager.store(a4);
		manager.commit();

		Acompanhamento a5;
		a5 = new Acompanhamento("Macarrão", 2.95);
		manager.store(a5);
		manager.commit();
				
		Acompanhamento a6;
		a6 = new Acompanhamento("Salada Caesar", 3.30);
		manager.store(a6);
		manager.commit();
		
		Acompanhamento a7;
		a7 = new Acompanhamento("Batata Sauteé", 3.30);
		manager.store(a7);
		manager.commit();
		
		Acompanhamento a8;
		a8 = new Acompanhamento("Ovo Cozido", 2.00);
		manager.store(a8);
		manager.commit();
		
				
		
		//Cadastro de Pratos
		Prato p1;
		p1 = new Prato("Lombinho a Palmeira", c1);
		p1.adicionar(a2);
		p1.adicionar(a3);
		manager.store(p1);
		manager.commit();
		
		
		Prato p2;
		p2 = new Prato("Rabo a la Bezerra", c5);
		p2.adicionar(a6);
		p2.adicionar(a7);
		manager.store(p2);
		manager.commit();
		
		Prato p3;
		p3 = new Prato("Linguiça Estrela", c6);
		p3.adicionar(a3);
		p3.adicionar(a8);
		manager.store(p3);		
		manager.commit();		
		
		Prato p4;
		p4 = new Prato("Linguiça Completa Estrela", c6);
		p4.adicionar(a3);
		p4.adicionar(a8);
		p4.adicionar(a7);
		manager.store(p4);		
		manager.commit();	

		System.out.println("cadastrou.");

	}	


	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}
