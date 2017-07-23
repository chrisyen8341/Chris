package com.context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PutMap")
public class PutMap extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public void init() throws ServletException {
    	ServletContext context=getServletContext();
    	
    	//會員性別
    	Map<String,String> mGender=new HashMap<String,String>();
    	mGender.put("0", "男");
    	mGender.put("1", "女");
    	mGender.put("2", "不願透露");
    	context.setAttribute("mGender", mGender);
    	
    	//會員感情狀態
    	Map<String,String> mRelation=new HashMap<String,String>();
    	mRelation.put("0", "單身");
    	mRelation.put("1", "穩定交往");
    	mRelation.put("2", "不公開");
    	System.out.println(mRelation.get(0));
    	context.setAttribute("mRelation", mRelation);
    	
    	
    	//寵物性別
    	Map<String,String> pGender=new HashMap<String,String>();
    	pGender.put("0", "公");
    	pGender.put("1", "母");
    	context.setAttribute("pGender", pGender);
    	System.out.println("===============");
    	
      }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
