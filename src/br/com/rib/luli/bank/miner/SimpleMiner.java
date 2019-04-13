package br.com.rib.luli.bank.miner;

import java.util.Random;
import java.util.Scanner;

import br.com.rib.luli.bank.object.Account;

public class SimpleMiner implements Miner{

	private Random random;
	private Scanner scanner;

	public SimpleMiner() {
		random = new Random(System.currentTimeMillis());
		scanner = new Scanner(System.in);
	}

	public int newFreeGame() {
		int count = 0;
		System.out.print("Acerte o numero aleatorio entre 0 e 100 com no maximo 5 tentativas: ");
		int luckyNumber = random.nextInt(100) + 1;
		do {

			int entradaN = 0;
			try {
				entradaN = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.print("Apenas numeros!");
				continue;
			}
			if (entradaN < luckyNumber) {
				System.out.println("O numero certo é maior!");
			} else if (entradaN > luckyNumber) {
				System.out.println("O numero certo é menor!");
			} else {
				System.out.println("Acertô mizeravi!");
				return 1;
			}
			count++;
			System.out.print("Tente novamente: ");

			if (count >= 5) {
				System.out.println("Você falhou!");
				return 0;
			}
		} while (true);

	}

	public void miner(Account conta) {
		int newBit = newFreeGame();
		if (newBit == 1) {
			conta.setSaldo(conta.getSaldo() + 1);
		}
	}

	@Override
	public Miner newInstance() {
		return new SimpleMiner();
	}
}
