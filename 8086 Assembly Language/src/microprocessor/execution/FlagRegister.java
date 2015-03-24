package microprocessor.execution;

public enum FlagRegister {
	PF(0), ZF(0), SF(0), AF(0), CF(0), OF(0), TF(0), IF(0), DF(0);
	
	private int val;
	private FlagRegister(int value) {
		this.val = value;
	}
	
	public int getVal() {
		return val;
	}
	
	public void setVal(int val) {
		this.val = val;
	}
	
}
