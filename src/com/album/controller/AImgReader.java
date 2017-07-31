package com.album.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.album.model.Album;
import com.album.model.AlbumService;
import com.albumimg.model.AlbumImg;
import com.albumimg.model.AlbumImgService;
import com.member.model.Member;
import com.member.model.MemberService;

@WebServlet("/front_end/album/AImgReader.do")
public class AImgReader extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Integer imgNo=Integer.parseInt(req.getParameter("imgNo"));
		AlbumImgService albumImgSvc=new AlbumImgService();
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			AlbumImg albumImg=albumImgSvc.getOneAlbumImg(imgNo);
			out.write(albumImg.getImgFile());

		} catch (Exception e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
