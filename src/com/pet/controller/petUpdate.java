package com.pet.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import com.pet.model.Pet;
import com.pet.model.PetService;


@WebServlet("/petUpdate")
@MultipartConfig
public class petUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		PetService petSvc = new PetService();
		
		if ("petUpdate".equals(action)) {
		
			List<String> errorMsgs = new LinkedList<String>();
			
			try {
				/***************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer petNo = null;
				try {
					petNo = Integer.parseInt(req.getParameter("petNo").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("系統錯誤");
				}
				
				Pet pet=petSvc.getOnePet(petNo);
					
				String petName = req.getParameter("petName");
				if (petName == null || petName.trim().isEmpty()) {
					errorMsgs.add("請填寫寵物姓名");
				}

				String petSpecies = req.getParameter("petSpecies");
				if (petSpecies == null || petSpecies.trim().isEmpty()) {
					errorMsgs.add("請填寫寵物品種");
				}

				java.sql.Date petBday = null;
				try {
					petBday = java.sql.Date.valueOf(req.getParameter("petBday"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("日期格式錯誤");
				}
				

				String petIntro = req.getParameter("petIntro");
				if (petIntro == null || petIntro.trim().isEmpty()) {
					errorMsgs.add("請輸入正確的地址");
				}
				
				Pet fPet = new Pet();
				fPet.setPetNo(pet.getPetNo());
				fPet.setMemNo(pet.getMemNo());
				fPet.setPetName(petName);
				fPet.setPetGender(pet.getPetGender());
				fPet.setPetKind(pet.getPetKind());
				fPet.setPetSpecies(petSpecies);
				fPet.setPetBday(petBday);
				fPet.setPetIntro(petIntro);
				fPet.setPetImg(pet.getPetImg());
				fPet.setPetStatus(pet.getPetStatus());
				byte[] petImg = pet.getPetImg();
				Collection<Part> parts = req.getParts();

				for (Part part : parts) {
					if (part.getName().equals("petImg") && getFileNameFromPart(part) != null
							&& part.getContentType().startsWith("image")) {
						petImg = getPictureByteArray(part.getInputStream());
						fPet.setPetImg(petImg);
					}
					if (getFileNameFromPart(part) != null && part.getName().equals("petImg")
							&& !(part.getContentType().startsWith("image"))) {
						errorMsgs.add("照片格式有誤");
					}
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher dispatcher = req.getRequestDispatcher("/petInfoUpdate.jsp");
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("pet", fPet);
					dispatcher.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				
		
				petSvc.updatePet(petNo, pet.getMemNo(), petName, pet.getPetKind(), pet.getPetGender(), petSpecies, petIntro, 
						petBday, petImg, pet.getPetStatus());
				

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				//在取一次 不在上面petSvc取是因為上面的Pet 會少PETNO
				res.sendRedirect(req.getContextPath() + "/petInfo.jsp");
			} catch (Exception e) {
				System.out.println("error");
			}
		}

	}

	
	
	public static byte[] getPictureByteArray(InputStream fis) throws IOException {
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

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	
	
	
	
	
	
	
	
	
	
	
}
