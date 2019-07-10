package test.thread;

public class Test {

	public static void main(String[] args) throws Exception {
		Thread t_m = Thread.currentThread();
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (t_m) {
					t_m.notify();
				}
			}
		}).start();
		System.out.println("111");
		synchronized (t_m) {
			t_m.wait();
		}
		System.out.println("222");
	}

}
