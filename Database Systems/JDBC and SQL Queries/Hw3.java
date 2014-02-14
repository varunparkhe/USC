import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

public class Hw3 {
	
	Connection con = null;	
	String[] connectionparams = new String[5];
	
	public static void main(String[] args) {
		Hw3 obj = new Hw3();
		if( args!= null && args.length > 2)
		{
			if(obj.FileExists(args[0]))//check FileExists()
			{
				if(obj.ParseDBParams(args[0]))
				{
					if(obj.CreateConnection())
					{	
						obj.ExecuteQuery(args);
			
						obj.CloseConnection();
					}
				}
			}
		}
		
	}
	void ExecuteFirstquery(String[] args)
	{
		if(args.length == 4)
		{
			try
			{
			String strDate = args[2]+ "-"+args[3];
			String Query = "select real_name, tag , nationality from players" +
					" where year(birthday) = ? AND month(birthday)=? ";
		    PreparedStatement querystatement = con.prepareStatement(Query); 
		    querystatement.setString(1, args[2]);
		    querystatement.setString(2, args[3]);
		    ResultSet rs = querystatement.executeQuery();
		    int i = 0;
		    while(rs.next())
		    {
		    	if(i==0)
				{
					System.out.println("+----------------------------+----------------------------+------------------------------------------------+");
		    		System.out.printf("|%-30s|%-30s|%-30s|\n","real_name","tag","nationality");
				}
		    	//System.out.println("| "+ rs.getString(1)+ " | "+ rs.getString(2) + " | " + rs.getString(3) + "|");
		    	System.out.printf("|%-30s|%-30s|%-30s|\n", rs.getString(1),rs.getString(2),rs.getString(3));
				i++;
		    }
		    
		    if( i == 0)
		    {
		    	System.out.println(" Empty set :  There are no players born on"+ strDate);
		    }
			else
				System.out.println("+----------------------------+----------------------------+------------------------------------------------+");
		    
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				System.out.println("Error!!! Exception handled. check whether the inputs are valid");
			}
		}
		else
		{
			System.out.println("Error !! Incorrect number of input to query q1");
		}
	}
	void ExecuteSecondQuery(String[] args)
	{
		if(args.length == 4)
		{
			try
			{
			
			String Query = " select * from members where player=? and team=? and end_date is null";
		    PreparedStatement querystatement = con.prepareStatement(Query); 
		    querystatement.setString(1,args[2]);
		    querystatement.setString(2,args[3]);
		    Date todaydate= new java.util.Date();
		    java.sql.Date date = new java.sql.Date(todaydate.getTime());
			ResultSet rs = querystatement.executeQuery();
		    if(rs.next())
		    {
		    	System.out.println("Player already part of the team");
//		    	String UpdateQuery = "update members set player = ? AND team_id=? and start_date=? and end_date=null";		    	
//		        PreparedStatement updatestatement = con.prepareStatement(UpdateQuery);
//		        updatestatement.setString(1,args[2]);
//		        updatestatement.setString(2, args[3]);
//		        updatestatement.setDate(3, date);
//		        updatestatement.executeUpdate();
//		        System.out.println();
		    }
		    else
		    {
		    	String strQuery = "select team from members where player=? and end_date is not null";
		    	PreparedStatement querystatement1 = con.prepareStatement(strQuery);
		    	
		    	querystatement1.setString(1, args[2]);
		    	ResultSet rs1 = querystatement1.executeQuery();
		    	if(rs1.next())
		    	{
		    		// The username exists in user table so no foreign key violation
		    		String strInsertQuery = "update members set end_date=?";
		    		PreparedStatement insertstatement= con.prepareStatement(strInsertQuery);
		    		insertstatement.setDate(1, date);
		    		insertstatement.executeUpdate();
		    		System.out.println("Successfully remvoved player from old team ");
		    	}
		    	else
		    	{
		    		String strInsertQuery = "insert into members values(?,?,?,?)";
		    		PreparedStatement insertstatement= con.prepareStatement(strInsertQuery);
		    		insertstatement.setString(1, args[2]);
		    		insertstatement.setString(2, args[3]);
		    		insertstatement.setDate(3, date);
		    		insertstatement.setString(4, null);
		    		insertstatement.executeUpdate();
		    
		    	}
		    } 
		    
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				System.out.println("Error!!! Exception handled. check whether the inputs are valid");
			}
		}
		else
		{
			System.out.println("Error !! Incorrect number of input to query q2");
		}
		
	}
	java.sql.Date getdate(String strDate)
	{
		strDate = strDate.replace("\"", "");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		java.sql.Date ret_date = null;;
		try
		{
		ret_date = new java.sql.Date(formatter.parse(strDate).getTime());
		}
		catch(Exception ex)
		{
			System.out.println("Exception handled : GetInsertableDate()");
		}
		return ret_date;
	}
	void ExecuteQuery(String[] args)
	{
		if(args[1].equals("q1"))
		{
				ExecuteFirstquery(args);
		}
		else if (args[1].equals("q2"))
		{
				ExecuteSecondQuery(args);
		}
		
		else
		{
			System.out.println("Error!! the query "+args[1]+ "not found. Please enter queries between q1 and q3");
		}
	}
	boolean FileExists(String strFile)
	{
		boolean bReturn = false; 
		try
		{
			File strFile1 = new File(strFile);
			if(!strFile1.exists())
			{
				System.out.println("Error!!! File "+ strFile + " does not exist");
				return bReturn;
			}
			bReturn = true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Exception handled : Validate()");
		}
		return bReturn;
	}
	boolean ParseDBParams(String strFile)
	{
		boolean bReturn = false;
		try
		{		
			FileInputStream fstream = new FileInputStream(strFile);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strtemp;
		    int i;
			for(i =0 ; i <5 && (strtemp = br.readLine())!= null; i++ )
			{
			    connectionparams[i] = strtemp;
			}
				  
			if(i != 5)
			{
				System.out.println("Error!! The database parameter file has error");
			}
			else
				bReturn = true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Exception handled : ParseDBParams()");
		}
		return bReturn;
	}
	boolean CreateConnection()
	{
		boolean bReturn = false;
		try
		{			
			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			con = DriverManager.getConnection("jdbc:mysql://"+connectionparams[0]+":"+connectionparams[1]+"/"+connectionparams[2],connectionparams[3],connectionparams[4]);
			if(con != null)
			{
				System.out.println("connection successfully established");
				bReturn = true;
			}
			else
				System.out.println("Error!!! could not connect to database");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Error in connecting to Database");
		}
		return bReturn;
	}
	void CloseConnection()
	{
		try
		{
			if(con != null)
				con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Exception handled in closing connection ");
		}
	}
}
