import java.sql.*;
import java.util.*;

public class Login {

	String Username;
	String pass;
	
	public void getCredentials()
	{
		Scanner in=new Scanner(System.in);
		System.out.print("Enter Username:");
		Username=in.next();
		System.out.print("Enter Password:");
		pass=in.next();
	}
	
	public String encrypt()
	{
		String password="";
		for(int i=0;i<pass.length();i++)
		{
			password=password+(char)((int)pass.charAt(i)+1);
		}
		
		return password;
		
	}

	public int librarianLogin(Connection conn)
	{
		int i=0;
		getCredentials();
		try {
			
			PreparedStatement p=conn.prepareStatement("select count(*) from Librarian_Info where Username=\""+ this.Username +"\" and Pass=\""+encrypt()+"\"");
			ResultSet rs=p.executeQuery();
			rs.next();
			i=rs.getInt(1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
		
	}
	
	
	public int studentLogin(Connection conn)
	{
		int i=0;
		getCredentials();
		try {
		
			PreparedStatement p=conn.prepareStatement("select count(*) from Student_Info where Username=\""+Username +"\" and Pass=\""+encrypt()+"\"");
			ResultSet rs=p.executeQuery();
			rs.next();
			i=rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
		
	}
	
}
