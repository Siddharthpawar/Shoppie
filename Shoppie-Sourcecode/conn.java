import java.sql.*;
import java.util.Scanner;
public class conn 
{
	public Connection c;
	public Statement st;
	
	public conn()
	{
		String url = "jdbc:mysql://localhost:3306/shoppie_db";
		String userName = "root";
		String db_pass = "project";
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(url, userName, db_pass);
			st = c.createStatement();
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}

}
