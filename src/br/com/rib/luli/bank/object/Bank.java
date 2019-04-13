package br.com.rib.luli.bank.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Serializable{
	private static final long serialVersionUID = -5722236408562309423L;
	private float imposto;
	private List<Account> clientes;
	private List<Account> contasDesativadas;
	private int contagemDeContas;

	public void addCliente(Account cliente) {
		clientes.add(cliente);
	}

	public void removeCliente(Account cliente) {
		clientes.remove(cliente);
		contasDesativadas.add(cliente);
	}

	@Override
	public String toString() {
		String armazenaBanco;
		armazenaBanco = "Banco [imposto=" + imposto + "]\n";
		for (Account clienty : clientes) {
			armazenaBanco += clienty;
		}
		return armazenaBanco;
	}

	public List<Account> getClientes() {
		return clientes;
	}
	
	public Account getAccountbyID(int accountID) {
		for(Account client : clientes) {
			if(client.getNumero() == accountID)
					return client;
		}
		return null;
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

	public Bank() {
		contasDesativadas = new ArrayList<>();
		clientes = new ArrayList<>();
		imposto = (float) 0.01;
		contagemDeContas = 1;
	}
}
