/**
 * Copyright ©2018 simon.zeng All Rights Reserved cn.nullaher.test.hadoop.WorkCountReduce.java
 * nullaher@gmail.com 2018年1月26日
 */
package cn.nullaher.test.hadoop;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @autor: zxy@uinpay.cn
 * @desc : ...
 */
public class WorkCountReducer extends Reducer<Text , LongWritable , Text , LongWritable> {
	
	@Override
	protected void reduce(Text key , Iterable<LongWritable> values , Context context)
	    throws IOException , InterruptedException{
		// super.reduce(arg0 , arg1 , arg2);
		long count = 0;
		for(LongWritable value : values){
			count += value.get();
		}
		context.write(key , new LongWritable(count));
	}
	
	// @Override
	// protected void reduce(Text key , Iterable<LongWritable> values ,
	// Reducer<Text , LongWritable , Text , LongWritable>.Context context)
	// throws IOException , InterruptedException{
	// long count = 0;
	// for(LongWritable value : values){
	// count += value.get();
	// }
	// context.write(key , new LongWritable(count));
	// }
	
}
