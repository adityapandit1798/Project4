package in.co.rays.proj4.exception;
/**
 * RecordNotFoundException thrown when a record not found occurred
 * 
 * @author Aditya Pandit
 *
 */
public class RecordNotFoundException extends Exception {
	
	//:ErrorMessage
	
	public RecordNotFoundException(String msg) {
		super(msg);
	}

}
