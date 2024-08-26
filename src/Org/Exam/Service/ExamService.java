package Org.Exam.Service;

import java.util.List;
import java.util.Date;
import Org.Exam.Model.ExamModel;
import Org.Exam.Model.ScheduleModel;
import Org.Exam.Repository.ExamRepository;

public class ExamService 
{
	ExamRepository examRepo=new ExamRepository();
	public int isAddExam(ExamModel model)
	{
		if(examRepo.isExamPresent(model.getName()))
		{
			return -1;
		}
		else if(examRepo.isAddExam(model))
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	public List<ExamModel> getAllExams()
	{
		return examRepo.getAllExam();
	}
	public ExamModel getExamIdByName(String name)
	{
		return examRepo.getExamIdByName(name);
	}
	public boolean isSetSchedule(ExamModel model,String subName)
	{
		Date d=new Date();
		String[]s=d.toLocaleString().split(",");
		String dsplit[]=s[0].split("-");
		ScheduleModel smodel=model.getSchduleModel();
		Date userDate=smodel.getExamDate();
		String userDateArr[]=userDate.toLocaleString().split(",");
		String userDates[]=userDateArr[0].split("-");
		if(Integer.parseInt(userDates[2])>=Integer.parseInt(dsplit[2]) && userDates[1].equals(dsplit[1]))
		{
			if(Integer.parseInt(userDates[0])>=Integer.parseInt(dsplit[0]))
			{
				System.out.println("You can Schedule exam");
				return examRepo.isSetSchedule(model,subName)?true:false;		
			}
			else
			{
				System.out.println("You may be insert date before System");	
			}
		}
		else
		{
			System.out.println("You may be insert previous year or may be previous Month");
		}
		return false;
	}
}
