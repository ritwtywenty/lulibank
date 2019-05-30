package br.com.rib.luli.bank.net;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.rib.luli.bank.miner.ClickerMinerControl;

public class ClikerClient extends JFrame {
	private static final long serialVersionUID = -202079253489837277L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtUserName;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	Socket comunication;
	byte readerBuffer[];

	public ClikerClient() {
		readerBuffer = new byte[1024 * 1024];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblClikerMiner = new JLabel("Cliker Miner");
		GridBagConstraints gbc_lblClikerMiner = new GridBagConstraints();
		gbc_lblClikerMiner.gridwidth = 4;
		gbc_lblClikerMiner.insets = new Insets(0, 0, 5, 5);
		gbc_lblClikerMiner.gridx = 0;
		gbc_lblClikerMiner.gridy = 0;
		contentPane.add(lblClikerMiner, gbc_lblClikerMiner);

		JLabel lblLuliCoins = new JLabel("Luli Coins");
		GridBagConstraints gbc_lblLuliCoins = new GridBagConstraints();
		gbc_lblLuliCoins.anchor = GridBagConstraints.WEST;
		gbc_lblLuliCoins.insets = new Insets(0, 0, 5, 5);
		gbc_lblLuliCoins.gridx = 0;
		gbc_lblLuliCoins.gridy = 1;
		contentPane.add(lblLuliCoins, gbc_lblLuliCoins);

		JButton btnClick = new JButton("Click");
		btnClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClicker();
			}
		});
		GridBagConstraints gbc_btnClick = new GridBagConstraints();
		gbc_btnClick.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClick.gridwidth = 2;
		gbc_btnClick.insets = new Insets(0, 0, 5, 0);
		gbc_btnClick.gridx = 2;
		gbc_btnClick.gridy = 1;
		contentPane.add(btnClick, gbc_btnClick);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 4;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblClikerCoins = new JLabel("Cliker Coins");
		GridBagConstraints gbc_lblClikerCoins = new GridBagConstraints();
		gbc_lblClikerCoins.anchor = GridBagConstraints.EAST;
		gbc_lblClikerCoins.insets = new Insets(0, 0, 5, 5);
		gbc_lblClikerCoins.gridx = 0;
		gbc_lblClikerCoins.gridy = 3;
		contentPane.add(lblClikerCoins, gbc_lblClikerCoins);

		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.gridwidth = 3;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 3;
		contentPane.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);

		JLabel lblAccount = new JLabel("Account");
		GridBagConstraints gbc_lblAccount = new GridBagConstraints();
		gbc_lblAccount.anchor = GridBagConstraints.EAST;
		gbc_lblAccount.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccount.gridx = 0;
		gbc_lblAccount.gridy = 4;
		contentPane.add(lblAccount, gbc_lblAccount);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 4;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblPerformace = new JLabel("Performance per click");
		GridBagConstraints gbc_lblPerformace = new GridBagConstraints();
		gbc_lblPerformace.insets = new Insets(0, 0, 5, 5);
		gbc_lblPerformace.gridx = 2;
		gbc_lblPerformace.gridy = 4;
		contentPane.add(lblPerformace, gbc_lblPerformace);

		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setText("+1");
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 4;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblUserName = new JLabel("User Name");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.anchor = GridBagConstraints.EAST;
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 5;
		contentPane.add(lblUserName, gbc_lblUserName);

		txtUserName = new JTextField();
		GridBagConstraints gbc_txtUserName = new GridBagConstraints();
		gbc_txtUserName.insets = new Insets(0, 0, 5, 5);
		gbc_txtUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUserName.gridx = 1;
		gbc_txtUserName.gridy = 5;
		contentPane.add(txtUserName, gbc_txtUserName);
		txtUserName.setColumns(10);

		JLabel lblClickCount = new JLabel("Click count");
		GridBagConstraints gbc_lblClickCount = new GridBagConstraints();
		gbc_lblClickCount.insets = new Insets(0, 0, 5, 5);
		gbc_lblClickCount.gridx = 2;
		gbc_lblClickCount.gridy = 5;
		contentPane.add(lblClickCount, gbc_lblClickCount);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setText("0");
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 5;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel lblWorkers = new JLabel("Your business");
		GridBagConstraints gbc_lblWorkers = new GridBagConstraints();
		gbc_lblWorkers.gridwidth = 4;
		gbc_lblWorkers.insets = new Insets(0, 0, 5, 0);
		gbc_lblWorkers.gridx = 0;
		gbc_lblWorkers.gridy = 6;
		contentPane.add(lblWorkers, gbc_lblWorkers);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 4;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 7;
		contentPane.add(tabbedPane, gbc_tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Workers", null, panel, null);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Shop", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNewWorker = new JLabel("Keyok");
		GridBagConstraints gbc_lblNewWorker = new GridBagConstraints();
		gbc_lblNewWorker.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewWorker.gridx = 0;
		gbc_lblNewWorker.gridy = 0;
		panel_1.add(lblNewWorker, gbc_lblNewWorker);

		JButton btnClicks = new JButton("+1000 Clicks\r\n");
		btnClicks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onBuyWorker();
			}
		});
		GridBagConstraints gbc_btnClicks = new GridBagConstraints();
		gbc_btnClicks.gridx = 0;
		gbc_btnClicks.gridy = 1;
		panel_1.add(btnClicks, gbc_btnClicks);
	}

	public String chunk(String send) {
		try {
			System.out.println("Request: " + send);
			comunication.getOutputStream().write(send.getBytes());
			comunication.getOutputStream().flush();
			clearBuffer(readerBuffer);
			comunication.getInputStream().read(readerBuffer);
			System.out.println("Response: " + new String(readerBuffer));
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
		return new String(readerBuffer);
	}

	public boolean openConnection(String user, String pass) {
		String response;

		try {
			comunication = new Socket(InetAddress.getByName("localhost"), 54589);

			// Verifica se a conta existe
			response = chunk("show number " + user + ";" + pass);
			
			if (response.startsWith("Error")) {
				throw new Exception("Server wrong response!");
			}

			if (!response.startsWith("account")) {
				throw new Exception("Server wrong response: " + response);
			}

			// Inicia o miner para a conta
			response = chunk("start clicker-mine to " + response.split("account: ")[1].trim());
			
			if (!response.startsWith("cliker-miner-start")) {
				throw new Exception("Server wrong response: " + response);
			}
			
			JOptionPane.showMessageDialog(null, "You are connected!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Click error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;

	}

	public void onClicker() {
		try {
			String clickMsg = "click";
			comunication.getOutputStream().write(clickMsg.getBytes());
			comunication.getOutputStream().flush();
			clearBuffer(readerBuffer);
			comunication.getInputStream().read(readerBuffer);
			String res = new String(readerBuffer);
			ByteArrayInputStream bais = new ByteArrayInputStream(res.getBytes());
			ObjectInputStream ois = new ObjectInputStream(bais);
			ClickerMinerControl cmc = (ClickerMinerControl) ois.readObject();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(cmc);
			oos.flush();
			update(cmc);
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Click error", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void update(ClickerMinerControl cmc) {
		textField.setText("" + cmc.acc.getLimite());
		textField_4.setText("" + cmc.clickCountAll);
		textField_3.setText("" + cmc.clickCount);
		textField_2.setText("" + cmc.clickPerformance);
		textField_1.setText("" + cmc.acc.getNumero());
		txtUserName.setText(cmc.acc.getNome());
	}

	private void clearBuffer(byte[] buffer) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = '\0';
		}
	}

	public void onBuyWorker() {
		// ClikerItemList cil = new ClikerItemList();
	}

}
