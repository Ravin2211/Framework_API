/* Excel File → XLUtility → DataProvider → Test Method */

package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XLUtility {

        private String filePath;
        private Workbook workbook;
        private Sheet sheet;

        public XLUtility(String filePath) {
            this.filePath = filePath;
            try {
                FileInputStream fis = new FileInputStream(filePath);
                workbook = WorkbookFactory.create(fis);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void setSheet(String sheetName) {
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
        }

        public int getRowCount() {
            return sheet.getLastRowNum() + 1;
        }

        public int getCellCount(int rowNum) {
            Row row = sheet.getRow(rowNum);
            if (row == null) return 0;
            return row.getLastCellNum();
        }

        public String getCellData(int rowNum, int colNum) {
            Row row = sheet.getRow(rowNum);
            if (row == null) return "";

            Cell cell = row.getCell(colNum);
            if (cell == null) return "";

            if (cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == CellType.NUMERIC)
                return String.valueOf(cell.getNumericCellValue());
            else if (cell.getCellType() == CellType.BOOLEAN)
                return String.valueOf(cell.getBooleanCellValue());
            else
                return "";
        }

        public void setCellData(int rowNum, int colNum, String value) {
            Row row = sheet.getRow(rowNum);
            if (row == null)
                row = sheet.createRow(rowNum);

            Cell cell = row.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellValue(value);

            saveFile();
        }

        private void saveFile() {
            try {
                FileOutputStream fos = new FileOutputStream(filePath);
                workbook.write(fos);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void close() {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


