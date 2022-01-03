package in.co.rays.proj4.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.exception.RecordNotFoundException;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.UserModel;

public class testuser {

	public static void main(String[] args) throws Exception {
		 testAdd();
		// testregisteruser();
		// testupdate();
		// testFindByLogin();
		// TESTCHANGEPASSWORD();
		//testforget();

	}

	private static void testFindByLogin() throws ParseException, ApplicationException {
		// TODO Auto-generated method stub
		
	UserBean ub=new UserBean();
		
		UserModel um= new UserModel();
	    ub =um.findByLogin("umesh@gmail.com");
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		ub.setDob(sdf.parse("12/12/2004"));

		ub.setFirstName("ravi");
		ub.setLastName("fatangde");
		ub.setLogin("ravi@rediff.com");
		ub.setPassword("12345");
		ub.setConfirmpassword("12345");
		ub.setDob(sdf.parse("11/20/2015"));
		ub.setGender("Female");
		ub.setRoleID(2);
		ub.setID(2);
		um.findByLogin(ub.getLogin());
		System.out.println("test user \n" + ub);
		
		
	}

	private static void testforget() throws ApplicationException, RecordNotFoundException {
		// TODO Auto-generated method stub
		UserBean bean = new UserBean();
		UserModel um = new UserModel();

		boolean flag = um.forgetPassword("RohitKadhne");

		System.out.println("test fprget  "+flag);
	}

	public static void TESTCHANGEPASSWORD() throws Exception {
		// TODO Auto-generated method stub

		UserModel um = new UserModel();
		UserBean ub = um.findByLogin("Aditya123");
		System.out.println(ub);
		String oldPassword = ub.getPassword();
		System.out.println("old password  " + oldPassword);
		ub.setID(ub.getID());  //from find by login
		ub.setPassword("12345");
		ub.setConfirmpassword("12345");
		String newPassword = ub.getPassword();

		um.changePassword(3, oldPassword, newPassword);
		System.out.println("password has been change successfully");

	}

	public static void testupdate() throws Exception {
		// TODO Auto-generated method stub
		UserBean ub = new UserBean();
		UserModel um = new UserModel();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		

		ub.setFirstName("ravi");
		ub.setLastName("fatangde");
		ub.setLogin("ravi@rediff.com");
		ub.setPassword("12345");
		ub.setConfirmpassword("12345");
		ub.setDob(sdf.parse("11/20/2015"));
		ub.setGender("Female");
		ub.setRoleID(2);
		ub.setID(2);
		System.out.println("test user \n" + ub);
		um.update(ub);

	}

	public static void testregisteruser() throws Exception {
		UserBean ub = new UserBean();
		UserModel um = new UserModel();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		ub.setID(8L);
		ub.setFirstName("vipin");
		ub.setLastName("kumawat");
		ub.setLogin("rru1dhay@gmail.com");
		ub.setPassword("4444");
		ub.setConfirmpassword("4444");
		ub.setDob(sdf.parse("11/20/2015"));
		ub.setGender("Male");
		ub.setRoleID(2);

		um.registerUser(ub);

		System.out.println("Successfully register");
		System.out.println(ub.getFirstName());
		System.out.println(ub.getLogin());
		System.out.println(ub.getLastName());
		System.out.println(ub.getDob());

	}

	public static void testAdd() throws Exception {
		UserBean ub = new UserBean();
		UserModel um = new UserModel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		ub.setFirstName("Rohit");
		ub.setLastName("Kadhne");
		ub.setMobileNo("789456123");
		ub.setGender("male");
		ub.setDob(sdf.parse("12-01-1999"));
		ub.setPassword("123456879");
		ub.setConfirmpassword("123456879");
		ub.setLogin("RohitKadhne");
		um.add(ub);

	}

}
