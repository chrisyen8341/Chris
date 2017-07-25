package com.member.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.pet.model.Pet;
import com.pet.model.PetService;

@WebServlet("/PetImgReader")
public class PetImgReader extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String petNo=req.getParameter("petNo");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		HttpSession session=req.getSession();
		try {
			PetService petSvc=new PetService();			
			Pet pet=petSvc.getOnePet(Integer.parseInt(petNo));
			out.write(pet.getPetImg());

		} catch (Exception e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
