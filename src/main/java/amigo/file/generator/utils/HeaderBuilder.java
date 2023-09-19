package amigo.file.generator.utils;

import java.util.*;

/**
 * Gets the header from the user and returns the appropriate header
 */
public class HeaderBuilder {
    Object[] headersArr;
    public Object[] getHeaders(List<String> headersValue) {
        Object[] headers = new Object[headersValue.size()];
        int i = 0;
        for(String header : headersValue) {
           headers[i] = header;
           i++;
        }
        headersArr = new Object[headers.length];
        return  headers;
    }

    public Object[] processHeader(Map<String, Object> jsonMap) {
        List<String> headers = new ArrayList<>();
        DataManipulator dataManipulator = new DataManipulator();
        Map<String, List<Object>> columnsInfoMap = new LinkedHashMap<>();

        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            String key = entry.getKey();
            if(key.equals("headers")) {
                Object value = entry.getValue();
                Map<String, Object> nestedMap = (Map<String, Object>) value;
                for (Map.Entry<String, Object> nestedEntry : nestedMap.entrySet()) {
                    String nestedKey = nestedEntry.getKey();
                    Object nestedValue = nestedEntry.getValue();
                    headers.add(nestedKey);
                    columnsInfoMap.put(nestedKey, List.of(((LinkedHashMap) nestedValue).entrySet().toArray()));
                }
            }
        }
        Object [] objectsArray = getHeaders(headers);
        return objectsArray;
    }
}
