package br.com.rib.luli.bank;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	private float imposto;
	private List<Conta> clientes;
	private List<Conta> contasDesativadas;
	private int contagemDeContas;

	public void addCliente(Conta cliente) {
		clientes.add(cliente);
	}

	public void removeCliente(Conta cliente) {
		clientes.remove(cliente);
		contasDesativadas.add(cliente);
	}

	@Override
	public String toString() {
		String armazenaBanco;
		armazenaBanco = "Banco [imposto=" + imposto + "]\n";
		for (Conta clienty : clientes) {
			armazenaBanco += clienty;
		}
		return armazenaBanco;
	}

	public List<Conta> getClientes() {
		return clientes;
	}

	public int getContagemDeContas() {
		return contagemDeContas++;
	}

	public int getContagemDeContasInfo() {
		return contagemDeContas;
	}

	public void setContagemDeContas(int contagemDeContas) {
		this.contagemDeContas = contagemDeContas;
	}

	public Banco() {
		contasDesativadas = new ArrayList<>();
		clientes = new ArrayList<>();
		imposto = (float) 0.01;
		contagemDeContas = 1;
	}
}
