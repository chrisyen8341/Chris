package com.member.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.pet.model.Pet;

@WebServlet("/PetImgReader")
public class PetImgReader extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		HttpSession session=req.getSession();
		try {
			Pet pet=(Pet)session.getAttribute("pet");
			out.write(pet.getPetImg());

		} catch (Exception e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
