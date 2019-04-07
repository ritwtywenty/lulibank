package br.com.rib.luli.bank;

public class Conta {
	String nome;
	String senha;
	float Saldo;
	float limite;
	int numero;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public float getSaldo() {
		return Saldo;
	}
	public void setSaldo(float saldo) {
		Saldo = saldo;
	}
	public float getLimite() {
		return limite;
	}
	public void setLimite(float limite) {
		this.limite = limite;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	@Override
	public String toString() {
		return "Conta [nome=" + nome + ", senha=" + senha + ", Saldo=" + Saldo + ", limite=" + limite + ", numero="
				+ numero + "]" + "\n";
	}
}
