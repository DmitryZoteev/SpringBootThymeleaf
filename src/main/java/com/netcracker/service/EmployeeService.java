package com.netcracker.service;

import com.netcracker.controller.EmailController;
import com.netcracker.entity.Employee;
import com.netcracker.repository.EmployeeRepository;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    EmailService emailService;

    @Autowired
    EmployeeRepository employeeRepository;

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> findBySurnameAndName(String name, String surname){
        return employeeRepository.findBySurnameAndName(name, surname);
    }

    // отправка сообщения на почту
    public void sendMailMessage(Employee employee){
        try {
            emailService.sendSimpleEmail(employee.getEmail(),
                    "Welcome "
                            + employee.getSurname() + " "
                            + employee.getName() + "!",
                    "Adding successfully");
        } catch (MailException mailException) {
            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
        }
    }

    // получение информации о браузере пользователя и времени
    public void getBrowserInfo(Model model, Optional<Employee> employee, HttpServletRequest request){
        HttpSession session = request.getSession(true);
        Date lastAccessedTime = new Date(session.getLastAccessedTime());

        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        String browser = userAgent.getBrowser().getName() + " " + userAgent.getBrowserVersion();

        model.addAttribute("employees", employee.get());
        model.addAttribute("lastAccessedTime", lastAccessedTime.toString());
        model.addAttribute("userAgent", browser);
    }
}
