package com.qinyuan.cms.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.domain.Channel;

public class FileUtil {
	public static List<Article> readContent() throws Exception{
		String url="D:\\testFile";
		File file = new File(url);
		File[] listFiles = file.listFiles();
		String time="2015-02-03 12:24:58";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = format.parse(time);
		int i=0;
		ArrayList<Article> list = new ArrayList<Article>();
		for (File file2 : listFiles) {
			String title = file2.getName();
			BufferedReader bw = new BufferedReader(new FileReader(file2));
			String content="";
			String str="";
			 while((str=bw.readLine())!=null){
				content+=str;
			 }
			bw.close();
			Article article = new Article();
			article.setTitle(title.substring(0,title.length()-4));
			String newContent="";
			if(content.length()>250){
				newContent=content.substring(0,240);
			}else{
				newContent=content;
			}
			article.setContent(newContent); 
			article.setHits(new Random().nextInt(100));
			Channel channel = new Channel();
			channel.setId(new Random().nextInt(6)+1);
			article.setChannel(channel);
			article.setKeywords(title+","+WordSortUtil.wordSortDesc(newContent));
			article.setDeleted(false);
			article.setPicture("/upload/2afd6aca2c4147d0b34c31184c67569d.jpg");
			article.setHot(new Random().nextBoolean());
			Date date2 = new Date(date.getTime()+i*7*60*60*1000);
			article.setCreated(date2);
			String summary="";
			if(content.length()>140){
				summary=content.substring(0,140);
			}else{
				summary=content;
			}
			article.setSummary(summary);
			list.add(article);
			i++;
		}
		return list;
	}
}
