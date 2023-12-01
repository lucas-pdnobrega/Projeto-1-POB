package regras_negocio;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;

import daojpa.DAO;
import daojpa.DAOAcompanhamento;
import daojpa.DAOCarne;
import daojpa.DAOPrato;
import daojpa.DAOUsuario;
import modelo.Acompanhamento;
import modelo.Carne;
import modelo.Prato;
import modelo.Usuario;

public class Fachada {
	private Fachada() {}

	private static DAOAcompanhamento daoAcompanhamento = new DAOAcompanhamento();  
	private static DAOCarne daoCarne = new DAOCarne(); 
	private static DAOPrato daoPrato = new DAOPrato(); 
	private static DAOUsuario daousuario = new DAOUsuario(); 
	public static Usuario logado;	//contem o objeto Usuario logado em TelaLogin.java

	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}
	
	public static Acompanhamento cadastrarAcompanhamento(String nome, double preco) throws Exception {
		DAO.begin();
		Acompanhamento acompanhamento = daoAcompanhamento.read(nome);
		if (acompanhamento!=null)
			throw new Exception("Acompanhamento já cadastrado! " + nome);
		acompanhamento = new Acompanhamento(nome, preco);
		
		daoAcompanhamento.create(acompanhamento);
		DAO.commit();
		
		return acompanhamento;
	}
	
	public static Carne cadastrarCarne(String nome, double preco) throws Exception {
		DAO.begin();
		
		Carne carne = daoCarne.read(nome);
		if (carne!=null)
			throw new Exception("Carne já cadastrada! " + nome);
		carne = new Carne(nome, preco);
		
		daoCarne.create(carne);
		DAO.commit();
		
		return carne;
	}
	
	public static Prato cadastrarPrato(String nomePrato, String nomeCarne) throws Exception {
		DAO.begin();
		Prato prato = daoPrato.read(nomePrato);
		if (prato!=null)
			throw new Exception("Prato já cadastrado! " + nomePrato);
		Carne carne = daoCarne.read(nomeCarne);
		if (carne==null)
			throw new Exception("Carne não existe! " + nomeCarne);
		
		prato = new Prato(nomePrato, carne);
		
		System.out.println(prato);

		daoPrato.create(prato);
		System.out.println("criou");
		DAO.commit();
		
		return prato;
	}
	
	public static void adicionarAcompanhamentoPrato(String nomeAcompanhamento, String nomePrato) throws Exception {
		DAO.begin();
		
		Acompanhamento acompanhamento = daoAcompanhamento.read(nomeAcompanhamento);
		if (acompanhamento == null)
			throw new Exception ("Acompanhamento não existe! " + nomeAcompanhamento);
		
		Prato prato = daoPrato.read(nomePrato);
		if (prato == null)
			throw new Exception ("Prato não existe! " + nomePrato);
		
		prato.adicionar(acompanhamento);
		daoPrato.update(prato);
		DAO.commit();
		}
	
	public static void removerAcompanhamentoPrato(String nomeAcompanhamento, String nomePrato) throws Exception {
		DAO.begin();
		
		Acompanhamento acompanhamento = daoAcompanhamento.read(nomeAcompanhamento);
		if (acompanhamento == null)
			throw new Exception ("Acompanhamento não existe! " + nomeAcompanhamento);
		
		Prato prato = daoPrato.read(nomePrato);
		if (prato == null)
			throw new Exception ("Prato não existe! " + nomePrato);
		
		if (prato.localizar(nomeAcompanhamento) == null) {
			throw new Exception ("Prato não possui acompanhamento " + nomeAcompanhamento);
		}
		
		prato.remover(acompanhamento);
		daoPrato.update(prato);
		DAO.commit();
		}
	
	public static void excluirAcompanhamento(String nome) throws Exception {
		DAO.begin();
		
		Acompanhamento acompanhamento = daoAcompanhamento.read(nome);
		if (acompanhamento == null)
			throw new Exception ("Acompanhamento não existe! " + nome);
		
		System.out.println(acompanhamento);
		
		List<Prato> pratos = Fachada.listarPratos();
		
		for (Prato p : pratos) {
			if (p.localizar(nome)!=null) {
				p.remover(acompanhamento);
				daoPrato.update(p);
			}
		}
		
		daoAcompanhamento.delete(acompanhamento);
		DAO.commit();
	}
	
	public static void excluirCarne(String nome) throws Exception {
		DAO.begin();
		
		Carne carne = daoCarne.read(nome);
		if (carne == null)
			throw new Exception ("Carne não existe! " + nome);
		
		System.out.println(carne);
		
		List<Prato> pratos = Fachada.listarPratos();
		
		for (Prato p : pratos) {
			if (p.getCarne().getNome().equals(nome)) {
				Fachada.excluirPrato(p.getNome());
			}
		}
		
		daoCarne.delete(carne);
		DAO.commit();
	}
	
	public static void excluirPrato(String nome) throws Exception {
		DAO.begin();
		
		Prato prato = daoPrato.read(nome);
		if (prato == null)
			throw new Exception ("Prato não existe! " + prato);
		
		System.out.println(prato);
		
		//Remover todos acompanhamentos do prato alvo
		prato.esvaziar();
		daoPrato.update(prato);
		
		//Remover carne do prato
		prato.setCarne(null);
		
		daoPrato.update(prato);
		daoPrato.delete(prato);
		DAO.commit();
	}
	
	public static List<Acompanhamento> listarAcompanhamentos() {
		DAO.begin();
		List<Acompanhamento> resultados = daoAcompanhamento.readAll();
		DAO.commit();
		return resultados;
	}
	
	public static List<Carne> listarCarnes() {
		DAO.begin();
		List<Carne> resultados = daoCarne.readAll();
		DAO.commit();
		return resultados;
	}
	
	public static List<Prato> listarPratos() {
		DAO.begin();
		List<Prato> resultados = daoPrato.readAll();
		DAO.commit();
		return resultados;
	}
	
	public static List<Usuario>  listarUsuarios(){
		DAO.begin();
		List<Usuario> resultados =  daousuario.readAll();
		DAO.commit();
		return resultados;
	} 

	public static List<Acompanhamento> acompanhamentosPreco(double preco) {
		DAO.begin();
		List<Acompanhamento> resultados = daoAcompanhamento.consultarPrecoAcompanhamento(preco);
		DAO.commit();
		return resultados;
	}
	
	public static List<Prato> acompanhamentoPrato(String nome) {
		DAO.begin();
		List<Prato> resultados = daoPrato.consultarAcompanhamentoNome(nome);
		DAO.commit();
		return resultados;
	}
	
	public static List<Prato> carnePrato(String nome) {
		DAO.begin();
		List<Prato> resultados = daoPrato.consultarCarneNome(nome);
		DAO.commit();
		return resultados;
	}
	
	public static List<Prato> pratosNAcompanhamentos(int quantidade) {
		DAO.begin();
		List<Prato> resultados = daoPrato.consultarPratosComMaisDeNAcompanhamentos(quantidade);
		DAO.commit();
		return resultados;
	}
	
	public static Acompanhamento localizarAcompanhamento(String nome) {
		return daoAcompanhamento.read(nome);
	}
	
	public static Carne localizarCarne(String nome) {
		return daoCarne.read(nome);
	}
	
	public static Prato localizarPrato(String nome) {
		return daoPrato.read(nome);
	}
	
	//------------------Usuario------------------------------------
	public static Usuario cadastrarUsuario(String nome, String senha) throws Exception{
		DAO.begin();
		Usuario usu = daousuario.read(nome);
		if (usu!=null)
			throw new Exception("Usuario ja cadastrado:" + nome);
		usu = new Usuario(nome, senha);

		daousuario.create(usu);
		DAO.commit();
		return usu;
	}
	public static Usuario localizarUsuario(String nome, String senha) {
		Usuario usu = daousuario.read(nome);
		if (usu==null)
			return null;
		if (! usu.getSenha().equals(senha))
			return null;
		return usu;
	}
}
