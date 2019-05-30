package br.com.rib.luli.bank.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import br.com.rib.luli.bank.miner.ClickerMinerControl;
import br.com.rib.luli.bank.object.Bank;

public class LocalPersistence {

	public static Properties luliConfig;
	public static Properties luliActions;

	public static void loadDefaultLuliConfig() {
		luliConfig = new Properties();
		luliConfig.setProperty("default-local-path", "config/");
		
		luliActions = new Properties();
		luliActions.setProperty("smine", "br.com.rib.luli.bank.miner.SimpleMiner");
		luliActions.setProperty("clicker-mine", "br.com.rib.luli.bank.miner.ClikerMiner");
		
		ClickerMinerControl.initPriceThings();
	}

	public static void saveBank(Bank bank) {
		try {
			File luliData = new File(luliConfig.getProperty("default-local-path"));
			FileOutputStream fos = new FileOutputStream(luliData);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(bank);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Bank loadBank() {
		try {
			File luliData = new File(luliConfig.getProperty("default-local-path"));
			FileInputStream fis = new FileInputStream(luliData);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Bank bank = (Bank) ois.readObject();
			ois.close();
			fis.close();
			return bank;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
