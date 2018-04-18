package UtilProcessor.model;

import java.util.HashMap;

public class WebCrawlerInfoModel {

	HashMap<String, String> crawlerInfoOutputModel;

	public WebCrawlerInfoModel() {
		// TODO Auto-generated constructor stub
		crawlerInfoOutputModel = new HashMap<String, String>(); 

	}
	
	void SetCrawlerOutputInfoModel(HashMap<String, String> crawlerInfoOutputModelData) {
		crawlerInfoOutputModel = crawlerInfoOutputModelData;
	}
	
	public void AddToCrawlerOutputInfoModel(String webURL, String title) {
		crawlerInfoOutputModel.put(webURL, title);
	}
	
	public HashMap<String, String> GetCrawlerOutputInfoModel() {
		return crawlerInfoOutputModel;
	}

}
