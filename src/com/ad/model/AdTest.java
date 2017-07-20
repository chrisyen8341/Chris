package com.ad.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class AdTest {

	public static void main(String[] args) throws IOException {
		AdJDBCDAO dao = new AdJDBCDAO();

//		 新增
		 Ad ad1 = new Ad();
		 ad1.setAdContent("廣告內容在這邊");
		 byte[] adImg = getPictureByteArray("C:\\Users\\cuser\\DownloadsSHOW-ME-THE-CODE-.jpg");
		 ad1.setAdImg(adImg);
		 ad1.setAdOnline(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
		 ad1.setAdOffline(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
		 ad1.setAdFee(100.00);
		 dao.insert(ad1);
		 System.out.println("新增完惹");

		// 修改
		// Ad ad2 = new Ad();
		// ad2.setAdContent("修改的廣告內容");
		// byte[] adImg =
		// getPictureByteArray("C:\\Users\\Phate\\Downloads\\IMG_4099.jpg");
		// ad2.setAdImg(adImg);
		// ad2.setAdOnline(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
		// ad2.setAdOffline(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
		// ad2.setAdFee(200.00);
		// ad2.setAdNo(2);
		// dao.update(ad2);
		// System.out.println("修改完惹");

		// 刪除
		// dao.delete(1);
		// System.out.println("刪掉惹,糟糕");

//		 查詢
//		 Ad ad3 = dao.findByPrimaryKey(1);
//		 System.out.println(ad3.getAdNo());
//		 System.out.println(ad3.getAdContent());
//		 System.out.println(ad3.getAdImg());
//		 System.out.println(ad3.getAdOnline());
//		 System.out.println(ad3.getAdOffline());
//		 System.out.println(ad3.getAdFee());
//		 System.out.println("找完惹");

		// 查全部
//		List<Ad> adList = dao.getAll();
//		for (Ad ad : adList) {
//			System.out.println(ad.getAdNo());
//			System.out.println(ad.getAdContent());
//			System.out.println(ad.getAdImg());
//			System.out.println(ad.getAdOnline());
//			System.out.println(ad.getAdOffline());
//			System.out.println(ad.getAdFee());
//			System.out.println("===================");
//		}

	}

	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

}