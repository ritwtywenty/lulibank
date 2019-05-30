package br.com.rib.luli.bank.miner;

import java.io.Serializable;
import java.util.Properties;

import br.com.rib.luli.bank.object.Account;

public class ClickerMinerControl implements Serializable {
	private static final long serialVersionUID = 123456799L;
	public int clickCount;
	public int clickCountAll;
	public int clickPerformance;
	public Account acc;
	public static Properties priceThings = new Properties();

	public ClickerMinerControl(Account account) {
		this.acc = account;
		clickCount = 0;
		clickCountAll = 0;
		clickPerformance = 0;
	}

	public static void initPriceThings() {
		priceThings.setProperty("Keyok", "1000");
	}

	public void click() {
		clickCountAll++;
		clickCount++;
	}

	@Override
	public String toString() {
		return "ClickerMinerControl [clickCount=" + clickCount + ", clickCountAll=" + clickCountAll
				+ ", clickPerformance=" + clickPerformance + ", acc=" + acc + "]";
	}
}
