package Org.Exam.Repository;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.*;
import Org.Exam.helper.PathHelper;

public class DBConfig 
{
	protected Connection conn;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	public DBConfig()
	{
		try
		{
			
			PathHelper phelp=new PathHelper();
			Class.forName(PathHelper.p.getProperty("driver"));
			conn=DriverManager.getConnection(PathHelper.p.getProperty("url"),PathHelper.p.getProperty("user"),PathHelper.p.getProperty("pass"));
		}
		catch(Exception ex)
		{
			System.out.println("Error is"+ex);
		}
	}
}
