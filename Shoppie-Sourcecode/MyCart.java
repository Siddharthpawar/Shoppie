import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class MyCart {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyCart window = new MyCart();
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
	public MyCart() {
		
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
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 902, 566);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 888, 55);
		panel.setBackground(SystemColor.desktop);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("My Cart");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		//Table start
		table = new JTable();
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		model = new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"SNo.", "Product Id", "Product Name", "Price", "Rating", "Quantity"
				}
			); 
				
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(4);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		//table.setBounds(28, 77, 832, 364);
		//frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(28, 77, 832, 364);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 3, true));
		scrollPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JTableHeader Theader = table.getTableHeader();
		Theader.setBackground(Color.GRAY);
		Theader.setForeground(Color.BLACK);
		Theader.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(scrollPane);
		
		loadCartTable();
		// Table end
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(64, 474, 95, 31);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBuy.setBounds(741, 474, 95, 31);
		frame.getContentPane().add(btnBuy);
		
		JLabel lblNewLabel_1 = new JLabel("Quantity :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(373, 473, 95, 33);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(478, 474, 76, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		//Back button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new CustomerTab();
			}
		});
		
		
		//Buy button
		btnBuy.addActionListener(new ActionListener() {
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
						prod_Id = (String) model.getValueAt(table.getSelectedRow(), 1);
						prod_Name = (String) model.getValueAt(table.getSelectedRow(), 2);
						prod_Pricestr = (String) model.getValueAt(table.getSelectedRow(), 3);
						prod_Quantity = Integer.parseInt((String) model.getValueAt(table.getSelectedRow(), 5));
						prod_Price = Integer.parseInt(prod_Pricestr);
					}
					catch(Exception e)
					{
						throw new MyException.ProductNotSelected("");
					}

					
					try{orderQuantity = Integer.parseInt(textField.getText());}
					catch(Exception e) {throw new MyException.Invalid_tf_shop("");}
					
					if(orderQuantity==0) {throw new MyException.Invalid_tf_shop("");}
					
					int totalPrice = prod_Price*orderQuantity;
					int currBalance = Wallet.checkBalance(Login.userId);
					
					//successful purchase
					if((prod_Quantity>=orderQuantity) && (currBalance >= totalPrice))
					{
						Wallet.updateBalance(currBalance - totalPrice);
						Wallet.addToRetailerBalance(Shop.getRetailerId(prod_Id), totalPrice);
						Shop.updateQuantity(prod_Id, prod_Quantity - orderQuantity);
						Shop.addToOrders(Login.userId, prod_Id, orderQuantity);
						removeFromCart(prod_Id);
						JOptionPane.showMessageDialog(frame, prod_Name + " ("+orderQuantity+") Successfully purchased","Purchase",JOptionPane.INFORMATION_MESSAGE);
						
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
		
	}

	
	private void loadCartTable()
	{
		conn cobj = new conn();
		
		String query1 = "SELECT products FROM cart where Customer_Id='" + Login.userId + "';";
		
		String prodstr;
		String[] products;
		try 
		{
			ResultSet rs = cobj.st.executeQuery(query1);
			rs.next();
			prodstr = rs.getString("products");
			products = prodstr.split(",");

			int index = 1;
			
			for(String prodId : products)
			{
				String query2 = "select name,price,rating,quantity from products where Product_Id='" + prodId +"';";
				rs = cobj.st.executeQuery(query2);
				rs.next();
				
				String prodName = rs.getString("Name");
				String prodPrice = rs.getString("Price");
				String prodRating = rs.getString("Rating");
				String prodQuantity = rs.getString("Quantity");
				
				Object[] newRow = {index, prodId, prodName, prodPrice, prodRating, prodQuantity};
				model.addRow(newRow);
				
				index++;
			}
			
		}
		catch (SQLException e) 
		{
			//empty cart
			e.printStackTrace();
		}
	}
	
	
	private void removeFromCart(String prodId)
	{
		conn cobj = new conn();
		
		String query1 = "SELECT products FROM cart where Customer_Id='" + Login.userId + "';";
		
		try
		{
			ResultSet rs = cobj.st.executeQuery(query1);
			rs.next();
			String prodStr = rs.getString("products");
			List<String> products = new LinkedList(Arrays.asList(prodStr.split(",")));
			
			products.remove(prodId);
			String newProdStr = String.join(",", products);
			
			String query2 = "update cart set products='" +  newProdStr +"' where Customer_Id='" + Login.userId + "';";
			
			cobj.st.executeUpdate(query2);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		model.removeRow(table.getSelectedRow());
		
	}
	
}
