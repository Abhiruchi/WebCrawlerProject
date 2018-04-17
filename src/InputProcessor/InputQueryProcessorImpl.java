package InputProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputQueryProcessorImpl implements InputQueryProcessor {

	public InputQueryProcessorImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processInput(String baseURL, int depth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInput() throws IOException {
		
        System.out.println("Enter Base URL");   

        BufferedReader reader = 
                   new BufferedReader(new InputStreamReader(System.in));
        
        // Reading data 
        String baseURL = reader.readLine();
 
        System.out.println("Enter required depth to crawl");   

        int reqDepth = Integer.parseInt(reader.readLine());

        // Printing the read line
        System.out.println("Crawling for " + baseURL + " till depth = " + reqDepth);
        
	}

}
