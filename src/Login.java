import java.sql.*;


public class Login {

	public String encrypt(String pass)
	{
		String password="";
		for(int i=0;i<pass.length();i++)
		{
			password=password+(char)((int)pass.charAt(i)+1);
		}
		
		return password;
		
	}

	public int librarianLogin(String Username,String password,Connection conn)
	{
		int i=0;
		
		try {
		
			PreparedStatement p=conn.prepareStatement("select * from Librarian_Info where Username="+Username +" and password="+encrypt(password));
			i=p.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
		
	}
	
	
	public int studentLogin(String Username,String password,Connection conn)
	{
		int i=0;
		
		try {
		
			PreparedStatement p=conn.prepareStatement("select * from Student_Info where Username="+Username +" and password="+encrypt(password));
			i=p.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
		
	}
	
}
