package microprocessor.memory;

import java.util.HashMap;

public class IdentifierMemory {
	private static HashMap<String, Integer> identifier;
	private final static IdentifierMemory id = new IdentifierMemory();
	
	private IdentifierMemory() {
		identifier = new HashMap<String, Integer>();
	}
	
	public synchronized static IdentifierMemory getIDMemory() {
		return id;
	}
	
	public synchronized void putWord(String id, int data) {
		identifier.put(id, data);
	}
	
	public synchronized void putDoubleWord(String id, int data[]) {
		identifier.put(id, data[0]);
		identifier.put(id + "2", data[1]);
	}
	
	public synchronized int getWord(String id) {
		int x = identifier.get(id);
		return x;
	}
	
	public synchronized int[] getDoubleWord(String id) {
		int x[] = new int[2];
		x[0] = identifier.get(id);
		x[1] = identifier.get(id + "2");
		return x;
	}
}
