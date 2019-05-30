package br.com.rib.luli.bank.miner;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import br.com.rib.luli.bank.object.Account;

public class ClikerMiner implements Miner {

	boolean active;
	ClickerMinerControl cmc;

	@Override
	public void miner(Account conta) {
		cmc = new ClickerMinerControl(conta);
		active = true;
	}

	@Override
	public Miner newInstance() {
		return new ClikerMiner();
	}

	@Override
	public String onStart() {
		return "cliker-miner-start";
	}

	@Override
	public String onNewAction(String string) {
		try {
			if (string.startsWith("click")) {
				cmc.click();
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				oos.writeObject(cmc);
				oos.flush();
				String response = new String(bos.toByteArray());
				oos.close();
				bos.close();
				return response;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return "click-error";
	}

	@Override
	public boolean isActive() {
		return active;
	}

}
