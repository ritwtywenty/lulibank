package br.com.rib.luli.bank.miner;

import java.io.Serializable;

public class ClickWorker implements Serializable{
	private static final long serialVersionUID = -7859737186557243049L;
	private ClickerMinerControl clickerMinerControl;
	public String name;
	public int time;

	public ClickWorker(String name, ClickerMinerControl clickerMinerControl) {
		time = 0;
		this.name = name;
		this.clickerMinerControl = clickerMinerControl;
	}

	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(clickerMinerControl.clikerMiner.active)
				{
					clickerMinerControl.click();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
