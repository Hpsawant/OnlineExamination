package Org.Exam.helper;

import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

public class PathHelper 
{
	public static String completePath="";
	public static Properties p=new Properties();
	
	public static final String filePath="C:\\Users\\hp\\Desktop\\Giris's Tech Hub\\StudentExaminationProjectDocks\\QuestionBank";
	
	public PathHelper()
	{
		try
		{
			Path currentDirectoryPath=FileSystems.getDefault().getPath("");
			String currentDirectoryName=currentDirectoryPath.toAbsolutePath().toString();
			completePath=currentDirectoryName+"\\Resources\\DB.Properties";
			FileInputStream finf = new FileInputStream(completePath);
			p.load(finf);
		}
		catch(Exception ex)
		{
			System.out.println("Error is"+ex);
		}
	}
}
