package OutputProcessor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import UtilProcessor.WebCrawlerUtil;

public class OutputResultsProcessorImpl implements OutputResultsProcessor {

	public OutputResultsProcessorImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleOutput(WebCrawlerUtil crawlerOp) {
		
		System.out.println("OUTPUT ------- ");

		// Output the parsed data output after crawler operation
		//Delimiter used in CSV file
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		
		//CSV file header
		final String FILE_HEADER = "TITLE , WEB URL";
		String fileName = "results.csv";

		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(fileName);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
					    
			for (Map.Entry<String, String> entry : crawlerOp.getParsedDataOutput().entrySet()) {
			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			    fileWriter.append(String.valueOf(entry.getKey()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(entry.getValue());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	


	}

	


}
