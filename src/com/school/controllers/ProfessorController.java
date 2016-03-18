package com.school.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.beans_model.Professor;
import com.school.beans_model.Subject;
import com.school.dao.interfaces.ProfessorDao;
import com.school.dao.interfaces.SubjectDao;

@Controller
public class ProfessorController {
	
	private static final String PROFESSOR = "professor";
	
	@Autowired
	private ProfessorDao profDao;
	@Autowired
	private SubjectDao subjectDao;
	
	
	@RequestMapping("/accountProf")
	public String profAccount(Principal principal, Model model) {
		
			Professor prof = profDao.getProfessorByUsernameWithSubjects(principal.getName());
			model.addAttribute("loggedProfessor", prof);
			
			return "profAccount";
	}
	
	@RequestMapping(value="/getProfessorWithSubjects/{profId}")
	public String getProfessorWithSubjects(@PathVariable Long profId, Model model) {
		
		Professor prof = profDao.getProfessorWithSubjectsById(profId);
		List<Subject> subjects = prof.getSubjects();
		model.addAttribute(PROFESSOR, prof);
		model.addAttribute("subjects", subjects);
		
		return "professor";
	}
	
	@RequestMapping(value="/getSubject/{subjectId}")
	public String getSubject(@PathVariable Long subjectId, Model model) {
		
		Subject subject = subjectDao.getSubjectWithProfessor(subjectId);
		Professor prof = subject.getProfessor();
		
		model.addAttribute("subject", subject);
		model.addAttribute(PROFESSOR, prof);
		
		return "subjectInfo";
		
	}
	
}
