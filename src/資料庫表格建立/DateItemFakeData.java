package 資料庫表格建立;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantJDBCDAO;
import com.restaurant.model.RestaurantService;

public class DateItemFakeData {
	
	
	
	public static void main(String[] args){
		
		Map<Integer,Integer> pMember=new HashMap<Integer,Integer>();
		for(int i=5001;i<=5015;i++){
			for(int j=0;j<3;j++){
			pMember.put(i, i-4000+j);
			}
		}

		RestaurantJDBCDAO restDao=new RestaurantJDBCDAO();
		Map<Integer,String> rest=new HashMap<Integer,String>();
		for(int i=7006;i<=7080;i++){
			String loc=restDao.findByPK(i).getRestLocate();
			rest.put(i, loc);
		}
		
		List<String> dText=new ArrayList<String>();
		dText.add("Hey～ 親愛的你們，最近好嘛～ 好一陣子沒有跟你們喝杯咖啡、下午茶了。伴著這美麗的沉靜，就這樣我們開始了美好的一天晨霧慢慢散去，就成了這番景象好了，天亮了，我們可以盡情的玩耍了。");
		dText.add("Hi~最近天氣特別好，在這美好的天氣裡 最適合交好朋友，如果你願意陪我一起吃頓飯，會是在完美不過的一天了");
		dText.add("阿囉哈，大家好啊，我最近超愛看中國有嘻哈的，或許我們可以邊吃飯邊聊中國有嘻哈喔的，");
		dText.add("你在看我嗎，沒錯就是妳，還不快來吃飯交個朋友，我家可愛的寵物正在等著你呢");
		dText.add("我家的寵物最近很孤單呢，她正在等著你喔，鳩咪");
		dText.add("我最近在教我家狗狗學習Hibernate，她已經開始會建組態擋了，或許我們可以邊吃飯邊聊Java喔");
		
		
		List<String> dTitle=new ArrayList<String>();
		dTitle.add("我哭著哭著說沒事你怎麼就相信了呢");
		dTitle.add("快來一起玩吧");
		dTitle.add("Yo this is your best chance");
		dTitle.add("天氣超好的 明天一起吃個飯吧");
		dTitle.add("我想妳了，再者寒冷的天氣裡");
		
		List<String> dDate=new ArrayList<String>();
		dDate.add("20170820");
		dDate.add("20170821");
		dDate.add("20170815");

		List<String> mDate=new ArrayList<String>();
		mDate.add("20170830");
		mDate.add("20170825");
		mDate.add("20170822");
		
		List<String> time=new ArrayList<String>();
		time.add("20:30:20");
		time.add("12:30:20");
		time.add("09:30:20");
		
		
		
		Random rand = new Random();
		for(int i=0;i<100;i++){
			Integer pMemberRand=rand.nextInt(pMember.size())+1;
			Integer restRand=rand.nextInt(rest.size())+1;
			Integer title=rand.nextInt(dTitle.size())+1;
		
			String sql="INSERT INTO DATEITEM values (DATEITEMNO_SQ.NEXTVAL,"+(pMemberRand).toString()+","+restRand+",\'"+dTitle.get(title)+"\'";
			System.out.println(sql); 
			
		}
		
		
		
		
	}
}
