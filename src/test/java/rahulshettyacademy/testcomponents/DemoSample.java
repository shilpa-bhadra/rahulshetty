package rahulshettyacademy.testcomponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoSample {

	DataFormatter formatter = new DataFormatter();
	
	
	@Test(dataProvider="req")
	public void testSample(String username,String password, String intValue) {
		System.out.println(username+password+intValue);

	}
	
	
	
	@DataProvider(name="req")
public Object[][] getData() throws IOException {

FileInputStream fip = new FileInputStream("C:\\Users\\shilpab\\OLB_eclipse_workspace\\SeleniumFrameworkDesign\\Sample\\ExcelData.xlsx");

	@SuppressWarnings("resource")
	XSSFWorkbook workbook = new XSSFWorkbook(fip);
	
	Sheet sheet =workbook.getSheet("data");
	int totalRow =sheet.getLastRowNum();
		System.out.println("total row is :"+totalRow);
	
	short totalColumn=sheet.getRow(0).getLastCellNum();
	System.out.println("total column is :"+totalColumn);
	
	Object[][] data = new Object[totalRow-1][totalColumn];
	Row row;
	for(int i=0;i<totalRow-1;i++) {
		
		row =sheet.getRow(i+1);
		for(int j=0;j<totalColumn;j++) {
			
			Cell cell=row.getCell(j);
			data[i][j]=	formatter.formatCellValue(cell);
			
			
			
		}
	}
	
	
	return data;
	
	
	}
		

}
