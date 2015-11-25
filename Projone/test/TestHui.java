import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * This file created at 2015-6-7.
 *
 * Copyright (c) 2002-2015 Bingosoft, Inc. All rights reserved.
 */

/**
 * <code>{@link TestHui}</code>
 *
 * TODO : document me
 *
 * @author Administrator
 */
public class TestHui {

	static String outputfilename = "F:\\hui_outputdir";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO implement TestHui.main
		createFile();
//		deleteFile(new File(outputfilename));
	}
	
	public static void createFile(){
		String[] dir = {"F:\\June","F:\\May"};
		for(int i=18;i<32;i++){			
			File flist = new File(dir[1]+"\\"+i);
			fileCheck(flist);
		}
		File flist2 = new File(dir[0]);
		fileCheck(flist2);
	}
	
	public static void fileCheck(File f){
		if(f.isDirectory()){
			File[] tmp = f.listFiles();
			for(File fil:tmp){
				fileCheck(fil);
			}
		}else{
			if(f.getName().startsWith("100")){
				copyFile(f.getPath(),outputfilename+"\\type1\\"+f.getName());
			}else if(f.getName().startsWith("300")){
				copyFile(f.getPath(),outputfilename+"\\type2\\"+f.getName());
			}else{
				copyFile(f.getPath(),outputfilename+"\\type3\\"+f.getName());
			}
		}
		
	}
	
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
			System.out.println(oldPath+"\t"+newPath);
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	public static void deleteFile(File f){
		if(f.isDirectory()){
			File[] tmp = f.listFiles();
			for(File fil:tmp){
				deleteFile(fil);
			}
		}else{
			f.delete();
		}
	}
}
