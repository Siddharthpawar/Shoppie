import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.lang.Exception;
import javax.swing.JPanel;
import java.awt.SystemColor;

public class Shop {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shop window = new Shop();
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
	public Shop() 
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
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.setBounds(100, 100, 788, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(35, 85, 94, 35);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(132, 89, 412, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(575, 89, 89, 31);
		frame.getContentPane().add(btnNewButton);
		
		table = new JTable();
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"Product_Id", "Name", "Price", "Rating", "Quantity"
				}
			);
		table.setModel(model);
		table.getColumnModel().getColumn(1).setPreferredWidth(189);
		table.getColumnModel().getColumn(2).setPreferredWidth(67);
		table.getColumnModel().getColumn(3).setPreferredWidth(49);
		table.getColumnModel().getColumn(4).setPreferredWidth(56);
		//table.setBounds(35, 94, 693, 353);
		//frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 3, true));
		scrollPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		scrollPane.setBounds(35, 141, 695, 386);
		
		JTableHeader Theader = table.getTableHeader();
		Theader.setBackground(Color.GRAY);
		Theader.setForeground(Color.BLACK);
		Theader.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(scrollPane);

		
		JButton btnNewButton_1 = new JButton("Buy");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(641, 551, 89, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Quantity :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(202, 552, 100, 35);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setBounds(300, 554, 55, 31);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2.setBounds(35, 552, 100, 35);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("Add To Cart");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_1_1.setBounds(479, 552, 135, 35);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JLabel lblShop = new JLabel("Shop");
		lblShop.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblShop.setBounds(337, 11, 94, 35);
		frame.getContentPane().add(lblShop);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.desktop);
		panel.setBounds(0, 0, 774, 63);
		frame.getContentPane().add(panel);
		
		
		//Search button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int numRows = model.getRowCount();
				for(int i=numRows-1; i>=0; i--)
				{
					model.removeRow(i);
				}
				
				String search = textField.getText().toLowerCase();
				String[] searchTags = search.split(" ");
				
				conn cobj = new conn();
				
				String query = "SELECT * FROM products;";
				try 
				{
					ResultSet rs = cobj.st.executeQuery(query);
					
					while(rs.next())
					{
						String products = rs.getString("Tag").toLowerCase();
						String[] productTags = products.split("/");
						
						int flag = 0;
						for(String s1: searchTags)
						{
							for(String s2: productTags)
							{
								if(s1.equals(s2))
								{
									String prod_Id = rs.getString("Product_Id");
									String prod_Name = rs.getString("Name");
									String prod_Price = rs.getString("Price");
									String prod_Rating = rs.getString("Rating");
									String prod_Quantity = rs.getString("Quantity");
									
									Object[] newRow = {prod_Id, prod_Name, prod_Price, prod_Rating, prod_Quantity};
									model.addRow(newRow);
									
									flag = 1;break;
								}
							}
							
							if(flag == 1) break;
						}
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}

			}
		});
		
		
		
		
		//Buy button
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				int orderQuantity=0;
				int prod_Price=0;
				String prod_Id=null;
				String prod_Name=null;
				String prod_Pricestr=null;
				int prod_Quantity=0;
				
				try
				{
					try
					{
						prod_Id = (String) model.getValueAt(table.getSelectedRow(), 0);
						prod_Name = (String) model.getValueAt(table.getSelectedRow(), 1);
						prod_Pricestr = (String) model.getValueAt(table.getSelectedRow(), 2);
						prod_Quantity = Integer.parseInt((String) model.getValueAt(table.getSelectedRow(), 4));
						prod_Price = Integer.parseInt(prod_Pricestr);
					}
					catch(Exception e)
					{
						throw new MyException.ProductNotSelected("");
					}

					
					try{orderQuantity = Integer.parseInt(textField_1.getText());}
					catch(Exception e) {throw new MyException.Invalid_tf_shop("");}
					
					if(orderQuantity==0) {throw new MyException.Invalid_tf_shop("");}
					
					int totalPrice = prod_Price*orderQuantity;
					int currBalance = Wallet.checkBalance(Login.userId);
					
					//successful purchase
					if((prod_Quantity>=orderQuantity) && (currBalance >= totalPrice))
					{
						Wallet.updateBalance(currBalance - totalPrice);
						Wallet.addToRetailerBalance(getRetailerId(prod_Id), totalPrice);
						updateQuantity(prod_Id, prod_Quantity - orderQuantity);
						addToOrders(Login.userId, prod_Id, orderQuantity);
						JOptionPane.showMessageDialog(frame, prod_Name + " ("+orderQuantity+") Successfully purchased","Purchase",JOptionPane.INFORMATION_MESSAGE);
						btnNewButton.doClick();	//refreshing content
						
					}
					else if(currBalance < totalPrice)
					{
						JOptionPane.showMessageDialog(frame, "Insufficient funds in wallet !\nWallet balance : "+currBalance, "Purchase",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Products unavailable currently !", "Purchase" ,JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				catch(MyException.Invalid_tf_shop e1)
				{
					JOptionPane.showMessageDialog(frame, "Enter valid Quantity !","Purchase",JOptionPane.INFORMATION_MESSAGE);
				}
				catch(MyException.ProductNotSelected e2)
				{
					JOptionPane.showMessageDialog(frame, "First select a product to buy !", "Purchase" ,JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		
		
		//back button
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new CustomerTab();
			}
		});
		
		
		//add to cart button
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String prodId = (String) model.getValueAt(table.getSelectedRow(), 0);
				String prodName = (String) model.getValueAt(table.getSelectedRow(), 1);
				
				conn cobj = new conn();
				
				String query1 = "SELECT products FROM cart where Customer_Id='" + Login.userId + "';";
				
				try
				{
					ResultSet rs = cobj.st.executeQuery(query1);
					rs.next();
					
					String prodStr = rs.getString("products");
					List<String> products = new LinkedList(Arrays.asList(prodStr.split(",")));
					
					products.add(prodId);
					String newProdStr = String.join(",", products);
					
					String query2 = "update cart set products='" +  newProdStr +"' where Customer_Id='" + Login.userId + "';";
					
					cobj.st.executeUpdate(query2);
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					String query3 = "insert into cart values('" + Login.userId + "','" + prodId + "');";
					
					try{cobj.st.executeUpdate(query3);}
					catch(SQLException se) {se.printStackTrace();}
				}
				
				JOptionPane.showMessageDialog(frame, prodName + " added to cart !","Add to Cart",JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	
	public static void updateQuantity(String prod_Id, int newQuantity)
	{
		
		conn cobj = new conn();
		String query;

		query = "update products set quantity=" + newQuantity +" where Product_Id='" + prod_Id + "';";

		try 
		{
			cobj.st.executeUpdate(query);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public static void addToOrders(String custId, String prodId, int quantity)
	{
		try
		{
			conn cobj = new conn();
			String query1, query2;
		
			query1 = "select count(*) as count from orders;";
			ResultSet rs ;
		
			rs = cobj.st.executeQuery(query1);
			rs.next();
			int cnum = rs.getInt("count") + 1;
			//String order_Id = "o" + Integer.toString(cnum);
			int order_Id = cnum;

			query2 = "insert into orders values(" + order_Id + ",'" + custId + "','" + prodId + "'," + quantity + ");";

			cobj.st.executeUpdate(query2);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public static String getRetailerId(String prodId)
	{
		String RetailerId = "";
		try
		{
			conn cobj = new conn();
			
			String query = "select merchant_Id from products where Product_Id='" + prodId + "';";
			
			ResultSet rs = cobj.st.executeQuery(query);
			rs.next();
			
			RetailerId = rs.getString("merchant_Id");
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return RetailerId;
		
	}
}
