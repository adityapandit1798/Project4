package in.co.rays.proj4.exception;
/**
 *  DuplicateRecordException thrown when a duplicate record occurred
 *
 * @author Aditya Pandit
 *
 */
public class DuplicateRecordException extends Exception{
	//:ErrorMessage
	
	public DuplicateRecordException (String msg) {
		super(msg);
	}

}
