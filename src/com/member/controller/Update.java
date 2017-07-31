package com.member.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
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

import com.member.model.Member;
import com.member.model.MemberService;
import com.pet.model.Pet;
import com.pet.model.PetService;

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
				/*****************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
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
					RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/member/memberInfoUpdate.jsp");
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
				res.sendRedirect(req.getContextPath() + "/front_end/member/memberInfo.jsp");
			} catch (Exception e) {
				System.out.println("error");
			}
		}

		// 修改密碼
		if ("pwdChange".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			/*****************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/

			String memPwd = req.getParameter("memPwd");
			if (!memPwd.equals(member.getMemPwd())) {
				errorMsgs.add("目前的密碼錯誤");
			}

			String memNewPwd = req.getParameter("memNewPwd");
			if (!(memNewPwd.matches(".*[a-zA-Z]+.*") && memNewPwd.trim().length() > 5)) {
				errorMsgs.add("新密碼格式不符");
			}

			Map<String, String> falsePwd = new HashMap<String, String>();
			falsePwd.put("memPwd", memPwd);
			falsePwd.put("memNewPwd", memNewPwd);

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/member/memPwdChange.jsp");
				req.setAttribute("falsePwd", falsePwd);
				req.setAttribute("errorMsgs", errorMsgs);
				dispatcher.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			memSvc.updateMember(member.getMemNo(), member.getMemId(), memNewPwd, member.getMemName(),
					member.getMemSname(), member.getMemGender(), member.getMemIdNo(), member.getMemBday(),
					member.getMemPhone(), member.getMemAddress(), member.getMemEmail(), member.getMemImg(),
					member.getMemReported(), member.getMemStatus(), member.getMemRelation(), member.getMemSelfintro(),
					member.getMemFollowed(), member.getMemPoint(), member.getMemSaleRank(), member.getMemLongtitude(),
					member.getMemLatitude(), member.getMemLocTime(), member.getMemLocStatus());

			/****************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 *************/
			Integer memNo = member.getMemNo();
			session.removeAttribute("member");
			Member newMember = memSvc.getOneMember(memNo);
			session.setAttribute("member", newMember);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/member/memPwdChange.jsp");
			req.setAttribute("success", "密碼修改成功");
			dispatcher.forward(req, res);

		}

		// 會員註冊
		if ("register".equals(action)) {

			/****************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			String memId = req.getParameter("memId").trim();
			String memPwd = req.getParameter("memPwd").trim();
			String memName = req.getParameter("memName").trim();
			String memSname = req.getParameter("memSname").trim();
			String memIdNo = req.getParameter("memIdNo").trim();
			String memPhone = req.getParameter("memPhone").trim();
			String memAddress = req.getParameter("memAddress").trim();
			String memEmail = req.getParameter("memEmail").trim();

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			if (memId == null || memId.isEmpty()) {
				errorMsgs.add("請填寫帳號");
			}
			if (memPwd == null || memPwd.isEmpty()) {
				errorMsgs.add("請填寫密碼");
			}
			if (memName == null || memName.isEmpty()) {
				errorMsgs.add("請填寫姓名");
			}
			if (memSname == null || memSname.isEmpty()) {
				errorMsgs.add("請填寫暱稱");
			}

			Integer memGender = null;
			try {
				memGender = Integer.parseInt(req.getParameter("memGender").trim());
			} catch (IllegalArgumentException e) {
				memGender = 1;
				errorMsgs.add("請輸入性別");
			}

			if (memIdNo == null || memIdNo.isEmpty()) {
				errorMsgs.add("請填寫身分證字號");
			}

			java.sql.Date memBday = null;
			try {
				memBday = java.sql.Date.valueOf(req.getParameter("memBday").trim());
			} catch (IllegalArgumentException e) {
				memBday = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入生日!");
			}

			if (memPhone == null || memPhone.isEmpty()) {
				errorMsgs.add("請填寫手機");
			}
			if (memAddress == null || memAddress.isEmpty()) {
				errorMsgs.add("請填寫地址");
			}
			if (memEmail == null || memEmail.isEmpty()) {
				errorMsgs.add("請填寫信箱");
			}

			byte[] memImg = null;
			Collection<Part> parts = req.getParts();
			for (Part part : parts) {
				if (part.getName().equals("memImg") && getFileNameFromPart(part) != null
						&& part.getContentType().startsWith("image")) {
					memImg = getPictureByteArray(part.getInputStream());
				}
				if (getFileNameFromPart(part) != null && part.getName().equals("memImg")
						&& !(part.getContentType().startsWith("image"))) {
					errorMsgs.add("會員照片格式有誤");
				}
			}

			/****************** 有寵物會執行下方 *****************/
			String petName = null;
			String petKind = null;
			Integer petGender = null;
			byte[] petImg = null;

			if (((String) req.getParameter("petOrNot")).equals("1")) {
				petName = req.getParameter("petName").trim();
				if (petName == null || petName.isEmpty()) {
					errorMsgs.add("請輸入寵物姓名");
				}

				petKind = req.getParameter("petKind").trim();
				if (petKind == null || petKind.isEmpty()) {
					errorMsgs.add("請輸入寵物類別");
				}

				petGender = Integer.parseInt(req.getParameter("petGender").trim());

				for (Part part : parts) {
					if (part.getName().equals("petImg") && getFileNameFromPart(part) != null
							&& part.getContentType() != null) {
						petImg = getPictureByteArray(part.getInputStream());
					}
					if (getFileNameFromPart(part) != null && part.getName().equals("petImg")
							&& !(part.getContentType().startsWith("image"))) {
						errorMsgs.add("寵物照片格式有誤");
					}
				}
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/newFileBig5.jsp");
				req.setAttribute("errorMsgs", errorMsgs);
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/

			// 判斷是否有養寵物
			if (((String) req.getParameter("petOrNot")).equals("0")) {
				memSvc.addMember(memId, memPwd, memName, memSname, memGender, memIdNo, memBday, memPhone, memAddress,
						memEmail, memImg, 0, 0, 2, "test", 0, 0, 0, 0.00, 0.00,
						new Timestamp((new java.util.Date()).getTime()), 0);
			} else {

				memSvc.addMemberWithPet(memId, memPwd, memName, memSname, memGender, memIdNo, memBday, memPhone,
						memAddress, memEmail, memImg, 0, 0, 2, "test", 0, 0, 0, 0.00, 0.00,
						new Timestamp((new java.util.Date()).getTime()), 0, petName, petKind, petGender, "test", "test",
						new Date(2010 - 05 - 02), petImg, 0);
			}

			/***************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 *************/
			Member memberR = memSvc.getOneMemberById(memId);
			session.setAttribute("member", memberR);
			res.sendRedirect(req.getContextPath() + "/front_end/index.jsp");

		}

		if ("login".equals(action)) {

			// 判斷是否為空值
			String memId = req.getParameter("memId");
			String memPwd = req.getParameter("memPwd");

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			if (allowUser(memId, memPwd) == null) {
				Member errMember = new Member();
				errMember.setMemId(memId);
				errMember.setMemPwd(memPwd);

				errorMsgs.add("帳號密碼錯誤");
				req.setAttribute("member", errMember);
				RequestDispatcher sendBackView = req.getRequestDispatcher("/front_end/member/login.jsp");
				sendBackView.forward(req, res);
			} else {
				Member memberl = memSvc.getOneMemberById(memId);
				session.setAttribute("member", memberl);
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
				res.sendRedirect(req.getContextPath() + "/front_end/index.jsp");
			}

		}

		// 會員登出
		if ("logout".equals(action)) {
			session.removeAttribute("member");
			res.sendRedirect(req.getContextPath() + "/front_end/index.jsp");

		}

		// 會員查詢
		if ("search".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			/*****************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			String search = req.getParameter("search");
			if (search == null || search.trim().isEmpty()) {
				errorMsgs.add("請填寫搜尋內容");
			}

			String type = req.getParameter("type");

			String loc = req.getParameter("loc");

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher(loc);
				req.setAttribute("errorMsgs", errorMsgs);
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 2.修改完成,準備轉交(Send the Success view)
			 *************/
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/searchResult.jsp");

			req.setAttribute("type", type);

			req.setAttribute("search", search);

			failureView.forward(req, res);

		}

		// 會員資訊流覽
		if ("viewOtherMem".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			/*****************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			Integer memNo=null;
			try {
				memNo = Integer.parseInt(req.getParameter("memNo"));
			} catch (Exception e) {
				errorMsgs.add("系統問題，暫時無法預覽");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/searchResult");
				req.setAttribute("errorMsgs", errorMsgs);
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 2.修改完成,準備轉交(Send the Success view)
			 *************/
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/viewMemInfo.jsp");

			req.setAttribute("memNo", memNo);

			failureView.forward(req, res);

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

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

	protected Member allowUser(String memId, String memPwd) {
		MemberService memSvc = new MemberService();
		Member member = memSvc.getOneMemberById(memId);

		if (member == null) {
			return null;
		} else if (!member.getMemPwd().equals(memPwd)) {
			return null;
		} else {
			return member;
		}
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(400, 300, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 400, 300, null);
		g.dispose();

		return resizedImage;
	}
}
