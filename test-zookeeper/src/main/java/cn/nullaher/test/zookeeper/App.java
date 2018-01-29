package cn.nullaher.test.zookeeper;

import java.io.IOException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args){
		String connectionString = "192.168.3.51:2181,192.168.3.52:2181,192.168.3.53:2181";
		int sessionTimeout = 1000;
		try{
			ZooKeeper zk = new ZooKeeper(connectionString , sessionTimeout , null);
			zk.create("/a" , "testRoot".getBytes() , ZooDefs.Ids.OPEN_ACL_UNSAFE ,
			    CreateMode.PERSISTENT);
		}catch(IOException e){
			e.printStackTrace();
		}catch(KeeperException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
