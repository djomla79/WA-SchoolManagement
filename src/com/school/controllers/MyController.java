package com.school.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.school.beans_model.Admin;
import com.school.beans_model.Professor;
import com.school.beans_model.Student;
import com.school.beans_model.User;
import com.school.dao.interfaces.AdminDao;
import com.school.dao.interfaces.ProfessorDao;
import com.school.dao.interfaces.StudentDao;

@Controller
public class MyController {
	
	public static String homePage = "home";
	public static String adminPage = "redirect:/admin";
	public static String registrationPage = "registrationPage";
	
	@Autowired
	private ProfessorDao profDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private AdminDao adminDao;
	
	
//	@Autowired
//	private UserDao userDao;
	
	@RequestMapping({"/", "/home"})
	public String goHome() {
		return homePage;
	}
	
//	@RequestMapping({"/", "/home"})
//	public String home(Authentication auth, Principal principal, HttpSession session) {
//		
//		if(principal != null) {
//			User user = userDao.getUserByUsername(principal.getName());
//			session.setAttribute("user", user);
//			if(auth != null) {
//				String authority = auth.getAuthorities().toString();
//				if (authority.equals("[ADMIN]")) {
//					session.setAttribute("admin", user);
//				} else if (authority.equals("[PROFESSOR]")) {
//					session.setAttribute("professor", user);
//				} else if (authority.equals("[STUDENT]")) {
//					session.setAttribute("student", user);
//				}
//			}
//		}
//		return "home";
//	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return homePage;
	}
	
	@RequestMapping("/registerUser")
	public String registrationPage(@ModelAttribute User user) {
		return registrationPage;
	}
	
	@RequestMapping(value="/registerAdmin", method=RequestMethod.POST)
	public String registerAdmin(User user) {
		Admin admin = new Admin(user);
		admin.setEnabled(true);
		admin.setAuthority("ADMIN");
		if(adminDao.saveAdmin(admin) != null) {
			return adminPage;
		}
		return registrationPage;
	}
	
	@RequestMapping(value="/registerProfessor", method=RequestMethod.POST)
	public String registerProfessor(User user) {
		Professor prof = new Professor(user);
		prof.setEnabled(true);
		prof.setAuthority("PROFESSOR");
		if(profDao.saveProfessor(prof) != null) {
			return adminPage;
		}
		return registrationPage;
	}
	
	@RequestMapping(value="/registerStudent", method=RequestMethod.POST)
	public String registerStudent(User user) {
		Student student = new Student(user);
		student.setEnabled(true);
		student.setAuthority("STUDENT");
		if(studentDao.saveStudent(student) != null) {
			return adminPage;
		}
		return registrationPage;
	}
	
}
