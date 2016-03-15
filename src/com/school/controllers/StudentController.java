package com.school.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.beans_model.Absence;
import com.school.beans_model.Grade;
import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.beans_model.SubjectRequest;
import com.school.dao.interfaces.AbsenceDao;
import com.school.dao.interfaces.GradeDao;
import com.school.dao.interfaces.StudentDao;
import com.school.dao.interfaces.SubjectDao;

@Controller
public class StudentController {
	
	public static String accountStudent = "redirect:/accountStudent";
	public static String accountProfessor = "redirect:/accountProf";
	public static String studentGrading = "studentGrading";
	
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private GradeDao gradeDao;
	@Autowired
	private AbsenceDao absenceDao;
	
	
	@RequestMapping("/accountStudent")
	public String studentAccount(Principal principal, Model model) {
		
		Student student = studentDao.getStudentWithSubjects(principal.getName());
		
		model.addAttribute("student", student);
		model.addAttribute("totalAverage", gradeDao.getStudentTotalAverageGradesById(student));
		model.addAttribute("allSubjects", subjectDao.getAll());
		
		return "studentAccount";
	}
	
	@RequestMapping(value="/getSubjectWithStudents/{subjectId}")
	public String getSubjectWithStudents(@PathVariable Long subjectId, Model model) {
		
		Subject subject = subjectDao.getSubjectWithStudentsById(subjectId);
		model.addAttribute("subject", subject);
		model.addAttribute("students", subject.getStudents());
		
		return "allStudents";
	}
	
	@RequestMapping(value="/getStudentWithSubjectAndGrades", params={"student", "subject"})
	public String getStudentWithSubjects(@RequestParam("student") Long studentId,
			@RequestParam("subject") Long subjectId, Model model) {
		
		Student student = studentDao.getStudentById(studentId);
		Subject subject = subjectDao.getSubjectById(subjectId);
		
		model.addAttribute("student", student);
		model.addAttribute("subject", subject);
		model.addAttribute("grades", gradeDao.getStudentGradesByStudentAndSubjectId(student, subject));
		model.addAttribute("subjectAverage", gradeDao.getStudentSubjectAverageGradesById(student, subject));
		
		return "studentInfo";
	}
	
	@RequestMapping(value="/getStudentWithSubjectAndAddGrade", params={"student", "subject"})
	public String getStudentWithSubjectsAndGrades(@RequestParam("student") Long studentId,
			@RequestParam("subject") Long subjectId, Model model) {
		
		Subject subject = subjectDao.getSubjectById(subjectId);
		Student student = studentDao.getStudentById(studentId);
		
		model.addAttribute("student", student);
		model.addAttribute("subject", subject);
		
		return studentGrading;
	}
	
	@RequestMapping(value="/getSubjectToStudent", params={"student", "subject"})
	public String getSubjectToStudent(@RequestParam("student") Long studentId,
			@RequestParam("subject") Long subjectId) {
		
		studentDao.addSubjectToStudent(studentId, subjectDao.getSubjectById(subjectId));
		subjectDao.addStudentToSubject(studentDao.getStudentById(studentId), subjectId);
		
		return accountStudent;
	}
	
	@RequestMapping(value="/sendSubjectRequest", params={"student", "subject"})
	public String sendSubjectRequest(@RequestParam("student") Long studentId,
			@RequestParam("subject") Long subjectId) {
		
		Student student = studentDao.getStudentWithRequestsById(studentId);
		Subject subject = subjectDao.getSubjectById(subjectId);
		
		SubjectRequest request = new SubjectRequest();
		request.setStudentId(student.getId());
		request.setSubjectId(subject.getId());
		
		student.getSubjectRequests().add(request);
		studentDao.mergeStudent(student);
		
		return accountStudent;
	}
	
	@RequestMapping(value="/addGradeToStudent",
			params={"student", "subject", "gradeValue"},
			method=RequestMethod.POST)
	public String addGradeToStudent(@RequestParam("student") Long studentId,
			@RequestParam("subject") Long subjectId,
			@RequestParam("gradeValue") Integer gradeValue) {
		
		Subject subject = subjectDao.getSubjectById(subjectId);
		Student student = studentDao.getStudentById(studentId);
		Grade grade = new Grade();
		
		if (gradeValue >= 6 || gradeValue <= 10) {
			grade.setSubject(subject);
			grade.setStudent(student);
			grade.setGradeValue(gradeValue);
			studentDao.addGradeToStudent(studentId, grade);
			return studentGrading;
		} else {
			return accountProfessor;
		}
		
	}
	
	@RequestMapping(value="/addAbsenceToStudent", params={"student", "subject"})
	public String addAbsenceToStudent(@RequestParam("student") Long studentId,
			@RequestParam("subject") Long subjectId) {
		
		Subject subject = subjectDao.getSubjectById(subjectId);
		Student student = studentDao.getStudentById(studentId);
		Absence absence = new Absence();
		absence.setSubject(subject);
		absence.setStudent(student);
		absence.setAbsenceCounter(1);
		studentDao.addAbsenceToStudent(studentId, absence);
		
		return accountProfessor;
	}
	
	@RequestMapping(value="/getGradesAndAbsences", params={"student", "subject"})
	public String getGrades(@RequestParam("student") Long studentId , 
			@RequestParam("subject") Long subjectId, Model model) {
		
		Student student = studentDao.getStudentById(studentId);
		Subject subject = subjectDao.getSubjectById(subjectId);
		
		model.addAttribute("student", student);
		model.addAttribute("subject", subject);
		model.addAttribute("absences", absenceDao.getStudentAbsences(student, subject));
		model.addAttribute("grades", gradeDao.getStudentGradesByStudentAndSubjectId(student, subject));
		model.addAttribute("subjectAverage", gradeDao.getStudentSubjectAverageGradesById(student, subject));
		
		return "studentGrades";
	}
	
}
