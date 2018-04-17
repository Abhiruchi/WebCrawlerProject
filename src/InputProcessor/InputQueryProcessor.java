package InputProcessor;

import java.io.IOException;

public interface InputQueryProcessor {
	
	void handleInput() throws IOException;

	String GetURLString();

	int GetDepth();

}
