package amigo.file.generator.service;

import amigo.file.generator.utils.DataManipulator;
import amigo.file.generator.utils.HeaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FileGeneratorService {
    Logger logger = LoggerFactory.getLogger(FileGeneratorService.class);

    public boolean generateFile(Map<String, Object> jsonObject) {
        boolean isSuccess;
        HeaderBuilder headerBuilder = new HeaderBuilder();
        DataManipulator dataManipulator = new DataManipulator();
        FileWriterService fileWriterService = new FileWriterService();
        Map<Integer, Object[]> dataMap = new TreeMap<>();

        // Get the headers and Build them for the file.
        Object[] headers = headerBuilder.processHeader(jsonObject);
        dataMap.put(1, headers);

        // iterate over the fetched data and put it into data map
        Map<Integer, Object[]> newData = dataManipulator.insertDataToMap(dataMap, jsonObject);
        dataMap = newData;
        try {
            // write to the file
            isSuccess = fileWriterService.writeToFile(dataMap, (String) jsonObject.get("fileType"));
        }
        catch (Exception e) {
            isSuccess = false;
            logger.error("Something went wrong while downloading files : " + e.getMessage());
        }
        return isSuccess;
    }
}
