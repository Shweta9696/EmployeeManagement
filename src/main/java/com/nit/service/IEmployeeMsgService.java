package com.nit.service;

import java.util.List;

import com.nit.entity.Employee;

public interface IEmployeeMsgService 
{
	public List<Employee> showAllEmployee();
	public String registerEmployee(Employee emp);
	public Employee fetchEmployeeById(int id);
	public String editEmployee(Employee emp);
	public String deleteEmployeeById(int id);
	

}
