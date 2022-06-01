package com.netcracker.controller;

import com.netcracker.entity.Employee;
import com.netcracker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public String employeeForm(Model model){
        model.addAttribute("employee", new Employee());
        return "add_employee";
    }

    @PostMapping("/employee")
    public String employeeSubmit(@ModelAttribute @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "add_employee";
        }
        employeeService.save(employee);
        // отправка сообщения на почту
        employeeService.sendMailMessage(employee);
        return "redirect:/employee";
    }

    @GetMapping("/get-information")
    public String getAll(Model model){
        model.addAttribute("employees", employeeService.findAll());
        return "search_result";
    }

    @GetMapping("/get-information-emp")
    public String getEmployeeBySurnameAndName(
            Model model,
            HttpServletRequest request,
            @RequestParam String surname,
            @RequestParam String name){

        Optional<Employee> employee = employeeService.findBySurnameAndName(surname, name);
        if (!employee.isPresent()) {
            model.addAttribute("surname", surname);
            model.addAttribute("name", name);
            return "not_found";
        }
        employeeService.getBrowserInfo(model, employee, request);
        return "search_result";
    }

}
