package cn.nullaher.test.hbase;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos.Get;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos.Result;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos.Scan;
import org.apache.hadoop.hbase.protobuf.generated.ZooKeeperProtos.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.ipc.Server.Connection;
import org.apache.zookeeper.Op.Delete;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args){
		System.out.println("Hello World!");
		
		Configuration config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum" , "svr1:2181,svr2:2181,svr3:2181");
		config.set("hbase.rootdir" , "hdfs://ns1/hbase");
		TableName.valueOf("person");
		
		// HBaseAdmin admin = new HBaseAdmin(conf);
		
	}
}
