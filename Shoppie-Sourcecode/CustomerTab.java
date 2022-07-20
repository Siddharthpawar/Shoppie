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
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CustomerTab {

	public static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerTab window = new CustomerTab();
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
	public CustomerTab() {
		
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
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 941, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String uname = Login.username_ip.toUpperCase();
		JLabel lblNewLabel = new JLabel("Hello " + uname + " !");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(157, 11, 598, 54);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Shop");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(354, 111, 171, 54);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnMyCart = new JButton("My Cart");
		btnMyCart.setBorderPainted(false);
		btnMyCart.setForeground(Color.WHITE);
		btnMyCart.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnMyCart.setBackground(Color.BLACK);
		btnMyCart.setBounds(554, 226, 171, 54);
		frame.getContentPane().add(btnMyCart);
		
		JButton btnMyOrders = new JButton("My Orders");
		btnMyOrders.setBorderPainted(false);
		btnMyOrders.setForeground(Color.WHITE);
		btnMyOrders.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnMyOrders.setBackground(Color.BLACK);
		btnMyOrders.setBounds(157, 226, 171, 54);
		frame.getContentPane().add(btnMyOrders);
		
		JButton btnMyWallet = new JButton("My Wallet");
		btnMyWallet.setBorderPainted(false);
		btnMyWallet.setForeground(Color.WHITE);
		btnMyWallet.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnMyWallet.setBackground(Color.BLACK);
		btnMyWallet.setBounds(157, 330, 171, 54);
		frame.getContentPane().add(btnMyWallet);
		
		JButton btnBack = new JButton("Logout");
		btnBack.setBorderPainted(false);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBackground(Color.BLACK);
		btnBack.setBounds(376, 471, 162, 41);
		frame.getContentPane().add(btnBack);
		
		JButton btnMyInfo = new JButton("My Info");
		btnMyInfo.setBorderPainted(false);
		btnMyInfo.setForeground(Color.WHITE);
		btnMyInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnMyInfo.setBackground(Color.BLACK);
		btnMyInfo.setBounds(554, 330, 171, 54);
		frame.getContentPane().add(btnMyInfo);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 99, 71));
		panel.setBounds(0, 0, 927, 77);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(CustomerTab.class.getResource("/icons/ecom1.jpg")));
		lblNewLabel_1.setBounds(0, 76, 927, 463);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		//logout button
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				Login.loginobj.frmShoppie.setVisible(true);
				
			}
		});
		
		
		//shop button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new Shop();
			}
		});
		
		
		//Wallet button
		btnMyWallet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new Wallet();
			}
		});
		
		
		//My orders button
		btnMyOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new MyOrders();
			}
		});
		
		
		//my cart button
		btnMyCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new MyCart();
			}
		});
		
		
		//my info
		btnMyInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new MyInfo();
			}
		});
	}
}
