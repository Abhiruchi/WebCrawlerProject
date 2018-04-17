package InputProcessor;

import java.io.IOException;

public interface InputQueryProcessor {
	
	void processInput(String baseURL, int depth);
	void handleInput() throws IOException;

}
