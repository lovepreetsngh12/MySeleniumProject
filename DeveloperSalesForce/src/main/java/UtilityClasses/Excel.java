package UtilityClasses;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
public void Excel_Function() throws IOException {
	File file =    new File(System.getProperty("user.dir") +"\\Resources.Utilities\\InputData.xlsx");
	FileInputStream inputStream = new FileInputStream(file);
XSSFWorkbook wb = new XSSFWorkbook(inputStream);
XSSFSheet sheet = wb.createSheet("salesforce Sheet");

// Create a row and put some cells in it. Rows are 0 based.
XSSFRow row1 = sheet.createRow(0);

// Create a cell
row1.createCell(0).setCellValue("abcgmail.com");

XSSFRow row2 = sheet.createRow(1);
row1.createCell(1).setCellValue("Australia");

// Write the output to a file
FileOutputStream fileOut = new FileOutputStream("read.xlsx");
wb.write(fileOut);
fileOut.close();
System.out.println("Successfully Created workbook");
}

public static String getvalue1() throws IOException {
	File file =    new File(System.getProperty("user.dir") +"\\Resources.Utilities\\InputData.xlsx");
	FileInputStream inputStream = new FileInputStream(file);
XSSFWorkbook wb = new XSSFWorkbook(inputStream);
// getting the sheet
XSSFSheet sh = wb.getSheet("salesforce Sheet");
// storing the value from the excel sheet
String value = sh.getRow(0).getCell(0).getStringCellValue();
return value;
}

public static String getvalue2() throws IOException {
	File file =    new File(System.getProperty("user.dir") +"\\Resources.Utilities\\InputData.xlsx");
	FileInputStream inputStream = new FileInputStream(file);
XSSFWorkbook wb = new XSSFWorkbook(inputStream);
// getting the sheet
XSSFSheet sh = wb.getSheet("salesforce Sheet");
// storing the value from the excel sheet
String value2 = sh.getRow(0).getCell(1).getStringCellValue();
return value2;
}

}