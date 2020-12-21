package payroll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import payroll.dao.CSVReader;
import payroll.dao.ExcelReader;
import payroll.model.Employee;
import payroll.service.EmployeeService;
import payroll.utils.Common;

public class PayrollApp {

	public static void main(String[] args) {
		ExcelReader reader = new ExcelReader();
		
//		CSVReader reader = new CSVReader();
		List<Employee> employees = new ArrayList<Employee>();
		EmployeeService service = new EmployeeService();
		
		try {
			employees = reader.readExcel("C:\\Users\\Admin\\eclipse-workspace\\Payroll_Excell\\src\\employees.xlsx");
			
//			employees = reader.readCSV("C:\\Users\\nguyenducchinh\\Downloads\\Payroll_Excell\\src\\employees.csv");

		for (Employee employee : employees) {
			int age = (int) Common.getDuration(employee.getDob());
			int salary = (int) service.getSalaryNow(employee);
			System.out.println(employee.getName() + " - " + age + " tuổi");
			System.out.println("Lương: " + salary + " VND");
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
