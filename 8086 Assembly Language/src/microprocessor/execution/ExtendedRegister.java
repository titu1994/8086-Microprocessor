package microprocessor.execution;

public enum ExtendedRegister {
	AX(0), BX(0), CX(0), DX(0);
	
	int val;
	private ExtendedRegister(int value) {
		this.val = value;
	}
	public synchronized int getVal() {
		return val;
	}
	public synchronized void setVal(int val) {
		this.val = val;
	}
	
	public synchronized int getAX() {
		int ax = (GeneralRegister.AH.getVal() << 8) | (GeneralRegister.AL.getVal() & 0xFF);
		AX.setVal(ax);
		return ax;
	}
	public synchronized int getBX() {
		int bx = (GeneralRegister.BH.getVal() << 8) | (GeneralRegister.BL.getVal() & 0xFF);
		BX.setVal(bx);
		return bx;
	}
	public synchronized int getCX() {
		int cx = (GeneralRegister.CH.getVal() << 8) | (GeneralRegister.CL.getVal() & 0xFF);
		CX.setVal(cx);
		return cx;
	}
	public synchronized int getDX() {
		int dx = (GeneralRegister.DH.getVal() << 8) | (GeneralRegister.DL.getVal() & 0xff);
		DX.setVal(dx);
		return dx;
	}
	
	public synchronized void setAX(int aX) {
		AX.setVal(aX);
		GeneralRegister.AH.setVal((byte) (AX.getVal() >>> 8));
		GeneralRegister.AL.setVal((byte) AX.getVal());
	}
	public synchronized void setBX(int bX) {
		BX.setVal(bX);
		GeneralRegister.BH.setVal((byte) (BX.getVal() >>> 8));
		GeneralRegister.BL.setVal((byte) BX.getVal());
	}
	public synchronized void setCX(int cX) {
		CX.setVal(cX);
		GeneralRegister.CH.setVal((byte) (CX.getVal() >>> 8));
		GeneralRegister.CL.setVal((byte) CX.getVal());
	}
	public synchronized void setDX(int dX) {
		DX.setVal(dX);
		GeneralRegister.DH.setVal((byte)(DX.getVal() >>> 8));
		GeneralRegister.DL.setVal((byte) (DX.getVal()));
	}
}
