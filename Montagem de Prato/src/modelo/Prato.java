package modelo;

import java.util.ArrayList;
import java.util.List;

import aplicacao.Util;

public class Prato {
	
	private int id;
	private String nome;
	private Carne carne;
	private List<Acompanhamento> acompanhamentos;
	
	public Prato(String nome, Carne carne) {
		this.id = Util.gerarIdPrato();
		this.nome = nome;
		this.carne = carne;
		this.acompanhamentos = new ArrayList<>();
	}
	
	public Acompanhamento localizar(String nome) {
		Acompanhamento out = null;
		
		for (Acompanhamento i : acompanhamentos) {
			if (i.getNome().equalsIgnoreCase(nome)) out = i;
		}
		return out;
	}
	
	public void adicionar(Acompanhamento acompanhamento) {
		this.acompanhamentos.add(acompanhamento);
	}
	
	public Acompanhamento remover(String nome) {
		Acompanhamento out = null;

		for (Acompanhamento i : acompanhamentos) {
			if (i.getNome().equalsIgnoreCase(nome)) {
				out = i;
				this.acompanhamentos.remove(i);
			}
		}
		return out;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Carne getCarne() {
		return carne;
	}

	public void setCarne(Carne carne) {
		this.carne = carne;
	}
	
	@Override
	public String toString() {
		String text = "id : " + id + "\nnome : " + nome + "\n" + carne + "\n";
		for (Acompanhamento a : acompanhamentos) {
			text += a + " ";
		}
		return text;
		
	}
	
}