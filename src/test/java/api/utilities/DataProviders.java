/* Excel File → XLUtility → DataProvider → Test Method */

package api.utilities;


import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "excelData")
    public Object[][] getExcelData() {

        String excelPath = System.getProperty("user.dir") + "/TestData/UserData.xlsx";
        String sheetName = "Sheet1";

        XLUtility xl = new XLUtility(excelPath);
        xl.setSheet(sheetName);

        int rowCount = xl.getRowCount();
        int colCount = xl.getCellCount(0);

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {               // skip header row
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = xl.getCellData(i, j);
            }
        }

        xl.close();
        return data;
    }

    @DataProvider(name = "getUsername")
    public Object[] getUsername() {

        String excelPath = System.getProperty("user.dir") + "/TestData/UserData.xlsx";
        String sheetName = "Sheet1";

        XLUtility xl = new XLUtility(excelPath);
        xl.setSheet(sheetName);

        int rowCount = xl.getRowCount();

        // Assuming username is in column 0
        Object[] usernames = new Object[rowCount - 1];

        for (int i = 1; i < rowCount; i++) {
            usernames[i - 1] = xl.getCellData(i, 0); // 0 → username column
        }

        xl.close();
        return usernames;
    }

}
