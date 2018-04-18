package UtilProcessor.model;

import java.util.LinkedHashMap;

public class WebCrawlerInfoModel {

	LinkedHashMap<String, String> crawlerInfoOutputModel;

	public WebCrawlerInfoModel() {
		// TODO Auto-generated constructor stub
		crawlerInfoOutputModel = new LinkedHashMap<String, String>(); 

	}
	
	void SetCrawlerOutputInfoModel(LinkedHashMap<String, String> crawlerInfoOutputModelData) {
		crawlerInfoOutputModel = crawlerInfoOutputModelData;
	}
	
	public void AddToCrawlerOutputInfoModel(String title, String webURL) {
		crawlerInfoOutputModel.put(title, webURL);
	}
	
	public LinkedHashMap<String, String> GetCrawlerOutputInfoModel() {
		return crawlerInfoOutputModel;
	}

}
