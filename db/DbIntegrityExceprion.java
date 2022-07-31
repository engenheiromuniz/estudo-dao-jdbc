package db;

public class DbIntegrityExceprion extends RuntimeException{
	
	public DbIntegrityExceprion(String msg) {
		super(msg); 
	}
}
