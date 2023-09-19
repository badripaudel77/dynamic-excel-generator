package amigo.file.generator.service;

import amigo.file.generator.utils.FileExtension;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;

public class FileWriterService {
    Logger logger = LoggerFactory.getLogger(FileWriterService.class);

    public boolean writeToFile(Map<Integer, Object[]> dataMap, String fileType) {
        String fileFolder = "filegenerator";
        boolean isSuccess;
        FileExtension fileExtension = new FileExtension();
        //Create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet, a workbook can have multiple sheets : in excel you can have multiple sheets in one excel file
        XSSFSheet sheet = workbook.createSheet("filegenerator.com");

        //Iterate over data and write to sheet
        Set<Integer> keyset = dataMap.keySet();
        int rownum = 0; // tracks the row number from 1 to .....
        for (Integer key : keyset) {
            Row row = sheet.createRow(rownum++); //whole row
            Object [] objArr = dataMap.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++); // cell is one box  in excel file []
                if(obj instanceof String)
                    ((Cell) cell).setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        // write to the Excel file
        try {
            //Write the workbook in file system
            String home = System.getProperty("user.home");
            if (new File(home + "/" + fileFolder).exists()) {
                fileFolder = "filegenerator";
            } else {
                new File(home + "/" + fileFolder).mkdir();
            }
            FileOutputStream out = new FileOutputStream(home + "/" + fileFolder + "/generated_file" + fileExtension.getFileExtension(fileType));
            workbook.write(out);
            isSuccess = true;
            out.close();
            logger.info("Saved To " + home + "/" + fileFolder);
        }
        catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
            logger.error("Something went wrong while downloading files : " + e.getMessage());
        }
        return isSuccess;
    }
}
