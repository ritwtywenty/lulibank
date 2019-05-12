package br.com.rib.luli.bank.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.rib.luli.bank.miner.Miner;
import br.com.rib.luli.bank.object.Account;
import br.com.rib.luli.bank.object.Bank;
import br.com.rib.luli.bank.persistence.LocalPersistence;

/**
 * This class defines the bank insertion language
 */
public class Parser {

	private String helpMsg;

	private Bank bank;
	private Miner miner;

	public Parser(Bank banco) {
		this.bank = banco;
		loadHelpMsg();
	}

	private void loadHelpMsg() {
		try {
			File helpFile = new File("help.txt");
			FileReader file = new FileReader(helpFile);
			BufferedReader br = new BufferedReader(file);
			String line;
			while ((line = br.readLine()) != null) {
				helpMsg += line + "\n";
			}
			br.close();
			file.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private String removeAccount(int id) {
		for (Account conta : bank.getClientes()) {
			if (conta.getNumero() == id) {
				bank.removeCliente(conta);
				return "Conta nº: " + id + " removida.";
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

	public Bank getBank() {
		return bank;
	}

	private String create(String command[]) {
		if (command.length < 2)
			return "create command error: " + command.toString();

		switch (command[1]) {
		case "account":

			if (command.length < 3)
				return "create account error: " + command.toString();

			String accountConfig[] = command[2].split(";");
			if (accountConfig.length < 2)
				return "create account password error try: create account name;password";

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

	public String start(String[] command) {
		String action = command[1];
		int accountID = 0;

		try {
			accountID = Integer.parseInt(command[3]);
		} catch (Exception e) {
			return "Erro no numero da conta";
		}

		Account account = bank.getAccountbyID(accountID);

		if (account == null)
			return "Conta não encontrada";

		String actionClass = LocalPersistence.luliActions.getProperty(action);
		try {
			miner = (Miner) Class.forName(actionClass).newInstance();
			miner.miner(account);
			return miner.onStart();
		} catch (Exception e) {
			return "Ação não encontrada";
		}
	}

	public String listsJobs() {
		String actions = "Actions:\n";
		for (Object obj : LocalPersistence.luliActions.keySet()) {
			actions += (String) obj + "\n";
		}
		return actions;
	}

	public String parseFromString(String string) {
		try {
			if (miner.isActive())
				return miner.onNewAction(string);
		} catch (NullPointerException e) {
		}

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
			return helpMsg;

		case "save":
			return saveFile(command);

		case "load":
			return loadFile(command);

		case "start":
			return start(command);

		case "jobs":
			return listsJobs();

		case "exit":
			return "server quit " + System.currentTimeMillis();

		case "init":
			LocalPersistence.loadDefaultLuliConfig();
			return "banco iniciado com configurações padrões";

		default:
			return "comando não encontrado";
		}
	}

}
