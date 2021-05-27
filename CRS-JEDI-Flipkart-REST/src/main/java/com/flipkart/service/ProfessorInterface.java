/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.RegisteredCourses;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotAssignedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAddedException;

/**
 * @author rutwi
 *
 */
public interface ProfessorInterface {
	
	/**
	 * @param studentID
	 * @param courseID
	 * @param grade
	 */
	public void addGrade(Integer studentID, Integer semesterID,String courseID, Integer grade) throws GradeNotAddedException;


	/**
	 * @param courseID
	 * @param semesterID
	 * @return 
	 * @throws CourseNotFoundException
	 */
	public ArrayList<RegisteredCourses> viewCourseStudents(String courseID, Integer semesterID) throws CourseNotFoundException;
	public ArrayList<Course> viewCourseProf(int instructorID) ;
	public void registerCourse(int instructorID, Integer semesterID, String courseID) throws SQLException;
	
	

}
