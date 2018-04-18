package UtilProcessor;

public class QueryProcessor {

	private String webURL;
	
	public QueryProcessor() {
		webURL = "";
	}

	public String GetQuery() {
		return webURL;
	}
	
	public void SetQuery(String webURLData) {
		 webURL = webURLData;
	}
	
}
