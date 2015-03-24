package microprocessor.execution;

import microprocessor.memory.MemoryManagementUnit;
import microprocessor.memory.SegmentRegister;
import microprocessor.util.BitwiseOperations;


public class ArithmaticLogicalUnit {
	private static final ArithmaticLogicalUnit alu = new ArithmaticLogicalUnit();

	private static int PF, ZF, SF, AF, CF, OF, TF, IF, DF;

	private ArithmaticLogicalUnit() {}

	public synchronized static ArithmaticLogicalUnit getALU() {
		return alu;
	}

	public synchronized void loadAHWithFlags() {
		byte x = 0;
		x =(byte) ((byte) (FlagRegister.SF.getVal() << 8) |
				(byte) (FlagRegister.ZF.getVal() << 7) |
				(byte) (0 << 6) |
				(byte) (FlagRegister.AF.getVal() << 5) |
				(byte) (0 << 4) |
				(byte) (FlagRegister.PF.getVal() << 3) |
				(byte) (0 << 2) |
				(byte) (FlagRegister.CF.getVal() << 1));

		GeneralRegister.AH.setVal(x);
	}

	public synchronized void storeAHWithFlags() {
		byte x = GeneralRegister.AH.getVal();
		FlagRegister.SF.setVal(x >> 8);
		FlagRegister.ZF.setVal(x >> 7);
		FlagRegister.AF.setVal(x >> 5);
		FlagRegister.PF.setVal(x >> 3);
		FlagRegister.CF.setVal(x >> 1);
	}

	public synchronized void pushFlagsOntoStack() {
		byte x = 0;
		x =(byte) ((byte) (FlagRegister.SF.getVal() << 8) |
				(byte) (FlagRegister.ZF.getVal() << 7) |
				(byte) (0 << 6) |
				(byte) (FlagRegister.AF.getVal() << 5) |
				(byte) (0 << 4) |
				(byte) (FlagRegister.PF.getVal() << 3) |
				(byte) (0 << 2) |
				(byte) (FlagRegister.CF.getVal() << 1));

		int y = x;
		MemoryManagementUnit.getMMU().pushStack(y);
	}

	public synchronized void popFlagFromStack() {
		int x = MemoryManagementUnit.getMMU().popStack();
		FlagRegister.SF.setVal(x >> 8);
		FlagRegister.ZF.setVal(x >> 7);
		FlagRegister.AF.setVal(x >> 5);
		FlagRegister.PF.setVal(x >> 3);
		FlagRegister.CF.setVal(x >> 1);
	}

	private synchronized void tempReadFlagsBeforeOp() {
		PF = FlagRegister.PF.getVal();
		ZF = FlagRegister.ZF.getVal(); 
		SF = FlagRegister.SF.getVal();
		AF = FlagRegister.AF.getVal();
		CF = FlagRegister.CF.getVal();
		OF = FlagRegister.OF.getVal();
		//TF = FlagRegister.TF.getVal();
		//IF = FlagRegister.IF.getVal();
		//DF = FlagRegister.DF.getVal();
	}


	private synchronized void tempWriteFlagsAfterOp() {
		FlagRegister.PF.setVal(PF);
		FlagRegister.ZF.setVal(ZF); 
		FlagRegister.SF.setVal(SF);
		FlagRegister.AF.setVal(AF);
		FlagRegister.CF.setVal(CF);
		FlagRegister.OF.setVal(OF);
		//FlagRegister.TF.setVal(TF);
		//FlagRegister.IF.setVal(IF);
		//FlagRegister.DF.setVal(DF);
	}

	private synchronized void tempCalculateFlagsDuringOp(int result) {
		//PF Analysis
		int count = 0, n = result;
		while(n>0){
			n&=(n-1);
			count++;
		}
		if(count % 2 == 0)	PF = 1; 	else PF = 0;

		//ZF Analysis
		if(result == 0) ZF = 1; 		else ZF = 0;

		//SF Analysis
		if(BitwiseOperations.isNthBitSet(result, 32)) SF = 1; else SF = 0;

		//OF Analysis
		if(result >= 32767 || result <= -32768) OF = 1; else OF = 0;
	}

	private synchronized void tempCalculateCarryFlagsDuringOp(int a, int b, int op) {

		switch(op) {
		case 1: {//Addition
			//AF Calculation
			byte x = (byte) (a & 0x0f);
			byte y = (byte) (b & 0x0f);
			byte z = (byte) (x + y);
			if(z > 7) AF = 1; else AF = 0;

			//CF calculation
			if(a + b > 255) CF = 1; else CF = 0;
			break;
		}
		case 2: {//Subtraction
			byte x = (byte) (a & 0x0f);
			byte y = (byte) (b & 0x0f);
			byte z = (byte) (x - y);
			if(z < 0) AF = 1; else AF = 0;

			//CF calculation
			if(a < b) CF = 1; else CF = 0;
			break;
		}
		default: 
		}
	}

	private synchronized void tempCalculateAllFlagsDuringOpInt(int a, int b, int result, int op) {
		tempReadFlagsBeforeOp();
		tempCalculateFlagsDuringOp(result);
		tempCalculateCarryFlagsDuringOp(a, b, op);
		tempWriteFlagsAfterOp();
	}








	public synchronized void add(GeneralRegister al, GeneralRegister bl) {
		byte x = (byte) (al.getVal() + bl.getVal());
		tempCalculateAllFlagsDuringOpInt(al.getVal(), bl.getVal(), x, 1);
		if(SF == 1) {
			int y = al.getVal() + bl.getVal();
			ExtendedRegister.AX.setVal(y);
		}
		else al.setVal(x);
	}

	public synchronized void add(GeneralRegister al, int memoryLocation) {
		byte dat = MemoryManagementUnit.getMMU().getDataAtAddress(SegmentRegister.DS, memoryLocation);
		byte x = (byte) (al.getVal() + dat);
		tempCalculateAllFlagsDuringOpInt(al.getVal(), dat, x, 1);
		if(SF == 1) {
			int y = al.getVal() + dat;
			ExtendedRegister.AX.setVal(y);
		}
		else al.setVal(x);
	}

	public synchronized void add(int memoryLocation, GeneralRegister al) {
		byte dat = MemoryManagementUnit.getMMU().getDataAtAddress(SegmentRegister.DS, memoryLocation);
		byte x = (byte) (al.getVal() + dat);
		tempCalculateAllFlagsDuringOpInt(al.getVal(), dat, x, 1);
		if(SF == 1) {
			int y = al.getVal() + dat;
			MemoryManagementUnit.getMMU().putWordAtAddress(SegmentRegister.DS, memoryLocation, y);
		}
		else MemoryManagementUnit.getMMU().putDataAtAddress(SegmentRegister.DS, memoryLocation, x);

	}

	public synchronized void addImmediate(GeneralRegister al, byte immediate) {
		byte x = (byte) (al.getVal() + immediate);
		tempCalculateAllFlagsDuringOpInt(al.getVal(), immediate, x, 1);
		if(SF == 1) {
			int y = al.getVal() + immediate;
			ExtendedRegister.AX.setVal(y);
		}
		else al.setVal(x);
	}

	public synchronized void addImmediate(int memoryLocation, byte immediate) {
		byte dat = MemoryManagementUnit.getMMU().getDataAtAddress(SegmentRegister.DS, memoryLocation);
		byte x = (byte) (immediate + dat);
		tempCalculateAllFlagsDuringOpInt(dat, immediate, x, 1);
		if(SF == 1) {
			int y = immediate + dat;
			MemoryManagementUnit.getMMU().putWordAtAddress(SegmentRegister.DS, memoryLocation, y);
		}
		else MemoryManagementUnit.getMMU().putDataAtAddress(SegmentRegister.DS, memoryLocation, x);
	}





	public synchronized void add(ExtendedRegister al, ExtendedRegister bl) {
		int x = (al.getVal() + bl.getVal());
		tempCalculateAllFlagsDuringOpInt(al.getVal(), bl.getVal(), x, 1);
		al.setVal(x);
	}

	public synchronized void add(ExtendedRegister al, int memoryLocation) {
		int dat = MemoryManagementUnit.getMMU().getWordAtAddress(SegmentRegister.DS, memoryLocation);
		int x = (al.getVal() + dat);
		tempCalculateAllFlagsDuringOpInt(al.getVal(), dat, x, 1);
		al.setVal(x);
	}

	public synchronized void add(int memoryLocation, ExtendedRegister al) {
		int dat = MemoryManagementUnit.getMMU().getWordAtAddress(SegmentRegister.DS, memoryLocation);
		int x = (al.getVal() + dat);
		tempCalculateAllFlagsDuringOpInt(al.getVal(), dat, x, 1);
		MemoryManagementUnit.getMMU().putWordAtAddress(SegmentRegister.DS, memoryLocation, x);
	}

	public synchronized void addImmediate(ExtendedRegister al, int immediate) {
		int x = (al.getVal() + immediate);
		tempCalculateAllFlagsDuringOpInt(al.getVal(), immediate, x, 1);
		al.setVal(x);
	}

	public synchronized void addImmediateWord(int memoryLocation, int immediate) {
		int dat = MemoryManagementUnit.getMMU().getWordAtAddress(SegmentRegister.DS, memoryLocation);
		int x = (immediate + dat);
		tempCalculateAllFlagsDuringOpInt(immediate, dat, x, 1);
		MemoryManagementUnit.getMMU().putWordAtAddress(SegmentRegister.DS, memoryLocation, x);
	}



	



	public synchronized void sub(GeneralRegister al, GeneralRegister bl) {
		byte x = (byte) (al.getVal() - bl.getVal());
		tempCalculateAllFlagsDuringOpInt(al.getVal(), bl.getVal(), x, 2);
		if(SF == 1) {
			int y = al.getVal() - bl.getVal();
			ExtendedRegister.AX.setVal(y);
		}
		else al.setVal(x);
	}

	public synchronized void sub(GeneralRegister al, int memoryLocation) {
		byte dat = MemoryManagementUnit.getMMU().getDataAtAddress(SegmentRegister.DS, memoryLocation);
		byte x = (byte) (al.getVal() - dat);
		tempCalculateAllFlagsDuringOpInt(al.getVal(), dat, x, 2);
		if(SF == 1) {
			int y = al.getVal() - dat;
			ExtendedRegister.AX.setVal(y);
		}
		else al.setVal(x);
	}

	public synchronized void sub(int memoryLocation, GeneralRegister al) {
		byte dat = MemoryManagementUnit.getMMU().getDataAtAddress(SegmentRegister.DS, memoryLocation);
		byte x = (byte) (al.getVal() - dat);
		tempCalculateAllFlagsDuringOpInt(dat, al.getVal(), x, 2);
		if(SF == 1) {
			int y = -(al.getVal() - dat);
			MemoryManagementUnit.getMMU().putWordAtAddress(SegmentRegister.DS, memoryLocation, y);
		}
		else MemoryManagementUnit.getMMU().putDataAtAddress(SegmentRegister.DS, memoryLocation, x);
	}

	public synchronized void subImmediate(GeneralRegister al, int immediate) {
		byte x = (byte) (al.getVal() - immediate);
		tempCalculateAllFlagsDuringOpInt(al.getVal(), immediate, x, 2);
		if(SF == 1) {
			int y = al.getVal() - immediate;
			ExtendedRegister.AX.setVal(y);
		}
		else al.setVal(x);
	}

	public synchronized void subImmediate(int memoryLocation, int immediate) {
		byte dat = MemoryManagementUnit.getMMU().getDataAtAddress(SegmentRegister.DS, memoryLocation);
		byte x = (byte) (immediate - dat);
		tempCalculateAllFlagsDuringOpInt(immediate, dat, x, 2);
		if(SF == 1) {
			int y = immediate - dat;
			MemoryManagementUnit.getMMU().putWordAtAddress(SegmentRegister.DS, memoryLocation, y);
		}
		else MemoryManagementUnit.getMMU().putDataAtAddress(SegmentRegister.DS, memoryLocation, x);
	}

	public synchronized void negByte(GeneralRegister al) {
		byte x = (byte) (~al.getVal());
		tempCalculateAllFlagsDuringOpInt(al.getVal(), 0, x, 1);
		al.setVal(x);
	}

	public synchronized void cmpByte(GeneralRegister al, GeneralRegister bl) {
		byte x = (byte) (al.getVal() - bl.getVal());
		tempCalculateAllFlagsDuringOpInt(al.getVal(), bl.getVal(), x, 2);
		if(x > 0) {
			FlagRegister.CF.setVal(1);
			FlagRegister.ZF.setVal(0);
			FlagRegister.SF.setVal(1);
		}
		else if (x == 0) {
			FlagRegister.CF.setVal(0);
			FlagRegister.ZF.setVal(0);
			FlagRegister.SF.setVal(0);
		}
		else {
			FlagRegister.CF.setVal(0);
			FlagRegister.ZF.setVal(1);
			FlagRegister.SF.setVal(0);
		}
	}







	public synchronized void sub(ExtendedRegister al, ExtendedRegister bl) {
		int x = (al.getVal() - bl.getVal());
		tempCalculateAllFlagsDuringOpInt(al.getVal(), bl.getVal(), x, 2);
		al.setVal(x);
	}

	public synchronized void sub(ExtendedRegister al, int memoryLocation) {
		int dat = MemoryManagementUnit.getMMU().getWordAtAddress(SegmentRegister.DS, memoryLocation);
		int x = (al.getVal() - dat);
		tempCalculateAllFlagsDuringOpInt(al.getVal(), dat, x, 2);
		al.setVal(x);
	}

	public synchronized void sub(int memoryLocation, ExtendedRegister al) {
		int dat = MemoryManagementUnit.getMMU().getDataAtAddress(SegmentRegister.DS, memoryLocation);
		int x = (al.getVal() - dat);
		tempCalculateAllFlagsDuringOpInt(al.getVal(), dat, x, 2);
		MemoryManagementUnit.getMMU().putWordAtAddress(SegmentRegister.DS, memoryLocation, x);
	}

	public synchronized void subImmediate(ExtendedRegister al, int immediate) {
		int x = (al.getVal() - immediate);
		tempCalculateAllFlagsDuringOpInt(al.getVal(), immediate, x, 2);
		al.setVal(x);
	}

	public synchronized void subImmediateWord(int memoryLocation, int immediate) {
		int dat = MemoryManagementUnit.getMMU().getDataAtAddress(SegmentRegister.DS, memoryLocation);
		int x = (immediate - dat);
		tempCalculateAllFlagsDuringOpInt(dat, immediate, x, 2);
		MemoryManagementUnit.getMMU().putWordAtAddress(SegmentRegister.DS, memoryLocation, x);
	}

	public synchronized void negWord(ExtendedRegister al) {
		int x = (~al.getVal());
		tempCalculateAllFlagsDuringOpInt(al.getVal(), 0, x, 2);
		al.setVal(x);
	}

	public synchronized void cmpWord(ExtendedRegister al, ExtendedRegister bl) {
		int x = (al.getVal() - bl.getVal());
		tempCalculateAllFlagsDuringOpInt(al.getVal(), bl.getVal(), x, 2);
		if(x > 0) {
			FlagRegister.CF.setVal(1);
			FlagRegister.ZF.setVal(0);
			FlagRegister.SF.setVal(1);
		}
		else if (x == 0) {
			FlagRegister.CF.setVal(0);
			FlagRegister.ZF.setVal(0);
			FlagRegister.SF.setVal(0);
		}
		else {
			FlagRegister.CF.setVal(0);
			FlagRegister.ZF.setVal(1);
			FlagRegister.SF.setVal(0);
		}
	}






}
