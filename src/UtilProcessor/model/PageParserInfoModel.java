package UtilProcessor.model;

import java.util.HashSet;

public class PageParserInfoModel {

	HashSet<String> directedLinksHashSet;
	String mainPageTitle;
	String webURL;
	
	public PageParserInfoModel() {
		directedLinksHashSet = new HashSet<String>();
		mainPageTitle = "";
		webURL = "";
	}
	
	public String GetPageURL() {
		return webURL;
	}
	
	public String GetPageTitle() {
		return mainPageTitle;
	}
	
	public HashSet<String> GetLinkedPages() {
		return directedLinksHashSet;
	}
	
	public void SetPageURL(String webURLData) {
		webURL = webURLData;
	}
	
	public void SetPageTitle(String pageTitleData) {
		mainPageTitle = pageTitleData;
	}
	
	public void SetLinkedPages(HashSet<String> directedLinksHashSetData) {
		directedLinksHashSet = directedLinksHashSetData;
	}
	

}
