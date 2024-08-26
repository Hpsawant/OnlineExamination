package Org.Exam.Client;

import Org.Exam.Model.ExamModel;
import java.util.Date;
import Org.Exam.Model.QuestionModel;
import Org.Exam.Model.ScheduleModel;
import Org.Exam.Model.SubjectModel;
import Org.Exam.Service.ExamService;
import Org.Exam.Service.QuestionService;
import Org.Exam.Service.SubjectService;
import java.util.*;

public class ExamClientApplication 
{
	public static void main(String[] args) 
	{
		SubjectService sv=new SubjectService();
		QuestionService qService=new QuestionService();
		ExamService examService=new ExamService();
		do
		{
			System.out.println("1.Add new Subject");
			System.out.println("2.Add new Question");
			System.out.println("3.Add Bulk Question File");
			System.out.println("4.Add Exam");
			System.out.println("5.Enter Exam Schedule");
			System.out.println("6.Add Student");
			System.out.println("Enter Your Choise");
			Scanner sc=new Scanner(System.in);
			int choise=sc.nextInt();
			switch(choise)
			{
			case 1:
				sc.nextLine();
				System.out.println("Enter Subject Name");
				String subName=sc.nextLine();
				SubjectModel model=new SubjectModel();
				model.setName(subName);
				int result=sv.addSubject(model);
				System.out.println((result==1)?"Subject Added Succesfully.......":(result==-1)?"Subject Allready present.......":"Some Problem is there........");
				break;
				
			case 2:
				sc.nextLine();
				System.out.println("Enter Question");
				String question=sc.nextLine();
				System.out.println("Enter Option1");
				String op1=sc.nextLine();
				System.out.println("Enter Option2");
				String op2=sc.nextLine();
				System.out.println("Enter Option3");
				String op3=sc.nextLine();
				System.out.println("Enter Option4");
				String op4=sc.nextLine();
				System.out.println("Enter option number as answer");
				int ans=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Subject Name");
				subName=sc.nextLine();
				QuestionModel qmodel=new QuestionModel(question,op1,op2,op3,op4,ans);
				boolean b=qService.isAddQuestion(qmodel, subName);
				if(b)
				{
					System.out.println("Question Added Succesfully......");
				}
				else
				{
					System.out.println("Question is not Added.......");
				}
				break;
				
			case 3:
				sc.nextLine();
				System.out.println("Enter Subject Name for Store bulk Question");
				String  subname=sc.nextLine();
				qService.uploadBulkQuestion(subname);
				break;
				
			case 4:
				sc.nextLine();
				System.out.println("Enter Exam name Total marks and Passing marks");
				String examName=sc.nextLine();
				int totalMarks=sc.nextInt();
				int passingMarks=sc.nextInt();
				ExamModel examModel=new ExamModel(examName,totalMarks,passingMarks);
				result=examService.isAddExam(examModel);
				if(result==1)
				{
					System.out.println("Exam Added SuccesFully.......");
				}
				else if(result==-1)
				{
					System.out.println("Exam Already present.....");
				}
				else
				{
					System.out.println("Some Problem is there.......");
				}
				break;
				
			case 5:
					sc.nextLine();
					System.out.println("Hey user you have list of exam and select one for Schedule");
					System.out.println("****************************************************************");
					List<ExamModel> list=examService.getAllExams();
					for(ExamModel m:list)
					{
						System.out.println(m.getId()+"\t"+m.getName()+"\t"+m.getTotalMarks()+"\t"+m.getPassingMarks());
					}
					System.out.println("*********************************************************************");
					System.out.println("Enter Exam Name For Schedule");
					examName=sc.nextLine();
					ExamModel emodel=examService.getExamIdByName(examName);
					if(emodel !=null)
					{
						System.out.println("Enter Exam Date StartTime and EndTime");
						String dateFormat=sc.nextLine();
						Date d1 = new Date(dateFormat);
						String startTime=sc.nextLine();
						String endTime=sc.nextLine();
						ScheduleModel smodel = new ScheduleModel();
						smodel.setExamDate(d1);
						smodel.setStartTime(startTime);
						smodel.setEndTime(endTime);
						smodel.setExamid(emodel.getId());
						emodel.setScheduleModel(smodel);
						//b=examService.isSetSchedule(emodel);
						System.out.println("Enter Subject name To Schedule.....");
						subName=sc.nextLine();
						b=examService.isSetSchedule(emodel,subName);
						if(b)
						{
							System.out.println("Exam Schedule SuccesFully......");
						}
						else
						{
							System.out.println("Exam Not Scheduled......");
						}
					}
					else
					{
						System.out.println("Enter Correct Exam");
					}	
				break;
				 
				default:
					System.out.println("Wrong Choise......");
			}
		}
		while(true);
	}

}
