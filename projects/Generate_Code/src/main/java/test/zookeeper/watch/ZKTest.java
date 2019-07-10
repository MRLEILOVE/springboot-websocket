package test.zookeeper.watch;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZKTest {
	
	public static void main(String[] args) {
		Watcher watcher = new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println("public void process(WatchedEvent event) { event=" + event);
				System.out.println(Watcher.Event.EventType.None == event.getType());
				System.out.println(event.getPath());
				System.out.println(event.getState());
			}
		};
		try {
			ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 1000, watcher);
			
			List<String> list_child = zk.getChildren("/", watcher);
			System.out.println("list_child=" + list_child);
			
			byte[] bArr_data = zk.getData("/", false, null);
			String str_data = new String(bArr_data);
			System.out.println("str_data=" + str_data);
			
			zk.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
	}
	
}
