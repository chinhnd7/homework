package payroll.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import payroll.model.Employee;
import payroll.utils.Constants;

public class CSVReader {
	public List<Employee> readCSV(String csvFilePath) throws IOException, NumberFormatException {
		List<Employee> employees = new ArrayList<Employee>();

		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader(csvFilePath));

			while ((line = br.readLine()) != null) {
				employees.add(parseEmployee(line));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException crunchifyException) {
				crunchifyException.printStackTrace();
			}
		}

		return employees;
	}

	private Employee parseEmployee(String line) throws NumberFormatException {
		Employee employee = new Employee();
		String data[] = line.split(",");

		employee.setName(data[Constants.COLUMN_NAME]);
		employee.setDob(data[Constants.COLUMN_DOB]);
		employee.setRole(data[Constants.COLUMN_ROLE]);
		employee.setStartdate(data[Constants.COLUMN_START_DATE]);
		employee.setSalary(Integer.parseInt(data[Constants.COLUMN_SALARY]));

		return employee;
	}
}