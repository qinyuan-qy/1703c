package com.qinyuan.cms.test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.kafka.core.KafkaTemplate;

import com.alibaba.fastjson.JSON;
import com.qinyuan.cms.BaseTestCase;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.utils.FileUtil;
import com.qinyuan.cms.utils.StreamUtil;

public class ReadFileTest extends BaseTestCase{
	
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@SuppressWarnings("all")
	@Test
	public void ReadFile() throws Exception{
		/*File file = new File("D:\\testFile");
		File[] files = file.listFiles();
		for (File file2 : files) {
			String name = file2.getName();
			String string = name.substring(0,name.length()-4);
			BufferedReader reader = new BufferedReader(new FileReader(file2));
			String context = reader.readLine();
			System.out.println(string);
		}*/
		List<Article> readContent = FileUtil.readContent();
		for (Article article : readContent) {
			kafkaTemplate.send("test", "hh", JSON.toJSONString(article));
		}
		
	}
	
	@SuppressWarnings("all")
	@Test
	public void ReadFile1() throws Exception{
		File file = new File("D:\\testFile");
		File[] files = file.listFiles();
		for (File file2 : files) {
			String list = StreamUtil.readStrFile(file2);
			System.out.println(list);
		}
		
	}
		private static Date randomDate(String beginDate,String endDate) throws Exception{
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	            Date start = format.parse(beginDate);
	            Date end = format.parse(endDate);
		        long rtn = start.getTime() + (long)(Math.random() * (end.getTime() - start.getTime()));
		        Date date = new Date(rtn);
				return date;
	    }
	    
	    public static void main(String[] args) throws Exception {
	        for (int i=0;i<30;i++){
	            Date date = randomDate("2019-01-01","2019-01-31");
	            System.out.println(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(date));
	        }
	    }
	
	
}
