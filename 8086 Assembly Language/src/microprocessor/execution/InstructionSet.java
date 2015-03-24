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
	
	/**
	 * MOV AX,BX
	 * @param dest
	 * @param src
	 * @return
	 */
	public synchronized Instruction MOV(ExtendedRegister dest, ExtendedRegister src) {
		Instruction i = new Instruction("MOV", () -> {
			dest.setVal(src.getVal());
		});
		return i;
	}
	
	/**
	 * MOV AX,BL
	 * @param dest
	 * @param src
	 * @return
	 */
	public synchronized Instruction MOV(ExtendedRegister dest, GeneralRegister src) {
		Instruction i = new Instruction("MOV", () -> {
			dest.setVal(src.getVal());
		});
		return i;
	}
	
	/**
	 * MOV AL, 1 Byte data
	 * @param AL
	 * @param data
	 * @return
	 */
	public synchronized Instruction MOV_Immediate(GeneralRegister AL, final byte data) {
		Instruction i = new Instruction("MOV", () -> {
			AL.setVal(data);
		});
		return i;
	}
	
	/**
	 * MOV AX, 1 Word data
	 * @param AL
	 * @param data
	 * @return
	 */
	public synchronized Instruction MOV_Immediate(ExtendedRegister AL, final int data) {
		Instruction i = new Instruction("MOV", () -> {
			AL.setVal(data);
		});
		return i;
	}
	
	/**
	 * MOV [1000],1 Byte data
	 * @param memoryLocation
	 * @param data
	 * @return
	 */
	public synchronized Instruction MOV_Immediate(int memoryLocation, final byte data) {
		Instruction i = new Instruction("MOV", () -> {
			MemoryManagementUnit.getMMU().putDataAtAddress(SegmentRegister.DS, memoryLocation, data);
		});
		return i;
	}
	
	/**
	 * MOV [1000], 1 Word Data
	 * @param memoryLocation
	 * @param data
	 * @return
	 */
	public synchronized Instruction MOV_Immediate(int memoryLocation, final int data) {
		Instruction i = new Instruction("MOV", () -> {
			MemoryManagementUnit.getMMU().putWordAtAddress(SegmentRegister.DS, memoryLocation, data);
		});
		return i;
	}
	
	/**
	 * MOV CS,AX
	 * @param seg
	 * @param ex
	 * @return
	 */
	public synchronized Instruction MOV(SegmentRegister seg, ExtendedRegister ex) {
		Instruction i = new Instruction("MOV", () -> {
			seg.setVal(ex.getVal());
		});
		return i;
	}
	
	/**
	 * MOV ES,[SI]
	 * @param seg
	 * @param ix
	 * @return
	 */
	public synchronized Instruction MOV(SegmentRegister seg, IndexRegister ix) {
		Instruction i = new Instruction("MOV", () -> {
			seg.setVal(ix.getVal());
		});
		return i;
	}
	
	/**
	 * MOV AX,DS
	 * @param ex
	 * @param seg
	 * @return
	 */
	public synchronized Instruction MOV(ExtendedRegister ex, SegmentRegister seg) {
		Instruction i = new Instruction("MOV", () -> {
			ex.setVal(seg.getVal());
		});
		return i;
	}
	
	/**
	 * MOV [SI],ES
	 * @param ix
	 * @param seg
	 * @return
	 */
	public synchronized Instruction MOV(IndexRegister ix, SegmentRegister seg) {
		Instruction i = new Instruction("MOV", () -> {
			ix.setVal(ix.getVal());
		});
		return i;
	}
	
	/**
	 * PUSH IP
	 * @param ix
	 * @return
	 */
	public synchronized Instruction PUSH(IndexRegister ix) {
		Instruction i = new Instruction("PUSH", () -> {
			MemoryManagementUnit.getMMU().pushStack(ix);
		});
		return i;
	}
	
	/**
	 * PUSH AX
	 * @param ex
	 * @return
	 */
	public synchronized Instruction PUSH(ExtendedRegister ex) {
		Instruction i = new Instruction("PUSH", () -> {
			MemoryManagementUnit.getMMU().pushStack(ex);
		});
		return i;
	}
	
	/**
	 * POP IP
	 * @param ix
	 * @return
	 */
	public synchronized Instruction POP(IndexRegister ix) {
		Instruction i = new Instruction("POP", () -> {
			MemoryManagementUnit.getMMU().popStack(ix);
		});
		return i;
	}
	
	/**
	 * POP AX
	 * @param ex
	 * @return
	 */
	public synchronized Instruction POP(ExtendedRegister ex) {
		Instruction i = new Instruction("POP", () -> {
			MemoryManagementUnit.getMMU().popStack(ex);
		});
		return i;
	}
	
	/**
	 * XCHG AL,BL
	 * @param dest
	 * @param dest
	 * @return
	 */
	public synchronized Instruction XCHG(GeneralRegister dest, GeneralRegister src) {
		Instruction i = new Instruction("XCHG", () -> {
			byte dat = 0;
			dat = src.getVal();
			src.setVal(dest.getVal());
			dest.setVal(dat);
		});
		return i;
	}
	
	/**
	 * XCHG AX,BX
	 * @param dest
	 * @param src
	 * @return
	 */
	public synchronized Instruction XCHG(ExtendedRegister dest, ExtendedRegister src) {
		Instruction i = new Instruction("XCHG", () -> {
			int dat = 0;
			dat = src.getVal();
			src.setVal(dest.getVal());
			dest.setVal(dat);
		});
		return i;
	}
	
	/**
	 * XCHG IP,SI
	 * @param dest
	 * @param src
	 * @return
	 */
	public synchronized Instruction XCHG(IndexRegister dest, IndexRegister src) {
		Instruction i = new Instruction("XCHG", () -> {
			int dat = 0;
			dat = src.getVal();
			src.setVal(dest.getVal());
			dest.setVal(dat);
		});
		return i;
	}
	
	/**
	 * XCHG AX,SI
	 * @param dest
	 * @param src
	 * @return
	 */
	public synchronized Instruction XCHG(ExtendedRegister dest, IndexRegister src) {
		Instruction i = new Instruction("XCHG", () -> {
			int dat = 0;
			dat = src.getVal();
			src.setVal(dest.getVal());
			dest.setVal(dat);
		});
		return i;
	}
	
	/**
	 * XCHG SI,AX
	 * @param dest
	 * @param src
	 * @return
	 */
	public synchronized Instruction XCHG(IndexRegister dest, ExtendedRegister src) {
		Instruction i = new Instruction("XCHG", () -> {
			int dat = 0;
			dat = src.getVal();
			src.setVal(dest.getVal());
			dest.setVal(dat);
		});
		return i;
	}
	
	/**
	 * XLAT
	 * @return
	 */
	public synchronized Instruction XLAT() {
		Instruction i = new Instruction("XLAT", () -> {
			int memOffset = ExtendedRegister.BX.getBX() + GeneralRegister.AL.getVal();
			byte data = MemoryManagementUnit.getMMU().getDataAtAddress(SegmentRegister.DS, memOffset);
			GeneralRegister.AL.setVal(data);
		});
		return i;
	}
	
	/**
	 * All Hardware accessing instructions are unsupported
	 * @param al
	 * @param port
	 * @return
	 */
	public synchronized Instruction IN(GeneralRegister al, int port) {
		throw new UnsupportedOperationException("All Hardware accessing instructions are unsupported");
	}
	
	/**
	 * All Hardware accessing instructions are unsupported
	 * @param al
	 * @param ex
	 * @return
	 */
	public synchronized Instruction IN(GeneralRegister al, ExtendedRegister ex) {
		throw new UnsupportedOperationException("All Hardware accessing instructions are unsupported");
	}
	
	/**
	 * All Hardware accessing instructions are unsupported
	 * @param port
	 * @param al
	 * @return
	 */
	public synchronized Instruction OUT(int port, GeneralRegister al) {
		throw new UnsupportedOperationException("All Hardware accessing instructions are unsupported");
	}
	
	/**
	 * All Hardware accessing instructions are unsupported
	 * @param ex
	 * @param al
	 * @return
	 */
	public synchronized Instruction OUT(ExtendedRegister ex, GeneralRegister al) {
		throw new UnsupportedOperationException("All Hardware accessing instructions are unsupported");
	}
	
	/**
	 * LEA AX, count
	 * @param ax
	 * @param dataName
	 * @return
	 */
	public synchronized Instruction LEA(ExtendedRegister ax, String dataName) {
		Instruction i = new Instruction("LEA", () -> {
			ax.setVal(MemoryManagementUnit.getMMU().getIdentifierWord(dataName));
		});
		return i;
	}
	
	/**
	 * LEA SI, count
	 * @param ix
	 * @param dataName
	 * @return
	 */
	public synchronized Instruction LEA(IndexRegister ix, String dataName) {
		Instruction i = new Instruction("LEA", () -> {
			ix.setVal(MemoryManagementUnit.getMMU().getIdentifierWord(dataName));
		});
		return i;
	}
	
	/**
	 * LDS AX, count
	 * @param ax
	 * @param dataName
	 * @return
	 */
	public synchronized Instruction LDS(ExtendedRegister ax, String dataName) {
		Instruction i = new Instruction("LDS", () -> {
			int x[] = MemoryManagementUnit.getMMU().getIdentifierDoubleWord(dataName);
			ax.setVal(x[0]);
			SegmentRegister.DS.setVal(x[1]);
		});
		return i;
	}
	
	/**
	 * LDS SI, count
	 * @param ix
	 * @param dataName
	 * @return
	 */
	public synchronized Instruction LDS(IndexRegister ix, String dataName) {
		Instruction i = new Instruction("LDS", () -> {
			int x[] = MemoryManagementUnit.getMMU().getIdentifierDoubleWord(dataName);
			ix.setVal(x[0]);
			SegmentRegister.DS.setVal(x[1]);
		});
		return i;
	}
	
	/**
	 * LES AX, count
	 * @param ax
	 * @param dataName
	 * @return
	 */
	public synchronized Instruction LES(ExtendedRegister ax, String dataName) {
		Instruction i = new Instruction("LES", () -> {
			int x[] = MemoryManagementUnit.getMMU().getIdentifierDoubleWord(dataName);
			ax.setVal(x[0]);
			SegmentRegister.ES.setVal(x[1]);
		});
		return i;
	}
	
	/**
	 * LES SI, count
	 * @param ix
	 * @param dataName
	 * @return
	 */
	public synchronized Instruction LES(IndexRegister ix, String dataName) {
		Instruction i = new Instruction("LES", () -> {
			int x[] = MemoryManagementUnit.getMMU().getIdentifierDoubleWord(dataName);
			ix.setVal(x[0]);
			SegmentRegister.ES.setVal(x[1]);
		});
		return i;
	}
	
	/**
	 * LAFH
	 * @return
	 */
	public synchronized Instruction LAHF() {
		Instruction i = new Instruction("LAHF", () -> {
		ArithmaticLogicalUnit.getALU().loadAHWithFlags();
		});
		return i;
	}
	
	/**
	 * SAHF
	 * @return
	 */
	public synchronized Instruction SAHF() {
		Instruction i = new Instruction("SAHF", () -> {
		ArithmaticLogicalUnit.getALU().storeAHWithFlags();
		});
		return i;
	}
	
	/**
	 * POPF
	 * @return
	 */
	public synchronized Instruction POPF() {
		Instruction i = new Instruction("POPF", () -> {
		ArithmaticLogicalUnit.getALU().popFlagFromStack();
		});
		return i;
	}
	
	/**
	 * PUSHF
	 * @return
	 */
	public synchronized Instruction PUSHF() {
		Instruction i = new Instruction("PUSHF", () -> {
		ArithmaticLogicalUnit.getALU().pushFlagsOntoStack();
		});
		return i;
	}
	
	
	
}
