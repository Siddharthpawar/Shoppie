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

public class MyProducts {

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
					MyProducts window = new MyProducts();
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
	public MyProducts() {
		
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
		panel.setBackground(SystemColor.desktop);
		panel.setBounds(0, 0, 888, 55);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("My Products");
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
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 3, true));
		scrollPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		scrollPane.setBounds(28, 82, 832, 364);
		
		JTableHeader Theader = table.getTableHeader();
		Theader.setBackground(Color.GRAY);
		Theader.setForeground(Color.BLACK);
		Theader.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(scrollPane);
		
		loadProductsTable();
		// Table end
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(51, 468, 95, 31);
		frame.getContentPane().add(btnNewButton);
		
		
		//Back button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				new RetailerTab();
			}
		});
		
	}

	
	private void loadProductsTable()
	{
		conn cobj = new conn();
		
		String query = "SELECT * FROM products where Merchant_Id='" + Login.userId + "';";
		try 
		{
			ResultSet rs = cobj.st.executeQuery(query);
			
			int index = 1;
			while(rs.next())
			{
				String prodId = rs.getString("Product_Id");
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
			e.printStackTrace();
		}
	}


}
