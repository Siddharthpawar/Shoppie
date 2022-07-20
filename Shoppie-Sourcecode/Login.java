import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingConstants;

public class Login {

	public JFrame frmShoppie;
	private JTextField txtField;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public String userType = "Customer";
	public static Login loginobj;
	public static String username_ip;
	public static String pass_ip;
	public static String userId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmShoppie.setVisible(true);
					
					try {
						UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
						SwingUtilities.updateComponentTreeUI(window.frmShoppie);
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(window.frmShoppie, "LookAndFeel not set", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		loginobj = this;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShoppie = new JFrame();
		frmShoppie.setTitle("Shoppie");
		frmShoppie.getContentPane().setBackground(SystemColor.scrollbar);
		frmShoppie.setBounds(100, 100, 510, 552);
		frmShoppie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShoppie.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("User Name :");
		lblNewLabel_3.setBackground(new Color(230, 230, 250));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(67, 239, 142, 32);
		frmShoppie.getContentPane().add(lblNewLabel_3);
		
		txtField = new JTextField();
		txtField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtField.setBounds(219, 245, 157, 26);
		frmShoppie.getContentPane().add(txtField);
		txtField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Password :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(67, 297, 119, 32);
		frmShoppie.getContentPane().add(lblNewLabel_4);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(219, 302, 157, 28);
		frmShoppie.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(219, 369, 119, 45);
		frmShoppie.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("New User ?");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(106, 435, 103, 40);
		frmShoppie.getContentPane().add(lblNewLabel_5);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Customer");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNewRadioButton.setBounds(219, 183, 111, 23);
		frmShoppie.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Retailer");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNewRadioButton_1.setBounds(354, 183, 111, 23);
		frmShoppie.getContentPane().add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_6 = new JLabel("Iam a :");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setForeground(new Color(0, 0, 0));
		lblNewLabel_6.setBackground(Color.BLACK);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_6.setBounds(67, 176, 87, 32);
		frmShoppie.getContentPane().add(lblNewLabel_6);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(219, 443, 111, 26);
		frmShoppie.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setIcon(new ImageIcon(Login.class.getResource("/icons/shop6.gif")));
		lblNewLabel_13.setBounds(0, 0, 496, 149);
		frmShoppie.getContentPane().add(lblNewLabel_13);
		
		
		
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				userType = "Customer";
			}
		});
		
		
		
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				userType = "Retailer";
			}
		});
		
		
		
		//Login Button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				conn cobj = new conn();
				
				String query;
				
				if(userType.equals("Customer"))
				{
					query = "SELECT * FROM customer;";
				}
				else
				{
					query = "SELECT * FROM merchant;";
				}
				
				System.out.println(userType);
				username_ip = txtField.getText();
				pass_ip = passwordField.getText();
				
				try
				{
					ResultSet rs = cobj.st.executeQuery(query);

					Boolean access = false;
					while(rs.next())
					{
						String uname = rs.getString("username");
						String pass = rs.getString("password");
					
						if(uname.equals(username_ip) && pass.equals(pass_ip))
						{
							access = true;
							break;
						}
					}
					if(access)
					{
						System.out.println("Access Granted!");
						JOptionPane.showMessageDialog(frmShoppie, "Login Successful","Login",JOptionPane.INFORMATION_MESSAGE);
						
						if(userType.equals("Customer"))
						{
							userId = rs.getString("Customer_Id");
							
							frmShoppie.setVisible(false);
							new CustomerTab();
						}
						else
						{
							userId = rs.getString("Merchant_Id");
							
							frmShoppie.setVisible(false);
							new RetailerTab();
						}
						
						
					}
					else
					{
						System.out.println("Access Denied");
						JOptionPane.showMessageDialog(frmShoppie, "Username or Password is incorrect !","Login",JOptionPane.ERROR_MESSAGE);
					}
					
					txtField.setText("");
					passwordField.setText("");
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			
		});
		
		
		
		//Register button
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(userType.equals("Customer"))
				{
					System.out.println("Customer Registration");
					frmShoppie.setVisible(false);
					new CustomerRegister();
					
				}
				else
				{
					System.out.println("Retailer Registration");
					frmShoppie.setVisible(false);
					new RetailerRegister();
				}
			}
		});
		
	}
}
