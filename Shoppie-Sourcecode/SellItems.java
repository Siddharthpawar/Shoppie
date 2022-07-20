import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JPanel;

public class SellItems {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellItems window = new SellItems();
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
	public SellItems() {
		
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
		frame.setBounds(100, 100, 955, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sell Your Products");
		lblNewLabel.setBounds(368, 11, 243, 42);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_1.setBounds(26, 121, 161, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Product Tags :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(26, 198, 161, 31);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Product Price :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_1_2.setBounds(587, 121, 150, 31);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Product Quantity :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_1_3.setBounds(587, 217, 187, 31);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(191, 122, 302, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_1.setColumns(10);
		textField_1.setBounds(747, 122, 135, 31);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_2.setColumns(10);
		textField_2.setBounds(191, 199, 214, 31);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_3.setColumns(10);
		textField_3.setBounds(786, 218, 96, 31);
		frame.getContentPane().add(textField_3);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(426, 199, 67, 31);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setBounds(60, 413, 108, 42);
		frame.getContentPane().add(btnBack);
		
		JButton btnListMyProduct = new JButton("List my Product");
		btnListMyProduct.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnListMyProduct.setBounds(657, 413, 208, 42);
		frame.getContentPane().add(btnListMyProduct);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(542, 103, 35, 260);
		frame.getContentPane().add(separator);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.ITALIC, 15));
		textField_4.setBackground(SystemColor.inactiveCaption);
		textField_4.setEditable(false);
		textField_4.setBounds(26, 255, 467, 31);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnClear.setBounds(229, 297, 82, 29);
		frame.getContentPane().add(btnClear);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.desktop);
		panel.setBounds(0, 1, 941, 73);
		frame.getContentPane().add(panel);
		
		
		
		//back button
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new RetailerTab();
			}
		});
		
		
		//add tag button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String newTag = textField_2.getText();
				String Tags = textField_4.getText();
				if(newTag.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Add a valid tag !", "Sell" ,JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					if(Tags.equals("")) {textField_4.setText(newTag);}
					
					else {textField_4.setText(Tags + "/" + newTag);}
				}
				
				textField_2.setText("");
			}
		});
		
		
		
		//list my product button
		btnListMyProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try 
				{
					String prodName = textField.getText();
					int prodPrice = Integer.parseInt(textField_1.getText());
					String prodTags = textField_4.getText();
					int prodQuantity = Integer.parseInt(textField_3.getText());
					
					addToProducts(prodName, prodPrice, prodTags, prodQuantity);
					
					JOptionPane.showMessageDialog(frame, prodName + " ("+prodQuantity+") successfully listed","Sell",JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame, "Invalid Text Fields !", "Sell" ,JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		
		
		//clear button
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textField_4.setText("");
			}
		});
		
		
	}
	
	
	
	public void addToProducts(String prodName, int prodPrice, String prodTags, int prodQuantity)
	{
		try
		{
			conn cobj = new conn();
			String query1, query2;
		
			query1 = "select count(*) as count from products;";
			ResultSet rs ;
		
			rs = cobj.st.executeQuery(query1);
			rs.next();
			int count = rs.getInt("count") + 1;
			String prodId = "p" + Integer.toString(count);
			
			double initialRating = 0;
			int reviews = 0;
			String retailerId = Login.userId;

			query2 = "insert into products values('" + prodId + "','" + prodName + "','" + prodTags + "'," + prodPrice 
					+ "," + initialRating + "," + prodQuantity + ",'" + retailerId + "',"+ reviews +");";

			cobj.st.executeUpdate(query2);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
