package com.platform.cocktail.cocktail_platform.config;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class CodeConfig {
	public HashMap<String, Integer> codeMap(){
		return map;
	}
	
	private final HashMap<String, Integer> map = new HashMap<String, Integer>() {{
		
		// 맛 관련 코드
		put("sweet", 1001);
		put("sour", 1002);
		put("bitter", 1004);
		put("salty", 1008);
		put("sweet,sour", 1003);
		put("sweet,bitter", 1005);
		put("sweet,salty", 1009);
		put("sour,bitter", 1006);
		put("sour,salty", 1010);
		put("bitter,salty", 1012);
		put("sweet,sour,bitter", 1007);
		put("sweet,sour,salty", 1011);
		put("sweet,bitter,salty", 1013);
		put("sour,bitter,salty", 1014);
		put("sweet,sour,bitter,salty", 1015);
		
		// 향 관련 코드
		put("alcohol", 2001);
		put("coffee&chocolate", 2002);
		put("general", 2004);
		put("citrus", 2008);
		put("tropical", 2016);
		put("alcohol,coffee&chocolate", 2003);
		put("alcohol,general", 2005);
		put("alcohol,citrus", 2009);
		put("alcohol,tropical", 2017);
		put("coffee&chocolate,general", 2006);
		put("coffee&chocolate,citrus", 2010);
		put("coffee&chocolate,tropical", 2018);
		put("general,citrus", 2012);
		put("general,tropical", 2020);
		put("citrus,tropical", 2024);
		put("alcohol,coffee&chocolate,general", 2007);
		put("alcohol,coffee&chocolate,citrus", 2011);
		put("alcohol,coffee&chocolate,tropical", 2019);
		put("alcohol,general,citrus", 2013);
		put("alcohol,general,tropical", 2021);
		put("alcohol,citrus,tropical", 2025);
		put("coffee&chocolate,general,citrus", 2014);
		put("coffee&chocolate,general,tropical", 2022);
		put("coffee&chocolate,citrus,tropical", 2026);
		put("general,citrus,tropical", 2028);
		put("alcohol,coffee&chocolate,general,citrus", 2015);
		put("alcohol,coffee&chocolate,general,tropical", 2023);
		put("alcohol,coffee&chocolate,citrus,tropical", 2027);
		put("alcohol,general,citrus,tropical", 2029);
		put("coffee&chocolate,general,citrus,tropical", 2030);
		put("alcohol,coffee&chocolate,general,citrus,tropical", 2031);

		// 색 관련 코드
		put("transparent", 3001);
		put("white", 3002);
		put("black", 3004);
		put("red", 3008);
		put("orange", 3016);
		put("yellow", 3032);
		put("green", 3064);
		put("blue", 3128);
		put("purple", 3256);
		put("multi", 3512);
		
		// 도수 관련 코드
		put("beer", 4001);
		put("makgeoli", 4002);
		put("whitewine", 4004);
		put("redwine", 4008);
		put("soju", 4016);
		put("whiskey", 4032);
		put("under beer", 4000);
		put("beer,makgeoli", 4003);
		put("makgeoli,whitewine", 4006);
		put("whitewine,redwine", 4012);
		put("redwine,soju", 4024);
		put("soju,whiskey", 4048);
		put("over whiskey", 4096);
		
		// 날씨 관련 코드
		put("sunny", 5001);
		put("cloudy", 5002);
		put("rainy", 5004);
		put("snowy", 5008);
		put("sunny,cloudy", 5003);
		put("sunny,rainy", 5005);
		put("sunny,snowy", 5009);
		put("cloudy,rainy", 5006);
		put("cloudy,snowy", 5010);
		put("rainy,snowy", 5012);
		put("sunny,cloudy,rainy", 5007);
		put("sunny,cloudy,snowy", 5011);
		put("sunny,rainy,snowy", 5013);
		put("cloudy,rainy,snowy", 5014);
		put("sunny,cloudy,rainy,snowy", 5015);

		// 연령대 관련 코드
		put("20", 6001);
		put("30", 6002);
		put("40", 6004);
		put("50", 6008);
		put("20,30", 6003);
		put("20,40", 6005);
		put("20,50", 6009);
		put("30,40", 6006);
		put("30,50", 6010);
		put("40,50", 6012);
		put("20,30,40", 6007);
		put("20,30,50", 6011);
		put("20,40,50", 6013);
		put("30,40,50", 6014);
		put("20,30,40,50", 6015);
		
		// 동행인 관련 코드
		put("alone", 7001);
		put("friend", 7002);
		put("family", 7004);
		put("lover", 7008);
		put("etc", 7016);
		put("alone,friend", 7003);
		put("alone,family", 7005);
		put("alone,lover", 7009);
		put("alone,etc", 7017);
		put("friend,family", 7006);
		put("friend,lover", 7010);
		put("friend,etc", 7018);
		put("family,lover", 7012);
		put("family,etc", 7020);
		put("lover,etc", 7024);
		put("alone,friend,family", 7007);
		put("alone,friend,lover", 7011);
		put("alone,friend,etc", 7019);
		put("alone,family,lover", 7013);
		put("alone,family,etc", 7021);
		put("alone,lover,etc", 7025);
		put("friend,family,lover", 7014);
		put("friend,family,etc", 7022);
		put("friend,lover,etc", 7026);
		put("family,lover,etc", 7028);
		put("alone,friend,family,lover", 7015);
		put("alone,friend,family,etc", 7023);
		put("alone,friend,lover,etc", 7027);
		put("alone,family,lover,etc", 7029);
		put("friend,family,lover,etc", 7030);
		put("alone,friend,family,lover,etc", 7031);
		
		// 이벤트 관련 코드
		put("cocktail", 8001);
		put("atmosphere", 8002);
		put("friendship", 8004);
		put("celebrate", 8008);
		put("cocktail,atmosphere", 8003);
		put("cocktail,friendship", 8005);
		put("cocktail,celebrate", 8009);
		put("atmosphere,friendship", 8006);
		put("atmosphere,celebrate", 8010);
		put("friendship,celebrate", 8012);
		put("cocktail,atmosphere,friendship", 8007);
		put("cocktail,atmosphere,celebrate", 8011);
		put("cocktail,friendship,celebrate", 8013);
		put("atmosphere,friendship,celebrate", 8014);
		put("cocktail,atmosphere,friendship,celebrate", 8015);
		
	}};
}
