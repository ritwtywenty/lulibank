package br.com.rib.luli.bank;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class defines the bank insertion language
 * 
 * @author nokhy@outlook.com
 * 
 *         create account name;password -- returns a new account number remove[
 *         remove account [account id] -- remove account
 *         account code -- remove an account by code show -- returns a full data
 *         report help -- returns info about all commands
 */
public class Parser {

	private String help_msg = "lulibank #\n" + "create account name;password -- returns a new account number\n"
			+ "show -- returns a full data report\n"
			+ "help -- returns info about all commands\n"
			+ "remove account [account id] -- remove account";

	private Banco banco;

	public Parser(Banco banco) {
		this.banco = banco;
	}

	private String removeAccount(int id) {
		for (Conta conta : banco.getClientes()) {
			if (conta.getNumero() == id) {
				banco.removeCliente(conta);
				return "Conta nº: " + id + " removida";
			}
		}
		return "remove account error: account not found";
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

	private String remove(String command[]) {
		if (command.length < 2)
			return "remove command error: " + command.toString();

		switch (command[1]) {
		case "account":
			if (command.length < 3)
				return "remove account error: " + command.toString();

			int id = -1;
			try {
				id = Integer.parseInt(command[2]);
			} catch (Exception e) {
				return "remove account error: account code";
			}
			return removeAccount(id);

		default:
			return "comando inválido para create";
		}
	}

	private String show() {
		String buffer = "--- lulibank data #\n";
		for (Conta conta : banco.getClientes()) {
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

		case "remove":
			return remove(command);

		case "show":
			return show();

		case "help":
			return help_msg;
		default:
			return "comando não encontrado";
		}
	}

}
