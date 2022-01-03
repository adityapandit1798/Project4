package in.co.rays.proj4.bean;
/**
 * @author Aditya Pandit
 *
 */

public class MarksheetBean extends BaseBean {
	 /**
     * Rollno of Student
     **/
	private String rollno ;
	
	private long studentId;
	 /**
     * Rollno of Student
     **/
	private String name;
	 /**
     * Physics marks of Student
     **/
	private int physics;
	 /**
     * Chemistry marks of Student
     **/
	private int chemistry;
	 /**
     * Maths marks of Student
     **/
	private int maths;
	
	 /**
     * accessor
     **/
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhysics() {
		return physics;
	}
	public void setPhysics(int physics) {
		this.physics = physics;
	}
	public int getChemistry() {
		return chemistry;
	}
	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}
	public int getMaths() {
		return maths;
	}
	public void setMaths(int maths) {
		this.maths = maths;
	}
	public String getKey() {
		// TODO Auto-generated method stub
		return ID+"";
	}
	public String getValue() {
		// TODO Auto-generated method stub
		return rollno;
	}
	@Override
	public String toString() {
		return "MarksheetBean [rollno=" + rollno + ", studentId=" + studentId + ", name=" + name + ", physics="
				+ physics + ", chemistry=" + chemistry + ", maths=" + maths + "]";
	}
	
	

}
