package microprocessor.execution.register;

public enum IndexRegister {
	IP (0), SI(0), DI(0), SP(0), BP(0);

	private int val;
	private IndexRegister(int value) {
		val = value;
	}
	public synchronized int getVal() {
		return val;
	}
	public synchronized void setVal(int val) {
		this.val = val;
	}
	
}