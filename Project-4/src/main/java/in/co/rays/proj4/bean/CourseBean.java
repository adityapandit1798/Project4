package in.co.rays.proj4.bean;
/**
 * @author Aditya Pandit
 *
 */
public class CourseBean extends BaseBean {
	 @Override
	public String toString() {
		return "CourseBean [Course_Name=" + Course_Name + ", Description=" + Description + ", Duration=" + Duration
				+ "]";
	}

	private String Course_Name;
	 /**
     * description of course
     */
	private String Description;
	 /**
     * duration of course
     */
	private String Duration;
	 /**
     * accessor 
     * 
     *      
     **/
	public String getCourse_Name() {
		return Course_Name;
	}
	public void setCourse_Name(String course_Name) {
		this.Course_Name = course_Name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		this.Description = description;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		this.Duration = duration;
	}
	public String getKey() {
		// TODO Auto-generated method stub
		return ID+"";
	}
	public String getValue() {
		// TODO Auto-generated method stub
		return Course_Name;
	}
	
	

}
