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
	
	public static final String STUDENT = "student";
	public static final String SUBJECT = "subject";
	public static final String ACCOUNT_STUDENT = "redirect:/accountStudent";
	public static final String ACCOUNT_PROFESSOR = "redirect:/accountProf";
	public static final String STUDENT_GRADING = "studentGrading";
	
	/** autowired interfaces */
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private GradeDao gradeDao;
	@Autowired
	private AbsenceDao absenceDao;
	
	/** return student page with student and his subjects
	 *  total average grades of all subjects 
	 *  and subjects that this student doesn't have */
	@RequestMapping("/accountStudent")
	public String studentAccount(Principal principal, Model model) {
		
		Student student = studentDao.getStudentWithSubjects(principal.getName());
		
		model.addAttribute(STUDENT, student);
		model.addAttribute("totalAverage", gradeDao.getStudentTotalAverageGradesById(student));
		model.addAttribute("subjects", subjectDao.getSubjectsNotOfThisStudent(student));
		
		return "studentAccount";
	}
	/** return page with subject and all its students */
	@RequestMapping(value="/getSubjectWithStudents/{subjectId}")
	public String getSubjectWithStudents(@PathVariable Long subjectId, Model model) {
		
		Subject subject = subjectDao.getSubjectWithStudentsById(subjectId);
		model.addAttribute(SUBJECT, subject);
		model.addAttribute("students", subject.getStudents());
		
		return "allStudents";
	}
	/** return info page of student,
	 *  one of his subject, all grades and grades average value of this subject */
	@RequestMapping(value="/getStudentWithSubjectAndGrades", params={"student", "subject"})
	public String getStudentWithSubjects(@RequestParam("student") Long studentId,
			@RequestParam("subject") Long subjectId, Model model) {
		
		Student student = studentDao.getStudentById(studentId);
		Subject subject = subjectDao.getSubjectById(subjectId);
		
		model.addAttribute(STUDENT, student);
		model.addAttribute(SUBJECT, subject);
		model.addAttribute("absences", absenceDao.getStudentAbsences(student, subject));
		model.addAttribute("grades", gradeDao.getStudentGradesByStudentAndSubjectId(student, subject));
		model.addAttribute("subjectAverage", gradeDao.getStudentSubjectAverageGradesById(student, subject));
		
		return "studentInfo";
	}
	/** returns page with student and one of his subject */
	@RequestMapping(value="/getStudentWithSubjectAndAddGrade", params={"student", "subject"})
	public String getStudentWithSubjectsAndGrades(@RequestParam("student") Long studentId,
			@RequestParam("subject") Long subjectId, Model model) {
		
		Subject subject = subjectDao.getSubjectById(subjectId);
		Student student = studentDao.getStudentById(studentId);
		
		model.addAttribute(STUDENT, student);
		model.addAttribute(SUBJECT, subject);
		
		return STUDENT_GRADING;
	}
	/** adding subject to student, and student to subject */
	@RequestMapping(value="/getSubjectToStudent", params={"student", "subject"})
	public String getSubjectToStudent(@RequestParam("student") Long studentId,
			@RequestParam("subject") Long subjectId) {
		
		studentDao.addSubjectToStudent(studentId, subjectDao.getSubjectById(subjectId));
		subjectDao.addStudentToSubject(studentDao.getStudentById(studentId), subjectId);
		
		return ACCOUNT_STUDENT;
	}
	/** setting student-id and subject-id to subject-request, 
	 *  and adding that subject-request to student */
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
		
		return ACCOUNT_STUDENT;
	}
	/** setting subject, student and gradeValue to grade,
	 *  and adding that grade to student */
	@RequestMapping(value="/addGradeToStudent",
			params={"student", "subject", "gradeValue"},
			method=RequestMethod.POST)
	public String addGradeToStudent(@RequestParam("student") Long studentId,
			@RequestParam("subject") Long subjectId,
			@RequestParam("gradeValue") Integer gradeValue) {
		
		Subject subject = subjectDao.getSubjectById(subjectId);
		Student student = studentDao.getStudentById(studentId);
		
		if (gradeValue >= 6 || gradeValue <= 10) {
			Grade grade = new Grade();
			grade.setSubject(subject);
			grade.setStudent(student);
			grade.setGradeValue(gradeValue);
			studentDao.addGradeToStudent(studentId, grade);
			return STUDENT_GRADING;
		} else {
			return ACCOUNT_PROFESSOR;
		}
		
	}
	/** setting subject, student and counter to absence,
	 *  and adding that absence to student */
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
		
		return ACCOUNT_PROFESSOR;
	}
	/** return page with student, his subject,
	 *  and grades, absences of its subject,
	 *  and grades average value of this subject */
	@RequestMapping(value="/getGradesAndAbsences", params={"student", "subject"})
	public String getGrades(@RequestParam("student") Long studentId , 
			@RequestParam("subject") Long subjectId, Model model) {
		
		Student student = studentDao.getStudentById(studentId);
		Subject subject = subjectDao.getSubjectById(subjectId);
		
		model.addAttribute(STUDENT, student);
		model.addAttribute(SUBJECT, subject);
		model.addAttribute("absences", absenceDao.getStudentAbsences(student, subject));
		model.addAttribute("grades", gradeDao.getStudentGradesByStudentAndSubjectId(student, subject));
		model.addAttribute("subjectAverage", gradeDao.getStudentSubjectAverageGradesById(student, subject));
		
		return "studentGrades";
	}
	
}
