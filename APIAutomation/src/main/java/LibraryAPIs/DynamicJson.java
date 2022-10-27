package LibraryAPIs;

import Basics.Files.Payload;
import Basics.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    @Test
    public void addBookAPI() throws IOException{
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        List<String> data = get_data_by_testcase_name("One");
        System.out.println(data);
        HashMap<String, Object> map_to_json = new HashMap<>();
        map_to_json.put("name", data.get(1));
        map_to_json.put("isbn", data.get(2));
        map_to_json.put("aisle", data.get(3));
        map_to_json.put("author", data.get(4));
        System.out.println(map_to_json);
        String response = given().log().all().header("Content-Type", "application/json").body(map_to_json)
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        String id = ReusableMethods.get_data_from_json(response, "ID");
        System.out.println(id);
    }
    @DataProvider(name = "BooksData")
    public Object[][] getData(){
        return new Object[][]{{"uutyt005", "224200011"}, {"uutyt006", "224200012"}, {"uutyt007", "224200013"}};
//        Map<String, String> map_to_json = new HashMap<>();
//        map_to_json.put("name", "Learn Appium Automation with Java");
//        map_to_json.put("isbn", "uutyt");
//        map_to_json.put("aisle", "22420009");
//        map_to_json.put("author", "Shrilakshmijjdjhjdjdjd");
//        return new Object[][] {map_to_json};
    }

    public ArrayList<String> get_data_by_testcase_name(String testcase_name) throws IOException {
        //XSSFWorkbook accepts fileInputStream argument
        FileInputStream fis = new FileInputStream("C:\\Users\\lkshm\\OneDrive\\Desktop\\Automation\\APIAutomation\\APIAutomation\\src\\main\\java\\LibraryAPIs\\Apache-POI-usecases.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int num_of_sheets = workbook.getNumberOfSheets();
        XSSFSheet sheet = null;
        for(int i = 0; i < num_of_sheets; i++)
            if (workbook.getSheetAt(i).getSheetName().equalsIgnoreCase("Data_Set_2"))
                sheet = workbook.getSheetAt(i);
        // Identify the column by scanning the entire 1st row
        // once column is identified then scan entire testcases column to identify purchase testcase row
        // after you grab purchase row, pull all the data of that row and feed into rest
        Iterator<Row> rows = sheet.iterator();
        Row first_row = rows.next();
        Iterator<Cell> cellIterator = first_row.cellIterator();
        int col = 0;
        while (cellIterator.hasNext()){
            if (cellIterator.next().getStringCellValue().equalsIgnoreCase("Testcase"))
                break;
            col++;
        }
        Row row = null;
        while (rows.hasNext()){
            row = rows.next();
            if (row.getCell(col).getStringCellValue().equalsIgnoreCase(testcase_name))
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
