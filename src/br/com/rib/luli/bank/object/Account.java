package br.com.rib.luli.bank.object;

import java.io.Serializable;

import br.com.rib.luli.util.LuliUtil;

public final class Account implements Serializable{
	private static final long serialVersionUID = 123456789L;
	private String nome;
	private String senha;
	private float saldo;
	private float limite;
	private int numero;
	
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
		this.senha = LuliUtil.encode(senha);
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
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
		return "Conta [nome=" + nome + ", senha=" + senha + ", Saldo=" + saldo + ", limite=" + limite + ", numero="
				+ numero + "]" + "\n";
	}
}
