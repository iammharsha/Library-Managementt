import java.sql.*;
import java.util.*;

public class Librarian extends Library{

	int Librarian_ID;
	String Librarian_Name;
	String Librarian_Username;
	String Librarian_password;
	
	public void issueBook(Connection conn)
	{
		
		int i=0;
		Library l=new Library();
		Student s=new Student();
		Scanner in=new Scanner(System.in);
		s.setStudentId();
		s.setStudentName();
		System.out.print("Enter Book ID : ");
		int bID=in.nextInt();
		l.setBookId(bID);
		l.setQuantity(-1);
		int b=s.searchStudent(conn);
		System.out.println(b);
		if(b>0)
		{
		try {
			
				
					PreparedStatement p=conn.prepareStatement("update Book set Quantity=Quantity -1 where Quantity>0 and Book_Id="+l.getBookId());	
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
							p.setInt(3, l.getBookId());
							p.setDate(4, new java.sql.Date((today.getTime())));
							p.execute();
				
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
			System.out.println("Student Not in Data Base");
		}
		
	}
	
}
