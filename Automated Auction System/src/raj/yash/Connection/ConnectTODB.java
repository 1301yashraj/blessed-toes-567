package raj.yash.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectTODB {
    
	public static Connection connect() {
		Connection con = null;
		//load Driver in memory 
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		//Making a Connection with Our Database 
		String url = "jdbc:mysql://localhost:3306/Automated_Auction";
		try
		{
		 con = DriverManager.getConnection(url,"root","root");
		} 
		catch (SQLException e)
		{
		 e.printStackTrace();
		}
			

		return con;
	}
}
