public class Test {

	static void showAllThread(String prefix) {
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		while (tg.getParent() != null) {
			tg = tg.getParent();
		}
		int i_activeCount = tg.activeCount();
		System.out.println(prefix + "\t\ti_activeCount=" + i_activeCount);
		Thread threads[] = new Thread[i_activeCount];
		int i_enumerate = tg.enumerate(threads);
		System.out.println(prefix + "\t\ti_enumerate=" + i_enumerate);
		for (int i = 0; i < i_activeCount; i++) {
			System.out.println(prefix + "\t\ti=" + i + ", 线程号：" + threads[i].getId() + ", 线程名：" + threads[i].getName());
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		showAllThread("111111111111");
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				Thread t = Thread.currentThread();
				for (int i = 0; i < 10; i++) {
					System.out.println("t.getId()=" + t.getId() + ", t.getName()=" + t.getName() + ", i=" + i);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "name=haha");
		t.start();
		
		showAllThread("222222222222");
		
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
