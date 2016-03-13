package com.school.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.beans_model.Subject;
import com.school.beans_model.SubjectRequest;
import com.school.dao.interfaces.AdminDao;
import com.school.dao.interfaces.ProfessorDao;
import com.school.dao.interfaces.StudentDao;
import com.school.dao.interfaces.SubjectDao;
import com.school.dao.interfaces.SubjectRequestDao;

@Controller
public class AdminController {
	
	public static String adminPage = "redirect:/admin";
	public static String addSubject = "addSubject";
	
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private ProfessorDao profDao;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private SubjectRequestDao subjectRequestDao;
	
	@RequestMapping("/admin")
	public String adminPage(Principal principal, Model model) {
		
		model.addAttribute("admin", adminDao.getAdminByUsername(principal.getName()));
		model.addAttribute("professors", profDao.getAll());
		model.addAttribute("subjectRequests", subjectRequestDao.getAll());
		
		return "admin";
	}
	
	@RequestMapping("/addingSubject")
	public String addSubject(@ModelAttribute("subject") Subject subject) {
		return addSubject;
	}
	
	@RequestMapping(value="/addSubject", method=RequestMethod.POST)
	public String subjectAdded(Subject subject) {
		if(subjectDao.saveSubject(subject) != null) {
			return adminPage;			
		}
		return addSubject;
	}
	
	@RequestMapping("/addSubjectToProf")
	public String addSubjectToProf(Model model) {
		
		model.addAttribute("professors", profDao.getAllProfessorsWithSubjects());
		model.addAttribute("subjects", subjectDao.getAll());
		
		return "addSubjectsToProfessor";
	}
	
	@RequestMapping(value="/addSubjectToProfessor", params={"professor", "subject"})
	public String addSubjectToProfessor(@RequestParam("professor") Long profId, @RequestParam("subject") Long subjectId) {
		
		profDao.addSubjectToProfessorById(profId, subjectDao.getSubjectById(subjectId));
		subjectDao.addProfessorToSubjectById(profDao.getProfessorById(profId), subjectId);
		
		return adminPage;			
	}
	
	@RequestMapping(value="/acceptSubjectRequest/{subjectRequestId}")
	public String acceptSubjectRequest(@PathVariable Long subjectRequestId) {
	
	   SubjectRequest subjectRequest = subjectRequestDao.getSubjectRequestById(subjectRequestId);
	
	   studentDao.addSubjectToStudentByRequestId(subjectRequestId);
	   subjectRequestDao.deleteSubjectRequest(subjectRequest);
	
	   return adminPage;
	}
	
	@RequestMapping(value="/declineSubjectRequest/{subjectRequestId}")
	public String declineSubjectRequest(@PathVariable Long subjectRequestId) {
	
	   SubjectRequest subjectRequest = subjectRequestDao.getSubjectRequestById(subjectRequestId);
	
	   studentDao.removeSubjectRequestByRequestId(subjectRequestId);
	   subjectRequestDao.deleteSubjectRequest(subjectRequest);
	
	   return adminPage;
	}
	
}
