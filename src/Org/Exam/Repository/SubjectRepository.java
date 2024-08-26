package Org.Exam.Repository;


import Org.Exam.Model.SubjectModel;
import java.util.*;

public class SubjectRepository extends DBConfig
{
	List<String> list=new ArrayList<String>();
	public boolean isAddSubject(SubjectModel model)
	{
		try
		{
			stmt=conn.prepareStatement("insert into subject values('0',?)");
			stmt.setString(1,model.getName());
			return stmt.executeUpdate()>0?true:false;
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			return false;
		}
	}
	public boolean isSubjectPresent(String subName)
	{
		try
		{
			stmt=conn.prepareStatement("Select *from subject where Subname=?");
			stmt.setString(1, subName);
			rs=stmt.executeQuery();
			return rs.next();
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	public List<String> getAllSubject()
	{
		try
		{
			stmt=conn.prepareStatement("select subname from subject");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				list.add(rs.getString("subname"));
			}
			return list.size()>0?list:null;
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			return null;
		}
		
	}
	
}
