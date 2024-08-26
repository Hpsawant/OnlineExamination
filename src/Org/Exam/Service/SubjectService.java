package Org.Exam.Service;

import Org.Exam.Model.SubjectModel;
import java.util.*;
import Org.Exam.Repository.SubjectRepository;

public class SubjectService 
{
	SubjectRepository subRepo=new SubjectRepository();
	
	public int addSubject(SubjectModel model)
	{
		return (subRepo.isSubjectPresent(model.getName()))?-1:subRepo.isAddSubject(model)?1:0; 
	}
	public List<String> getAllSubject()
	{
		return this.subRepo.getAllSubject();
	}
	
}
 