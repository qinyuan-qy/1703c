package com.qinyuan.cms.test;

import java.io.File;
import java.util.List;

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
	private KafkaTemplate kafkaTemplate;
	
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
}
