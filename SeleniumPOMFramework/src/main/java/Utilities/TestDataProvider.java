package Utilities;

import java.util.Hashtable;


public class TestDataProvider {
/*@Test(dataProvider="getTestData")
	public void SampleTestOne(Hashtable<String,String> table ) {
	System.out.println(table.get("Col2 "));

	}
*/
	
	public static Object[][] getTestData(String dataFileName,String SheetName,String TestName) {
		ReadExcelDataFile readData = new ReadExcelDataFile(
				System.getProperty("user.dir") + "//src//test//java//"+dataFileName);
		String sheetName = SheetName;
		String testName = TestName;

		// Find Start Row of TestCase
		int startRowNum = 0;
		while (!readData.getCellData(sheetName, 0, startRowNum).equalsIgnoreCase(testName)) {
			startRowNum++;
		}

		int startTestColumn = startRowNum + 1;
		int startTestRow = startRowNum + 2;
		// find no. if rows of each test case
		int rows = 0;
		while (!readData.getCellData(sheetName, 0, startTestRow + rows).equals("")) {
			rows++;
		}

		int columns = 0;
		while (!readData.getCellData(sheetName, columns, startTestColumn).equals("")) {
			columns++;
		}

		Object[][] dataset = new Object[rows][1];
Hashtable<String,String> dataTable = null;
		int datasetRownum = 0;
		for (int rowNumber = startTestRow; rowNumber <= startTestColumn + rows; rowNumber++) {
			dataTable = new Hashtable<String,String>();
			for (int colNumber = 0; colNumber < columns; colNumber++) {
				String key =readData.getCellData(sheetName, colNumber, startTestColumn);
				String value=readData.getCellData(sheetName, colNumber, rowNumber);
				dataTable.put(key, value);
				//dataset[datasetRownum][colNumber] = readData.getCellData(sheetName, colNumber, rowNumber);
				
			}
			dataset[datasetRownum][0]=dataTable;
			datasetRownum++;
		}

		return dataset;
	}
}
