package microprocessor.execution.register;

public enum GeneralRegister {
	AH ((byte)0), AL ((byte)0), BH((byte)0), BL((byte)0), CH((byte)0), CL((byte)0), DH((byte)0), DL((byte)0);
	
	int val;
	private GeneralRegister(byte value) {
		this.val = value;
	}
	public synchronized byte getVal() {
		return (byte) val;
	}
	public synchronized void setVal(byte val) {
		this.val = val;
	}
	
	/*
	public synchronized int getAX() {
		AX = 0;
		AX = (AH << 8) | (AL & 0xFF);
		return AX;
	}
	public synchronized int getBX() {
		BX = 0;
		BX = (BH << 8) | (BL & 0xFF);
		return BX;
	}
	public synchronized int getCX() {
		CX = 0;
		CX = (CX << 8) | (CL & 0xFF);
		return CX;
	}
	public synchronized int getDX() {
		DX = 0;
		DX = (DX << 8) | (DL & 0xff);
		return DX;
	}
	
	public synchronized void setAX(int aX) {
		AX = aX;
		AH = (byte) (AX >>> 8);
		AL = (byte) AX;
	}
	public synchronized void setBX(int bX) {
		BX = bX;
		BH = (byte) (AX >>> 8);
		BL = (byte) BX;
	}
	public synchronized void setCX(int cX) {
		CX = cX;
		CH = (byte) (AX >>> 8);
		CL = (byte) CX;
	}
	public synchronized void setDX(int dX) {
		DX = dX;
		DH = (byte) (DX >>> 8);
		DL = (byte) (DX);
	}
	*/
	
}