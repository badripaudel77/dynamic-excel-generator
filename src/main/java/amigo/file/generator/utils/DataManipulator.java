package amigo.file.generator.utils;

import java.util.Map;

/**
 * This class is responsible for getting data and data map and put that data in the data map and
 * finally return that for writing to the file
 * Processes the json data which is nested in the payload,
 */
public class DataManipulator {

    public Map<Integer, Object[]> insertDataToMap(Map<Integer, Object[]> dataMap, Map<String, Object> jsonObject) {
        Integer j = 2;
        Integer noOfRecords = jsonObject.get("noOfRecords") instanceof Integer ? (Integer) jsonObject.get("noOfRecords") : 50;
        DataManipulator dataManipulator = new DataManipulator();

        // Get the headers configuration from jsonObject, "headers" is the payload
        Map<String, Map<String, Object>> headersConfig = (Map<String, Map<String, Object>>) jsonObject.get("headers");
        for (int i = 0; i < noOfRecords; i++) {
            // Create an Object[] to hold the dynamically generated data
            Object[] rowData = new Object[headersConfig.size()];
            int index = 0;
            // Iterate over the headers configuration and generate data for each field
            for (Map.Entry<String, Map<String, Object>> entry : headersConfig.entrySet()) {
                Map<String, Object> fieldConfig = entry.getValue();
                String fieldType = fieldConfig.get("type") != null ? (String) fieldConfig.get("type") : "string";
                // Generate data based on field type and configuration
                String prefix = fieldConfig.get("prefix") != null ? ((String) fieldConfig.get("prefix")).trim() : "";
                String postfix = fieldConfig.get("postfix") != null ? ((String) fieldConfig.get("postfix")).trim() : "";
                String value = fieldConfig.get("value") != null ? ((String) fieldConfig.get("value")).trim() : "";
                String formattedValue = this.formatValue(prefix, postfix, value, fieldType, i, j, index);
                rowData[index] = formattedValue;
                index++;
            }
            // Put the generated data into the dataMap
            dataMap.put(j, rowData);
            j++;
        }
        return dataMap;
    }

    private String formatValue(String prefix, String postfix, String value, String fieldType, Integer i, Integer j, Integer index) {
        String formattedValue = "";
        DataFaker dataFaker = new DataFaker();
        switch (fieldType) {
            case "email":
                // csed-amigo2240@abc.io [csed- => prefix, amigo => value, abc.io => postfix]
                formattedValue = prefix + "" +  value + j + "" + j + "" + index + ""+ i + "@" + (postfix == null ? "abc.io" : postfix);
                break;
            case "phone":
                formattedValue = prefix + " "+ dataFaker.getRandomPhoneNumber();
                break;
            case "name":
                String name = prefix + " " + value + " " + j + " " + j + i + " " + index + postfix;
                formattedValue = name != null ? name : dataFaker.getName();
                break;
            default:
                String defaultColumnValue = prefix + " " + value + " " + j + " " + j + i + " " + index +  " " + postfix;
                formattedValue = defaultColumnValue;
                break;
        }
        return formattedValue;
    }
}
