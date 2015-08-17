package microprocessor.execution.register;

import microprocessor.memory.Memory;

public enum SegmentRegister {
	SS (0),	CS (0),	ES (0), DS (0);
	
	private int val;
	private SegmentRegister(int value) {
		this.val = value;
	}
	
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = (val % Memory.MEMORY_SPACE);
	}
}

