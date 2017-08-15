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

@WebServlet("/front_end/album/AVideoReader.do")
public class AVideoReader extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Integer imgNo=Integer.parseInt(req.getParameter("imgNo"));
		AlbumImgService albumImgSvc=new AlbumImgService();
		res.setBufferSize(1024*1024);
		res.setContentType("video/mp4");
		ServletOutputStream out = res.getOutputStream();
		System.out.print("come here");
		try {
			AlbumImg albumImg=albumImgSvc.getOneAlbumImg(imgNo);
			BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(albumImg.getImgFile()));
			byte[] buf = new byte[4 * 1024]; // 4K buffer
			int len;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			in.close();
		} catch (Exception e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
