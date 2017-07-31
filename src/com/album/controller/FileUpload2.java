package com.album.controller;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.albumimg.model.AlbumImg;
import com.albumimg.model.AlbumImgService;

@WebServlet("/FileUpload2")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024
* 1024)
public class FileUpload2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		AlbumImgService aImgSvc=new AlbumImgService();
		Collection<Part> parts = request.getParts();
		HttpSession session=request.getSession();
		LinkedList<Integer> imgNos=null;
		
		
		
			for (Part part:parts) {
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {

					Timestamp currentTime = new Timestamp(System.currentTimeMillis());
					Integer imgNo=aImgSvc.addAlbumImg2(1,part.getName() , "為此找片新增點描述吧", currentTime, currentTime,
							part.getName(), part.getContentType(), getPictureByteArray(part.getInputStream()));
					imgNos=(LinkedList<Integer>)session.getAttribute("imgNos");
					if(imgNos==null){
						List<Integer> imgNoss=new LinkedList<Integer>();
						imgNoss.add(imgNo);
						session.setAttribute("imgNos", imgNoss);
					}
					else{
					imgNos.add(imgNo);
					session.setAttribute("imgNos", imgNos);
					}
				}
		} 

			
		System.out.println("123456");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "ok");
		response.getWriter().write(jsonObject.toString());
			
	}
	

	public static byte[] getPictureByteArray(InputStream fis) throws IOException {
		BufferedImage originalImage = ImageIO.read(fis);
		int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		BufferedImage resizeImageJpg = resizeImage(originalImage, type);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(resizeImageJpg, "jpg", baos);
		baos.flush();
		baos.close();
		return baos.toByteArray();
	}
	
	
	private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(400, 300, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 400, 300, null);
		g.dispose();

		return resizedImage;
	}
	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
