package com.qinyuan.cms.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class StreamUtil {
	
	/**
	 * 1. 关闭流
	 * @param inputStreams
	 */
	public static void closeAll(InputStream ... inputStreams){
		for (InputStream inputStream : inputStreams) {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 2. 复制流 
	 * @param srcPathStr
	 * @param desPathStr
	 */
    public static void copyFile(FileInputStream fis, FileOutputStream fos,Boolean inclose,Boolean outclose) {

        try{
            //创建搬运工具
            byte datas[] = new byte[1024*8];
            //创建长度
            int len = 0;
            //循环读取数据
            while((len = fis.read(datas))!=-1){
                fos.write(datas,0,len);
            }
            //3.释放资源
            if(inclose.equals(true)){
            	fis.close();
            	System.out.println("输入流已关闭");
            }else{
            	System.out.println("输入流未关闭");
            }
            if(outclose.equals(true)){
            	fis.close();
            	System.out.println("输出流已关闭");
            }else{
            	System.out.println("输出流未关闭");
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
	
	/**
	 * 3. 读取文本文件
	 * @param txtFile
	 * @return
	 */
	public static String readStrFile(File txtFile){
		String newContent="";
		InputStream fileInputStream = null;
        BufferedReader br = null;
        //StringBuffer sb = new StringBuffer();
        try {
        	fileInputStream = new FileInputStream(txtFile);
            br = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            /*String readLine = null;
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }*/
            String content="";
			String str="";
			 while((str=br.readLine())!=null){
				content+=str;
			 }
			br.close();
			if(content.length()>250){
				newContent=content.substring(0,240);
			}else{
				newContent=content;
			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newContent;
		
	}
	
	
	/**
	 * 4. 按行读取文本文件
	 * @param txtFile
	 * @return
	 */
	public static List<String> readLineFile(File txtFile){
		InputStream fileInputStream = null;
        BufferedReader br = null;
        LinkedList<String> lineList = new LinkedList<String>();
        try {
        	fileInputStream = new FileInputStream(txtFile);
            br = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
            	lineList.add(readLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lineList;
		
	}
	
	
	
    /**
     * 5. 写入文本文件
     */
    public static void writeFile(String str,File file) {
        try {
        	if(!file.exists()){
        		file.createNewFile();   // 如果文件不存在则创建新文件,有同名的文件的话直接覆盖
        	}
        	FileWriter writer = new FileWriter(file,true);
            BufferedWriter out = new BufferedWriter(writer);
            out.write(str);    // \r\n即为换行
            out.flush();       // 把缓存区内容压入文件
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 6. 网络文件下载
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
        URL url = new URL(urlStr);  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
                //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();  
        //获取自己数组
        byte[] getData = readInputStream(inputStream);    

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);    
        FileOutputStream fos = new FileOutputStream(file);     
        fos.write(getData); 
        if(fos!=null){
            fos.close();  
        }
        if(inputStream!=null){
            inputStream.close();
        }
        System.out.println("info:"+url+" download success"); 

    }
    
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        return bos.toByteArray();  
    }
    
    
    
    

    public void downloadLocal(HttpServletResponse response,Boolean inclose,Boolean httpclose) throws FileNotFoundException {
        // 下载本地文件
        String fileName = "test.txt".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("F:\\test\\test.txt");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
