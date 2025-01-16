class MyThread<T> extends Thread {
	
	private Function<T, void> task ; 
	private T args; 

	public MyThread (Function<T, void> task, T args) {
		this.task = task; 
		this.args = params; 
	}  

	@Override public void run () { 
		task.apply(args);	
	} 
} 
