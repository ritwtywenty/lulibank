package br.com.rib.luli.bank;

import java.time.LocalDateTime;
import java.util.Scanner;

import br.com.rib.luli.util.LuliUtil;

public class Terminal {

	String startMessage = " /$$           /$$ /$$ /$$                           /$$      \n"
			+ "| $$          | $$|__/| $$                          | $$      \n"
			+ "| $$ /$$   /$$| $$ /$$| $$$$$$$   /$$$$$$  /$$$$$$$ | $$   /$$\n"
			+ "| $$| $$  | $$| $$| $$| $$__  $$ |____  $$| $$__  $$| $$  /$$/\n"
			+ "| $$| $$  | $$| $$| $$| $$  \\ $$  /$$$$$$$| $$  \\ $$| $$$$$$/ \n"
			+ "| $$| $$  | $$| $$| $$| $$  | $$ /$$__  $$| $$  | $$| $$_  $$ \n"
			+ "| $$|  $$$$$$/| $$| $$| $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$\n"
			+ "|__/ \\______/ |__/|__/|_______/  \\_______/|__/  |__/|__/  \\__/" + "\n"
			+ "-----------------------------------------------------------------";

	boolean executando;
	Parser parser;
	Scanner scanner;
	Banco banco;

	public Terminal() {
		executando = false;
		banco = new Banco();
		parser = new Parser(banco);
		scanner = new Scanner(System.in);
	}

	public void listener() {
		LocalDateTime now = LocalDateTime.now();
		System.out.println(startMessage);
		System.out.println("lulibank " + LuliUtil.dateFormatter(now) + " #" + banco.getContagemDeContas());
		do {
			System.out.print("$ ");
			String commandLine = scanner.nextLine();

			if (commandLine.equalsIgnoreCase("exit")) {
				executando = false;
				continue;
			}

			String resposta = parser.parseFromString(commandLine);
			if(!resposta.endsWith("\n"))
				resposta += "\n";
			System.out.println(resposta);
		} while (executando);
	}

	public void startListener() {
		executando = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				listener();
			}
		}).start();
	}

	public static void main(String[] args) {
		Terminal terminalLocal = new Terminal();
		terminalLocal.startListener();
	}

}
