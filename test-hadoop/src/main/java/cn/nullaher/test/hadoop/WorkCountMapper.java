/**
 * Copyright ©2018 simon.zeng All Rights Reserved cn.nullaher.test.hadoop.WordCountMapper.java
 * nullaher@gmail.com 2018年1月26日
 */
package cn.nullaher.test.hadoop;

import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @autor: zxy@uinpay.cn
 * @desc : ...
 */
public class WorkCountMapper extends Mapper<LongWritable , Text , Text , LongWritable> {

	@Override
	protected void map(LongWritable key , Text value , Context context)
	    throws IOException , InterruptedException{
//		super.map(key , value , context);
		String line = value.toString();
		String[] words = StringUtils.split(line , " ");
		for(String word : words){
			context.write(new Text(word) , new LongWritable(1));
		}
		
	}
	
	
//	@Override
//	protected void map(LongWritable key , Text value ,
//	    Mapper<LongWritable , Text , Text , LongWritable>.Context context)
//	    throws IOException , InterruptedException{
//		super.map(key , value , context);
//		String line = value.toString();
//		String[] words = StringUtils.split(line , " ");
//		for(String word : words){
//			context.write(new Text(word) , new LongWritable(1));
//		}
//	}
	
}
