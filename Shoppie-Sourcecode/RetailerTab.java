import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class RetailerTab {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RetailerTab window = new RetailerTab();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RetailerTab() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(128, 128, 128));
		frame.setBounds(100, 100, 941, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String uname = Login.username_ip.toUpperCase();
		JLabel lblNewLabel = new JLabel("Hello " + uname + " !");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(361, 11, 263, 54);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Sell Items");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(382, 160, 171, 54);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnMyOrders = new JButton("My Products");
		btnMyOrders.setBorderPainted(false);
		btnMyOrders.setForeground(Color.WHITE);
		btnMyOrders.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnMyOrders.setBackground(Color.BLACK);
		btnMyOrders.setBounds(133, 303, 171, 54);
		frame.getContentPane().add(btnMyOrders);
		
		JButton btnMyWallet = new JButton("My Wallet");
		btnMyWallet.setBorderPainted(false);
		btnMyWallet.setForeground(Color.WHITE);
		btnMyWallet.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnMyWallet.setBackground(Color.BLACK);
		btnMyWallet.setBounds(382, 303, 171, 54);
		frame.getContentPane().add(btnMyWallet);
		
		JButton btnBack = new JButton("Logout");
		btnBack.setBorderPainted(false);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBackground(Color.BLACK);
		btnBack.setBounds(391, 451, 162, 41);
		frame.getContentPane().add(btnBack);
		
		JButton btnMyInfo = new JButton("My Info");
		btnMyInfo.setBorderPainted(false);
		btnMyInfo.setForeground(Color.WHITE);
		btnMyInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnMyInfo.setBackground(Color.BLACK);
		btnMyInfo.setBounds(630, 303, 171, 54);
		frame.getContentPane().add(btnMyInfo);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 127, 80));
		panel.setBounds(0, 0, 927, 83);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(RetailerTab.class.getResource("/icons/ecom.jpg")));
		lblNewLabel_1.setBounds(0, 82, 927, 457);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		//Logout button
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				Login.loginobj.frmShoppie.setVisible(true);
			}
		});
		
		
		//My wallet Button
		btnMyWallet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new Wallet();
			}
		});
		
		
		//Sell Items button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new SellItems();
			}
		});
		
		//My products button
		btnMyOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new MyProducts();
			}
		});
		
		//My Info button
		btnMyInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new MyInfo();
			}
		});
	}
}
