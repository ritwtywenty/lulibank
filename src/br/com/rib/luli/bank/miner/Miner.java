package br.com.rib.luli.bank.miner;

import br.com.rib.luli.bank.object.Account;

public interface Miner {
	public void miner(Account conta);
	
	public Miner newInstance();
	public String onStart();
	public String onNewAction(String string);
	public boolean isActive();
}
