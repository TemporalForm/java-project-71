package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.Map;
import java.util.SortedMap;

public class Json {
    public static String buildJsonResult(SortedMap<String, Map<String, Object>> differenceTree) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("Difference.json"), differenceTree);
        String resultDiff = objectMapper.writeValueAsString(differenceTree);
        return resultDiff;
    }
}
