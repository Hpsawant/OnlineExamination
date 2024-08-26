package Org.Exam.Repository;

import Org.Exam.Model.QuestionModel;

public class QuestionRepository extends DBConfig
{
	private int questionId;
	private int getQuestionId()
	{
		try
		{
			stmt=conn.prepareStatement("select max(qid) from question");
			rs=stmt.executeQuery();
			if(rs.next())
			{
				questionId=rs.getInt(1);
			}
			++questionId;
		}
		catch(Exception ex)
		{
			System.out.println("Errpr is:"+ex);
			
		}
		return questionId;
	}
	public int getSubjectIdByName(String name)
	{
		try
		{
			stmt=conn.prepareStatement("select subid from subject where subname=?");
			stmt.setString(1, name);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
			}
			else
			{
				return -1;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			return 0;
		}
	}
	public boolean isAddQuestion(QuestionModel qModel,String subName)
	{
		try
		{
			int qid=this.getQuestionId();
			if(qid!=0)
			{
				stmt=conn.prepareStatement("insert into question values(?,?,?,?,?,?,?)");
				stmt.setInt(1, qid);
				stmt.setString(2, qModel.getName());
				stmt.setString(3, qModel.getOp1());
				stmt.setString(4, qModel.getOp2());
				stmt.setString(5, qModel.getOp3());
				stmt.setString(6, qModel.getOp4());
				stmt.setInt(7, qModel.getAnswer());
				int value=stmt.executeUpdate();
				if(value>0)
				{
					int sid=this.getSubjectIdByName(subName);
					if(sid!=-1)
					{
						stmt=conn.prepareStatement("insert into subjectquestionjoin values(?,?)");
						stmt.setInt(1, qid);
						stmt.setInt(2, sid);
						return stmt.executeUpdate()>0?true:false;
					}
					else if(sid==-1)
					{
						System.out.println("Subject not Found");
						return false;
					}
					else
					{
						System.out.println("Some Problem is there........");
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	public boolean uploadBulkQuestion(String question[],String subName)
	{
		try
		{
			int qid=this.getQuestionId();
			if(qid!=0)
			{
				stmt=conn.prepareStatement("insert into question values(?,?,?,?,?,?,?)");
				stmt.setInt(1, qid);
				stmt.setString(2, question[0]);
				stmt.setString(3, question[1]);
				stmt.setString(4, question[2]);
				stmt.setString(5, question[3]);
				stmt.setString(6, question[4]);
				stmt.setInt(7, Integer.parseInt(question[5].trim()));
				int value=stmt.executeUpdate();
				if(value>0)
				{
					int sid=this.getSubjectIdByName(subName);
					if(sid!=-1)
					{
						stmt=conn.prepareStatement("insert into subjectquestionjoin values(?,?)");
						stmt.setInt(1, qid);
						stmt.setInt(2, sid);
						return stmt.executeUpdate()>0?true:false;
					}
					else if(sid==-1)
					{
						System.out.println("Subject not Found");
						return false;
					}
					else
					{
						System.out.println("Some Problem is there........");
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
}
