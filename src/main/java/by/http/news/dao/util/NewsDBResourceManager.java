package by.http.news.dao.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class NewsDBResourceManager {
	
	private static final String DB_FILENAME = "properties.db_news";
	
	private final static NewsDBResourceManager INSTANCE = new NewsDBResourceManager();
	
	private ResourceBundle bundle = ResourceBundle.getBundle(DB_FILENAME, Locale.US);
	
	private NewsDBResourceManager() {}
	
	public static NewsDBResourceManager getInstance() {
		
		return INSTANCE;
	}
	
	public String getValue(String key) {
		
		return bundle.getString(key);
	}

}