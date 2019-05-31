package br.com.rib.luli.bank.miner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import br.com.rib.luli.bank.object.Account;

public class ClickerMinerControl implements Serializable {
	private static final long serialVersionUID = 123456799L;
	public int clickCount;
	public int clickCountAll;
	public int clickPerformance;
	public ClikerMiner clikerMiner;
	public ArrayList<ClickWorker> workers;
	public Account acc;
	public static Properties priceThings = new Properties();

	public ClickerMinerControl(Account account, ClikerMiner clikerMiner) {
		workers = new ArrayList<>();
		this.acc = account;
		clickCount = 0;
		clickCountAll = 0;
		clickPerformance = 0;
		this.clikerMiner = clikerMiner;
	}

	public static void initPriceThings() {
		priceThings.setProperty("Keyok", "10");
	}

	public void click() {
		clickCountAll++;
		clickCount++;
	}

	public void buy(String thing) {
		String price = priceThings.getProperty(thing);
		if (price != null) {
			int intPrice = Integer.parseInt(price);
			if(clickCount < intPrice)
				return;
			clickCount -= Integer.parseInt(price);
			ClickWorker cw = new ClickWorker(thing, this);
			workers.add(cw);
		}
	}

	@Override
	public String toString() {
		return "ClickerMinerControl [clickCount=" + clickCount + ", clickCountAll=" + clickCountAll
				+ ", clickPerformance=" + clickPerformance + ", acc=" + acc + "]";
	}
}
