package in.co.rays.proj4.bean;

import java.util.Date;
/**
 * @author Aditya Pandit
 *
 */
public class TimetableBean extends BaseBean {
	
	private String Course_Name;
	private int Course_Id;
	private String Subject_Name;
	private int Subject_Id;
	private Date Exam_Date;
	private String Exam_Time;
	private String Semester;

	
	public String getCourse_Name() {
		return Course_Name;
	}
	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}
	public int getCourse_Id() {
		return Course_Id;
	}
	public void setCourse_Id(int course_Id) {
		Course_Id = course_Id;
	}
	public String getSubject_Name() {
		return Subject_Name;
	}
	public void setSubject_Name(String subject_Name) {
		Subject_Name = subject_Name;
	}
	public int getSubject_Id() {
		return Subject_Id;
	}
	public void setSubject_Id(int subject_Id) {
		Subject_Id = subject_Id;
	}
	public Date getExam_Date() {
		return Exam_Date;
	}
	public void setExam_Date(Date date) {
		Exam_Date = date;
	}
	
	public String getExam_Time() {
		return Exam_Time;
	}
	public void setExam_Time(String exam_Time) {
		Exam_Time = exam_Time;
	}

	public String getSemester() {
		return Semester;
	}
	public void setSemester(String semester) {
		Semester = semester;
	}
	public String getKey() {
		// TODO Auto-generated method stub
		return ID+"";
	}
	public String getValue() {
		// TODO Auto-generated method stub
		return Course_Name;
	}
	@Override
	public String toString() {
		return "TimetableBean [Course_Name=" + Course_Name + ", Course_Id=" + Course_Id + ", Subject_Name="
				+ Subject_Name + ", Subject_Id=" + Subject_Id + ", Exam_Date=" + Exam_Date + ", Exam_Time=" + Exam_Time
				+ ", Semester=" + Semester + "]";
	}
	
	
	
	
	

}
