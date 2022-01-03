package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.bean.TimetableBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.model.TimetableModel;

public class testimetable {

	public static void main(String[] args) throws Exception {
		 testAdd();
		// testcheckbysem();
		// testlist();
		// testupdate();
		//testFindbyupk();

		
	}

	private static void testFindbyupk() throws ApplicationException {
		// TODO Auto-generated method stub

		TimetableBean stb = new TimetableBean();

		TimetableModel stm = new TimetableModel();

		stb = stm.findByPK(2);

		System.out.println(stb.getID());
		System.out.println(stb.getCourse_Id());
		System.out.println(stb.getCourse_Name());
		System.out.println(stb.getCreatedBy());
		System.out.println(stb.getModifiedBy());
		System.out.println(stb.getCreatedDatetime());
		System.out.println(stb.getModifiedDatetime());

	}

	public static void testupdate() throws Exception, DuplicateRecordException {
		// TODO Auto-generated method stub
		TimetableBean tb = new TimetableBean();
		TimetableModel tm = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = new Date();
		dt = sdf.parse("03/03/2012");
		tb.setCourse_Name("botany");
		
		tb.setSubject_Id(2);
		tb.setCourse_Id(1);
		tb.setExam_Date(dt);
		tb.setCreatedDatetime(new Timestamp(new Date().getTime()));
		tb.setID(4);

		tm.update(tb);

	}

	public static void testlist() throws Exception {
		// TODO Auto-generated method stub
		TimetableBean tb = new TimetableBean();
		TimetableModel tm = new TimetableModel();
		List list = new ArrayList();
		list = tm.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			tb = (TimetableBean) it.next();
			System.out.println(tb.getCourse_Id());
			System.out.println(tb.getCourse_Name());
			// System.out.println(fb.getDescription());
			System.out.println(tb.getCreatedBy());
		}

	}

	
	public static void testAdd() throws Exception, DuplicateRecordException {
		// TODO Auto-generated method stub
		TimetableBean tb = new TimetableBean();
		TimetableModel tm = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = new Date();
		dt = sdf.parse("03/03/2012");

		tb.setCourse_Name("COMMERCE");
		tb.setCourse_Id(5);
		tb.setSubject_Name("maths");
		tb.setSubject_Id(2);
		tb.setExam_Date(dt);
		tb.setExam_Time("4:00");
		
		tm.add(tb);

	}

}
