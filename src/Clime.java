

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Clime {
	
	
	private static final String INSERT_REST = "INSERT INTO REST (RESTNO,RESTNAME,RESTADD,RESTLOCATE,RESTPHONE,"
				+ "RESTINTRO,RESTKIND,RESTREVIEWSTATUS,RESTLONGTITUDE,RESTLATITUDE)" + "VALUES(REST_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)";
	
	
	
	public static void main(String[] args) {
		
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String oraUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "petym";
		String passwd = "123456";
		
		

		BufferedReader reader = null;
		Connection conn= null;
		PreparedStatement pstmt= null;
		
		List<String> list =  new ArrayList<String>();;
		List<String> list1=  new ArrayList<String>();;
		List<String> list2=  new ArrayList<String>();;
		
		try {
			URL url = new URL("http://tinyurl.com/3bp8zf7");
					
			Document xmlDoc = Jsoup.parse(url, 3000);

			Class.forName(driver);
			conn = DriverManager.getConnection(oraUrl, userid, passwd);
			

			FileWriter writer = new FileWriter("D://restname.csv");

			Elements tr = xmlDoc.select("tr");
			for (int i = 2; i < 85; i++) {
				String rest = (String) tr.get(i).text();
				writer.write(rest);
			}
			writer.close();
System.out.println("寫入完畢");
			reader = new BufferedReader(new FileReader("D://restname.csv"));
			String name = reader.readLine().replaceAll("[?]", "");
			String name0 = name.replace("(誠品中山店內)", "誠品中山店內-");
			String name1 = name0.replace("(亞米雅米寵物生活館)", "亞米雅米寵物生活館-");
			String name2 = name1.replace("0931-331220", "(03)955-6783");
			String name3 = name2.replace("(", ",(");
			String name4 = name3.replace("巷26", "巷26號");
			String name5 = name4.replace("二段806", "二段806號");
			String name6 = name5.replace("號", "號,");
			String name7 = name6.replace("1F", "");
			String name8 = name7.replace("2F", "");
			String name9 = name8.replace("之1", "");
			String name10 = name9.replace("2樓", "");
			String name11 = name10.replace("餐 廳 名 電 話 地 址", "");
			String name12 = name11.replace("台", ",台");
			String name13 = name12.replace("苗", ",苗");
			String name14 = name13.replace("新竹", ",新竹");
			String name15 = name14.replace("桃園", ",桃園");

			FileWriter writer1 = new FileWriter("D://Trestname.csv");
			writer1.write(name15);
			writer1.close();

			String[] name16 = name15.split(",");
			
			System.out.println("CSV完畢");			
			
			for (int j = 0; j < name16.length; j++) {
				if (j % 3 == 0) {
					list.add(name16[j]);
				} else if (j % 3 == 1) {
					list1.add(name16[j]);
				} else {
					list2.add(name16[j]);
				}
			}
			Iterator<String> restNameList = list.iterator();//餐廳名稱
			Iterator<String> restPhoneList = list1.iterator();//餐廳電話
			Iterator<String> restAddList = list2.iterator();
			Iterator<String> restAddListForSubString = list2.iterator();
			Iterator<String> restAddListGMap = list2.iterator();
			
			System.out.println("Iterator完畢");
			
			List<Double> lat = new ArrayList<>();
			List<Double> lng = new ArrayList<>();
			while(restAddListGMap.hasNext()){
				String sKeyWord = restAddListGMap.next();
				URL urlFromGMap  = new URL(String.format("http://maps.googleapis.com/maps/api/geocode/json?address=%s&sensor=false&language=zh-TW", 
				URLEncoder.encode(sKeyWord, "UTF-8")));//p=%s is KeyWord in	            
				URLConnection connFromGMap = urlFromGMap.openConnection();
				String line;
				StringBuilder builder = new StringBuilder();
				BufferedReader readerFromGMap = new BufferedReader(new InputStreamReader(connFromGMap.getInputStream(),"utf-8"));
				while ((line = readerFromGMap.readLine()) != null) {builder.append(line);}
					JSONObject json = new JSONObject(builder.toString()); //轉換json格式
				    JSONArray ja = json.getJSONArray("results");//取得json的Array物件
				        for (int i = 0; i < ja.length(); i++) {
			                  
			            lat.add(ja.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
			            lng.add(ja.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
			                 
				    } 
				
			}
			Iterator<Double> latList = lat.iterator();
			Iterator<Double> lngList = lng.iterator();
			
			System.out.println("GOOGLE完畢");
			
			for (int k = 0; k < 77; k++) {
				pstmt = conn.prepareStatement(INSERT_REST);
				int kindOfPet = (int) (Math.random() * 3);
				int restReviewStatus = (int) (Math.random() * 3);
				pstmt.setString(1,restNameList.next());
				pstmt.setString(2,restAddList.next());
				pstmt.setString(3, restAddListForSubString.next().substring(0,2) +"縣");
				pstmt.setString(4,restPhoneList.next());
				pstmt.setString(5, "petRestaurantIntro"+k);
				pstmt.setInt(6, kindOfPet);
				pstmt.setInt(7, restReviewStatus);
				pstmt.setDouble(8, lngList.next());
				pstmt.setDouble(9, latList.next());
				pstmt.executeUpdate();
			}
			
			
			
			
			System.out.println("完畢");	
			
			
			   

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		

	}

}
