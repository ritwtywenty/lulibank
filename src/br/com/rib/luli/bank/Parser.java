package br.com.rib.luli.bank;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class defines the bank insertion language
 * 
 * @author nokhy@outlook.com
 * 
 *         create account name;password -- returns a new account number
 */
public class Parser {

	Banco banco;

	public Parser(Banco banco) {
		this.banco = banco;
	}

	private String createAccount(String name, String password) {
		if (name.length() < 6 || name.length() > 30)
			return "error, account name is invalid: " + name;

		if (password.length() < 6 || password.length() > 30)
			return "error, account password is invalid: " + password;

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Warning, md5 is out");
		}

		Conta novaConta = new Conta();
		novaConta.setNome(name);
		novaConta.setNumero(banco.getContagemDeContas());

		if (messageDigest != null)
			novaConta.setSenha(messageDigest.digest().toString());
		else
			novaConta.setSenha(password);
		
		banco.addCliente(novaConta);

		return "new account " + novaConta.getNumero();
	}

	private String create(String command[]) {
		if (command.length < 2)
			return "create command error: " + command.toString();

		switch (command[1]) {
		case "account":

			if (command.length < 3)
				return "create account error: " + command.toString();

			String accountConfig[] = command[2].split(";");
			return createAccount(accountConfig[0], accountConfig[1]);

		default:
			return "comando inválido para create";
		}
	}

	private String show() {
		String buffer = "--- lulibank data #\n";
		for (Conta conta : banco.clientes) {
			buffer += conta.toString();
		}
		return buffer;
	}

	public String parseFromString(String string) {
		if (string.length() < 3)
			return "comando inválido!";

		String command[] = string.split(" ");

		switch (command[0]) {
		case "create":
			return create(command);

		case "show":
			return show();
		default:
			return "comando não encontrado";
		}
	}

}
