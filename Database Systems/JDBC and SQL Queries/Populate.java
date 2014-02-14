import java.sql.*;
import java.util.StringTokenizer;
import java.io.*;




public class Populate {

	Connection con = null;

	String[] connectionparams = new String[5];

	public static void main(String[] args) {

		Populate obj = new Populate();
		if(args.length == 7)
				{
		if(obj.Validate(args))//populate.Validate(args))
		{
			if(obj.ParseDBParams(args[0]))
			{
				System.out.println("Connecting with dbparams arguments");
				if(obj.CreateConnection())
				{
					if(!obj.CleanUpTables())
						return;
					System.out.println("Entering all the data into database");
					if(!obj.UpdatePlayers(args[1])) return;
					System.out.println(" Inserted data into Players table... ");
					if(!obj.UpdateTeams(args[2])) return;
					System.out.println(" Inserted data into Teams table... ");
					if(!obj.UpdateMembers(args[3])) return;
					System.out.println(" Inserted data into  Members table... ");
					if(!obj.Updatetour(args[4])) return;
					System.out.println(" Inserted data into Tournaments table... ");
					if(!obj.UpdateMatches(args[5])) return;
					System.out.println(" Inserted data into Matches table... ");
					if(!obj.UpdateEarnings(args[6])) return;
					System.out.println(" Inserted data into Earnings table... ");
					
					System.out.println("All the Data values filled in. Please check the database to confirm!");
				}
			}
			obj.CloseConnection();
		}
	}
			else
			{
				System.out.println("Error!! Invalid number of arguments");
			}		
		}

	boolean Validate(String[] strFiles)
	{
		boolean Return = false; 
		try
		{
			for(int i =0 ; i < strFiles.length; i++)
			{
				File strFile = new File(strFiles[i]);
				if(!strFile.exists())
				{
					System.out.println("File "+ strFiles[0] + " does not exist");
					return Return;
				}
			}
			System.out.println("File Validated");
			Return = true;
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			System.out.println("Exception handled : Validate()");
		}
		return Return;
	}
	boolean ParseDBParams(String strFile)
	{
		boolean Return = false;
		try
		{		
			FileInputStream fstream = new FileInputStream(strFile);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strtemp;
			int i;
			System.out.println("Contents of the dbparams file are");
			for(i =0 ; i <5 && (strtemp = br.readLine())!= null; i++ )
			{
				connectionparams[i] = strtemp;

				System.out.println(connectionparams[i]);
			}

			if(i != 5)
			{
				System.out.println("Error!! The database parameters are not in the given format");
			}
			else
				Return = true;
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			System.out.println("Exception handled : ParseDBParams()");
		}
		return Return;
	}
	boolean CreateConnection()
	{
		boolean bReturn = false;

		try
		{			
			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			//con = DriverManager.getConnection("jdbc:mysql://localhost:1433/ResultTracker","root","");*
			con = DriverManager.getConnection("jdbc:mysql://"+connectionparams[0]+":"+connectionparams[1]+"/"+connectionparams[2],connectionparams[3],connectionparams[4]);
			if(con != null)
			{
				System.out.println("Connection successfully established");
				bReturn = true;
			}
			else
				System.out.println("Error!!! could not connect to database. Please try later");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Error!!! could not connect to database. Please check the credentials");
		}
		return bReturn;
	}

	boolean UpdatePlayers(String strFile)
	{
		boolean bReturn = false;
		int row = 1;
		try
		{
			FileInputStream fstream = new FileInputStream(strFile);
			DataInputStream  dat = new DataInputStream (fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(dat));

			String insertQuery = "insert into players values (?,?,?,?,?,?)";
			PreparedStatement insertstatement = con.prepareStatement(insertQuery);
			//String temp = "";
			/*insertstatement.setString(1,"1");
			insertstatement.setString(2,"1");
			insertstatement.setString(3,"1");
			insertstatement.setString(4,"1");
			insertstatement.setString(5,"1");
			insertstatement.setString(6,temp); */
			String line;
			while ((line = in.readLine()) != null)
	            {	        	 
				int i=1;
	        	String [] r= line.split("\\,");
	        	for(String a: r)
	        	{
	        		//System.out.println(st.nextElement());
	        		
	        		String values = a.replace("\"","");

	        	//	for(int i=0; i<strValues.length;i++) 
	        		//{
	        			System.out.print("Value of i: "+i+" "+values);
	        			System.out.println();
	        			
	        			if(values.isEmpty())
	        			{
	        				insertstatement.setString(i,null);
	        			}
	        			
	        			insertstatement.setString(i,values);
	        			
	        			
	        			i++;
	        	//	}
	        	
	        		//row++;
	        	}
	        	
	        	insertstatement.executeUpdate();
	           }
			bReturn = true;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Error while inserting data into players table , row number :"+row);
		}
		return bReturn;
	}


	boolean UpdateTeams(String strFile)
	{
		boolean bReturn = false;
		int row = 1;
		try
		{
			FileInputStream fstream = new FileInputStream(strFile);
			DataInputStream  dat = new DataInputStream (fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(dat));

			String insertQuery = "insert into teams values (?,?,?,?)";
			PreparedStatement insertstatement = con.prepareStatement(insertQuery);
			String line;
			while ((line = in.readLine()) != null)
	            {	        	 
				int i=1;
	        	String [] r= line.split("\\,");
	        	for(String a: r)
	        	{
	        		//System.out.println(st.nextElement());
	        		
	        		String values = a.replace("\"","");
	        			System.out.print(values);
	        			System.out.println();
	        			
	        			if(values==null)
	        			{
	        				insertstatement.setString(i,null);	
	        			}
	        			else
	        				insertstatement.setString(i,values);
	        			
	        			i++;
	        	//	}
	        	
	        		//row++;
	        	}
	 
	        	for(; i <=4; i ++)
	        	{
	        		insertstatement.setString(i, null);
	        	}
	        	insertstatement.executeUpdate();
	           }
			
			bReturn = true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Error while inserting data into teams table , row number :"+row);
		}
		return bReturn;
	}

	boolean UpdateMembers(String strFile)
	{
		boolean bReturn = false;
		int row = 1;
		try
		{
			FileInputStream fstream = new FileInputStream(strFile);
			DataInputStream  dat = new DataInputStream (fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(dat));

			String insertQuery = "insert into members values (?,?,?,?)";
			PreparedStatement insertstatement = con.prepareStatement(insertQuery);

			String line;
			while ((line = in.readLine()) != null)
	            {	        	 
				int i=1;
	        	String [] r= line.split("\\,");
	        	for(String a: r)
	        	{
	        		//System.out.println(st.nextElement());
	        		
	        		String values = a.replace("\"","");
	        			System.out.print(values);
	        			System.out.println();
	        			
	        			insertstatement.setString(i,values);
	        			if(values==null)
	        			{
	        				insertstatement.setString(i,null);
	        			}
	        			i++;
	        	//	}
	        	
	        		row++;
	        	}
	        	for(; i <=4; i ++)
	        	{
	        		insertstatement.setString(i, null);
	        	}
	        	insertstatement.executeUpdate();
	           }
			bReturn = true;
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			System.out.println("Error while inserting data into members table , row number :"+row);
		}
		return bReturn;
	}
	boolean Updatetour(String strFile)
	{
		boolean bReturn = false;
		int row = 1;
		
		try
		{
			FileInputStream fstream = new FileInputStream(strFile);
			DataInputStream  dat = new DataInputStream (fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(dat));

			String insertQuery = "insert into tournaments values (?,?,?,?)";
			PreparedStatement insertstatement = con.prepareStatement(insertQuery);

			String line;
			while ((line = in.readLine()) != null)
	            {	        	 
				int i=1;
	        	String [] r= line.split("\\,");
	        	for(String a: r)
	        	{
	        		//System.out.println(st.nextElement());
	        		
	        		String values = a.replace("\"","");
	        			System.out.print(values);
	        			System.out.println();
	        			
	        			insertstatement.setString(i,values);
	        			
	        			if(values==null)
	        			{
	        				insertstatement.setString(i,null);
	        			}
	        			i++;
	        			row++;
	        	
	        	}
	        	for(; i <=4; i ++)
	        	{
	        		insertstatement.setString(i, null);
	        	}
	        	insertstatement.executeUpdate();
	        	
	           }
			bReturn = true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Error while inserting data into tournaments table, row number :"+row);
		}
		return bReturn;
	}
	boolean UpdateMatches(String strFile)
	{
		boolean bReturn = false;
		int row = 1;
		try
		{
			FileInputStream fstream = new FileInputStream(strFile);
			DataInputStream  dat = new DataInputStream (fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(dat));

			String insertQuery = "insert into matches values (?,?,?,?,?,?,?,?)";
			PreparedStatement insertstatement = con.prepareStatement(insertQuery);

			String line;
			while ((line = in.readLine()) != null)
	            {	        	 
				int i=1;
	        	String [] r= line.split("\\,");
	        	for(String a: r)
	        	{
	        		//System.out.println(st.nextElement());
	        		
	        		String values = a.replace("\"","");
	        			System.out.print(values);
	        			System.out.println();
	        			
	        			insertstatement.setString(i,values);
	        			if(values==null)
	        			{
	        				insertstatement.setString(i,null);
	        			}
	        			i++;
	        	//	}
	        	
	        		//row++;
	        	}
	        	for(; i <=8; i ++)
	        	{
	        		insertstatement.setString(i, null);
	        	}
	        	insertstatement.executeUpdate();
	           }
			bReturn = true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Error while inserting data into matches table , row number :" + row);
		}
		return bReturn;
	}
	boolean UpdateEarnings(String strFile)
	{
		boolean bReturn = false;
		int row = 1;
		try
		{
			FileInputStream fstream = new FileInputStream(strFile);
			DataInputStream  dat = new DataInputStream (fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(dat));

			String insertQuery = "insert into earnings values (?,?,?,?)";
			PreparedStatement insertstatement = con.prepareStatement(insertQuery);

			String line;
			while ((line = in.readLine()) != null)
	            {	        	 
				int i=1;
	        	String [] r= line.split("\\,");
	        	for(String a: r)
	        	{
	        		//System.out.println(st.nextElement());
	        		
	        		String values = a.replace("\"","");
	        			System.out.print(values);
	        			System.out.println();
	        			
	        			insertstatement.setString(i,values);
	        			if(values==null)
	        			{
	        				insertstatement.setString(i,null);
	        			}
	        			i++;
	        	//	}
	        	
	        		//row++;
	        	}
	        	for(; i <=4; i ++)
	        	{
	        		insertstatement.setString(i, null);
	        	}
	        	insertstatement.executeUpdate();
	           }
			
			bReturn = true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Error while inserting data into earnings table , row number :" + row);
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
			//ex.printStackTrace();
			System.out.println("Error in closing the connection ");
		}
	}
	boolean CleanUpTables()
	{
		boolean bReturn = false;
		try
		{			
			Statement s = con.createStatement();
			s.executeUpdate("delete from players;");
			s.executeUpdate("delete from teams;");
			s.executeUpdate("delete from members;");
			s.executeUpdate("delete from tournaments;");
			s.executeUpdate("delete from matches;");
			s.executeUpdate("delete from earnings;");
			s.close();
			bReturn = true;
			System.out.println("All the tables are cleared");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Error while clearing the previous contents of the table");
		}

		return bReturn;
	}

}