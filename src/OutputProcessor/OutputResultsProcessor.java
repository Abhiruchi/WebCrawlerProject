package OutputProcessor;

import java.util.HashSet;

public interface OutputResultsProcessor {

	void handleOutput(String string, HashSet<String> hashSet);

}
