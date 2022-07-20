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
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.SystemColor;

public class MyInfo {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyInfo window = new MyInfo();
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
	public MyInfo() 
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
	 * 
	 */
	private void initialize()  {
		frame = new JFrame();
		frame.setBounds(100, 100, 951, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My Info");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(390, 11, 114, 45);
		frame.getContentPane().add(lblNewLabel);
		
		
		String userName = Login.username_ip;
		JLabel lblNewLabel_1 = new JLabel("Name : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(29, 101, 81, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password : ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(521, 101, 105, 36);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Phone : ");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(29, 180, 81, 36);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(61, 354, 94, 36);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Address :");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2_1.setBounds(521, 195, 99, 36);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Pincode : ");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2_2.setBounds(29, 246, 94, 36);
		frame.getContentPane().add(lblNewLabel_1_2_2);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPane.setEditable(false);
		textPane.setBounds(630, 195, 272, 150);
		frame.getContentPane().add(textPane);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setEditable(false);
		textField.setBounds(120, 104, 232, 36);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(630, 104, 232, 36);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(120, 183, 232, 36);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(120, 249, 232, 36);
		frame.getContentPane().add(textField_3);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(444, 84, 27, 306);
		frame.getContentPane().add(separator);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.desktop);
		panel.setBounds(0, 0, 937, 68);
		frame.getContentPane().add(panel);
		
		loadData();
		
		
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
		
	}
	
	private void loadData()
	{
		conn cobj = new conn();
		String query;
		
		if(Login.userId.charAt(0)=='c')
		{
			query = "select * from customer where Customer_Id='" + Login.userId + "';";
		}
		else
		{
			query = "select * from merchant where Merchant_Id='" + Login.userId + "';";
		}
		
		
		try
		{
			ResultSet rs = cobj.st.executeQuery(query);
			rs.next();
			
			String name = rs.getString("username");
			String password = rs.getString("password");
			String phone = rs.getString("phone");
			String address = rs.getString("address");
			String pincode = rs.getString("pincode");
			
			textField.setText(name);
			textField_1.setText(password);
			textField_2.setText(phone);
			textField_3.setText(pincode);
			textPane.setText(address);
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		
	}
		
}
