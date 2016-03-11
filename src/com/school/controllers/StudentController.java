package com.school.controllers;

import java.security.Principal;
import java.util.List;

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
import com.school.dao.interfaces.StudentDao;
import com.school.dao.interfaces.SubjectDao;

@Controller
public class StudentController {
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private SubjectDao subjectDao;
	
	
	@RequestMapping("/accountStudent")
	public String studentAccount(Principal principal, Model model) {
		
		Student student = studentDao.getStudentWithSubjectsAndGrades(principal.getName());
		List<Subject> allSubjects = (List<Subject>) subjectDao.getAll();
		
		model.addAttribute("student", student);
		model.addAttribute("allSubjects", allSubjects);
		
		return "studentAccount";
	}
//	@RequestMapping(value="/accountStudent/{studentId}")
//	public String studentAccount(@PathVariable Long studentId, Model model) {
//		
//		Student student = studentDao.getStudentWithSubjectsAndGradesById(studentId);
//		List<Subject> allSubjects = (List<Subject>) subjectDao.getAll();
//		
//		model.addAttribute("student", student);
//		model.addAttribute("allSubjects", allSubjects);
//		
//		return "studentAccount";
//	}
	
	@RequestMapping(value="/getSubjectWithStudents/{subjectId}")
	public String getSubjectWithStudents(@PathVariable Long subjectId, Model model) {
		
		Subject subject = subjectDao.getSubjectWithStudentsById(subjectId);
		List<Student> students = subject.getStudents();
		model.addAttribute("students", students);
		
		return "allStudents";
	}
	
	@RequestMapping(value="/getStudentWithSubjects/{studentId}")
	public String getStudentWithSubjects(@PathVariable Long studentId, Model model) {
		
		Student student = studentDao.getStudentWithSubjectsById(studentId);
		List<Subject> subjects = student.getStudentSubjects();
		model.addAttribute("student", student);
		model.addAttribute("subjects", subjects);
		
		return "studentInfo";
	}
	
	@RequestMapping(value="/getStudentWithSubjectsAndGrades/{studentId}")
	public String getStudentWithSubjectsAndGrades(@PathVariable Long studentId, Model model) {
		
		Student student = studentDao.getStudentWithSubjectsAndGradesById(studentId);
		List<Subject> subjects = student.getStudentSubjects();
		List<Grade> grades = student.getStudentGrades();
		model.addAttribute("student", student);
		model.addAttribute("studentSubjects", subjects);
		model.addAttribute("studentGrades", grades);
		
		return "studentGrading";
	}
	
	@RequestMapping(value="/getSubjectToStudent", params={"student", "subject"})
	public String getSubjectToStudent(@RequestParam("student") Long studentId, @RequestParam("subject") Long subjectId) {
		
		studentDao.addSubjectToStudent(studentId, subjectDao.getSubjectById(subjectId));
		subjectDao.addStudentToSubject(studentDao.getStudentById(studentId), subjectId);
		
		return "redirect:/accountStudent";
	}
	
	@RequestMapping(value="/sendSubjectRequest", params={"student", "subject"})
	public String sendSubjectRequest(@RequestParam("student") Long studentId, @RequestParam("subject") Long subjectId) {
		
		Student student = studentDao.getStudentWithRequestsById(studentId);
		Subject subject = subjectDao.getSubjectById(subjectId);
		
		SubjectRequest request = new SubjectRequest();
		request.setStudentId(student.getId());
		request.setSubjectId(subject.getId());
		
		student.getSubjectRequests().add(request);
		studentDao.mergeStudent(student);
		
		return "redirect:/accountStudent";
	}
	
	@RequestMapping(value="/addGradeToStudent", params={"student", "subject", "gradeValue"}, method=RequestMethod.POST)
	public String addGradeToStudent(@RequestParam("student") Long studentId,
									@RequestParam("subject") Long subjectId,
									@RequestParam("gradeValue") Integer gradeValue) {
		
		Subject subject = subjectDao.getSubjectById(subjectId);
		Grade grade = new Grade();
		
		if (gradeValue >= 6 || gradeValue <= 10) {
			grade.setSubject(subject);
			grade.setGradeValue(gradeValue);
			studentDao.addGradeToStudent(studentId, grade);
			return "studentGrading";
		} else {
			return "redirect:/accountProf";
		}
		
	}
	
	@RequestMapping(value="/addAbsenceToStudent", params={"student", "subject"})
	public String addAbsenceToStudent(@RequestParam("student") Long studentId, @RequestParam("subject") Long subjectId) {
		
		Subject subject = subjectDao.getSubjectById(subjectId);
		Absence absence = new Absence();
		absence.setSubject(subject);
		absence.setAbsence(1);
		studentDao.addAbsenceToStudent(studentId, absence);
		
		return "redirect:/accountProf";
	}
	
	/** Zapocete i jos nedovrsene metode */
//	@RequestMapping(value="/getGrades", params={"student", "subject"})
//	public String getGrades(@RequestParam("student") Long studentId , @RequestParam("subject") Long subjectId, Model model) {
//		
//		Student student = studentDao.getStudentById(studentId);
//		Subject subject = subjectDao.getSubjectById(subjectId);
//		List<Grade> grades = studentDao.getListOfGradesBySubjectAndStudent(subject, student);
//		model.addAttribute("grades", grades);
//		//model.addAttribute("listOfGrades", listOfGrades);
//		model.addAttribute("subject", subject);
//		model.addAttribute("student", student);
//		
//		return "studentGrades";
//	}
//	
//	@RequestMapping(value="/getAbsences", params={"student", "subject"})
//	public String getAbsences(@RequestParam("student") Long studentId , @RequestParam("subject") Long subjectId, Model model) {
//		
//		Subject subject = subjectDao.getSubjectById(subjectId);
//		//Student student = studentDao.getStudentWithGradesBySubjectId(subject);
//		model.addAttribute("subject", subject);
//		//model.addAttribute("student", student);
//		
//		return "studentGrades";
//	}
}
