package microprocessor.memory;

import microprocessor.execution.IndexRegister;

public class MemoryManagementUnit {
	private static final MemoryManagementUnit mmu = new MemoryManagementUnit();
	private static Memory memory;

	private MemoryManagementUnit() {memory = Memory.getMemory();}

	public synchronized static MemoryManagementUnit getMMU() {
		return mmu;
	}

	public synchronized int getPhysicalAddress(SegmentRegister segmentRegs, int logicalAddress) {
		int phyAddress = 0;
		phyAddress = segmentRegs.getVal() * 10 + logicalAddress;
		return phyAddress;
	}
	
	public synchronized int getPhysicalAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, int logicalOffset) {
		int phyAddress = 0;
		phyAddress = segmentRegs.getVal() * 10 + indexRegs.getVal() + logicalOffset;
		return phyAddress;
	}
	
	public synchronized int getPhysicalAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, byte logicalOffset) {
		int phyAddress = 0;
		phyAddress = segmentRegs.getVal() * 10 + indexRegs.getVal() + logicalOffset;
		return phyAddress;
	}
	
	public synchronized int getPhysicalAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset) {
		int phyAddress = 0;
		phyAddress = segmentRegs.getVal() * 10 + indexRegs.getVal() + baseOffset.getVal();
		return phyAddress;
	}
	
	public synchronized int getPhysicalAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset, int logicalOffset) {
		int phyAddress = 0;
		phyAddress = segmentRegs.getVal() * 10 + indexRegs.getVal() + baseOffset.getVal() + logicalOffset;
		return phyAddress;
	}
	
	public synchronized int getPhysicalAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset, byte logicalOffset) {
		int phyAddress = 0;
		phyAddress = segmentRegs.getVal() * 10 + indexRegs.getVal() + baseOffset.getVal() + logicalOffset;
		return phyAddress;
	}
	
	
	
	
	
	
	
	public synchronized int putDataAtAddress(SegmentRegister segmentRegs, int logicalAddress, byte val) {
		int phyAddress = getPhysicalAddress(segmentRegs, logicalAddress);
		memory.put(phyAddress, val);
		return phyAddress;
	}
	
	public synchronized int putDataAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, int logicalOffset, byte val) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, logicalOffset);
		memory.put(phyAddress, val);
		return phyAddress;
	}
	
	public synchronized int putDataAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, byte logicalOffset, byte val) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, logicalOffset);
		memory.put(phyAddress, val);
		return phyAddress;
	}
	
	public synchronized int putDataAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset, byte val) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, baseOffset);
		memory.put(phyAddress, val);
		return phyAddress;
	}
	
	public synchronized int putDataAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset, int logicalOffset, byte val) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, baseOffset, logicalOffset);
		memory.put(phyAddress, val);
		return phyAddress;
	}
	
	public synchronized int putDataAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset, byte logicalOffset, byte val) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, baseOffset, logicalOffset);
		memory.put(phyAddress, val);
		return phyAddress;
	}
	
	
	
	
	
	
	public synchronized byte getDataAtAddress(SegmentRegister segmentRegs, int logicalAddress) {
		int phyAddress = getPhysicalAddress(segmentRegs, logicalAddress);
		byte val = memory.get(phyAddress);
		return val;
	}
	
	public synchronized byte getDataAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, int logicalOffset) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, logicalOffset);
		byte val = memory.get(phyAddress);
		return val;
	}
	
	public synchronized byte getDataAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, byte logicalOffset) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, logicalOffset);
		byte val = memory.get(phyAddress);
		return val;
	}
	
	public synchronized byte getDataAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, baseOffset);
		byte val = memory.get(phyAddress);
		return val;
	}
	
	public synchronized byte getDataAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset, int logicalOffset) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, baseOffset, logicalOffset);
		byte val = memory.get(phyAddress);
		return val;
	}
	
	public synchronized byte getDataAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset, byte logicalOffset) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, baseOffset, logicalOffset);
		byte val = memory.get(phyAddress);
		return val;
	}
	
	
	
	
	
	
	public synchronized int putWordAtAddress(SegmentRegister segmentRegs, int logicalAddress, int val) {
		int phyAddress = getPhysicalAddress(segmentRegs, logicalAddress);
		memory.putWord(phyAddress, val);
		return phyAddress;
	}
	
	public synchronized int putWordAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, int logicalOffset, int val) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, logicalOffset);
		memory.putWord(phyAddress, val);
		return phyAddress;
	}
	
	public synchronized int putWordAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, byte logicalOffset, int val) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, logicalOffset);
		memory.putWord(phyAddress, val);
		return phyAddress;
	}
	
	public synchronized int putWordAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset, int val) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, baseOffset);
		memory.putWord(phyAddress, val);
		return phyAddress;
	}
	
	public synchronized int putWordAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset, int logicalOffset, int val) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, baseOffset, logicalOffset);
		memory.putWord(phyAddress, val);
		return phyAddress;
	}
	
	public synchronized int putWordAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset, byte logicalOffset, int val) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, baseOffset, logicalOffset);
		memory.putWord(phyAddress, val);
		return phyAddress;
	}
	
	
	
	
	

	
	public synchronized int getWordAtAddress(SegmentRegister segmentRegs, int logicalAddress) {
		int phyAddress = getPhysicalAddress(segmentRegs, logicalAddress);
		return memory.getWord(phyAddress);	
	}
	
	public synchronized int getWordAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, int logicalOffset) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, logicalOffset);
		return memory.getWord(phyAddress);	
	}
	
	public synchronized int getWordAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, byte logicalOffset) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, logicalOffset);
		return memory.getWord(phyAddress);	
	}
	
	public synchronized int getWordAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, baseOffset);
		return memory.getWord(phyAddress);	
	}
	
	public synchronized int getWordAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset, int logicalOffset) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, baseOffset, logicalOffset);
		return memory.getWord(phyAddress);	
	}
	
	public synchronized int getWordAtAddress(SegmentRegister segmentRegs, IndexRegister indexRegs, IndexRegister baseOffset, byte logicalOffset) {
		int phyAddress = getPhysicalAddress(segmentRegs, indexRegs, baseOffset, logicalOffset);
		return memory.getWord(phyAddress);	
	}
	
}
