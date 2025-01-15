package com.nit.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.Employee;
import com.nit.repository.IEmployeeRepository;

@Service
public class EmployeeMsgServiceImpl implements IEmployeeMsgService {
	
	@Autowired
	private IEmployeeRepository empRepo;

	@Override
	public List<Employee> showAllEmployee() {
		List<Employee> list=StreamSupport.stream(empRepo.findAll().spliterator(),false).toList();
	//	return list.stream().toList();//performs the natural sorting
		
		return list.stream().sorted((emp1,emp2)->emp1.getEname().compareTo(emp2.getEname())).toList();//based on employee name
		
		
	}

	@Override
	public String registerEmployee(Employee emp) {
		// use service
		int idVal=empRepo.save(emp).getEmpno();
		
		return "Employee is registered eith the id value:"+idVal;
	}

	@Override
	public Employee fetchEmployeeById(int id) {
		
		return empRepo.findById(id).orElseThrow(()->new IllegalArgumentException("invalid id"));
	}

	@Override
	public String editEmployee(Employee emp) {
		Optional<Employee> opt=empRepo.findById(emp.getEmpno());
		if(opt.isPresent()) {
			
			empRepo.save(emp);
			return emp.getEmpno()+" Employee is update";
		}
		return emp.getEmpno()+" Employee is not found for update";
	}
	@Override
	public String deleteEmployeeById(int id) {
		Optional<Employee> opt=empRepo.findById(id);
		if(opt.isPresent()) {
			empRepo.deleteById(id);
			return id+" Employee is deleted";
		}
		return id+" Employee is not found for deletion";
	}

}













