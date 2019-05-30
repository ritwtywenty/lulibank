package br.com.rib.luli.bank.net;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JProgressBar;
import java.awt.Insets;
import javax.swing.JButton;

public class ClikerItemList extends JPanel {
	private static final long serialVersionUID = 1633819581285440008L;
	
	public ClikerItemList() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblWorkerName = new JLabel("Worker Name");
		GridBagConstraints gbc_lblWorkerName = new GridBagConstraints();
		gbc_lblWorkerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblWorkerName.gridx = 0;
		gbc_lblWorkerName.gridy = 0;
		add(lblWorkerName, gbc_lblWorkerName);
		
		JButton btnUpgrade = new JButton("Upgrade");
		GridBagConstraints gbc_btnUpgrade = new GridBagConstraints();
		gbc_btnUpgrade.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpgrade.gridx = 1;
		gbc_btnUpgrade.gridy = 0;
		add(btnUpgrade, gbc_btnUpgrade);
		
		JButton btnDemitir = new JButton("Dismiss");
		GridBagConstraints gbc_btnDemitir = new GridBagConstraints();
		gbc_btnDemitir.insets = new Insets(0, 0, 5, 0);
		gbc_btnDemitir.gridx = 2;
		gbc_btnDemitir.gridy = 0;
		add(btnDemitir, gbc_btnDemitir);
		
		JProgressBar progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridwidth = 3;
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 1;
		add(progressBar, gbc_progressBar);

	}

}
