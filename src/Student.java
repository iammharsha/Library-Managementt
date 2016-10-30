import java.util.*;
import java.sql.*;
import java.sql.Date;


public class Student extends Library{

	int Student_ID;
	String Student_Name;
	String Student_User_Name;
	String Student_Password;
	int Issued_Book_ID;
	Date date;
	
	public void setStudentId()	{
		
		Scanner in=new Scanner(System.in);
		System.out.print("Enter Student ID:");
		Student_ID=in.nextInt();
		
	}
	
	public void setStudentName()	{
		
		Scanner in=new Scanner(System.in);
		System.out.print("Enter Student Name:");
		Student_Name=in.next();
		
	}
	
	public void setIssuedBookID()	{
		
		Scanner in=new Scanner(System.in);
		System.out.print("Enter Book ID:");
		Issued_Book_ID=in.nextInt();
		
	}
	
	public int getStudentID()
	{
		return Student_ID;
	}
	
	public String getStudentName()
	{
		return Student_Name;
	}
	
	public int getIssuedBookID()
	{
		return Issued_Book_ID;
	}
	
	
	public int searchStudent(Connection conn)
	{
		int i=0;
		ResultSet rs=null;
		PreparedStatement p;
		try {
			
			
			p=conn.prepareStatement("select count(*) from Student_Info where Student_ID="+this.getStudentID());
			rs=p.executeQuery();
			rs.next();
			i=rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		
		return i;
	}
	
	
	public void bookSearch(Connection conn)
	{
		
		super.setData();
		super.searchBook(conn);
		super.display(conn);
		
	}
	
}
