package UtilProcessor;

import java.io.IOException;
import java.util.HashSet;

import InputProcessor.InputQueryProcessor;

public interface WebCrawlerUtil {
	
	void crawlWebURLToGivenDepth(InputQueryProcessor input) throws IOException;

	String getParserDataTitle();

	HashSet<String> getParserHashSet();
}
