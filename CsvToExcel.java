// CsvToExcel.java
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import java.nio.file.*;

public class CsvToExcel {
    public static void main(String[] args) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Sheet1");
        int rownum = 0;
        for (String line : Files.readAllLines(Paths.get("input.csv"))) {
            XSSFRow row = sheet.createRow(rownum++);
            String[] cols = line.split(",", -1);
            for (int i = 0; i < cols.length; i++) row.createCell(i).setCellValue(cols[i]);
        }
        try (FileOutputStream fos = new FileOutputStream("output.xlsx")) { wb.write(fos); }
        wb.close();
        System.out.println("Saved output.xlsx");
    }
}
