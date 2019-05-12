package br.com.rib.luli.bank.miner;

import java.util.Random;

import br.com.rib.luli.bank.object.Account;

public class SimpleMiner implements Miner{

	private Random random;
	private int count;
	private int luckyNumber;
	private Account acc;
	private boolean active;

	public SimpleMiner() {
		random = new Random(System.currentTimeMillis());
		active = false;
	}

	public void miner(Account acc) {
		this.acc = acc;
	}

	@Override
	public Miner newInstance() {
		return new SimpleMiner();
	}

	@Override
	public String onStart() {
		count = 0;
		luckyNumber = random.nextInt(100) + 1;
		active = true;
		return "Acerte o numero aleatorio entre 0 e 100 com no maximo 5 tentativas: ";
	}

	@Override
	public String onNewAction(String string) {
		int entradaN = 0;
		
		try {
			entradaN = Integer.parseInt(string);
		} catch (Exception e) {
			return "Apenas numeros!";
		}
		
		count++;
		if (count >= 5) {
			active = false;
			return "Você falhou!";
		}
		
		if (entradaN < luckyNumber) {
			return "O numero certo é maior!" + "\nTente novamente: ";
		} else if (entradaN > luckyNumber) {
			return "O numero certo é menor!" + "\nTente novamente: ";
		} else {
			acc.setSaldo(acc.getSaldo() + 1);
			active = false;
			return "Acertô mizeravi! +1";
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}
}
