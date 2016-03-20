package com.school.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.school.dao.interfaces.UserDao;

@Controller
public class MyController {
	
	public static final String HOME_PAGE = "home";
	public static final String LOGIN = "login";
	public static final String USERNAME = "username";
	public static final String MESSAGES = "messages";
	public static final String MESSAGE_OK = "messageOk";
	public static final String SUCCES_REGISTER = " is successfully registered.";
	public static final String DUPLICATE_KEY = "DuplicateKey.user.username";
	public static final String REGISTRATION_PAGE = "registrationPage";
	
	/** autowired interfaces */
	@Autowired
	private ProfessorDao profDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;
	
	
	@RequestMapping({"/", "/home"})
	public String goHome() {
		return HOME_PAGE;
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return LOGIN;
	}
	/** logout method */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return HOME_PAGE;
	}
	
	@RequestMapping("/registerUser")
	public String showRegistrationPage(@ModelAttribute User user) {
		return REGISTRATION_PAGE;
	}
	/** registering admin */
	@RequestMapping(value="/registerAdmin", method=RequestMethod.POST)
	public String registerAdmin(@Valid User user, BindingResult result, Model model) {
		
		if (userDao.isUserAlreadyExists(user.getUsername())) {
			result.rejectValue(USERNAME, DUPLICATE_KEY);
			return REGISTRATION_PAGE;
		}
		if(!result.hasErrors()) {
			Admin admin = new Admin(user);
			admin.setEnabled(true);
			admin.setAuthority("ADMIN");
			adminDao.saveAdmin(admin);
			model.addAttribute(MESSAGE_OK, "Admin "+user.getName()+" "+user.getLastName()+SUCCES_REGISTER);
			return MESSAGES;
		}
		return REGISTRATION_PAGE;
	}
	/** registering professor */
	@RequestMapping(value="/registerProfessor", method=RequestMethod.POST)
	public String registerProfessor(@Valid User user, BindingResult result, Model model) {
		
		if (userDao.isUserAlreadyExists(user.getUsername())) {
			result.rejectValue(USERNAME, DUPLICATE_KEY);
			return REGISTRATION_PAGE;
		}
		if (!result.hasErrors()) {
			Professor prof = new Professor(user);
			prof.setEnabled(true);
			prof.setAuthority("PROFESSOR");
			profDao.saveProfessor(prof);
			model.addAttribute(MESSAGE_OK, "Professor "+user.getName()+" "+user.getLastName()+SUCCES_REGISTER);
			return MESSAGES;
		}
		return REGISTRATION_PAGE;
	}
	/** registering student */
	@RequestMapping(value="/registerStudent", method=RequestMethod.POST)
	public String registerStudent(@Valid User user, BindingResult result, Model model) {
		
		if (userDao.isUserAlreadyExists(user.getUsername())) {
			result.rejectValue(USERNAME, DUPLICATE_KEY);
			return REGISTRATION_PAGE;
		}
		if(!result.hasErrors()) {
			Student student = new Student(user);
			student.setEnabled(true);
			student.setAuthority("STUDENT");
			studentDao.saveStudent(student);
			model.addAttribute(MESSAGE_OK, "Student "+user.getName()+" "+user.getLastName()+SUCCES_REGISTER);
			return MESSAGES;
		}
		return REGISTRATION_PAGE;
	}
	
}
