package framework.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.HashMap;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper; //import for jackson databind
import org.apache.commons.io.FileUtils;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

		// Convert JSON from file to one String
		String jsonData = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		// Coverting JSON String to list of HashMaps using Jackson DataBind
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		
		return data;
	}
}
