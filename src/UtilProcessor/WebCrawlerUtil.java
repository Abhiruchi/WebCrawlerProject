package UtilProcessor;

import java.io.IOException;
import java.util.HashSet;

public interface WebCrawlerUtil {
	
	void crawlWebURLToGivenDepth(String webURL, int depth) throws IOException;

	HashSet<String> getData();

	String getParserDataTitle();

	HashSet<String> getParserHashSet();
}
