package com.school.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
		    .jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder)
			.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE binary username=?")
			.authoritiesByUsernameQuery("SELECT username, authority FROM users WHERE binary username=?")
			.rolePrefix("ROLE_");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/home").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/registerUser").authenticated()
			.antMatchers("/registerAdmin").authenticated()
			.antMatchers("/registerProfessor").authenticated()
			.antMatchers("/registerStudent").authenticated()
			.antMatchers("/acceptSubjectRequest/**").authenticated()
			.antMatchers("/declineSubjectRequest/**").authenticated()
			.antMatchers("/getSubject/**").authenticated()
			.antMatchers("/accountProf").access("hasRole('PROFESSOR') or hasRole('ADMIN')")
			.antMatchers("/getSubjectWithStudents/**").authenticated()
			.antMatchers("/getStudentWithSubjectAndAddGrade/**").authenticated()
			.antMatchers("/getProfessorWithSubjects/**").authenticated()
			.antMatchers("/getStudentWithSubjectAndGrades/**").authenticated()
			.antMatchers("/addGradeToStudent").authenticated()
			.antMatchers("/addAbsenceToStudent").authenticated()
			.antMatchers("/addingSubject").authenticated()
			.antMatchers("/addSubject").authenticated()
			.antMatchers("/addSubjectToProf").authenticated()
			.antMatchers("/addSubjectToProfessor").authenticated()
			.antMatchers("/accountStudent").access("hasRole('STUDENT') or hasRole('ADMIN')")
			.antMatchers("/getSubjectToStudent").authenticated()
			.antMatchers("/sendSubjectRequest").authenticated()
			.antMatchers("/getGradesAndAbsences").authenticated()
			.antMatchers("/logout").authenticated()
			.anyRequest().denyAll()
		.and()
			.formLogin()
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.failureUrl("/login?loginerror=true")
			.defaultSuccessUrl("/")
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
//		.and()
//		.exceptionHandling().accessDeniedPage("/accessDenied")
		
	}
	
}
