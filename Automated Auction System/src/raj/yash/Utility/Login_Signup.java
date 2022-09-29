package raj.yash.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_Signup {
   String username = "";
   String password = "";
   
   public static String signup(String tableName, String username,String password) {
	   String message =  "Unable to Register user";
	try(Connection con = ConnectTODB.connect())// we got the connection 
	{
		// now insert into our table 
		PreparedStatement ps = con.prepareStatement(
				"insert into "+tableName+" values (?,?)");
		
		ps.setString(1, username);
		ps.setString(2, password);
		
		int x = ps.executeUpdate();
		if(x>0) {
			message ="You Have been Registerd now you can login ";
		}
	}
	catch(SQLException e)
	{
		message = e.getMessage();
	}
	   return message;   
   }

public static boolean login(String tableName,String username,String password) {
	boolean logSuccess = false;
	 try(Connection con = ConnectTODB.connect()){
		 PreparedStatement ps = con.prepareStatement("Select * from "
		 		+ ""+tableName+" where username = ? AND password = ? ");
		 ps.setString(1, username);
		 ps.setString(2, password);
		 ResultSet rs = ps.executeQuery();
		 
		 if(rs.next()) {
			 logSuccess = true;
		 }
	 
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return logSuccess;
}


}
