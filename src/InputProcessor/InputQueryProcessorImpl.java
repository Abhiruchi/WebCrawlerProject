package InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputQueryProcessorImpl implements InputQueryProcessor {

	static String webURL = "";
	static int reqDepth = 0;
	
	public InputQueryProcessorImpl() {
		webURL = "";
		reqDepth = 0;	
	}


	@Override
	public void handleInput() throws IOException {
		
        System.out.println("Enter Base URL");   

        BufferedReader reader = 
                   new BufferedReader(new InputStreamReader(System.in));
        
        // Reading data 
        webURL = reader.readLine();
 
        System.out.println("Enter required depth to crawl");   

        reqDepth = Integer.parseInt(reader.readLine());

        // Printing the read line
        System.out.println("Crawling for " + webURL + " till depth = " + reqDepth);
        
	}
	
	public String GetURLString() {
		return webURL;
	}
	
	public int GetDepth() {
		return reqDepth;
	}

}
