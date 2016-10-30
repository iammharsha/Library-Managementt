import java.sql.*;
import java.util.*;

public class LibraryDemo {
	public static void main(String args[]) 
	{
		Connection con=null;
		Connection  stud=null;
		Librarian l=new Librarian();
		Student s=new Student();
		Login log=new Login();
		int login=0;
		Scanner in=new Scanner(System.in);
		int ch=0;
		int c=0;
		try {
			
				
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Library","root","qwerty");
			
			
			do
			{
				if(c==0)
				{
				System.out.println("Enter   1:Librarian Login\n\t2:Student Login\n\t3:Exit\t:");
				ch=in.nextInt();
				}
				switch(ch)
				{
				case 1:
				{
					if((login=log.librarianLogin(con))>0)
					{
					System.out.println("Enter   1:Issue Book\n\t2:Receive Book\n\t3:Add Book\n\t4:Logout\t:");
					c=in.nextInt();
					switch(c)
					{
						case 1:
						{
							l.issueBook(con);
							
							break;
						}
						
						case 2:
						{
							
							l.recieveBook(con);
							break;
						}
						
						case 3:
						{
							l.addBook(con);
							
							break;
						}
						
						case 4:
						{
							c=0;
							break;
						}
						
						default:
							System.out.println("Invalid Input");
					}
				}	
				
				else
				{
					System.out.println("Invalid Credentials");
				}
				}
				break;
				
				case 2:
				{
					if(c!=0||(login=log.studentLogin(con))>0)
					{
					System.out.println("Enter   1:Search Book\n\t2:Logout\t:");
					c=in.nextInt();
					switch(c)
					{
					case 1:
					s.bookSearch(con);
					break;
					case 2:
						c=0;
						break;
						default:
							System.out.println("Invalid Input");
					}
					}
					else
					{
						System.out.println("Invalid Credentials");
					}
				}
				break;
				
				case 3:
					break;
					
					default:
						System.out.println("Invalid Input");
				
				}
				
					
			}while(ch!=3);

			
			
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
