package com.task.seedproject.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeDoaService {

	private static List<Employee> employees = new ArrayList<Employee>();
	private static int empCount = 5;

	static {
		employees.add(new Employee(1, "ROMAIL", "AHMAD", "EMPLOYEE", "ACTIVE", "FINANCE"));
		employees.add(new Employee(2, "AUN", "AHMAD", "EMPLOYEE", "ACTIVE", "IT"));
		employees.add(new Employee(3, "ALIA", "BUTT", "EMPLOYEE", "ACTIVE", "COMPLAINCE"));
		employees.add(new Employee(4, "MAX", "WEEL", "EMPLOYEE", "ACTIVE", "HR"));
		employees.add(new Employee(5, "ZEE", "TON", "EMPLOYEE", "ACTIVE", "DFS"));
	}

	public List<Employee> getAllEmployees() {
		// HERE WE CALL RESPOSITORY WHEN DATA BASE CONNECT
		return employees;
	}

	public Employee saveEmployee(String firstname, String lastName, String description, String status,
			String department) {
		// HERE WE CALL RESPOSITORY WHEN DATA BASE CONNECT
		int id = ++empCount;
		Employee employee = new Employee(id, firstname, lastName, description, status, department);
		employees.add(employee);
		return employee;
	}

	public int dellEmployee(int empid) {
		int index =empid;
		employees.removeIf(e ->e.getId()==index );
		return employees.size();
	}

}
