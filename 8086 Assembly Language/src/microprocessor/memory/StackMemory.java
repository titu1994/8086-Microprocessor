package microprocessor.memory;

import java.util.HashMap;
import java.util.Stack;

import microprocessor.execution.register.IndexRegister;

public class StackMemory {
	private static final StackMemory sm = new StackMemory();
	private static HashMap<Integer, Integer> stack;
	
	private StackMemory() {
		stack = new HashMap<Integer, Integer>();
	}
	
	public synchronized static final StackMemory getStack() {
		return sm;
	}
	
	public synchronized void push(int data) {
		IndexRegister.SP.setVal(IndexRegister.SP.getVal() - 2);
		stack.put(IndexRegister.SP.getVal(), data);
	}
	
	public synchronized int pop() {
		int data = stack.get(IndexRegister.SP.getVal());
		IndexRegister.SP.setVal(IndexRegister.SP.getVal() + 2);
		return data;
	}
	
}
