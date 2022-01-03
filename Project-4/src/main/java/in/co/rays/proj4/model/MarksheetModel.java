package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DataBaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;
/**
 * JDBC Implementation of MarksheetModel
 * 
 * @author Aditya Pandit
 *
 */
public class MarksheetModel {
	/**
	 * Find next PK
	 *
	 */
	public long nextPK() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM st_marksheet");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pk = rs.getLong(1);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;
	}
	/**
	 *Add a Marksheet
	 *
	 */

	public int add(MarksheetBean mb) throws Exception, DuplicateRecordException {

		System.out.println("Model add Started-->"  + mb);

		Connection conn = null;

		// // get Student Name
		StudentModel sm = new StudentModel();
		StudentBean sb = sm.findByPK(mb.getStudentId());
		
		String studentname = (sb.getFirstName() + " " + sb.getLastName());
		System.out.println(studentname);
		
		MarksheetBean duplicateMarksheet = findByRollNo(mb.getRollno());
		System.out.println("duplicate markesheet -->" + duplicateMarksheet);
		int pk = 0;

		if (duplicateMarksheet != null) {
			throw new DuplicateRecordException("Roll Number already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ST_MARKSHEET VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, nextPK());
			ps.setString(2, mb.getRollno());
			ps.setLong(3, mb.getStudentId());
			ps.setString(4, studentname);
			ps.setInt(5, mb.getPhysics());
			ps.setInt(6, mb.getChemistry());
			ps.setInt(7, mb.getMaths());
			ps.setString(8, mb.getCreatedBy());
			ps.setString(9, mb.getModifiedBy());
			ps.setTimestamp(10, mb.getCreatedDatetime());
			ps.setTimestamp(11, mb.getModifiedDatetime());
			ps.executeUpdate();
			conn.commit();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error(e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in add marksheet");
		} finally {
			System.out.println("INSERTION DONE");
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model add End");
		return pk;
	}
	/**
	 *Delete a Marksheet
	 *
	 */
	public void delete(MarksheetBean mb) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM st_marksheet WHERE ID=?");

			ps.setLong(1, mb.getID());

			ps.executeUpdate();
			conn.commit();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete college");
		} finally {

			System.out.println("DELETION Done");
			JDBCDataSource.closeConnection(conn);
		}
	}

	/**
	 *Find marksheet by Rollno
	 *
	 */
	public MarksheetBean findByRollNo(String rollNo) throws ApplicationException {
		System.out.println("Model findByRollNo Started  ->"  +rollNo);

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_MARKSHEET WHERE ROLL_NO=?");
		System.out.println(sql);
		Connection conn = null;
		MarksheetBean mb = null;
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			System.out.println("inside try");

			ps.setString(1, rollNo);

			ResultSet rs = ps.executeQuery();
			

			while (rs.next()) {
				mb = new MarksheetBean();
				mb.setID(rs.getLong(1));
				mb.setRollno(rs.getString(2));
				mb.setStudentId(rs.getLong(3));
				mb.setName(rs.getString(4));
				mb.setPhysics(rs.getInt(5));
				mb.setChemistry(rs.getInt(6));
				mb.setMaths(rs.getInt(7));
				mb.setCreatedBy(rs.getString(8));
				mb.setModifiedBy(rs.getString(9));
				mb.setCreatedDatetime(rs.getTimestamp(10));
				mb.setModifiedDatetime(rs.getTimestamp(11));
				System.out.println(mb);
			}
			rs.close();
		} catch (Exception e) {
			// log.error(e);
			throw new ApplicationException("Exception in getting marksheet by roll no");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		System.out.println("Model findByRollNo End");
		return mb;
	}
	/**
	 *Find marksheet by PK
	 *
	 */

	public MarksheetBean findByPK(long pk) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_MARKSHEET WHERE ID=?");
		MarksheetBean mb = null;
		Connection conn = null;
		System.out.println("run " + pk);
		
		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				mb = new MarksheetBean();

				mb.setID(rs.getLong(1));
				mb.setRollno(rs.getString(2));
				mb.setStudentId(rs.getLong(3));
				mb.setName(rs.getString(4));
				mb.setPhysics(rs.getInt(5));
				mb.setChemistry(rs.getInt(6));
				mb.setMaths(rs.getInt(7));
				mb.setCreatedBy(rs.getString(8));
				mb.setModifiedBy(rs.getString(9));
				mb.setCreatedDatetime(rs.getTimestamp(10));
				mb.setModifiedDatetime(rs.getTimestamp(11));
			}

			rs.close();
		} catch (Exception e) {
       e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return mb;

	}
	/**
	 *update a Marksheet
	 *
	 */

	public void update(MarksheetBean mb) throws ApplicationException, DuplicateRecordException {

		// log.debug("Model update Started");
		Connection conn = null;
		MarksheetBean beanExist = findByRollNo(mb.getRollno());
		// Check if updated Roll no already exist
		if (beanExist != null && beanExist.getID() != mb.getID()) {
			throw new DuplicateRecordException("Roll No is already exist");
		}

		// get Student Name
		StudentModel sModel = new StudentModel();
		StudentBean studentbean = sModel.findByPK(mb.getStudentId());

		mb.setName(studentbean.getFirstName() + " " + studentbean.getLastName());

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ST_MARKSHEET SET ROLLNO=?,STUDENT_ID=?,NAME=?,PHYSICS=?,CHEMISTRY=?,MATHS=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			ps.setString(1, mb.getRollno());
			ps.setLong(2, mb.getStudentId());
			ps.setString(3, mb.getName());
			ps.setInt(4, mb.getPhysics());
			ps.setInt(5, mb.getChemistry());
			ps.setInt(6, mb.getMaths());
			ps.setString(7, mb.getCreatedBy());
			ps.setString(8, mb.getModifiedBy());
			ps.setTimestamp(9, mb.getCreatedDatetime());
			ps.setTimestamp(10, mb.getModifiedDatetime());
			ps.setLong(11, mb.getID());
			ps.executeUpdate();
			conn.commit();
			ps.close();
		} catch (Exception e) {
			// log.error(e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Marksheet ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

//		log.debug("Model update End");
	}

	/**
	 *search a Marksheet
	 *
	 */
	public List search(MarksheetBean mb) throws ApplicationException {
		return search(mb, 0, 0);
	}
	/**
	 *search a Marksheet with pagination
	 *
	 */
	public List search(MarksheetBean mb, int pageNo, int pageSize) throws ApplicationException {

		// log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("select * from ST_MARKSHEET where true=true");

		if (mb != null) {
			System.out.println("service" + mb.getName());
			if (mb.getID() > 0) {
				sql.append(" AND id = " + mb.getID());
			}
			if (mb.getRollno() != null && mb.getRollno().length() > 0) {
				sql.append(" AND rollno like '" + mb.getRollno() + "%'");
			}
			if (mb.getName() != null && mb.getName().length() > 0) {
				sql.append(" AND name like '" + mb.getName() + "%'");
			}
			if (mb.getPhysics() != 0 && mb.getPhysics() > 0) {
				sql.append(" AND physics = " + mb.getPhysics());
			}
			if (mb.getChemistry() != 0 && mb.getChemistry() > 0) {
				sql.append(" AND chemistry = " + mb.getChemistry());
			}
			if (mb.getMaths() != 0 && mb.getMaths() > 0) {
				sql.append(" AND maths = '" + mb.getMaths());
			}

		}

		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mb = new MarksheetBean();
				mb.setID(rs.getLong(1));
				mb.setRollno(rs.getString(2));
				mb.setStudentId(rs.getLong(3));
				mb.setName(rs.getString(4));
				mb.setPhysics(rs.getInt(5));
				mb.setChemistry(rs.getInt(6));
				mb.setMaths(rs.getInt(7));
				mb.setCreatedBy(rs.getString(8));
				mb.setModifiedBy(rs.getString(9));
				mb.setCreatedDatetime(rs.getTimestamp(10));
				mb.setModifiedDatetime(rs.getTimestamp(11));
				list.add(mb);
			}
			rs.close();
		} catch (Exception e) {
			// log.error(e);
			throw new ApplicationException("Update rollback exception " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model search End");
		return list;
	}
	/**
	 *list of a Marksheet
	 *
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}
	/**
	 *list of Marksheet
	 *
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {

		// log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_MARKSHEET");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;
		MarksheetBean mb = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mb = new MarksheetBean();
				mb.setID(rs.getLong(1));
				mb.setRollno(rs.getString(2));
				mb.setStudentId(rs.getLong(3));
				mb.setName(rs.getString(4));
				mb.setPhysics(rs.getInt(5));
				mb.setChemistry(rs.getInt(6));
				mb.setMaths(rs.getInt(7));
				mb.setCreatedBy(rs.getString(8));
				mb.setModifiedBy(rs.getString(9));
				mb.setCreatedDatetime(rs.getTimestamp(10));
				mb.setModifiedDatetime(rs.getTimestamp(11));
				list.add(mb);
			}
			rs.close();
		} catch (Exception e) {
			// log.error(e);
			throw new ApplicationException("Exception in getting list of Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model list End");
		return list;

	}
	/**
	 *Get MarksheetMeritList
	 *
	 */
	public List getMeritList(int pageNo, int pageSize) throws ApplicationException {
		// log.debug("Model MeritList Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer(
				"SELECT ID, ROLL_NO , NAME , PHYSICS , CHEMISTRY , MATHS , (PHYSICS + CHEMISTRY + MATHS) as total from ST_MARKSHEET WHERE (PHYSICS>33 AND CHEMISTRY>33 AND MATHS>33) ORDER BY TOTAL DESC");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MarksheetBean mb = new MarksheetBean();
				mb.setID(rs.getInt(1));
				mb.setRollno(rs.getString(2));
				mb.setName(rs.getString(3));
				mb.setPhysics(rs.getInt(4));
				mb.setChemistry(rs.getInt(5));
				mb.setMaths(rs.getInt(6));
				list.add(mb);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error(e);
			throw new ApplicationException("Exception in getting merit list of Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model MeritList End");
		return list;
	}
}
