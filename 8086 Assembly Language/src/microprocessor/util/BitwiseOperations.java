package microprocessor.util;

public class BitwiseOperations {
	
	public synchronized static final boolean isNthBitSet(int val, int n) {
		return (val & (1 << n)) != 0;
	}
	
	public synchronized static final int setNthBit(int val, int n) {
		 return val | (1 << n);
	}

	public synchronized static final int resetNthBit(int val, int n) {
		return val & ~(1 << n);
	}
	
	public synchronized static final int toggleNthBit(int val, int n) {
		return val ^ (1 << n);
	}
	
	public synchronized static final int formatInt(int x) {
		return x & 0xFF;
	}
}
