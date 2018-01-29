/**
 * 
 * Copyright ©2018 simon.zeng All Rights Reserved cn.nullaher.test.hadoop.WorkRunner.java
 * nullaher@gmail.com 2018年1月26日
 */
package cn.nullaher.test.hadoop;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @autor: zxy@uinpay.cn
 * @desc : ...
 */
public class WorkRunner {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
//		System.setProperty("hadoop.home.dir","C:/Users/simon/git/test/test-hadoop/hadoop-2.7.5" );
		
		Configuration conf = new Configuration();//
//		conf.set("fs.defaultFS" , "hdfs://svr1:9000/");
//		conf.set("fs.defaultFS" , "hdfs://localhost:9000/");
//		conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
		Job wkjob = Job.getInstance(conf);
		wkjob.setJarByClass(WorkRunner.class);
		wkjob.setMapperClass(WorkCountMapper.class);
		wkjob.setReducerClass(WorkCountReducer.class);
		
		wkjob.setOutputKeyClass(Text.class);
		wkjob.setOutputValueClass(LongWritable.class);	
		
		wkjob.setMapOutputKeyClass(Text.class);
		wkjob.setMapOutputValueClass(LongWritable.class);
		
		FileInputFormat.setInputPaths(wkjob ,new Path("hdfs://svr1:9000/testwc/hello.txt"));
		FileOutputFormat.setOutputPath(wkjob ,new Path("hdfs://svr1:9000/testwc/result3"));
		
//		String inputUrl="C:/Users/simon/git/test/test-hadoop/localdir/hello.txt";
//		String outputUrl="C:/Users/simon/git/test/test-hadoop/localdir/result";
//		FileInputFormat.setInputPaths(wkjob ,new Path(inputUrl));
//		FileOutputFormat.setOutputPath(wkjob ,new Path(outputUrl));
		
		
		wkjob.waitForCompletion(true);
	}
}
