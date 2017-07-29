package com.insertDummyBlob;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.actImg.model.ActImg;
import com.actImg.model.ActImgJDBCDAO;
import com.activity.model.Activity;
import com.activity.model.ActivityJDBCDAO;
import com.ad.model.Ad;
import com.ad.model.AdJDBCDAO;
import com.album.model.Album;
import com.album.model.AlbumJDBCDAO;
import com.albumimg.model.AlbumImg;
import com.albumimg.model.AlbumImgJDBCDAO;
import com.dateitem.model.DateItemJDBCDAO;
import com.dateitem.model.DateItemVO;
import com.member.model.Member;
import com.member.model.MemberJDBCDAO;
import com.pet.model.Pet;
import com.pet.model.PetJDBCDAO;
import com.product.model.Product;
import com.product.model.ProductJDBCDAO;
import com.restImg.model.RestImg;
import com.restImg.model.RestImgJDBCDAO;
import com.slide.model.Slide;
import com.slide.model.SlideJDBCDAO;

public class InsertDummyBlob2 {
	static int fixed_width=400;
	static int fixed_height=300;
	
	public static void main(String[] args) {

   
    
	// 餐廳會員照片修改
	int r = 7001;
    for (File file : new File("WebContent/DummyImg/restImg").listFiles()) { 
    	RestImgJDBCDAO restImgJDBCDAO=new RestImgJDBCDAO();
        RestImg restImg=restImgJDBCDAO.findByPK(r++);
        try {
			byte[] b = getPictureByteArray(file);
			restImg.setRestImg(b);
			restImgJDBCDAO.update(restImg);
		} catch (IOException e) {
			e.printStackTrace();
		}    
    }
    System.out.println("=============餐廳會員照片新增完畢================");

    
    // 發起活動照片修改
    int j = 8001;
    for (File file : new File("WebContent/DummyImg/activityInitImg").listFiles()) { 
    	ActivityJDBCDAO activityDAO=new ActivityJDBCDAO();
    	Activity activity=activityDAO.findByPK(j++);
        try {
			byte[] b = getPictureByteArray(file);
			activity.setActInitImg(b);
			activityDAO.update(activity);
		} catch (IOException e) {
			e.printStackTrace();
		}    
    }
    System.out.println("=============發起活動照片新增完畢================");


 // 活動照片修改
    int k = 8001;
    for (File file : new File("WebContent/DummyImg/actImg").listFiles()) { 
    	ActImgJDBCDAO actImgJDBCDAO=new ActImgJDBCDAO();
    	ActImg actImg=actImgJDBCDAO.findByPK(k++);
        try {
			byte[] b = getPictureByteArray(file);
			actImg.setActImg(b);
			actImgJDBCDAO.update(actImg);
		} catch (IOException e) {
			e.printStackTrace();
		}    
    }
    System.out.println("=============活動照片新增完畢================");
    
    
	// 廣告圖片修改
	int aa = 1;
	for (File file : new File("WebContent/DummyImg/ad").listFiles()) {
		AdJDBCDAO dao = new AdJDBCDAO();
		Ad ad = dao.findByPrimaryKey(aa++);
		try {
			byte[] b = getPictureByteArray(file);
			ad.setAdImg(b);
			dao.update(ad);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	System.out.println("=============廣告圖片修改完了================");

	// 幻燈片修改
	int s = 1;
	for (File file : new File("WebContent/DummyImg/slide").listFiles()) {
		SlideJDBCDAO dao = new SlideJDBCDAO();
		Slide slide = dao.findByPrimaryKey(s++);
		try {
			byte[] b = getPictureByteArray(file);
			slide.setSlideImg(b);
			dao.update(slide);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	System.out.println("=============幻燈片修改完了================");
    
	// 約會商品圖片修改
	int di=4001;
    for (File file : new File("WebContent/DummyImg/dateitem").listFiles()) { 
    	DateItemJDBCDAO dao=new DateItemJDBCDAO();
    	DateItemVO dateItem=dao.findByPk(di++);
        try {
			byte[] b = getPictureByteArray(file);
			dateItem.setDateItemImg(b);
			dao.update(dateItem);
		} catch (IOException e) {
			e.printStackTrace();
		}    
    }
    System.out.println("=============約會商品圖片修改================");   

    
	}

	
	private static BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = new BufferedImage(fixed_width, fixed_height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, fixed_width, fixed_height, null);
		g.dispose();

		return resizedImage;
	    }
	
	
	public static byte[] getPictureByteArray(File file) throws IOException {
		BufferedImage originalImage = ImageIO.read(file);
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		BufferedImage resizeImageJpg = resizeImage(originalImage, type);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( resizeImageJpg, "jpg", baos );
		baos.flush();
		baos.close();

		return baos.toByteArray();
	}
	
}
