package in.co.rays.proj4.exception;
/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 * 
 * @author Aditya Pandit
 *
 */
public class DataBaseException extends Exception{
	
	//: ErrorMessage
	public DataBaseException(String msg) {
		super(msg);
		
		
	}

}
