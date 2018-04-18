package InputProcessor;

public class InputModel {

	static String webURL;
	static int reqDepth;
	
	public InputModel() {
		webURL = "";
		reqDepth = 0;	
	}

	public String GetURLString() {
		return webURL;
	}
	
	public int GetDepth() {
		return reqDepth;
	}
	
	public void SetURLString(String webURLData) {
		webURL = webURLData;
	}
	
	public void SetDepth(int reqDepthData) {
		 reqDepth = reqDepthData;
	}
	
}
