package com.member.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.pet.model.Pet;

public class MemberTest {

	public static void main(String[] args) throws IOException {
		MemberJDBCDAO dao=new MemberJDBCDAO();
		
		
		//新增
//		Member member=new Member();
//		member.setMemId("chrisyen8342");
//		member.setMemPwd("d656545");
//		member.setMemName("顏佑軒");
//		member.setMemSname("小顏");
//		member.setMemGender("男");
//		member.setMemIdNo("F128546845");
//		member.setMemBday(java.sql.Date.valueOf("2017-07-10"));
//		member.setMemPhone("0930160401");
//		member.setMemAddress("中壢事中央陸65號");
//		member.setMemEmail("chrisyen8341@gmail.com");
//		member.setMemImg(new byte[1231654]);
//		member.setMemReported(10);
//		member.setMemStatus(0);
//		member.setMemRelation("單身");
//		member.setMemSelfintro("這是測試用的資料，我在自我介紹");
//		member.setMemFollowed(0);
//		member.setMemPoint(0);
//		member.setMemSaleRank(1);
//		member.setMemLongtitude(7.666);
//		member.setMemLatitude(25.2222);
//		member.setMemLocTime(new Timestamp((new Date()).getTime()));
//		member.setMemLocStatus(0);
//		dao.add(member);
//		System.out.println("執行完畢");
		
		
		//新增有寵物會員
//		Member member=new Member();
//		Pet pet=new Pet();
//		member.setMemId("QWPPQWEsd5648");
//		member.setMemPwd("d656545");
//		member.setMemName("顏佑軒");
//		member.setMemSname("小顏");
//		member.setMemGender(0);
//		member.setMemIdNo("F128546845");
//		member.setMemBday(java.sql.Date.valueOf("2017-07-10"));
//		member.setMemPhone("0930160401");
//		member.setMemAddress("中壢事中央陸65號");
//		member.setMemEmail("chrisyen8341@gmail.com");
//		member.setMemImg(getPictureByteArray(new File("WebContent/DummyImg/member/m6.png")));
//		member.setMemReported(10);
//		member.setMemStatus(0);
//		member.setMemRelation(1);
//		member.setMemSelfintro("這是測試用的資料，我在自我介紹");
//		member.setMemFollowed(0);
//		member.setMemPoint(0);
//		member.setMemSaleRank(1);
//		member.setMemLongtitude(7.666);
//		member.setMemLatitude(25.2222);
//		member.setMemLocTime(new Timestamp((new Date()).getTime()));
//		member.setMemLocStatus(0);
//		pet.setPetName("小小狗");
//		pet.setPetKind("狗");
//		pet.setPetGender(0);
//		pet.setPetSpecies("台灣土狗");
//		pet.setPetIntro("測試寵物介紹");
//		pet.setPetBday(java.sql.Date.valueOf("2012-06-10"));
//		pet.setPetImg(getPictureByteArray(new File("WebContent/DummyImg/pet/p1.jpg")));
//		dao.addWithPet(member, pet);
//		System.out.println("執行完畢");
//		
		
		
		
		//修改
//		Member member2=new Member();
//		member2.setMemNo(4);
//		member2.setMemId("changeinthere");
//		member2.setMemPwd("d656545");
//		member2.setMemName("顏佑軒");
//		member2.setMemSname("小顏");
//		member2.setMemGender("男");
//		member2.setMemIdNo("F128546845");
//		member2.setMemBday(java.sql.Date.valueOf("2017-07-10"));
//		member2.setMemPhone("0930160401");
//		member2.setMemAddress("changeinthere");
//		member2.setMemEmail("chrisyen8341@gmail.com");
//		member2.setMemImg(new byte[1231654]);
//		member2.setMemReported(10);
//		member2.setMemStatus(0);
//		member2.setMemRelation("單身");
//		member2.setMemSelfintro("測事修改");
//		member2.setMemFollowed(0);
//		member2.setMemPoint(0);
//		member2.setMemSaleRank(1);
//		member2.setMemLongtitude(7.666);
//		member2.setMemLatitude(25.2222);
//		member2.setMemLocTime(new Timestamp((new Date()).getTime()));
//		member2.setMemLocStatus(0);
//		dao.update(member2);
//		System.out.println("執行完畢");
		
		
		//刪除 多個表刪不動 member為多個table參照
//		dao.delete(3);
//		System.out.println("執行完畢");
		
		//查詢
//		Member member3=dao.findByPk(5);
//		System.out.println(member3.getMemNo()+",");
//		System.out.println(member3.getMemId()+",");
//		System.out.println(member3.getMemPwd()+",");
//		System.out.println(member3.getMemName()+",");
//		System.out.println(member3.getMemSname()+",");
//		System.out.println(member3.getMemGender()+",");
//		System.out.println(member3.getMemIdNo()+",");
//		System.out.println(member3.getMemBday()+",");
//		System.out.println(member3.getMemPhone()+",");
//		System.out.println(member3.getMemAddress()+",");
//		System.out.println(member3.getMemEmail()+",");
//		System.out.println(member3.getMemImg()+",");
//		System.out.println(member3.getMemReported()+",");
//		System.out.println(member3.getMemStatus()+",");
//		System.out.println(member3.getMemRelation()+",");
//		System.out.println(member3.getMemSelfintro()+",");
//		System.out.println(member3.getMemFollowed()+",");
//		System.out.println(member3.getMemPoint()+",");
//		System.out.println(member3.getMemSaleRank()+",");
//		System.out.println(member3.getMemLongtitude()+",");
//		System.out.println(member3.getMemLatitude()+",");
//		System.out.println(member3.getMemLocTime()+"。");
//		System.out.println(member3.getMemLocStatus()+"。");
		
		//查詢全部
//		List<Member> memberList=dao.getAll();
//		for(Member member4:memberList){
//			System.out.println(member4.getMemNo()+",");
//			System.out.println(member4.getMemId()+",");
//			System.out.println(member4.getMemPwd()+",");
//			System.out.println(member4.getMemName()+",");
//			System.out.println(member4.getMemSname()+",");
//			System.out.println(member4.getMemGender()+",");
//			System.out.println(member4.getMemIdNo()+",");
//			System.out.println(member4.getMemBday()+",");
//			System.out.println(member4.getMemPhone()+",");
//			System.out.println(member4.getMemAddress()+",");
//			System.out.println(member4.getMemEmail()+",");
//			System.out.println(member4.getMemImg()+",");
//			System.out.println(member4.getMemReported()+",");
//			System.out.println(member4.getMemStatus()+",");
//			System.out.println(member4.getMemRelation()+",");
//			System.out.println(member4.getMemSelfintro()+",");
//			System.out.println(member4.getMemFollowed()+",");
//			System.out.println(member4.getMemPoint()+",");
//			System.out.println(member4.getMemSaleRank()+",");
//			System.out.println(member4.getMemLongtitude()+",");
//			System.out.println(member4.getMemLatitude()+",");
//			System.out.println(member4.getMemLocTime()+"。");
//		}
		
		//查會員登錄用(ID,PWD)
//		System.out.println("帳密正確");
//		Member member3=dao.findById("OOOOOOO");
//		if(member3!=null){
//		System.out.println(member3.getMemNo()+",");
//		System.out.println(member3.getMemId()+",");
//		System.out.println(member3.getMemPwd()+",");
//		System.out.println(member3.getMemName()+",");
//		System.out.println(member3.getMemSname()+",");
//		System.out.println(member3.getMemGender()+",");
//		System.out.println(member3.getMemIdNo()+",");
//		System.out.println(member3.getMemBday()+",");
//		System.out.println(member3.getMemPhone()+",");
//		System.out.println(member3.getMemAddress()+",");
//		System.out.println(member3.getMemEmail()+",");
//		System.out.println(member3.getMemImg()+",");
//		System.out.println(member3.getMemReported()+",");
//		System.out.println(member3.getMemStatus()+",");
//		System.out.println(member3.getMemRelation()+",");
//		System.out.println(member3.getMemSelfintro()+",");
//		System.out.println(member3.getMemFollowed()+",");
//		System.out.println(member3.getMemPoint()+",");
//		System.out.println(member3.getMemSaleRank()+",");
//		System.out.println(member3.getMemLongtitude()+",");
//		System.out.println(member3.getMemLatitude()+",");
//		System.out.println(member3.getMemLocTime()+"。");
//		System.out.println(member3.getMemLocStatus()+"。");
//		}
//		

		
	}
	
	public static byte[] getPictureByteArray(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
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
	

}
