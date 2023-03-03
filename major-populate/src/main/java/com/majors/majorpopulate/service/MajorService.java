package com.majors.majorpopulate.service;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.Major;
import com.majors.majorpopulate.Section;
import com.majors.majorpopulate.Major.MajorElectiveGroup;
import com.majors.majorpopulate.repository.SqlCaller;
import com.majors.majorpopulate.student.Login;
import com.majors.majorpopulate.student.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;



public class MajorService {
    
    //make single connection to SQL. SqlCaller Class.
    //@Service 
    public static SqlCaller sql = new SqlCaller();
    
    public static List<Login> loggedInUser = new ArrayList<>();

    public MajorService(){
        
    }
    
    //adds all the majors to a list to add to the dropdown Select option on form.html
    public static List<String> populateMajorChoices() throws Exception{ 
        List<String> majorList = new ArrayList<>();
        majorList = sql.ShowMajorNames();
        return majorList;
    }

    //Gets required classes from SQLcaller Class
    public static List<String> showRequiredCourses(String majorId) throws Exception {
        List<String> coursesList = new ArrayList<>();
        coursesList = sql.getRequiredCoreClasses(majorId);
        return coursesList;
    }
    //Enters new user login credentials into the sql-db
      public static void CreateStudent(Student student) throws Exception{
        sql.CreateStudent(student.getName(), student.getPassword(), student.getMajor());
    }

    //Calls database from SQLcaller to see if there is user
    //if a user "logs out" and someone else logs in it will replace who the user is. 
    public static String validateLogin(String name, String password) throws Exception {
        boolean isThereAStudent = sql.matchCredentials(name, password);
        if (isThereAStudent == true){
            String majorName = sql.getMajorNameFromStudent(name, password);
            String majorId = sql.getMajorId(majorName);
            if (loggedInUser.size() == 1) { 
                loggedInUser.set(0, new Login(name, password, majorName, majorId)); return "1";
            } else {
                loggedInUser.add(new Login(name, password, majorName, majorId)); return "1";
            } 
        }   
        return "0";  
    }

    //Calls getMajorById from sqlCaller and populates the logged in "users" major in the controller
    public static Major getMajorById(String majorId) throws Exception{
        Major major = sql.GetMajorById(majorId);
        return major;
        
    }

  
    public static List<String> showCoursesByTerm(String term, Major major){
        List<String> courseList = new ArrayList<>();

        for (Course course: major.RequiredCourses) {
           for (Section eachClass : course.Classes()) {
                if (eachClass.CourseTerm() == term){
                    courseList.add(course.CourseName());
                }
           } 
        }
        return courseList;
    }

    public static Hashtable<MajorElectiveGroup,List<Course>> showElectivesByGroup(Major major){
        Hashtable<MajorElectiveGroup, List<Course>> courseList = new Hashtable<>();

        for (MajorElectiveGroup meg: major.MajorElectiveGroups) {
                courseList.put(meg, meg.CoursesInElectiveGroup());    
           } 
           return courseList;
        }

        public static HashMap<Integer, String> getFakeCourses(){
            HashMap<Integer, String> hm = new HashMap<>();
            for (int i = 0; i < 7; i++) {
                hm.put(i, "fake course" + String.valueOf(i));
            }
            return hm;
        }

        //gets sections times and info for the selected course from mainpage.html 
        public static List<Section> getSectionTimesByCourseName(String name) throws Exception {
            List<Section> section = sql.getSectionTimesByCourseName(name);
            return section;
        }
}
       



