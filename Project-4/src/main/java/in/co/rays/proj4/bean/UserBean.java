package in.co.rays.proj4.bean;

import java.sql.Timestamp;
import java.util.Date;
/**
 * @author Aditya Pandit
 *
 */
public class UserBean extends BaseBean {
	@Override
	public String toString() {
		return "UserBean [firstName=" + firstName + ", lastName=" + lastName + ", login=" + login + ", password="
				+ password + ", confirmpassword=" + confirmpassword + ", dob=" + dob + ", mobileNo=" + mobileNo
				+ ", roleID=" + roleID + ", gender=" + gender + ", ID=" + ID;
	}

	private static final String ACTIVE = null;
	private static final String INACTIVE = null;
	/**
	 * firstname of User
	 *
	 */
	private String firstName;
	/**
	 * lastname of User
	 *
	 */
	private String lastName;
	/**
	 * login of User
	 *
	 */
	private String login;
	/**
	 * password of User
	 *
	 */
	
	private String password;
	/**
	 * confirm password of User
	 *
	 */
	private String confirmpassword;
	/**
	 * date of birth of User
	 *
	 */
	private Date dob;
	/**
	 * mobileno of User
	 *
	 */
	private String mobileNo;
	/**
	 * role of User
	 *
	 */
	private long roleID;
	/**
	 * gender of User
	 *
	 */
	private String gender;

	/**
	 * accessor
	 *
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getRoleID() {
		return roleID;
	}

	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public static String getActive() {
		return ACTIVE;
	}

	public static String getInactive() {
		return INACTIVE;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return ID+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return firstName;
	}

}
