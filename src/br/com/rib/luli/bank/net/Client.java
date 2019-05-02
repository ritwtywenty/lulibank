package br.com.rib.luli.bank.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	Scanner scanner;
	Socket comunication;

	public Client() throws UnknownHostException, IOException {
		comunication = new Socket(InetAddress.getByName("localhost"), 54589);
		scanner = new Scanner(System.in);
		System.out.println("Conectado");
	}
	
	private void clearBuffer(byte[] buffer)
	{
		for(int i = 0; i < buffer.length; i++)
		{
			buffer[i] = '\0';
		}
	}
	
	public void terminalLoop() throws Exception
	{
		byte readerBuffer[] = new byte[1024 * 1024];
		String read = "";
		System.out.print("> ");
		do {
			clearBuffer(readerBuffer);
			read = scanner.nextLine();
			comunication.getOutputStream().write(read.getBytes());
			comunication.getInputStream().read(readerBuffer);
			System.out.println(new String(readerBuffer).trim() + "\n> ");
		} while(!read.equalsIgnoreCase("exit"));
	}
	
	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.terminalLoop();
	}

}
