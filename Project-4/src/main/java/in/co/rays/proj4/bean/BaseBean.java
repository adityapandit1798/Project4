package in.co.rays.proj4.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Parent class of all Beans in application. It contains generic attributes.
 * 
 * @author Aditya pandit
 *
 */
public abstract class BaseBean implements DropdownListBean, Serializable, Comparable<BaseBean> {
	 /**
     * Non Business primary key
     */
	
	protected long ID;
	 /**
     * Contains USER ID who created this database record
     */
	private String createdBy;
	 /**
     * Contains USER ID who modified this database record
     */
	private String modifiedBy;
	/**
     * Contains Created Timestamp of database record
     */
	private Timestamp createdDatetime;
    /**
     * Contains Modified Timestamp of database record
     */
	private Timestamp modifiedDatetime;

	 /**
     * accessor
     */
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	public int compareTo(BaseBean next) {
		return getValue().compareTo(next.getValue());

	}

}
