package com.album.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.album.model.AlbumService;
import com.albumimg.model.AlbumImg;
import com.albumimg.model.AlbumImgService;
import com.member.model.Member;


@WebServlet("/CreateAlbum")
public class CreateAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session=req.getSession();
		
		
		Member member=(Member)session.getAttribute("member");
		AlbumImgService aImgSvc=new AlbumImgService();
		AlbumService albumSvc=new AlbumService();
		List<AlbumImg> aImgs=new LinkedList<AlbumImg>();
		
		
		LinkedList<Integer> imgNos=(LinkedList<Integer>)session.getAttribute("imgNos");
		System.out.println(imgNos);
		for(int i=0;i<imgNos.size();i++){
			AlbumImg aImg=aImgSvc.getOneAlbumImg(imgNos.get(i));
			aImgs.add(aImg);		
		}
		byte[] b=aImgs.get(0).getImgFile();
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		albumSvc.addAlbumWithImg(member.getMemNo(),"test", currentTime, currentTime, 0, b, aImgs);
		session.removeAttribute("imgNos");
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
