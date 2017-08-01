package com.album.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.album.model.Album;
import com.album.model.AlbumService;
import com.albumimg.model.AlbumImg;
import com.albumimg.model.AlbumImgService;
import com.member.model.Member;

@WebServlet("/front_end/album/Album.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024
		* 1024)
public class AlbumC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");
		AlbumService albumSvc = new AlbumService();
		AlbumImgService aImgSvc=new AlbumImgService();
		String action = req.getParameter("action");

		

		//新增相簿
		if ("createAlbum".equals(action)) {
			List<AlbumImg> aImgs = new LinkedList<AlbumImg>();
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());

			String albumTitle = req.getParameter("albumTitle");

			Collection<Part> parts = req.getParts();
			for (Part part : parts) {
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {
					AlbumImg aImg = new AlbumImg();
					aImg.setImgTitle("為此找片新增點描述吧");
					aImg.setImgDesc("為此找片新增點描述吧");
					aImg.setImgCreatedTime(currentTime);
					aImg.setImgModifiedTime(currentTime);
					aImg.setImgFileName(getFileNameFromPart(part));
					aImg.setImgExtName(part.getContentType());
					aImg.setImgFile(getPictureByteArray(part.getInputStream()));
					aImgs.add(aImg);
				}
			}

			albumSvc.addAlbumWithImg(member.getMemNo(), albumTitle, currentTime, currentTime, 0,
					aImgs.get(1).getImgFile(), aImgs);

			res.sendRedirect(req.getContextPath() + "/front_end/album/albumShow.jsp");

		}
		
		
		
		
		//修改封面
		if ("changeCover".equals(action)) {
			
			/****************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer albumNo=null;
			try{
				albumNo=Integer.parseInt(req.getParameter("albumNo"));
			}
			catch(Exception e){
				////代寫////
			}
			
			Integer imgNo=null;
			try{
				imgNo=Integer.parseInt(req.getParameter("imgNo"));
			}
			catch(Exception e){
				//////代寫///////
			}
			
			
			
			/**************************** 2.修改完成,準備轉交(Send the Success view)*************/
	

			AlbumImg aImg=aImgSvc.getOneAlbumImg(imgNo);
			Album album=albumSvc.getOneAlbum(albumNo);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			albumSvc.updateAlbum(albumNo, album.getMemNo(), album.getAlbumTitle(), album.getAlbumCreatedTime(), currentTime, 0, aImg.getImgFile());
	
			
			
			/**************************** 3.修改完成,準備轉交(Send the Success view)*************/
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/album/albumShow.jsp?albumNo="+albumNo);

			successView.forward(req, res);
			
		}
		
		
		//刪除封面
		if ("deleteImg".equals(action)) {
			
			/****************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer albumNo=null;
			try{
				albumNo=Integer.parseInt(req.getParameter("albumNo"));
			}
			catch(Exception e){
				////代寫////
			}
			
			Integer imgNo=null;
			try{
				imgNo=Integer.parseInt(req.getParameter("imgNo"));
			}
			catch(Exception e){
				//////代寫///////
			}
			
			
			
			/**************************** 2.修改完成,準備轉交(Send the Success view)*************/
	
			aImgSvc.deleteAlbumImg(imgNo);

	
			
			
			/**************************** 3.修改完成,準備轉交(Send the Success view)*************/
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/album/aImgShow.jsp?albumNo="+albumNo);

			successView.forward(req, res);
			
		}
		
		//編輯相片
		if ("updateImg".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			/****************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer albumNo=null;
			try{
				albumNo=Integer.parseInt(req.getParameter("albumNo"));
			}
			catch(Exception e){
				errorMsgs.put("imgNo", "相簿代號錯誤");
			}
			
			Integer imgNo=null;
			try{
				imgNo=Integer.parseInt(req.getParameter("imgNo"));
			}
			catch(Exception e){
				errorMsgs.put("imgNo", "照片代號錯誤");
			}
			
			//好像也可以讓他為空 沒關係
			String imgTitle=req.getParameter("imgTitle");
			if (imgTitle == null || imgTitle.trim().isEmpty()) {
				errorMsgs.put("imgTitle", imgTitle);
			}
			
			//好像也可以讓他為空 沒關係
			String imgDesc=req.getParameter("imgDesc");
			if (imgDesc == null || imgDesc.trim().isEmpty()) {
				errorMsgs.put("imgDesc", imgDesc);
			}
			

			//我還沒做錯誤處理 map還沒送回去

			
			/**************************** 2.修改完成,準備轉交(Send the Success view)*************/
	

			AlbumImg aImg=aImgSvc.getOneAlbumImg(imgNo);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			aImgSvc.updateAlbumImg(imgNo, albumNo, imgTitle, imgDesc, aImg.getImgCreatedTime(), currentTime, aImg.getImgFileName(), aImg.getImgExtName(), aImg.getImgFile());


			System.out.println("=====================111111111111111======================");
			
			/**************************** 3.修改完成,準備轉交(Send the Success view)*************/
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/album/aImgShow.jsp?albumNo="+albumNo);

			successView.forward(req, res);
			
		}
		
		

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
