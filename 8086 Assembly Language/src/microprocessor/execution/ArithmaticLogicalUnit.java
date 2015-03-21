package microprocessor.execution;

public class ArithmaticLogicalUnit {
	private static final ArithmaticLogicalUnit alu = new ArithmaticLogicalUnit();
	private ArithmaticLogicalUnit() {}
	
	public synchronized static ArithmaticLogicalUnit getALU() {
		return alu;
	}

}
