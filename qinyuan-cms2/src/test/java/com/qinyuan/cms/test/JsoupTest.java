package com.qinyuan.cms.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.qinyuan.cms.BaseTestCase;
import com.qinyuan.cms.utils.SSL;

public class JsoupTest extends BaseTestCase{
	
	
	@Test
	public void jsoupTest() throws IOException{
		 SSL.trustEveryone();
		try {
			Document document = Jsoup.connect("https://news.163.com/").get();
			String html = document.html();
			Document parse = Jsoup.parse(html);
			Elements s = parse.select("a[href]");
			Pattern pattern = Pattern.compile("[\\\\/:*?\"<>|]");
			if(s!=null){
				for (Element a : s) {
					String url = a.attr("href");
					if(url.substring(0,5).equals("https")){
						if(url.indexOf("html")!=-1){
							Document connect = Jsoup.connect(url).get();
							String title = connect.title();
							if(connect.getElementById("endText")!=null){
								String text = connect.getElementById("endText").text();
								String replaceAll = pattern.matcher(title).replaceAll("");
								BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\testFile\\"+replaceAll+".txt"));
								bw.write(text);
								bw.newLine();
								bw.flush();
								bw.close();
							}
						}
						
					}
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
