package InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputQueryProcessorImpl implements InputQueryProcessor {
	
	InputModel model;
	
	public InputQueryProcessorImpl() {
        model = new InputModel();
	}

	@Override
	public void handleInput() throws IOException {
		
        System.out.println("Enter Base URL");   

        BufferedReader reader = 
                   new BufferedReader(new InputStreamReader(System.in));
        
        String webURL = "";
        int reqDepth = 0;
        
        // Reading data 
        try {
        	webURL = reader.readLine();
        } catch(Exception e) {
            System.out.println("Invalid input" + e.toString());
        }
        
        System.out.println("Enter required depth to crawl");   

        try {
            reqDepth = Integer.parseInt(reader.readLine());
        } catch(Exception e) {
            System.out.println("Invalid input" + e.toString());
        }
        
        

        model.SetDepth(reqDepth);
        model.SetURLString(webURL);
        
        // Printing the read line
        System.out.println("Crawling for " + webURL + " till depth = " + reqDepth);
        
	}
	
	public InputModel GetInputModel() {
		return model;
	}

}
