package com.album.controller;

import java.io.IOException;
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


@WebServlet("/CreateAlbum")
public class CreateAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session=req.getSession();
		AlbumImgService aImgSvc=new AlbumImgService();
		AlbumService albumSvc=new AlbumService();
		List<AlbumImg> aImgs=null;
		
		
		LinkedList<Integer> imgNos=(LinkedList<Integer>)session.getAttribute("imgNos");
		for(int i=0;i<imgNos.size();i++){
			System.out.println(imgNos.get(i));
			AlbumImg aImg=aImgSvc.getOneAlbumImg(imgNos.get(i));
			aImgs.add(aImg);
		}
		
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
