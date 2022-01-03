package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of SubjectModel
 * 
 * @author Aditya Pandit
 *
 */
public class SubjectModel {
	/**
	 * Find next PK
	 *
	 */
	private static Logger log = Logger.getLogger(CollegeModel.class);


	public long nextPK() throws ApplicationException {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM ST_SUBJECT");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.error("database Exception ...", e);
			throw new ApplicationException("Exception in NextPk of subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	/**
	 * add a subject
	 *
	 */
	public int add(SubjectBean stb) throws ApplicationException, DuplicateRecordException {
		System.out.println("Model add subject  Started  \n" + stb);
		Connection conn = null;
		int pk = 0;

		CourseModel coumodel = new CourseModel();
		CourseBean coubean = coumodel.findByPk(stb.getCourse_Id());
		System.out.println("course bean---> " + coubean);
		
		
		String courseName = coubean.getCourse_Name();
		System.out.println("course name -------->" + courseName);
		
		
		System.out.println("get sub name  " + stb.getSubject_Name());
		SubjectBean DuplicateSubjectName = findByName(stb.getSubject_Name());
		System.out.println("find by name -->"+DuplicateSubjectName);
		
		
		if (DuplicateSubjectName != null) {
			throw new DuplicateRecordException("Subject name Already Exsist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			System.out.println(pk + " in ModelJDBC");

			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement ps = conn.prepareStatement("INSERT INTO ST_SUBJECT VALUES(?,?,?,?,?,?,?,?,?)");

			ps.setLong(1, nextPK());
			ps.setString(2, stb.getSubject_Name());
			ps.setString(3, courseName);
			ps.setInt(4, stb.getCourse_Id());
			ps.setString(5, stb.getDescription());
			ps.setString(6, stb.getCreatedBy());
			ps.setString(7, stb.getModifiedBy());
			ps.setTimestamp(8, stb.getCreatedDatetime());
			ps.setTimestamp(9, stb.getModifiedDatetime());
			System.out.println("\n\n subject to be added -->"+ stb);
			ps.executeUpdate();
			conn.commit(); // End transaction
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		System.out.println("Model add End");
		
		System.out.println("Added sucessfully");
		System.out.println(pk);
		return pk;
	}

	/**
	 * delete a subject
	 *
	 */
	public void delete(SubjectBean stb) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM ST_SUBJECT WHERE ID=?");
			ps.setLong(1, stb.getID());
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception in Rollback of Delte Method of Subject Model" + ex.getMessage());
			}
			throw new ApplicationException("Exception in Delte Method of Subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * upadte a subject
	 *
	 */
	public void update(SubjectBean stb) throws ApplicationException, DuplicateRecordException {
		 log.debug("Model update Started");
		Connection conn = null;
		CourseModel coumodel = new CourseModel();
		CourseBean coubean = coumodel.findByPk(stb.getCourse_Id());
		String courseName = coubean.getCourse_Name();

		SubjectBean beanExist = findByName(stb.getSubject_Name());
		if (beanExist != null && stb.getID() != stb.getID()) {
			throw new DuplicateRecordException("Subject name Already Exsist");
		}
		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ST_SUBJECT SET Subject_Name=?,Course_NAME=?,Course_ID=?,Description=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			ps.setString(1, stb.getSubject_Name());
			ps.setString(2, courseName);
			ps.setInt(3, stb.getCourse_Id());
			ps.setString(4, stb.getDescription());
			ps.setString(5, stb.getCreatedBy());
			ps.setString(6, stb.getModifiedBy());
			ps.setTimestamp(7, stb.getCreatedDatetime());
			ps.setTimestamp(8, stb.getModifiedDatetime());
			ps.setLong(9, stb.getID());
			ps.executeUpdate();
			conn.commit(); // End transaction
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			// throw new ApplicationException("Exception in updating Subject ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 * find a subject by name
	 *
	 */
	public SubjectBean findByName(String name) throws ApplicationException {
		System.out.println("Subject Model findByName method Started -->" + name);
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE SUBJECT_NAME=?");
		Connection conn = null;
		SubjectBean stb = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				stb = new SubjectBean();

				stb.setID(rs.getLong(1));
				stb.setSubject_Name(rs.getString(2));
				stb.setCourse_Id(rs.getInt(3));
				stb.setCourse_Name(rs.getString(4));
				stb.setDescription(rs.getString(5));
				stb.setCreatedBy(rs.getString(6));
				stb.setModifiedBy(rs.getString(7));
				stb.setCreatedDatetime(rs.getTimestamp(8));
				stb.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.error("database Exception....", e);
			e.printStackTrace();

		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		System.out.println("Subject Model findByName method End " +stb);
		return stb;
	}

	/**
	 * find a subject by PK
	 *
	 */

	public SubjectBean findByPK(int pk) throws ApplicationException {
		System.out.println("Subject Model findBypk method Started  --->" + pk);
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE ID=?");
		Connection conn = null;
		SubjectBean stb = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				stb = new SubjectBean();
				stb.setID(rs.getLong(1));
				stb.setSubject_Name(rs.getString(2));
				stb.setCourse_Id(rs.getInt(4));
				stb.setCourse_Name(rs.getString(3));
				stb.setDescription(rs.getString(5));
				stb.setCreatedBy(rs.getString(6));
				stb.setModifiedBy(rs.getString(7));
				stb.setCreatedDatetime(rs.getTimestamp(8));
				stb.setModifiedDatetime(rs.getTimestamp(9));
				System.out.println(stb);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.error("database Exception....", e);
			throw new ApplicationException("Exception in findByPk Method of Subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		System.out.println("Subject Model findByPk method End");
		System.out.println("subject bean -->"+stb);
		return stb;
	}

	/**
	 * search a subject
	 *
	 */

	public List search(SubjectBean stb) throws ApplicationException {
		return search(stb, 0, 0);
	}

	/**
	 * search subject with pagination
	 *
	 */

	public List search(SubjectBean stb, int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Subject Model search method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE 1=1 ");
		System.out.println("model search" + stb.getID());

		if (stb != null) {
			if (stb.getID() > 0) {
				sql.append(" AND id = " + stb.getID());
			}
			if (stb.getCourse_Id() > 0) {
				sql.append(" AND Course_ID = " + stb.getCourse_Id());
			}

			if (stb.getSubject_Name() != null && stb.getSubject_Name().length() > 0) {
				sql.append(" AND Subject_Name like '" + stb.getSubject_Name() + "%'");
			}
			if (stb.getCourse_Name() != null && stb.getCourse_Name().length() > 0) {
				sql.append(" AND Course_Name like '" + stb.getCourse_Id() + "%'");
			}
			if (stb.getDescription() != null && stb.getDescription().length() > 0) {
				sql.append(" AND description like '" + stb.getDescription() + " % ");
			}

		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}
		System.out.println("sql is" + sql);
		Connection conn = null;
		ArrayList list = new ArrayList();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				stb = new SubjectBean();

				stb.setID(rs.getLong(1));
				stb.setSubject_Name(rs.getString(2));
				stb.setCourse_Id(rs.getInt(4));
				stb.setCourse_Name(rs.getString(3));
				stb.setDescription(rs.getString(5));
				stb.setCreatedBy(rs.getString(6));
				stb.setModifiedBy(rs.getString(7));
				stb.setCreatedDatetime(rs.getTimestamp(8));
				stb.setModifiedDatetime(rs.getTimestamp(9));
				list.add(stb);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			 log.error("database Exception....", e);
			throw new ApplicationException("Exception in search Method of Subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

	/**
	 * list of subject
	 *
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * list of subject with pagination
	 *
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Subject Model list method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT ");

		// Page Size is greater then Zero then aplly pagination
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}

		Connection conn = null;
		ArrayList list = new ArrayList();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SubjectBean stb = new SubjectBean();

				stb.setID(rs.getLong(1));
				stb.setSubject_Name(rs.getString(2));
				stb.setCourse_Id(rs.getInt(4));
				stb.setCourse_Name(rs.getString(3));
				stb.setDescription(rs.getString(5));
				stb.setCreatedBy(rs.getString(6));
				stb.setModifiedBy(rs.getString(7));
				stb.setCreatedDatetime(rs.getTimestamp(8));
				stb.setModifiedDatetime(rs.getTimestamp(9));
				list.add(stb);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Subject Model list method End");
		return list;
	}
}
