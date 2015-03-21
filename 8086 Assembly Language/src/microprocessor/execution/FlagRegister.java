package microprocessor.execution;

public enum FlagRegister {
	PF(false), ZF(false), SF(false), AF(false), CF(false), OF(false), TF(false), IF(false), DF(false);
	
	private boolean val;
	private FlagRegister(boolean value) {
		this.val = value;
	}
	public boolean isVal() {
		return val;
	}
	public void setVal(boolean val) {
		this.val = val;
	}
}
