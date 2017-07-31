package com.album.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.album.model.Album;
import com.album.model.AlbumService;
import com.member.model.Member;
import com.member.model.MemberService;

@WebServlet("/front_end/album/AlbumReader.do")
public class AlbumReader extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Integer albumNo=Integer.parseInt(req.getParameter("albumNo"));
		AlbumService albumSvc=new AlbumService();
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			Album album=albumSvc.getOneAlbum(albumNo);
			out.write(album.getAlbumImgFile());

		} catch (Exception e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
