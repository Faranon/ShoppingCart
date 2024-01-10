package facade;

public class Result extends DataTransfer{
	public static final int PRODUCT_NOT_FOUND = 1;
	public static final int PRODUCT_NOT_ISSUED = 2;
	public static final int PRODUCT_ISSUED = 3;
	public static final int OPERATION_COMPLETED = 4;
	public static final int OPERATION_FAILED = 5;
	
	private int resultCode;
	
	public int getResultCode() {
		return resultCode;
	}
	
	public void setResultCode(int newResultCode) {
		this.resultCode = newResultCode;
	}
}
