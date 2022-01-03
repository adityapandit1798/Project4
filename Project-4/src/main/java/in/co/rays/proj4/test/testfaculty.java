package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.FacultyModel;

public class testfaculty {

	public static void main(String[] args) throws Exception {

		//testAdd();
		 testLIst();

	}

	private static void testLIst() throws ApplicationException {
		// TODO Auto-generated method stub
		FacultyBean fb = new FacultyBean();
		FacultyModel fm = new FacultyModel();

		List list = new ArrayList();
		list = fm.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			fb = (FacultyBean) it.next();
			System.out.println(fb.getCollege_id());
			System.out.println(fb.getCourse_Name());
			System.out.println(fb.getCreatedBy());
		}
	}

	public static void testAdd() throws Exception {
		// TODO Auto-generated method stub
		FacultyBean fb = new FacultyBean();
		FacultyModel fm = new FacultyModel();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");

		fb.setFirst_Name("RISHABH");
		fb.setLast_Name("BAJAJ");
		fb.setGender("MALE");
		fb.setCollege_id(1);

		fb.setCourse_id(1);
		fb.setDOJ(sdf.parse("12/12/2008"));
		fb.setSubject_id(1);
		fb.setQualification("MBA");
		fb.setMobile_No("9876543211");
		fb.setCreatedDatetime(new Timestamp(new Date().getTime()));
		fb.setModifiedDatetime(new Timestamp(new Date().getTime()));
		fm.add(fb);

	}

}
