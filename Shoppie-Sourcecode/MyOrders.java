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
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class MyOrders {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyOrders window = new MyOrders();
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
	public MyOrders() {
		
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
		frame.getContentPane().setBackground(SystemColor.scrollbar);
		frame.setBounds(100, 100, 902, 566);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.desktop);
		panel.setBounds(0, 0, 888, 55);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("My Orders");
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
					"SNo.", "Order Number", "Product Id", "Product Name", "Price", "Quantity"
				}
			); 
				
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(2);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		
		table.getColumnModel().getColumn(3).setPreferredWidth(237);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(109);
		//table.setBounds(28, 77, 832, 364);
		//frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 3, true));
		scrollPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		scrollPane.setBounds(28, 77, 832, 364);
		
		JTableHeader Theader = table.getTableHeader();
		Theader.setBackground(Color.GRAY);
		Theader.setForeground(Color.BLACK);
		Theader.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(scrollPane);
		
		loadOrderTable();
		// Table end
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(64, 474, 95, 31);
		frame.getContentPane().add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBox.setBounds(587, 470, 50, 37);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Rating :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(495, 474, 95, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSubmit.setBounds(680, 474, 116, 31);
		frame.getContentPane().add(btnSubmit);
		
		
		//Back button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				CustomerTab.frame.setVisible(true);
			}
		});
		
		
		
		//submit button
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int orderId;
				String prodId = "";
				int ratingIp = 2;
				
				try
				{
					try
					{
						orderId = Integer.parseInt((String) model.getValueAt(table.getSelectedRow(), 1));
						prodId = (String) model.getValueAt(table.getSelectedRow(), 2);
						ratingIp = Integer.parseInt(comboBox.getSelectedItem().toString());
					}
					catch(Exception e)
					{
						e.printStackTrace();
						throw new MyException.ProductNotSelected("");
					}
					
					try 
					{
						updateProductRating(prodId, ratingIp);
						updateRetailerRating(Shop.getRetailerId(prodId), ratingIp);
						
						model.removeRow(table.getSelectedRow());
						
						deleteOrder(orderId);
						JOptionPane.showMessageDialog(frame, "Thanks you for your feedback !", "Rating" ,JOptionPane.INFORMATION_MESSAGE);
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
				catch(MyException.ProductNotSelected e2)
				{
					JOptionPane.showMessageDialog(frame, "First select a product !", "Rating" ,JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
	}
	
	
	private void loadOrderTable()
	{
		conn cobj = new conn();
		
		String query = "SELECT * FROM orders where Customer_Id='" + Login.userId + "' order by Order_Id desc;";
		try 
		{
			ResultSet rs = cobj.st.executeQuery(query);
			
			int index = 1;
			while(rs.next())
			{
				String orderId = rs.getString("Order_Id");
				String prodId = rs.getString("Product_Id");
				String prodQuantity = rs.getString("Quantity");
				String prodName = getProdName(prodId);
				String prodPrice = getProdPrice(prodId);
				
				Object[] newRow = {index, orderId, prodId, prodName, prodPrice, prodQuantity};
				model.addRow(newRow);
				
				index++;
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	
	
	private String getProdPrice(String prodId) throws SQLException {
		
		conn cobj = new conn();
		
		String query = "SELECT price from products where Product_Id='" + prodId + "';";
		
		ResultSet rs = cobj.st.executeQuery(query);
		rs.next();
		
		return rs.getString("price");
	}

	
	
	private String getProdName(String prodId) throws SQLException 
	{
		
		conn cobj = new conn();
		
		String query = "SELECT name from products where Product_Id='" + prodId + "';";
		
		ResultSet rs = cobj.st.executeQuery(query);
		rs.next();
		
		return rs.getString("name");
	}
	
	
	public void updateProductRating(String prodId, int ratingIp) throws SQLException
	{
		conn cobj = new conn();
		
		String query1 = "SELECT rating, reviews FROM products where Product_Id='" + prodId + "';";
		
		ResultSet rs = cobj.st.executeQuery(query1);
		rs.next();
		
		double rating = rs.getDouble("rating");
		int reviews = rs.getInt("reviews");
		
		double newRating;
		if(reviews==0) {newRating = ratingIp;}
		else {newRating = ((reviews*rating)+ratingIp)/(reviews+1);}
		
		int newReviews = reviews + 1;
		
		String query2 = "update products set rating=" + newRating + ", reviews=" + newReviews + " where product_Id='" + prodId + "';";
		
		cobj.st.executeUpdate(query2);
		
	}
	
	
	
	public void updateRetailerRating(String merchantId, int ratingIp) throws SQLException
	{
		conn cobj = new conn();
		
		String query1 = "SELECT rating, reviews FROM merchant where Merchant_Id='" + merchantId + "';";
		
		ResultSet rs = cobj.st.executeQuery(query1);
		rs.next();
		
		double rating = rs.getDouble("rating");
		int reviews = rs.getInt("reviews");
		
		double newRating;
		if(reviews==0) {newRating = ratingIp;}
		else {newRating = ((reviews*rating)+ratingIp)/(reviews+1);}
		
		int newReviews = reviews + 1;
		
		String query2 = "update merchant set rating=" + newRating + ", reviews=" + newReviews + " where merchant_Id='" + merchantId + "';";
		
		cobj.st.executeUpdate(query2);
		
	}
	
	public void deleteOrder(int orderId) throws SQLException
	{
		conn cobj = new conn();
		
		String query = "DELETE FROM orders WHERE (`Order_Id` = '" + orderId + "');";
		
		cobj.st.executeUpdate(query);
	}
}
