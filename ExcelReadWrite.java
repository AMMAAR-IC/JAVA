// ExcelReadWrite.java
import org.apache.poi.xssf.usermodel.*;
import java.io.*;

public class ExcelReadWrite {
    public static void main(String[] args) throws Exception {
        try (FileInputStream fis = new FileInputStream("data.xlsx");
             XSSFWorkbook wb = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = wb.getSheetAt(0);
            int lastRow = sheet.getLastRowNum() + 1;
            XSSFRow row = sheet.createRow(lastRow);
            row.createCell(0).setCellValue("New Item");
            row.createCell(1).setCellValue(123);

            try (FileOutputStream fos = new FileOutputStream("data_updated.xlsx")) {
                wb.write(fos);
            }
            System.out.println("Appended row to data_updated.xlsx");
        }
    }
}
