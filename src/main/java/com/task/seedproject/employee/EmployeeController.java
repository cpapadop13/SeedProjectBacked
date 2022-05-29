package com.task.seedproject.employee;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//this is rest controller class restcontoller expose some rest api
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController extends AbstractApi {

	@Autowired
	private EmployeeDoaService service;

	@RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> lovRole(HttpServletRequest request) {
		try {
			// get all employees
			System.out.print("Get Employee Method Called  \n\n\n");
			List<Employee> employees = service.getAllEmployees();
			if (employees != null) {
				System.out.print("Employee Method return values \\n\\n\\n");
				return getResponseFormat(HttpStatus.OK, "Record Found", employees);
			} else {

				return getResponseFormat(HttpStatus.BAD_REQUEST, "No Record Found", null);
			}
		} catch (Exception e) {
			return getResponseFormat(HttpStatus.NOT_FOUND, "General Processing Error", null);
		}

	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> addEmployee(@RequestBody String empdata,
			HttpServletRequest request) {
		try {
			JSONObject jsonObject = new JSONObject(empdata);
			String firstname = jsonObject.getString("firstName");
			String lastName = jsonObject.getString("lastName");
			String description = jsonObject.getString("description");
			String status = jsonObject.getString("status");
			String department = jsonObject.getString("department");
			// save Employee
			System.out.print("Save Employee Method Called \\n\\n\\n");
			Employee saveEmployee = service.saveEmployee(firstname, lastName, description, status, department);
			if (saveEmployee != null) {
				System.out.print("Employee Method Return Employee Object \\n\\n\\n");
				return getResponseFormat(HttpStatus.OK, "Employee Save Successfully", saveEmployee);
			} else {

				return getResponseFormat(HttpStatus.BAD_REQUEST, "Employee Save Un Successfully", null);
			}
		} catch (Exception e) {
			return getResponseFormat(HttpStatus.NOT_FOUND, "General Processing Error", null);
		}

	}

	@RequestMapping(value = "/delEmplyee/{empid}", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> dellEmployee(@PathVariable("empid") String empid) {
		try {
			// Dell Employee
			System.out.print("Del Employee Method Called");
			int dellEmployee = service.dellEmployee(Integer.parseInt(empid));
			if (dellEmployee >= 0) {
				System.out.print("Del Employee Method Return Nothnig \\n\\n\\n");
				return getResponseFormat(HttpStatus.OK, "Employee dell Successfuly", null);
			} else {
				return getResponseFormat(HttpStatus.BAD_REQUEST, "Employee dell  Un Successfully \\n\\n\\n", null);
			}
		} catch (Exception e) {
			return getResponseFormat(HttpStatus.NOT_FOUND, "General Processing Error", null);
		}

	}

}
