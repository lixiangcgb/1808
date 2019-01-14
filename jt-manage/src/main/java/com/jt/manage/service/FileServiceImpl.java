package com.jt.manage.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;

@Service
public class FileServiceImpl implements FileService {
	
	private String localDirPath = "E:/jt-upload";
	private String localUrlPath = "http://image.jt.com/";
	
	/**
	 * 木马.exe.jpg
	 * 实现步骤和思路
	 * 	1.校验图片的类型 (jpg|png|gif)
	 *  2.防止恶意程序    获取宽度和高度
	 *  3.为了减少图片数量,一般采用分文件存储的方式,提高检索效率
	 *  4.为了防止文件重名,则动态的生成文件名称
	 */
	@Override
	public PicUploadResult uploadFile(MultipartFile uploadFile) {
		PicUploadResult result = new PicUploadResult();
		//1.判断文件类型   abc.jpg JPG
		String fileName = uploadFile.getOriginalFilename();
		fileName = fileName.toLowerCase(); //将字符都转化为小写
		//2.使用正则表达式判断
		if(!fileName.matches("^.*\\.(jpg|png|gif)$")){
			//表示不是图片
			result.setError(1);
			return result;
		}
		
		//3.判断是否为恶意程序
		try {
			
		BufferedImage bufferedImage = 
				ImageIO.read(uploadFile.getInputStream());
		
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		
		
		//判断是否为图片
		if(width == 0 || height == 0){
			
			result.setError(1);
			return result;
		}
		
		
		/**
		 * 3.进行分文件存储
		 * 	1.按照日期划分   yyyy/MM/dd
		 *  2.hash 32位置 8/8/8/8
		 *  3.随机数 2个数字1个文件夹 3级   XX/XX/XX
		 *  动态生成文件名称: UUID + 随机数3位 + .jpg
		 *   
		 */
		
		//3.生成日期文件夹   2018/11/11
		String dateDir = 
		new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		//3.1判断文件夹是否存在 e:jt-upload/yyyy/MM/dd
		String dirFilePath = localDirPath + "/" + dateDir;
		File dirFile = new File(dirFilePath);
		
		if(!dirFile.exists()){
			
			//如果文件夹不存在,则新建
			dirFile.mkdirs();
		}
		
		
		//4.1生成UUID
		String uuid = UUID.randomUUID().toString()
				.replace("-", "");
		//4.2生成随机数
		int randomNum = new Random().nextInt(1000);
		
		//4.3截取文件后缀   .jpg
		String fileType = 
				fileName.substring(fileName.lastIndexOf("."));
		
		//4.4获取文件名称
		String imageFileName = uuid + randomNum + fileType;
		
		//E:jt-upload/yyyy/MM/dd/uuidxxx.jpg
		uploadFile.transferTo(new File(dirFilePath+"/" + imageFileName));
		
		//处理返回值
		result.setWidth(width+"");
		result.setHeight(height+"");
		
		//封装url http://image.jt.com/yyyy/MM/dd/abc.jpg
		String url = localUrlPath + dateDir + "/" + imageFileName;
		result.setUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(1);//表示图片有问题
			return result;
		}
		
		return result;
	}
}
