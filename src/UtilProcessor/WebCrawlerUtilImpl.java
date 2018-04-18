package UtilProcessor;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection.Response;
import InputProcessor.InputQueryProcessor;
import UtilProcessor.model.PageParserInfoModel;
import UtilProcessor.model.WebCrawlerInfoModel;
import java.net.URI;
import java.net.URISyntaxException;

public class WebCrawlerUtilImpl implements WebCrawlerUtil {

	PageParser parser;
	CrawlerVisitor crawler;
	String modelQuery;
	Response response;
	PageParserInfoModel pageParserModel;
	WebCrawlerInfoModel outputInfoModel;
	QueryProcessor query;

	//private HashSet<String> linksToCrawl;
	private HashSet<String> crawledLinks;
	private LinkedList<String> linksToCrawl;
	
	public WebCrawlerUtilImpl() {
		crawler = new CrawlerVisitor();
		parser = new PageParser();
		pageParserModel = new PageParserInfoModel();
		outputInfoModel = new WebCrawlerInfoModel();
		linksToCrawl = new LinkedList<String>();
		crawledLinks = new HashSet<String>();
		query = new QueryProcessor();
	}

	@Override
	public void crawlWebURLToGivenDepth(InputQueryProcessor input) throws IOException, URISyntaxException {
		try {
			UtilOp(input);
			
		} catch (Exception e) {
			System.out.println("Error!! Could not find page responses for the given input");
		}        
	}

	private void crawlerOnQueryOp(QueryProcessor query) throws IOException {
		
		response = crawler.crawlOnQuery(query);
		parser.parseRequiredContenton(response);
		pageParserModel = parser.GetParserInfoModel();
		
	}
	
	private void UtilOp(InputQueryProcessor input) throws IOException, URISyntaxException {
	
		int cnt = 0;
		
        int maxDepth = input.GetInputQuery().GetDepth();
            	
    	if (maxDepth != 0)
        	linksToCrawl.add(input.GetInputQuery().GetURLString());
    	else {
        	query.SetQuery(input.GetInputQuery().GetURLString());        	
        	crawlerOnQueryOp(query);
    		outputInfoModel.AddToCrawlerOutputInfoModel(pageParserModel.GetPageTitle(), pageParserModel.GetPageURL());	
    		return;
    	}
    	
    	URI uri = new URI(input.GetInputQuery().GetURLString());
	    String domain = uri.getHost();
	    String domainName = "";
	    
	    if (domain.startsWith("www."))
			domainName =  (String) domain.substring(4);
		else
			domainName =  (String) domain;
        
    	
		for (int currDepth = 0; currDepth < maxDepth;) {
			System.out.println("Processing for level " + (currDepth + 1));
			
	        while (linksToCrawl.size() != 0) {
	        	String currURL = linksToCrawl.poll();
	        	QueryProcessor query = new QueryProcessor();
	        	query.SetQuery(currURL);
		        System.out.println("Crawling For : " + currURL );

	        	if ( !crawledLinks.contains(currURL) ) {
	              
	        		try {
				    	  
	        			crawlerOnQueryOp(query);
	        			
	        			if (currDepth < maxDepth) {
		        			for (String queryString: pageParserModel.GetLinkedPages()) {
		        				
					            if (LinkedPageIsInSameDomain(queryString, domainName) == true) {
					            	
					            		linksToCrawl.add(queryString);
					            }
		        			}
	        			}
	        			
	        			//System.out.println(query.webURL);
	    	        	outputInfoModel.AddToCrawlerOutputInfoModel(pageParserModel.GetPageTitle(), pageParserModel.GetPageURL());

				      } catch (IOException e) {
				        System.err.println("For : " + currURL + "': " + e.getMessage());
				      }
		        	cnt++;

				} else {
		        	cnt++;

					System.out.println("already present !!!!!!!!!!!!!!!!");
				}
	        	
	        	crawledLinks.add(currURL);
	        	currDepth++;
			
			}
			
		}
		System.out.println(cnt);
	}
	
	private boolean LinkedPageIsInSameDomain(String currWebURL, String domainURL) {

		String patternString = ".*" + domainURL + ".*";
        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(currWebURL);
        boolean matches = matcher.matches();
        
		//System.out.println("Does domain url = " + patternString + " consists of curr web url =  " + currWebURL + ". Answer is " + matches);

		return matches;
		
	}
	@Override
	public HashMap<String, String>  getParsedDataOutput() {
		// TODO Auto-generated method stub
		return outputInfoModel.GetCrawlerOutputInfoModel();
	}

	

}
