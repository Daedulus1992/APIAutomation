package ApachePOIAPI;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDrivenTesting {
    @Test
    public void accessing_work_book() throws IOException {
        for (String data: get_testcase_name("Purchase"))
            System.out.print(data+" ");
    }

    public ArrayList<String> get_testcase_name(String testcase_name) throws IOException {
        //XSSFWorkbook accepts fileInputStream argument
        FileInputStream fis = new FileInputStream("C:\\Users\\lkshm\\OneDrive\\Desktop\\Automation\\APIAutomation\\APIAutomation\\src\\main\\java\\ApachePOIAPI\\Apache-POI-usecases.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int num_of_sheets = workbook.getNumberOfSheets();
        XSSFSheet sheet = null;
        for(int i = 0; i < num_of_sheets; i++)
            if (workbook.getSheetAt(i).getSheetName().equalsIgnoreCase("Data_Set_1"))
                sheet = workbook.getSheetAt(i);
        // Identify the column by scanning the entire 1st row
        // once column is identified then scan entire testcases column to identify purchase testcase row
        // after you grab purchase row, pull all the data of that row and feed into rest
        Iterator<Row> rows = sheet.iterator();
        Row first_row = rows.next();
        Iterator<Cell> cellIterator = first_row.cellIterator();
        int col = 0;
        while (cellIterator.hasNext()){
            if (cellIterator.next().getStringCellValue().equalsIgnoreCase("Testcases"))
                break;
            col++;
        }
        Row row = null;
        while (rows.hasNext()){
            row = rows.next();
            if (row.getCell(col).getStringCellValue().equalsIgnoreCase("Purchase"))
                break;
        }
        Iterator<Cell> cells = row.cellIterator();
        ArrayList<String> test_data = new ArrayList<>();
        while (cells.hasNext()) {
            Cell cell = cells.next();
            if (cell.getCellType() == CellType.STRING)
                test_data.add(cell.getStringCellValue());
            else test_data.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
        }
        return test_data;
    }
}
