package com.platform.cocktail.cocktail_platform.api.csv;

import com.platform.cocktail.cocktail_platform.domain.Category;
import com.platform.cocktail.cocktail_platform.domain.Menu;

public class MenuParser implements Parser<Menu>{

    @Override
    public Menu parse(String str) {
        String[] splitted = str.split(",");
        Menu menu = Menu.builder()
        				.storeCode(Integer.parseInt(splitted[0]))
        				.storeName(splitted[1])
        				.menuName(splitted[2])
        				.menuInfo(splitted[3])
        				.price(Integer.parseInt(splitted[4]))
        				.originFilename(splitted[6])
        				.savedFilename(splitted[7])
        				.build();
        				
        if(splitted[5].equals("food"))
        	menu.setCategory(Category.food);
        else if(splitted[5].equals("side"))
        	menu.setCategory(Category.side);
        else
        	menu.setCategory(Category.beverage);
//    
        return menu;
//        menu.setStoreCode(Integer.parseInt(splitted[0].replace("\"","")));
//        menu.setStoreName(splitted[1]);
//        
//        menu.setMenuName(splitted[2]);
//        if(!splitted[3].isEmpty())
//        	menu.setMenuInfo(splitted[3]);
//        else
//        	menu.setMenuInfo(null);
//        menu.setPrice(Integer.parseInt(splitted[4]));
//        
//        if(!splitted[6].isEmpty()) {        	
//	        menu.setOriginFilename(splitted[6]);
//	        menu.setSavedFilename(splitted[7]);
//        } else {
//        	menu.setOriginFilename(null);
//	        menu.setSavedFilename(null);
//        }

    }
}