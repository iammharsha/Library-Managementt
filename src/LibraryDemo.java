import java.sql.*;

public class LibraryDemo {
	public static void main(String args[]) 
	{
		Connection con=null;
		Connection  stud=null;
		Librarian l=new Librarian();
		try {
			
				
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Library","root","qwerty");
			
			l.issueBook(con);
			
			
			
		    con.close();
		    
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
}
