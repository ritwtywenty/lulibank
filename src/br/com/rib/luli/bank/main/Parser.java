package br.com.rib.luli.bank.main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.rib.luli.bank.object.Bank;
import br.com.rib.luli.bank.persistence.LocalPersistence;
import br.com.rib.luli.bank.object.Account;

/**
 * This class defines the bank insertion language
 * 
 * @author nokhy@outlook.com
 * 
 *         create account name;password -- returns a new account number remove[
 *         remove account [account id] -- remove account account code -- remove
 *         an account by code show -- returns a full data report help -- returns
 *         info about all commands init -- load default config load default --
 *         Load from default persistence data save default -- save to default
 */
public class Parser {

	private String help_msg = "lulibank #\n" + "init -- Inicia as configurações do banco\r\n"
			+ "create account name;password -- Returns a new account number\n"
			+ "show -- Returns a full data report\n" + "help -- Returns info about all commands\n"
			+ "remove account [account id] -- Remove account\n"
			+ "load default -- Load from default persistence data\r\n"
			+ "save default -- Save to default\r\n";

	private Bank bank;

	public Parser(Bank banco) {
		this.bank = banco;
	}

	private String removeAccount(int id) {
		for (Account conta : bank.getClientes()) {
			if (conta.getNumero() == id) {
				bank.removeCliente(conta);
				return "Conta nÂº: " + id + " removida.";
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

		Account novaConta = new Account();
		novaConta.setNome(name);
		novaConta.setNumero(bank.getContagemDeContas());

		if (messageDigest != null)
			novaConta.setSenha(messageDigest.digest().toString());
		else
			novaConta.setSenha(password);

		bank.addCliente(novaConta);

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
			return "comando invÃ¡lido para create";
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
			return "comando invÃ¡lido para create";
		}
	}

	private String show() {
		String buffer = "--- lulibank data #\n";
		for (Account conta : bank.getClientes()) {
			buffer += conta.toString();
		}
		return buffer;
	}

	public String saveFile(String[] command) {
		if (command.length < 2)
			return "save command error: " + command.toString();

		if (!command[1].equals("default"))
			return "não suportado";

		LocalPersistence.saveBank(bank);
		return "banco salvo";
	}

	public String loadFile(String[] command) {
		if (command.length < 2)
			return "load command error: " + command.toString();

		if (!command[1].equals("default"))
			return "não suportado";

		bank = LocalPersistence.loadBank();
		return "novo banco carregado!";
	}

	public String parseFromString(String string) {
		if (string.length() < 3)
			return "comando invÃ¡lido!";

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

		case "save":
			return saveFile(command);

		case "load":
			return loadFile(command);

		case "init":
			LocalPersistence.loadDefaultLuliConfig();
			return "banco iniciado com configurações padrões";

		default:
			return "comando nÃ£o encontrado";
		}
	}

}
