package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.SubjectModel;

public class testsubject {

	public static void main(String[] args) throws Exception {
		testlist();
		//TESTADD();
		//testfindbypk();

	}

	private static void testfindbypk() throws Exception {
		// TODO Auto-generated method stub
		 SubjectBean stb=new SubjectBean();
			
			SubjectModel stm= new SubjectModel();
		    stb =stm.findByPK(3);
			
			System.out.println(stb.getID());
			System.out.println(stb.getCourse_Id());
			System.out.println(stb.getCourse_Name());
			System.out.println(stb.getCreatedBy());
			System.out.println(stb.getModifiedBy());
			System.out.println(stb.getCreatedDatetime());
			System.out.println(stb.getModifiedDatetime());
		
	}

	public static void TESTADD() throws Exception {
		// TODO Auto-generated method stub
		SubjectBean stb = new SubjectBean();
		SubjectModel stm = new SubjectModel();
		stb.setCourse_Id(1);
		stb.setCourse_Name("eng phy");
		stb.setDescription("engineering physics");
		stb.setSubject_Name("computing");
		stb.setCreatedDatetime(new Timestamp(new Date().getTime()));
		stb.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		stm.add(stb);
		
	}

	public static void testlist() throws Exception {
		SubjectBean stb = new SubjectBean();
		SubjectModel stm = new SubjectModel();
		List list = new ArrayList();
		list=stm.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			stb = (SubjectBean) it.next();
			System.out.println("\n\n**********************");
			System.out.println(stb.getCourse_Id());
			System.out.println(stb.getCourse_Name());
			System.out.println(stb.getDescription());
			System.out.println(stb.getCreatedBy());
			

		}
		
	}

}
