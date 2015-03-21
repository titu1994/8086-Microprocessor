package microprocessor.execution;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutionUnit {
	private static final ExecutorService executor = Executors.newCachedThreadPool();
	private static final ExecutionUnit eu = new ExecutionUnit();

	private ExecutionUnit() {};

	public static final ExecutionUnit getEU() {
		return eu;
	}
	
	public synchronized final ExecutorService getExecturor() {
		return executor;
	}

	public synchronized static final void endExecution() {
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (Exception e) {}
	}
}
