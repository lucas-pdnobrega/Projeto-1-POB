package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Prato {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nome;
	private Carne carne;
	private List<Acompanhamento> acompanhamentos;
	
	public Prato() {}
	
	public Prato(String nome, Carne carne) {
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
	
	public void remover(Acompanhamento acompanhamento) {
		this.acompanhamentos.remove(acompanhamento);
	}
	
	public List<Acompanhamento> esvaziar() {
		List<Acompanhamento> saida = this.acompanhamentos;
		this.acompanhamentos = new ArrayList<>();
		return saida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Carne getCarne() {
		return carne;
	}

	public void setCarne(Carne carne) {
		this.carne = carne;
	}
	
	public List<Acompanhamento> getAcompanhamentos() {
		return acompanhamentos;
	}

	public void setAcompanhamentos(List<Acompanhamento> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
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