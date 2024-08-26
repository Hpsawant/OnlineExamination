package Org.Exam.Service;

import Org.Exam.Model.QuestionModel;
import java.io.*;
import java.util.List;
import java.util.*;

import Org.Exam.Repository.QuestionRepository;
import Org.Exam.helper.PathHelper;

public class QuestionService 
{
	QuestionRepository qRepo = new QuestionRepository();
	SubjectService ss=new SubjectService();
	public boolean isAddQuestion(QuestionModel qModel,String subName)
	{
		return qRepo.isAddQuestion(qModel,subName);
	}
	public boolean createFiles()
	{
		try
		{  
			String path="E:\\Giris's Tech Hub\\StudentExaminationProjectDocks\\questionsBank2";
			File f=new File(path);
			if(!f.exists())
			{
				f.mkdir();
			}
			List<String> subList=ss.getAllSubject();
			if(subList!=null)
			{
				for(String subName:subList)
				{
					f=new File(path+"\\"+subName+".csv");
					if(!f.exists())
					{
						f.createNewFile();
					}
				}
			}
			else
			{
				System.out.println("Subject not found.....");
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
		}
		return true;	
	}
	public boolean uploadBulkQuestion(String subName)
	{
		boolean b=this.createFiles();
		
		if(b)
		{
			File f=new File("E:\\Giris's Tech Hub\\StudentExaminationProjectDocks\\questionsBank2");
			File []fileList=f.listFiles();
			boolean flag=false;
			for(File file:fileList)
			{
				if(file.isFile())
				{
					int index=file.toString().indexOf(subName);
					//System.out.println(index);
					if(index!=-1)
					{
						flag=true;
						break;
					}
				}
			}
			if(flag)
			{
				try 
				{
					FileReader fr=new FileReader(PathHelper.filePath+"\\"+subName+".csv");
					BufferedReader br=new BufferedReader(fr);
					String question;
					flag=false;
					while((question=br.readLine())!=null)
					{
						String qwithop[]=question.split(",");
						flag=qRepo.uploadBulkQuestion(qwithop,subName);
					}
				} 
				catch(Exception e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("Subject file not found......");
			}
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
}
