import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.SystemColor;

public class Wallet {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wallet window = new Wallet();
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
	public Wallet() 
	{
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
		frame.setBounds(100, 100, 548, 353);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My Wallet");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(185, 11, 139, 45);
		frame.getContentPane().add(lblNewLabel);
		
		
		String userName = Login.username_ip;
		JLabel lblNewLabel_1 = new JLabel("User Name : " + userName);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(59, 79, 372, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		//Balance label
		String balance = checkBalance(Login.userId) + "";
		JLabel lblNewLabel_1_1 = new JLabel("Wallet Amount : " + balance);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(57, 136, 374, 36);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Add Amount :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(59, 195, 139, 36);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(218, 195, 123, 36);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(377, 196, 85, 36);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(59, 264, 94, 36);
		frame.getContentPane().add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.desktop);
		panel.setBounds(0, 0, 534, 66);
		frame.getContentPane().add(panel);
		
		
		//back button
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				if(Login.userId.charAt(0)=='c')
				{
					new CustomerTab();
				}
				else
				{
					new RetailerTab();
				}
			}
		});
		
		
		//add balance button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String userId = Login.userId;
				int balance = checkBalance(userId);
				int newBalance = 0;
				
				try 
				{
					int addAmount = Integer.parseInt(textField.getText());
					newBalance = balance + addAmount;
					conn cobj = new conn();
					String query = "update wallet set balance = " + newBalance + " where wallet_Id='"+userId+"';";
						
					try {cobj.st.executeUpdate(query);} 
					catch (SQLException e) {e.printStackTrace();}
						
					lblNewLabel_1_1.setText("Wallet Amount : " + newBalance);
					
					JOptionPane.showMessageDialog(frame, addAmount + " Successfully added","Balance",JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e) 
				{
					JOptionPane.showMessageDialog(frame, "Enter a valid amount !","Balance",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		
	}
	
	
	public static void createWallet(String walletId)
	{
		
		conn cobj = new conn();
		
		String query = "insert into wallet values('" + walletId + "'," + 0 + ");";

		try 
		{
			cobj.st.executeUpdate(query);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
			
	public static int checkBalance(String userId)
	{

		conn cobj = new conn();
				
		String query = "SELECT * FROM Wallet;";
		//String userId = Login.userId;
		int balance = 0;
		try 
		{
			ResultSet rs = cobj.st.executeQuery(query);
					
			while(rs.next()) 
			{
				String Id = rs.getString("Wallet_Id");
				
				if(userId.equalsIgnoreCase(Id))
				{
					balance = rs.getInt("Balance");
					return balance;
				}
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return balance;
	}
	
	
	
	//update balance
	public static void updateBalance(int newBalance)
	{
		conn cobj = new conn();
		
		String userId = Login.userId;
		String query = "update wallet set Balance=" + newBalance + " where Wallet_Id = '" + userId + "';";

		try 
		{
			cobj.st.executeUpdate(query);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	//update retailer wallet
	public static void addToRetailerBalance(String userId, int balance)
	{
		conn cobj = new conn();
		
		int newBalance = balance + checkBalance(userId);
		String query = "update wallet set Balance=" + newBalance + " where Wallet_Id = '" + userId + "';";

		try 
		{
			cobj.st.executeUpdate(query);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
