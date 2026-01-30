package utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Simple utility to read data from an Excel sheet.  This class uses
 * Apache POI to parse XLSX files.  It reads the first sheet by default
 * and returns its contents as a two‑dimensional Object array suitable
 * for TestNG data providers.  Only basic loops are used to avoid
 * introducing complex collection frameworks.
 */
public class ExcelUtils {

    /**
     * Reads the given Excel file and returns its contents as a 2D Object array.
     * The first row is considered as header and skipped.  Each cell value
     * is converted to a String.
     *
     * @param filePath absolute or relative path to the Excel file
     * @return two dimensional Object array with the data
     */
    public static Object[][] readExcelData(String filePath) {
        Object[][] data = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            // Use the first row for header, skip it
            Row firstRow = sheet.getRow(0);
            int colCount = firstRow.getLastCellNum();
            data = new Object[rowCount - 1][colCount];
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        // Convert the cell value to a string regardless of its original type
                        data[i - 1][j] = cell.toString();
                    } else {
                        data[i - 1][j] = "";
                    }
                }
            }
            workbook.close();
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("Error reading Excel file: " + e.getMessage());
        }
        return data;
    }
}