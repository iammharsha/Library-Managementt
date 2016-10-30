import java.sql.*;
import java.util.*;

public class Library {
	int bookId;
	String bookName;
	String authorName;
	String publisherName;
	int quantity;
	
	public int getBookId()
	{
		return bookId;
	}
	public void setBookId(int bId)
	{
		this.bookId=bId;
	}
	
	public String getBookName()
	{
		return this.bookName;
	}
	public void setBookName(String bName)
	{
		this.bookName=bName;
	}
	
	public String getAuthorName()
	{
		return this.authorName;
	}
	public void setAuthorName(String authName)
	{
		this.authorName=authName;
	}
	
	public String getPublisherName()
	{
		return this.publisherName;
	}
	public void setPublisherName(String pubName)
	{
		this.publisherName=pubName;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int qty)
	{
		this.quantity=qty;
	}
	
	
	
	
	public void setData()
	{
		Scanner in=new Scanner(System.in);
		System.out.print("Enter Book Id:");
		this.setBookId(in.nextInt());
		System.out.print("Enter Book Name:");
		this.setBookName(in.next());
		System.out.print("Enter Author Name:");
		this.setAuthorName(in.next());
		System.out.print("Enter Publisher Name:");
		this.setPublisherName(in.next());
		
	}
	
	
	
	
	public ResultSet searchBook(Connection conn)
	{
		ResultSet rs = null;
		try {
			
			PreparedStatement stat=conn.prepareStatement("select * from Book where Book_Id=? or Book_Name=? or Author_Name=? or Publisher_Name=?");
			stat.setInt(1, this.getBookId());
			stat.setString(2,this.getBookName());
			stat.setString(3, this.getAuthorName());
			stat.setString(4, this.getPublisherName());
			rs=stat.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	
	
	
	
	public int updateDB(Connection conn,int k,ResultSet rs){
		int i=0;
		try {
			PreparedStatement p=conn.prepareStatement("update Book SET Quantity=? where Book_Id=?");
			
			while(rs.next())
			if(rs.getInt("Book_Id")==this.bookId)
			{	
				p.setInt(1, rs.getInt("Quantity")+this.quantity);
				i=1;
				break;
			}
			if(i==0)
				p.setInt(1, this.quantity);
			p.setInt(2, this.getBookId());
			
			i=0;
			
			i=p.executeUpdate();
			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	
	
	
	
	public void addBook(Connection conn)
	{
		
		this.setData();
		Scanner kin=new Scanner(System.in);
		System.out.print("Enter Quantity:");
		this.setQuantity(kin.nextInt());
		ResultSet rs=this.searchBook(conn);
		int i=this.updateDB(conn,1,rs);
		if (i<=0)
		this.writeToDb(conn);
		
	}
	
	
	
	
	public void writeToDb(Connection conn)
	{
		try {
			
			PreparedStatement p=conn.prepareStatement("insert into Book (Book_Id,Book_Name,Author_Name,Publisher_Name,Quantity)"+"values(?,?,?,?,?)");
			
			p.setInt(1,this.getBookId());
			p.setString(2,this.getBookName());
			p.setString(3,this.getAuthorName());
			p.setString(4,this.getPublisherName());
			p.setInt(5,this.getQuantity());
			p.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void display(Connection conn)
	{
		
		ResultSet rs = null;
		this.setData();
		rs=this.searchBook(conn);
		try {
			if(rs==null)
				System.out.println("No Book in Library");
			while(rs.next())
			{
				Library lib= new Library();
				lib.bookId=rs.getInt("Book_Id");
				lib.bookName=rs.getString("Book_Name");
				lib.authorName=rs.getString("Author_Name");
				lib.publisherName=rs.getString("Publisher_Name");
				lib.quantity=rs.getInt("Quantity");
				
				System.out.println(lib.toString());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	@Override
	public String toString()
	{
		return String.format("Book Id:%d\tBook Name:%s\tAuthor Name:%s\tPublisher Name:%s\tQuantity:%d\n",this.bookId,this.bookName,this.authorName,this.publisherName,this.quantity);
		
	}
}
