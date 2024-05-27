package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseFileToMap(String filepath) throws Exception {
        Path pathToJson = Paths.get(filepath).toAbsolutePath();
        if (filepath.endsWith(".json")) {
            ObjectMapper objectMapper = new JsonMapper();
            Map<String, Object> jsonToMap = objectMapper.readValue(pathToJson.toFile(),
                    new TypeReference<Map<String, Object>>() {
                    });
            return jsonToMap;
        } else {
            ObjectMapper objectMapper = new YAMLMapper();
            Map<String, Object> yamlToMap = objectMapper.readValue(pathToJson.toFile(),
                    new TypeReference<Map<String, Object>>() {
                    });
            return yamlToMap;
        }
    }
}
