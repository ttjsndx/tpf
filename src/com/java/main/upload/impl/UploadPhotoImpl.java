package com.java.main.upload.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springside.components.ajaxupload.MonitoredDiskFileItem;

import com.java.main.upload.UploadFileInterface;
import com.java.util.CommonUtil;

public class UploadPhotoImpl implements UploadFileInterface{
	public boolean saveFile(HashMap inputData , List list){
		for (int i = 0; i < list.size(); i++) {
			MonitoredDiskFileItem mdfi = (MonitoredDiskFileItem) list.get(i);
			if (mdfi == null){
				continue;
			}
			this.doSaveFile(mdfi,inputData);
		}
		return false;
	}
	
	/**
	 * 保存文件到应用服务器
	 * 
	 * @param mdfi
	 * @param docbodyID
	 */
	private void doSaveFile(MonitoredDiskFileItem mdfi, HashMap params) {
		String fileType = ".jpg";
		String savePath = CommonUtil.getSystemSeq() + this.getFilePath();
		String fullPath = CommonUtil.getXrProperties("acws.localdisk.dir") + savePath;
		
		String uName = this.getFileName();
		byte buffer[] = new byte[10 * 1024 * 1024];	
		InputStream stream;
		OutputStream out = null;
		try {
			stream = mdfi.getInputStream();
			try {
				out = new FileOutputStream(fullPath + uName + ".jpg");
			} catch (IOException e) {
				// 创建文件夹
				mkdir(fullPath);
				out = new FileOutputStream(fullPath + uName + ".jpg");
			}
			int bytesRead = 0;
			while ((bytesRead = stream.read(buffer, 0, 2 * 1024 * 1024)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取文件所要保存的服务器路径(相对路径) 按上传日期，分月进行存储
	 */
	private String getFilePath() {
		String seqarator = CommonUtil.getSystemSeq();//获取系统的斜杠方向
		Date d = new Date();
		String filePath = "image" +  seqarator ;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		SimpleDateFormat sf1 = new SimpleDateFormat("MM");
		SimpleDateFormat sf2 = new SimpleDateFormat("dd");
		
		filePath += sf.format(d) + seqarator;
		filePath += sf1.format(d) + seqarator;
		filePath += sf2.format(d) + seqarator;
		
		return filePath;
	}

	/**
	 * 返回当前的时间戳
	 * @return
	 */
	private String getFileName(){
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * @date 2018-4-21
	 * @author @2wl
	 * @param mkdirName
	 * @说明 创建文件夹
	 */
	public void mkdir(String mkdirName) {
		try {
			File dirFile = new File(mkdirName);
			boolean bFile = dirFile.exists();
			if (bFile) {
			} else {
				bFile = dirFile.mkdirs();
				if (bFile) {
					System.out.println("创建文件夹成功：" + mkdirName);
				} else {
					System.out.println(" 文件夹创建失败，清确认磁盘没有写保护并且空件足够：" + mkdirName);
				}
			}
		} catch (Exception err) {
			System.err.println("ELS - Chart : 文件夹创建发生异常");
			err.printStackTrace();
			throw new RuntimeException(err);
		}
	}
	
}
