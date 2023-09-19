package amigo.file.generator.utils;

/**
 * This class is responsible for returning extension of the file based on what is given by the user
 */
public class FileExtension {
    public static String EXCEL_FILE = ".xlsx";
    public static String PDF_FILE = ".pdf";
    public static String CSV_FILE = ".csv";

    /**
     * @param type of the file to be saved as.
     * @return appropriate extension of the file such as .csv, .pdf etc.
     */
    public String getFileExtension(String type) {
        String extension = "";
        switch (type.toLowerCase()) {
            case "pdf":
                extension = PDF_FILE;
                break;
            case "csv":
                extension = CSV_FILE;
                break;
            default:
                extension = EXCEL_FILE;
                break;
        }
        return extension;
    }
}
