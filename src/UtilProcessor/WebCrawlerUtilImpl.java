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
      
    	linksToCrawlAtNextLevel.add(input.GetInputQuery().GetURLString());
    	
    	String domainName = "";
    	
    	try {
    		URI uri = new URI(input.GetInputQuery().GetURLString());
        	domainName = uri.getHost();
        	domainName.replaceFirst("^(https?://)?(www\\.)?(http?://)?", "");

		} catch (Exception e) {
			System.out.println("Could not find Domain for " + input.GetInputQuery().GetURLString());
		}
    	
		for (int currDepth = 0; currDepth <= maxDepth; currDepth++) {
			System.out.println("Processing for level " + (currDepth));
			
			linksToCrawl = (LinkedList<String>) linksToCrawlAtNextLevel.clone();
			
			linksToCrawlAtNextLevel.clear();
			
			System.out.println("Crawling at Level " + (currDepth));

	        while (linksToCrawl.size() != 0) {
	        	String currURL = linksToCrawl.poll();
	        	QueryProcessor query = new QueryProcessor();
	        	query.SetQuery(currURL);
		       
	        	if ( !crawledLinks.contains(currURL) ) {
	              
	        		try {
				    	  
	        			crawlerOnQueryOp(query);
	        			
	        			if (currDepth + 1 <= maxDepth) {
			        		for (String queryString: pageParserModel.GetLinkedPages()) {
			        				
						            if (LinkedPageIsInSameDomainMatcher(queryString, domainName) == true) {
						            	
						            		linksToCrawlAtNextLevel.add(queryString);
						            }
			        		}
	        			}
	        			if (outputInfoModel.GetCrawlerOutputInfoModel().get(pageParserModel.GetPageTitle()) == null) {
	        				outputInfoModel.AddToCrawlerOutputInfoModel(pageParserModel.GetPageTitle(), pageParserModel.GetPageURL());
	        				System.out.println("Title : " + pageParserModel.GetPageTitle() + " URL: " + pageParserModel.GetPageURL());
	        			}
				      } catch (IOException e) {
				        System.err.println("For : " + currURL + "': " + e.getMessage());
				      }
		        	
				} else {
					System.out.println("Title for" + currURL + "is tracked already !!");
				}
	        	crawledLinks.add(currURL);
			}
		}
	}
	
	private boolean LinkedPageIsInSameDomainMatcher(String currWebURL, String domainURL) throws URISyntaxException {

		try {
			URI uri = new URI(currWebURL);
	
	    	String domainNameCurrURL = uri.getHost();
		    
	    	domainNameCurrURL.replaceFirst("^(https?://)?(www\\.)?(http?://)?", "");
	    	
	    	if (domainNameCurrURL.equals(domainURL)) {
	    		return true;
	    	}
    	
		} catch (Exception e) {
			System.out.println("Unmatching Domains for " + currWebURL + " with " + domainURL);
		}
		return false;
  
	}
	
	@Override
	public HashMap<String, String>  getParsedDataOutput() {
		// TODO Auto-generated method stub
		return outputInfoModel.GetCrawlerOutputInfoModel();
	}

	

}
