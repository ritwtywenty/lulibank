package br.com.rib.luli.bank.net;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClickerLoginScreen extends JFrame {

	private static final long serialVersionUID = -7239832602362384783L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField txtAaabbb;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClickerLoginScreen frame = new ClickerLoginScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClickerLoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 192);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblClickerGame = new JLabel("Clicker Game");
		GridBagConstraints gbc_lblClickerGame = new GridBagConstraints();
		gbc_lblClickerGame.insets = new Insets(0, 0, 5, 5);
		gbc_lblClickerGame.gridx = 1;
		gbc_lblClickerGame.gridy = 0;
		contentPane.add(lblClickerGame, gbc_lblClickerGame);

		JLabel lblUser = new JLabel("User");
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.anchor = GridBagConstraints.EAST;
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 1;
		contentPane.add(lblUser, gbc_lblUser);

		txtAaabbb = new JTextField();
		txtAaabbb.setText("lbroot");
		GridBagConstraints gbc_txtAaabbb = new GridBagConstraints();
		gbc_txtAaabbb.insets = new Insets(0, 0, 5, 0);
		gbc_txtAaabbb.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAaabbb.gridx = 1;
		gbc_txtAaabbb.gridy = 1;
		contentPane.add(txtAaabbb, gbc_txtAaabbb);
		txtAaabbb.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		contentPane.add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		passwordField.setText("lbroot");
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 2;
		contentPane.add(passwordField, gbc_passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onLogin();
			}
		});
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 3;
		contentPane.add(btnLogin, gbc_btnLogin);
	}

	private void onLogin() {
		String user = txtAaabbb.getText();
		String password = new String(passwordField.getPassword());

		if (user.isEmpty()) {
			JOptionPane.showMessageDialog(null, "User are empty");
			return;
		}
		
		if (password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Password are empty");
			return;
		}

		ClikerClient cc = new ClikerClient();
		if (cc.openConnection(user, password)) {
			cc.setVisible(true);
			this.setVisible(false);
		}
	}

}
