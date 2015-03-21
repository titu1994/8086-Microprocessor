package microprocessor.memory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import microprocessor.execution.ExecutionUnit;
import microprocessor.execution.Instruction;

public class DMAC {
	private static final DMAC dmac = new DMAC();
	private DMAC() {};
	
	public static DMAC getDMAC() {
		return dmac;
	}
	
	public synchronized final void transferData(int memLocationFrom, int memLocationTo, int wordCount, Instruction inst) {
		ExecutionUnit.getEU().getExecturor().submit(new Runnable() {
			
			@Override
			public void run() {
				Memory m = Memory.getMemory();
				for(int i = memLocationFrom, j = memLocationTo, c = wordCount; c > 0; c--, i++, j++) {
					m.put(j, m.get(i));
				}
			}
		});
	}
	
	public synchronized final void storeData(int memLocationTo, int wordCount, Instruction inst, byte data[]) {
		ExecutionUnit.getEU().getExecturor().submit(new Runnable() {
			
			@Override
			public void run() {
				Memory m = Memory.getMemory();
				for(int i = memLocationTo, c = wordCount, j = 0; c > 0; c--, i++, j++) {
					m.put(i, data[j]);
				}
			}
		});
	}
	
	public synchronized final int[] retrieveData(int memLocationFrom, int wordCount, Instruction inst) {
		try {
			return ExecutionUnit.getEU().getExecturor().submit(new Callable<int[]>() {

				@Override
				public int[] call() throws Exception {
					int buff[] = new int[wordCount];
					Memory m = Memory.getMemory();
					for(int i = memLocationFrom, j = 0, c = wordCount; c > 0; c--, i++, j++) {
						buff[j] = m.get(i);
					}
					return buff;
				}
			}).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return new int[wordCount];
	}
	

}
