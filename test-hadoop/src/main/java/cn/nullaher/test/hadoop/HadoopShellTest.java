package cn.nullaher.test.hadoop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.junit.Before;
import org.junit.Test;

/**
 * Hello world!
 *
 */

public class HadoopShellTest {
	FileSystem fs;
	
	private String svrUrl;
	
	@Before
	public void init() throws IOException , InterruptedException , URISyntaxException{
		Configuration config = new Configuration();
		//local
		svrUrl = "hdfs://localhost:9000/";
		config.set("fs.defaultFS" , svrUrl);
		fs = FileSystem.get(config);
		
		//remote
		svrUrl = "hdfs://svr1:9000/";
		config.set("fs.defaultFS" , svrUrl);
		fs = FileSystem.get(new URI(svrUrl) , config , "hadoop");
	}
	
	@Test
	public void upload() throws IOException{
		FileInputStream ins = new FileInputStream(new File("localdir/hello.txt"));
		Path path = new Path(svrUrl + "testwc/hello.txt");
		// Path path = new Path(svrUrl + "user/simon/hello.txt");
		FSDataOutputStream os = fs.create(path);
		IOUtils.copy(ins , os);
	}
	
	@Test
	public void upload2() throws IOException{
		fs.copyFromLocalFile(new Path("localdir/hello.txt") , new Path(svrUrl + "user/hadoop/hello.txt"));
	}
	
	@Test
	public void download() throws IllegalArgumentException , IOException{
		fs.copyToLocalFile(new Path(svrUrl + "testdir2/hello.txt") , new Path("localdir/rmt-hello.txt"));
	}
	
	@Test
	public void listfile() throws FileNotFoundException , IllegalArgumentException , IOException{
		RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/") , true);
		while(files.hasNext()){
			LocatedFileStatus file = files.next();
			System.out.println(file.getPath());
		}
	}
	
	@Test
	public void mkdir() throws IllegalArgumentException , IOException{
		fs.mkdirs(new Path("/user/simon"));
	}
	
	@Test
	public void delete() throws IllegalArgumentException , IOException{
		System.out.println(fs.delete(new Path("/testdir2") , true));
		System.out.println(fs.delete(new Path("/hello.txt") , true));
		System.out.println(fs.delete(new Path("/hello2.txt") , true));
		System.out.println(fs.delete(new Path("/hello3.txt") , true));
	}
	
	public void testRPC(){
		Configuration conf = new Configuration();
		Builder builder = new RPC.Builder(conf);
		builder.setBindAddress("svr1").setPort(9000).setProtocol(getClass());
	}
}
