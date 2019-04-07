package br.com.rib.luli.bank;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	float imposto;
	List<Conta> clientes;
	int contagemDeContas;

	public void addCliente(Conta cliente) {
		clientes.add(cliente);
	}

	public void removeCliente(Conta cliente) {

	}

	@Override
	public String toString() {
		String armazenaBanco; 
		armazenaBanco =  "Banco [imposto=" + imposto + "]\n";
		for (Conta clienty: clientes) {
			armazenaBanco+=clienty;
		}
		return armazenaBanco;
	}
	

	public int getContagemDeContas() {
		return contagemDeContas++;
	}

	public void setContagemDeContas(int contagemDeContas) {
		this.contagemDeContas = contagemDeContas;
	}

	public Banco() {
		clientes = new ArrayList<>();
		imposto = (float) 0.01;
		contagemDeContas = 1;
	}
}
