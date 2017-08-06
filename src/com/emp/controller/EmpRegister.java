package com.emp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.aesencrypt.EncrypAES;
import com.email.MailService;
import com.emp.model.Emp;
import com.emp.model.EmpService;
import com.member.model.Member;

import java.util.Properties;
import java.util.UUID;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@WebServlet("/back_end/emp/EmpRegister.do")
public class EmpRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		List<String> errorMsgs = new LinkedList<String>();
		
		
		
		try {
			/***************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
			String empName = req.getParameter("empName");
			if (empName == null || empName.trim().isEmpty()) {
				errorMsgs.add("請填寫姓名");
			}
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (!empName.trim().matches(enameReg)) {
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			
			String empId = req.getParameter("empId");
			if (empId == null || empId.trim().isEmpty()) {
				errorMsgs.add("請填寫帳號");
			}
			String empIdReg = "^[(a-zA-Z0-9_)]{2,10}$";
			if (!empId.trim().matches(empIdReg)) {
				errorMsgs.add("員工帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
			}
			
			
			String empJob=req.getParameter("empJob");
			

			java.sql.Date empHireDate = null;
			try {
				empHireDate = java.sql.Date.valueOf(req.getParameter("empHireDate"));
			} catch (IllegalArgumentException e) {
				errorMsgs.add("日期格式錯誤");
			}
			
			String empEmail = req.getParameter("empEmail");
			if (!empEmail.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
				errorMsgs.add("請輸入正確的Email信箱");
			}

			String[] empAuthb=req.getParameterValues("empAuth");	
			if(empAuthb==null){
				errorMsgs.add("請至少給予員工一個權限");
			}

	
			Emp empf=new Emp();
			empf.setEmpName(empName);
			empf.setEmpId(empId);
			empf.setEmpJob(empJob);
			empf.setEmpHireDate(empHireDate);
			empf.setEmpEmail(empEmail);


			if (!errorMsgs.isEmpty()) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/back_end/emp/empRegister.jsp");
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("emp", empf);
				dispatcher.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			 
			
			/************** 密碼處理**********/
			//亂數產生亂碼
			
			UUID uuid=UUID.randomUUID();
			String pwd=uuid.toString().substring(0,8);
			UUID uuid2=UUID.randomUUID();
			String pwdSalt=uuid2.toString().substring(0,6);
			
			//Email寄發 
			System.out.println("===================此帳號密碼為"+pwd);
			
		      String to = empEmail;
		      
		      String subject = "員工到職密碼通知";
		      
		      String messageText = "嗨! " + empName+"，歡迎您加入寵物You&Me大家庭，您的員工帳號為 : "+empId + " 請謹記此密碼 : " + pwd + "\n" +" (已經啟用)"; 
		       
		      MailService mailService = new MailService();
		      mailService.sendMail(to, subject, messageText);
					
			
			
			//加密存入db 代修改 
		      byte[] keyBytes = null;
		        EncrypAES de=null;
				try {
					keyBytes = Base64.getDecoder().decode("xe/uyYXy0CtrFYDOV3ctkQ==".getBytes("UTF-8"));
				} catch (UnsupportedEncodingException e2) {
					e2.printStackTrace();
				}
		        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		        try {
					de=new EncrypAES();
				} catch (NoSuchAlgorithmException | NoSuchPaddingException e1) {
					e1.printStackTrace();
				}  
     
		        String  empPwd = de.Encrytor3(pwdSalt+pwd+pwdSalt, keySpec);
			
			
			
			/************** 權限處理***********/
			List<Integer> empAuthNos=new ArrayList<Integer>( );
			System.out.println("此帳號權限如下:");
			for(String eAuth:empAuthb){
				empAuthNos.add(Integer.parseInt(eAuth));
				System.out.println(eAuth);
			};
			

			
			Emp emp=new Emp();
			emp.setEmpName(empName);
			emp.setEmpId(empId);
			emp.setEmpJob(empJob);
			emp.setEmpPwd(empPwd);
			emp.setEmpPwdSalt(pwdSalt);
			emp.setEmpHireDate(empHireDate);
			emp.setEmpEmail(empEmail);
			EmpService empSvc=new EmpService();
			empSvc.addEmpWithAuth(empName, empJob, empId, empPwd, pwdSalt,0, empHireDate, empEmail,empAuthNos );
		
			

			/**************************** 3.修改完成,準備轉交(Send the Success view)*************/
			RequestDispatcher dispatcher = req.getRequestDispatcher("/back_end/emp/empRegister.jsp");
			req.setAttribute("errorMsgs", errorMsgs);
			errorMsgs.add("註冊成功");
			dispatcher.forward(req, res);

			
		
		} catch (Exception e) {
			System.out.println("error");
		}
		
	}

}
