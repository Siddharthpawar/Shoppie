import java.sql.*;
import java.util.Scanner;

public class Test_Login
{
	Test_Login() throws SQLException
	{
		conn cobj = new conn();
		
		String query1 = "select UserName from customer where customer_Id = \"c1\";";
		String query2 = "insert into customer values(\"c45\", \"suhail\", \"suha\");";
		String query3 = "SELECT * FROM customer;";
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Username : ");
		String uname_ip = sc.nextLine();
		System.out.print("Enter Password : ");
		String pass_ip = sc.nextLine();

		ResultSet rs = cobj.st.executeQuery(query3);

		Boolean access = false;
		while(rs.next())
		{
			String uname = rs.getString("username");
			String pass = rs.getString("password");
			if(uname.equals(uname_ip) && pass.equals(pass_ip))
			{
				access = true;
				break;
			}
			
		}

		if(access)
		{
			System.out.println("Access Granted!");
		}
		else
		{
			System.out.println("Access Denied");
		}
	}
	
	public static void main(String[] args) throws SQLException
	{
		new Test_Login();
	}
}


