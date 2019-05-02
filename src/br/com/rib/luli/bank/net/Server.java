package br.com.rib.luli.bank.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import br.com.rib.luli.bank.main.Parser;
import br.com.rib.luli.bank.object.Bank;

public class Server {
	public final int PORT = 54589;
	public ServerSocket serverSocket;
	Bank bank;
	Parser parser;
	boolean executando;

	public Server(Bank bank) {
		this.bank = bank;
		parser = new Parser(bank);
	}
	
	public Server(Bank bank, Parser parser) {
		this.bank = bank;
		this.parser = parser;
	}

	public void startListener() throws IOException {
		serverSocket = new ServerSocket(PORT);
		executando = true;
		listener();
	}

	private void listener() {
		System.out.println("LuliBankServer running at " + System.currentTimeMillis());
		try {
			do {
				Socket client = serverSocket.accept();
				System.out.println("New connection: " + client.getInetAddress().getHostAddress());
				new Thread(new Runnable() {
					@Override
					public void run() {
						clientComunication(client);
					}
				}).start();
			} while (executando);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void clearBuffer(byte[] buffer)
	{
		for(int i = 0; i < buffer.length; i++)
		{
			buffer[i] = '\0';
		}
	}

	private void clientComunication(Socket socket) {
		try {
			byte readerBuffer[] = new byte[1024 * 1024];
			String recv = "";
			do {
				clearBuffer(readerBuffer);
				socket.getInputStream().read(readerBuffer);
				recv = new String(readerBuffer).trim().toLowerCase();
				System.out.println("new request from "+ socket.getInetAddress().getHostAddress()+" : " + recv);
				String response = parser.parseFromString(recv);
				socket.getOutputStream().write(response.getBytes());
			} while (!recv.equalsIgnoreCase("exit"));
			socket.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) throws Exception{
		Bank bank = new Bank();
		Parser parser = new Parser(bank);
		System.out.println(parser.parseFromString("init"));
		System.out.println(parser.parseFromString("load default"));
		Server server = new Server(bank, parser);
		server.startListener();
	}
}
