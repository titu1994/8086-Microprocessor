package microprocessor.execution;

import microprocessor.memory.MemoryManagementUnit;
import microprocessor.memory.SegmentRegister;


public class InstructionSet {
	
	//General Purpose Data Transfer
	/**
	 * MOV MEMORY, AL
	 * @param memLocation
	 * @param AX
	 * @return
	 */
	public synchronized Instruction MOV(int memLocation, GeneralRegister AL) {
		byte val = AL.getVal();
		Instruction i = new Instruction("MOV", () -> {
			MemoryManagementUnit.getMMU().putDataAtAddress(SegmentRegister.DS, memLocation, val);
		});
		return i;
	}
	
	/**
	 * MOV MEMORY, AX
	 * @param memLocation
	 * @param AX
	 * @return
	 */
	public synchronized Instruction MOV(int memLocation, int memLocation2, ExtendedRegister AX) {
		int val = AX.getVal();
		Instruction i = new Instruction("MOV", () -> {
			MemoryManagementUnit.getMMU().putWordAtAddress(SegmentRegister.DS, memLocation, val);
		});
		return i;
	}
	
	/**
	 * MOV AL, MEMORY
	 * @param AX
	 * @param data
	 * @return
	 */
	public synchronized Instruction MOV(GeneralRegister AL, byte data) {
		Instruction i = new Instruction("MOV", () -> {
			AL.setVal(data);
		});
		return i;
	}
	
	/**
	 * MOV AX, MEMORY (2 locations)
	 * @param AX
	 * @param data
	 * @return
	 */
	public synchronized Instruction MOV(ExtendedRegister ax, int data) {
		Instruction i = new Instruction("MOV", () -> {
			ax.setAX(data);
		});
		return i;
	}
	
	/**
	 * MOV dstReg, srcReg
	 * @param dest
	 * @param src
	 * @return
	 */
	public synchronized Instruction MOV(GeneralRegister dest, GeneralRegister src) {
		Instruction i = new Instruction("MOV", () -> {
			dest.setVal(src.getVal());
		});
		return i;
	}
	
	
}
