package com.nit.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nit.entity.Employee;
import com.nit.service.IEmployeeMsgService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeOpration
{
	@Autowired
	private IEmployeeMsgService empService;
	
    @GetMapping("/")
	public String showHomePage() {
	return "welcome";
}
   @GetMapping("/report")
    public String showReport(Map<String,Object> map)
   {
	  try {
	   List<Employee> list=empService.showAllEmployee();
	   //keep the result in shared memory
    	map.put("empsList",list);
           //LVN
    	return "show_report";
    	
   }
	  catch (Exception e) {
		e.printStackTrace();
		map.put("errorMsg",e.getMessage());
		return "error";
		
	}
	
   }
   @GetMapping("/register")
   public String showEmployeeRegistrationPage( @ModelAttribute("emp") Employee name) {
	   
	return "employee_register";
	   
   }
   @PostMapping("/register")
   public String registerEmployee(HttpSession ses,@ModelAttribute("emp")Employee emp) {
	   try {
		  
		   String msg=empService.registerEmployee(emp);
		  
		   ses.setAttribute("resultMsg", msg);
		  
		   return "redirect:report";
	   }
	   catch (Exception e) {
		e.printStackTrace();
		ses.setAttribute("errorMsg",e.getMessage());
		return "error";
	}
	  
   }
   @GetMapping("/edit")
   public String showEditFormPage(@RequestParam("no") int no,@ModelAttribute("emp") Employee emp) {
	//use service
	   Employee emp1=empService.fetchEmployeeById(no);
	   BeanUtils.copyProperties(emp1, emp);
	   
	   return "employee_edit";
	   
   }
   @PostMapping("/edit")
   public String updateEmployeeDetails(@ModelAttribute("emp") Employee emp,
		   RedirectAttributes attrs) {
	   try {
	   //use service
	   String msg=empService.editEmployee(emp);
	   //keep result
	   attrs.addFlashAttribute("resultMsg", msg);
	   //LVN
   return "redirect:report";
   }
	   catch (Exception e) {
		e.printStackTrace();
		attrs.addAttribute("errorMsg",e.getMessage());
		return "erro";
	}
   }
   
   @GetMapping("/delete")
   public String removeEmployee(@RequestParam("no") int no,
		   Map<String,Object>map) {
			try {
	   //use service
	   String msg=empService.deleteEmployeeById(no);
	   map.put("resultMsg", msg);
	   return "forward:report";
			}
			catch (Exception e) {
				e.printStackTrace();
				map.put("errorMsg", e.getMessage());
				return "error";
			}
   }
   
}

























