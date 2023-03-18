package com.majors.majorpopulate.repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class AdminRepository {
/* 
Creates a new Section with all fields,
course_title, course_section, course_term, course_term_dates, course_location, course_type = NotNull
*/
    public void CreateSection(String courseTitle, String courseSection, String courseDays, String courseTerm,
            List<Date> courseTermDates, List<LocalTime> courseTime, String courseLocation, String courseBuildingNum,
            String courseRoomNum, String courseType, int seatsTaken, int seatsAvailable) {

                //TODO
    }
/* 
potentially updates all fields for a section. Only updates passed in fields.
*/
    public void UpdateSection(String courseTitle, String courseSection, String courseDays, String courseTerm,
            List<Date> courseTermDates, List<LocalTime> courseTime, String courseLocation, String courseBuildingNum,
            String courseRoomNum, String courseType, int seatsTaken, int seatsAvailable) {

                //TODO
    }
/* 
Creates a new Student in tbl_student
*/
    public void CreateStudent(String studentName, String studentPassword, String majorName) {
        //TODO
    }
/* 
updates grades in tbl_student_transcript based on studentId and courseId. Does not update completion status of tbl_registered
*/
    public void UpdateGrades(int studentId, String courseId, String grade) {
        //TODO
    }
/* 
changes the major associated with a student Id and updates tbl_student
*/
    public void ChangeMajor(int studentId, String majorId) {
        //TODO
    }
/* 
Takes a studentId, the courseId and removes the course from tbl_registered and adds it to tbl_student_transcript, along with the Grade recieved.
*/
public void SetCourseToCompleted(int studentId, String courseId, String grade) {
        //TODO
}

}
