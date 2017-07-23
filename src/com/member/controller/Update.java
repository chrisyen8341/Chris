package com.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
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

import com.member.model.Member;
import com.member.model.MemberService;

@WebServlet("/Update")
@MultipartConfig
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");
		MemberService memSvc = new MemberService();
		
		if ("memUpdate".equals(action)) {
		
			List<String> errorMsgs = new LinkedList<String>();
			
			try {
				/***************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String memSname = req.getParameter("memSname");
				if (memSname == null || memSname.trim().isEmpty()) {
					errorMsgs.add("請填寫暱稱");
				}

				String memName = req.getParameter("memName");
				if (memName == null || memName.trim().isEmpty()) {
					errorMsgs.add("請填寫姓名");
				}

				java.sql.Date memBday = null;
				try {
					memBday = java.sql.Date.valueOf(req.getParameter("memBday"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("日期格式錯誤");
				}
				
				String memPhone = req.getParameter("memPhone");
				if (!(memPhone.matches("[09]{2}[0-9]{2}-[0-9]{6}") || memPhone.matches("[09]{2}[0-9]{8}"))) {
					errorMsgs.add("手機格式錯誤");
				}

				Integer memGender = null;
				try {
					memGender = Integer.parseInt(req.getParameter("memGender").trim());
				} catch (IllegalArgumentException e) {
					memGender = 2;
					errorMsgs.add("請輸入性別");
				}

				Integer memRelation = null;
				try {
					memRelation = Integer.parseInt(req.getParameter("memRelation").trim());
				} catch (IllegalArgumentException e) {
					memRelation = 2;
					errorMsgs.add("請輸入感情狀態");
				}

				String memEmail = req.getParameter("memEmail");
				if (!memEmail.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
					errorMsgs.add("請輸入正確的Email信箱");
				}

				String memAddress = req.getParameter("memAddress");
				if (memAddress == null || memAddress.trim().isEmpty()) {
					errorMsgs.add("請輸入正確的地址");
				}

				String memSelfintro = req.getParameter("memSelfintro");
				if (memSelfintro == null || memSelfintro.trim().isEmpty()) {
					errorMsgs.add("請輸入正確的地址");
				}

				Member memberU = new Member();
				memberU.setMemSname(memSname);
				memberU.setMemName(memName);
				memberU.setMemBday(memBday);
				memberU.setMemPhone(memPhone);
				memberU.setMemGender(memGender);
				memberU.setMemRelation(memRelation);
				memberU.setMemEmail(memEmail);
				memberU.setMemAddress(memAddress);
				memberU.setMemSelfintro(memSelfintro);
				byte[] memImg = member.getMemImg();
				Collection<Part> parts = req.getParts();

				for (Part part : parts) {
					if (part.getName().equals("memImg") && getFileNameFromPart(part) != null
							&& part.getContentType().startsWith("image")) {
						memImg = getPictureByteArray(part.getInputStream());
						memberU.setMemImg(memImg);
					}
					if (getFileNameFromPart(part) != null && part.getName().equals("memImg")
							&& !(part.getContentType().startsWith("image"))) {
						errorMsgs.add("照片格式有誤");
					}
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher dispatcher = req.getRequestDispatcher("/memberInfoUpdate.jsp");
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("member", memberU);
					dispatcher.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				
				memSvc.updateMember(member.getMemNo(), member.getMemId(), member.getMemPwd(), memName, memSname,
						memGender, member.getMemIdNo(), memBday, memPhone, memAddress, memEmail, memImg,
						member.getMemReported(), member.getMemStatus(), memRelation, memSelfintro,
						member.getMemFollowed(), member.getMemPoint(), member.getMemSaleRank(),
						member.getMemLongtitude(), member.getMemLatitude(), member.getMemLocTime(),
						member.getMemLocStatus());
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				Integer memNo = member.getMemNo();
				session.removeAttribute("member");
				Member newMember = memSvc.getOneMember(memNo);
				session.setAttribute("member", newMember);
				res.sendRedirect(req.getContextPath() + "/memberInfo.jsp");
			} catch (Exception e) {
				System.out.println("error");
			}
		}

		
		if ("pwdChange".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			/***************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
			String memPwd=req.getParameter("memPwd");
			if(!memPwd.equals(member.getMemPwd())){
				errorMsgs.add("目前的密碼錯誤");
			}
			
			String memNewPwd=req.getParameter("memNewPwd");
			if(!(memNewPwd.matches(".*[a-zA-Z]+.*")&&memNewPwd.trim().length()>5)){
				errorMsgs.add("新密碼格式不符");
			}
			
			Member fMember=new Member();
			fMember.setMemPwd(memPwd);
			
			if(!errorMsgs.isEmpty()){
				RequestDispatcher dispatcher=req.getRequestDispatcher("memPwdChange.jsp");
				req.setAttribute("member", fMember);
				req.setAttribute("errorMsgs", errorMsgs);
				dispatcher.forward(req, res);
				return;
			}
			
			
			/*************************** 2.開始修改資料 *****************************************/
			memSvc.updateMember(member.getMemNo(), member.getMemId(), memNewPwd, member.getMemName(), member.getMemSname(), member.getMemGender(), member.getMemIdNo(),
					member.getMemBday(), member.getMemPhone(), member.getMemAddress(), member.getMemEmail(), member.getMemImg(), member.getMemReported(), member.getMemStatus(),
					member.getMemRelation(), member.getMemSelfintro(), member.getMemFollowed(), member.getMemPoint(), member.getMemSaleRank(),
					member.getMemLongtitude(), member.getMemLatitude(), member.getMemLocTime(), member.getMemLocStatus());
			
			/**************************** 3.修改完成,準備轉交(Send the Success view)*************/
			Integer memNo = member.getMemNo();
			session.removeAttribute("member");
			Member newMember = memSvc.getOneMember(memNo);
			session.setAttribute("member", newMember);
			RequestDispatcher dispatcher=req.getRequestDispatcher("memPwdChange.jsp");
			req.setAttribute("success", "密碼修改成功");
			dispatcher.forward(req, res);

			
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
