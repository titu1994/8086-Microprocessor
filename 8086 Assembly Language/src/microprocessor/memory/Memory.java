package microprocessor.memory;

import java.util.HashMap;

public class Memory {
	private static final Memory obj = new Memory();
	private static HashMap<Integer, Byte> memory;
	public static int MEMORY_SPACE = 1048576;
	
	private Memory() {
		memory = new HashMap<Integer, Byte>(MEMORY_SPACE);
		for(int i = 0; i < MEMORY_SPACE; i++) 
			memory.put(i, (byte) 0);
	}
	
	public static Memory getMemory() {
		return obj;
	}
	
	public synchronized final void put(int memLocation, byte memValue) {
		memory.put((memLocation % MEMORY_SPACE), memValue);
	}
	
	public synchronized final byte get(int memLocation) {
		return memory.get((memLocation % MEMORY_SPACE));
	}
	
	public synchronized final void putWord(int memLocation, int memValue) {
		memory.put((memLocation % MEMORY_SPACE), (byte) (memValue >>> 8));
		memory.put(((memLocation + 1) % MEMORY_SPACE), (byte) (memValue));
	}
	
	public synchronized final int getWord(int memLocation) {
		byte val = memory.get((memLocation % MEMORY_SPACE));
		byte val2 = memory.get(((memLocation + 1) % MEMORY_SPACE));
		int data = (val << 8) | (val2 & 0xFF);
		return data;
	}
}
