package microprocessor.execution;


public class Instruction {
	private static int id = 0;
	private final String instructionType;
	private final int opcode;
	private final Runnable inst;

	private int immediateData;
	private GeneralRegister genReg1, genReg2;
	private int memory;
	private int mode;
	private int lowDisp, highDisp;
	
	public Instruction(String instructionType, Runnable inst) {
		this(instructionType, inst, 0, null, null, 0, 0, 0, 0);
	}
	
	public Instruction(String instructionType, Runnable inst, GeneralRegister reg) {
		this(instructionType, inst, 0, reg, null, 0, 0, 0, 0);
	}
	
	public Instruction(String instructionType, Runnable inst, int immediate, GeneralRegister reg, GeneralRegister reg2) {
		this(instructionType, inst, immediate, reg, reg2, 0, 0, 0, 0);
	}
	
	public Instruction(String instructionType, Runnable inst, GeneralRegister reg, GeneralRegister reg2, int mode) {
		this(instructionType, inst, 0, reg, reg2, 0, mode, 0, 0);
	}
	
	public Instruction(String instructionType, Runnable inst, GeneralRegister reg, GeneralRegister reg2, int mode, int lowDisp) {
		this(instructionType, inst, 0, reg, reg2, 0, mode, lowDisp, 0);
	}
	
	public Instruction(String instructionType, Runnable inst, GeneralRegister reg, GeneralRegister reg2, int mode, int lowDisp, int highDisp) {
		this(instructionType, inst, 0, reg, reg2, 0, mode, lowDisp, highDisp);
	}

	public Instruction(String instructionType, Runnable inst, int intermediateData, GeneralRegister reg, GeneralRegister genReg2, int memory, int mode, int lowDisp, int highDisp) {
		this.instructionType = instructionType.toUpperCase();
		this.opcode = id++;
		this.inst = inst;
		this.immediateData = intermediateData;
		this.genReg1 = reg;
		this.genReg2 = genReg2;
		this.memory = memory;
		this.mode = mode;
		this.lowDisp = lowDisp;
		this.highDisp = highDisp;
	}

	public synchronized int getImmediateData() {
		return immediateData;
	}

	public synchronized void setImmediateData(int immediateData) {
		this.immediateData = immediateData;
	}

	public synchronized GeneralRegister getGenReg1() {
		return genReg1;
	}

	public synchronized void setGenReg1(GeneralRegister genReg1) {
		this.genReg1 = genReg1;
	}

	public synchronized GeneralRegister getGenReg2() {
		return genReg2;
	}

	public synchronized void setGenReg2(GeneralRegister genReg2) {
		this.genReg2 = genReg2;
	}

	public synchronized int getMemory() {
		return memory;
	}

	public synchronized void setMemory(int memory) {
		this.memory = memory;
	}

	public synchronized int getMode() {
		return mode;
	}

	public synchronized void setMode(int mode) {
		this.mode = mode;
	}

	public synchronized int getLowDisp() {
		return lowDisp;
	}

	public synchronized void setLowDisp(int lowDisp) {
		this.lowDisp = lowDisp;
	}

	public synchronized int getHighDisp() {
		return highDisp;
	}

	public synchronized void setHighDisp(int highDisp) {
		this.highDisp = highDisp;
	}

	public synchronized String getInstructionType() {
		return instructionType;
	}

	public synchronized int getOpcode() {
		return opcode;
	}

	public synchronized Runnable getInst() {
		return inst;
	}
	
	
}
