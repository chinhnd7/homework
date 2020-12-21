package payroll.service;

import payroll.model.Employee;
import payroll.utils.Common;
import payroll.utils.Constants;

public class EmployeeService {
	
	public double getSalaryNow (Employee employee) {
		// Calculate years worked
		int yearsWorking = (int) Common.getDuration(employee.getStartdate());
		
		// Calculate salary
		double salaryNow = employee.getSalary() * Math.pow(1 + Constants.SALARY_INCR, yearsWorking);
		
		return salaryNow;
	}
}
