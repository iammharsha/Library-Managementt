import java.sql.*;
import java.util.*;

public class Librarian extends Library{

	int Librarian_ID;
	String Librarian_Name;
	String Librarian_Username;
	String Librarian_password;
	
	
	
	public boolean searchIssuedBook(Connection conn,Student s)
	{
		int i=0;
		try {
			
			PreparedStatement p=conn.prepareStatement("Select Count(*) from Issued_Book where Student_ID="+s.getStudentID()+" and Issued_Book_ID=" +super.getBookId());
			ResultSet rs=p.executeQuery();
			rs.next();
			i=rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(i==1)
			return true;
		else return false;
		
	}
	
	
	
	public void issueBook(Connection conn)
	{
		
		int i=0;
		Student s=new Student();
		Scanner in=new Scanner(System.in);
		s.setStudentId();
		s.setStudentName();
		System.out.print("Enter Book ID : ");
		int bID=in.nextInt();
		super.setBookId(bID);
		super.setQuantity(-1);
		int b=s.searchStudent(conn);
		if(b>0)
		{
		if( !searchIssuedBook(conn,s))
		{
		
		try {
			
				
					PreparedStatement p=conn.prepareStatement("update Book set Quantity=Quantity -1 where Quantity>0 and Book_Id="+super.getBookId());	
					i=p.executeUpdate();
					if(i==0)
					{
						System.out.println("Book Not Available");
					
					}
					else
					{
						try {
				
							java.util.Date today = new java.util.Date();
							p=conn.prepareStatement("insert into Issued_Book (Student_ID,Student_Name,Issued_Book_ID,Date_Issued)"+"values(?,?,?,?)");
							p.setInt(1, s.getStudentID());
							p.setString(2,s.getStudentName());
							p.setInt(3, super.getBookId());
							p.setDate(4, new java.sql.Date((today.getTime())));
							p.execute();
							System.out.println("Book Issued");
				
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else
			System.out.println("Book Already Issued");
		}
		else
		{
			System.out.println("Student Not in Data Base");
		}
		
	}
	
	
	
	
	public void recieveBook(Connection conn)
	{
		
		int i=0;
		Student s=new Student();
		Scanner in=new Scanner(System.in);
		s.setStudentId();
		s.setStudentName();
		System.out.print("Enter Book ID : ");
		int bID=in.nextInt();
		super.setBookId(bID);
		super.setQuantity(-1);
		int b=s.searchStudent(conn);
		if(b>0)
		{
		if(searchIssuedBook(conn,s))
		{
		try {
			
				
					PreparedStatement p=conn.prepareStatement("update Book set Quantity=Quantity +1 where Book_Id="+super.getBookId());	
					i=p.executeUpdate();
					if(i==0)
					{
						System.out.println("Book Not Found In Data Base");
					}
					else
					{
						try {
				
							p=conn.prepareStatement("delete from Issued_Book where Student_ID="+s.getStudentID()+" and Issued_Book_ID="+super.getBookId());

							
							p.execute();
							System.out.println("Book Received");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else
		{
			System.out.println("Book Was Not Issued To Student");
		}
		}
		else
		{
			System.out.println("Student Not in Data Base");
		}
		
	}
	
}
