package payroll.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import payroll.model.Employee;
import payroll.utils.Constants;

public class ExcelReader {
	
	public List<Employee> readExcel(String excelFilePath) throws IOException {
        List<Employee> listEmployees = new ArrayList<>();
 
        // Get file
        File file = new File(excelFilePath);
        FileInputStream fis = new FileInputStream(file);
 
        // Get workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
 
        // Get sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
 
        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
 
            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();
 
            // Read cells and set value for employee object
            Employee employee = new Employee();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                case Constants.COLUMN_NAME:
                    employee.setName("" + getCellValue(cell));
                    break;
                case Constants.COLUMN_DOB:
                    employee.setDob("" + getCellValue(cell));
                    break;
                case Constants.COLUMN_ROLE:
                    employee.setRole("" + getCellValue(cell));
                    break;
                case Constants.COLUMN_START_DATE:
                    employee.setStartdate("" + getCellValue(cell));
                    break;
                case Constants.COLUMN_SALARY:
                    employee.setSalary(new BigDecimal((double) cellValue).intValue());
                    break;

                default:
                    break;
                }
            }
            listEmployees.add(employee);
        }
        workbook.close();
        fis.close();
 
        return listEmployees;
    }
	
    // Get cell value
    private Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = cell.getNumericCellValue();
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
        case BLANK:
        case ERROR:
            break;
        default:
            break;
        }
 
        return cellValue;
    }
}
