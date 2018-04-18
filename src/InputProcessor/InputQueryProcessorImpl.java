package InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputQueryProcessorImpl implements InputQueryProcessor {
	
	InputModel modelQuery;
	
	public InputQueryProcessorImpl() {
		modelQuery = new InputModel();
	}

	@Override
	public void handleInput() throws IOException {
		
        System.out.println("Enter Base URL");   

        BufferedReader reader = 
                   new BufferedReader(new InputStreamReader(System.in));
        
        String webURL = "";
        int reqDepth = 0;
        
        // Reading web url
        try {
        	webURL = reader.readLine();
        } catch(Exception e) {
            System.out.println("Invalid input" + e.toString());
        }
        
        System.out.println("Enter required depth to crawl");   

        // Reading depth
        try {
            reqDepth = Integer.parseInt(reader.readLine());
        } catch(Exception e) {
            System.out.println("Invalid input" + e.toString());
        }
        

        modelQuery.SetDepth(reqDepth);
        modelQuery.SetURLString(webURL);
        
        // Reading for web url and depth
        System.out.println("Crawling for " + webURL + " till depth = " + reqDepth);
        
	}
	
	public InputModel GetInputQuery() {
		return modelQuery;
	}


}
