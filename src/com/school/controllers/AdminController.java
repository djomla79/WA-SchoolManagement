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
	
	public static final String ADMIN_PAGE = "redirect:/admin";
	public static final String ADD_SUBJECT = "addSubject";
	
	/** autowired interfaces */
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
	
	/** returns admin page with user-admin and all professors and subject-requests */
	@RequestMapping("/admin")
	public String adminPage(Principal principal, Model model) {
		
		model.addAttribute("admin", adminDao.getAdminByUsername(principal.getName()));
		model.addAttribute("professors", profDao.getAll());
		model.addAttribute("subjectRequests", subjectRequestDao.getAll());
		
		return "admin";
	}
	
	@RequestMapping("/addingSubject")
	public String addSubject(@ModelAttribute("subject") Subject subject) {
		return ADD_SUBJECT;
	}
	/** saving new subject */
	@RequestMapping(value="/addSubject", method=RequestMethod.POST)
	public String subjectAdded(Subject subject) {
		if(subjectDao.saveSubject(subject) != null) {
			return ADMIN_PAGE;			
		}
		return ADD_SUBJECT;
	}
	/** returns all professors with their subjects */
	@RequestMapping("/addSubjectToProf")
	public String addSubjectToProf(Model model) {
		
		model.addAttribute("professors", profDao.getAllProfessorsWithSubjects());
		model.addAttribute("subjects", subjectDao.getAll());
		
		return "addSubjectsToProfessor";
	}
	/** adding subject to professor and professor to subject */
	@RequestMapping(value="/addSubjectToProfessor", params={"professor", "subject"})
	public String addSubjectToProfessor(@RequestParam("professor") Long profId, @RequestParam("subject") Long subjectId) {
		
		profDao.addSubjectToProfessorById(profId, subjectDao.getSubjectById(subjectId));
		subjectDao.addProfessorToSubjectById(profDao.getProfessorById(profId), subjectId);
		
		return ADMIN_PAGE;			
	}
	/** adding subject to student by subject-request 
	 *  sent by student then deleting that subject-request */
	@RequestMapping(value="/acceptSubjectRequest/{subjectRequestId}")
	public String acceptSubjectRequest(@PathVariable Long subjectRequestId) {
	
	   SubjectRequest subjectRequest = subjectRequestDao.getSubjectRequestById(subjectRequestId);
	
	   studentDao.addSubjectToStudentByRequestId(subjectRequestId);
	   subjectRequestDao.deleteSubjectRequest(subjectRequest);
	
	   return ADMIN_PAGE;
	}
	/** Declining and removing subject-request sent by student */
	@RequestMapping(value="/declineSubjectRequest/{subjectRequestId}")
	public String declineSubjectRequest(@PathVariable Long subjectRequestId) {
	
	   SubjectRequest subjectRequest = subjectRequestDao.getSubjectRequestById(subjectRequestId);
	
	   studentDao.removeSubjectRequestByRequestId(subjectRequestId);
	   subjectRequestDao.deleteSubjectRequest(subjectRequest);
	
	   return ADMIN_PAGE;
	}
	
}
