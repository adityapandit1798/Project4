package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.util.DataValidator;

public class testcourse {

	public static void main(String[] args) throws DuplicateRecordException, Exception {
		 //testadd();
		 testfindbypk();

		String som = "Skjlefjks@123";
		System.out.println(som);

		boolean viks = DataValidator.isPassword(som);

		System.out.println(viks);
	}

	public static void testfindbypk() throws Exception {
		CourseBean stb = new CourseBean();

		CourseModel stm = new CourseModel();
		stb = stm.findByPk(1);
		System.out.println("Stb -->" + stb);

		System.out.println(stb.getID());
		// System.out.println(stb.getCourse_Id());
		System.out.println(stb.getCourse_Name());
		System.out.println(stb.getCreatedBy());
		System.out.println(stb.getModifiedBy());
		System.out.println(stb.getCreatedDatetime());
		System.out.println(stb.getModifiedDatetime());

	}

	public static void testadd() throws Exception, DuplicateRecordException {
		CourseBean crsb = new CourseBean();
		CourseModel crsm = new CourseModel();
		crsb.setCourse_Name("eng phy");
		crsb.setDuration("2yrs");
		crsb.setDescription("engineering physics");
		crsb.setCreatedDatetime(new Timestamp(new Date().getTime()));

		crsm.add(crsb);

	}

}
