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
	private LinkedList<String> linksToCrawlAtNextLevel;

	public WebCrawlerUtilImpl() {
		crawler = new CrawlerVisitor();
		parser = new PageParser();
		pageParserModel = new PageParserInfoModel();
		outputInfoModel = new WebCrawlerInfoModel();
		linksToCrawl = new LinkedList<String>();
		linksToCrawlAtNextLevel = new LinkedList<String>();
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
			
        int maxDepth = input.GetInputQuery().GetDepth();
            	
    	///if (maxDepth != 0)
    	linksToCrawlAtNextLevel.add(input.GetInputQuery().GetURLString());
    	/*else {
			System.out.println("Processing for level 0" );

        	query.SetQuery(input.GetInputQuery().GetURLString());        	
        	crawlerOnQueryOp(query);
    		outputInfoModel.AddToCrawlerOutputInfoModel(pageParserModel.GetPageTitle(), pageParserModel.GetPageURL());	
    		return;
    	}*/
    	
    	URI uri = new URI(input.GetInputQuery().GetURLString());
	    String domain = uri.getHost();
	    String domainName = "";
	    
	    if (domain.startsWith("www."))
			domainName =  (String) domain.substring(4);
		else
			domainName =  (String) domain;
        
    	
		for (int currDepth = 0; currDepth <= maxDepth; currDepth++) {
			System.out.println("Processing for level " + (currDepth));
			
			System.out.println(linksToCrawlAtNextLevel);

			LinkedList<String> clone = (LinkedList<String>) linksToCrawlAtNextLevel.clone();
			linksToCrawl = clone;
			
			linksToCrawlAtNextLevel.clear();
			
			System.out.println(linksToCrawl);
			
	        while (linksToCrawl.size() != 0) {
	        	String currURL = linksToCrawl.poll();
	        	QueryProcessor query = new QueryProcessor();
	        	query.SetQuery(currURL);
		        System.out.println("Crawling For Level " + (currDepth) + " for url : " + currURL );

	        	if ( !crawledLinks.contains(currURL) ) {
	              
	        		try {
				    	  
	        			crawlerOnQueryOp(query);
	        			
	        			if (currDepth + 1 <= maxDepth) {
			        		for (String queryString: pageParserModel.GetLinkedPages()) {
			        				
						            if (LinkedPageIsInSameDomain(queryString, domainName) == true) {
						            	
						            		linksToCrawlAtNextLevel.add(queryString);
						            }
			        		}
	        			}
	        			if (outputInfoModel.GetCrawlerOutputInfoModel().get(pageParserModel.GetPageTitle()) == null)
	        				outputInfoModel.AddToCrawlerOutputInfoModel(pageParserModel.GetPageTitle(), pageParserModel.GetPageURL());

				      } catch (IOException e) {
				        System.err.println("For : " + currURL + "': " + e.getMessage());
				      }
		        	
				} else {
					System.out.println("Title Tracked already !!");
				}
	        	crawledLinks.add(currURL);
			}
		}
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
