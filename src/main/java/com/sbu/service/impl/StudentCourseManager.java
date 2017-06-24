package com.sbu.service.impl;

import com.sbu.dao.impl.StudentCourseDao;
import com.sbu.entity.Course;
import com.sbu.entity.ProfCourseSem;
import com.sbu.entity.Semester;
import com.sbu.entity.Student;
import com.sbu.entity.StudentCourse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentCourseManager /*implements UserManager*/ {

    public StudentCourseManager() {
    }

    @Autowired
    public StudentCourseDao studentCourseDao;
    @Autowired
    public SemesterManager semesterManager;

    @Transactional
    public List<StudentCourse> getByStudent(Student student) {
        return studentCourseDao.getByStudent(student);
    }

    @Transactional
    public StudentCourse getByStudentAndPCS(Student st, ProfCourseSem pcs) {
        return studentCourseDao.getByStudentAndPCS(st, pcs);
    }

    //****NEW****STUDENT LIST
    @Transactional
    public List<StudentCourse> getByPCS(ProfCourseSem pcs) {
        return studentCourseDao.getByPCS(pcs);
    }

    @Transactional
    public void update(StudentCourse sc) {
        studentCourseDao.update(sc);
    }

    //****NEW****AKHZ DARS
    @Transactional
    public ArrayList<Integer> passedCourses(Student student) {
        ArrayList<Integer> result = new ArrayList<>();
        for (StudentCourse sc : getByStudent(student)) {
            if (sc.getConfirmed()) {
                result.add(sc.getPcsid().getCourseid().getId());
            }
        }
        return result;
    }

    @Transactional
    public void insert(StudentCourse sc) {
        studentCourseDao.insert(sc);
    }

    //passed
    @Transactional
    public boolean hasPassed(Student s, ProfCourseSem pcs) {
        Course c = pcs.getCourseid();
        List<StudentCourse> sc = getByStudent(s);
        for(StudentCourse studentcourse:sc)
        {
            if(studentcourse.getPcsid().getCourseid().getId()==c.getId() && studentcourse.getConfirmed())
                return false;
        }
        return true;
    }

    //duplicate selection
    @Transactional
    public boolean alreadySelected(Student s, ProfCourseSem pcs) {
        Course c = pcs.getCourseid();
        Semester sem = semesterManager.getCurrentSemester();       
        List<StudentCourse> sc = getByStudent(s);
        for(StudentCourse studentcourse:sc)
        {
            if(studentcourse.getPcsid().getCourseid().getId()==c.getId() 
                    && studentcourse.getPcsid().getSemid().getId()==sem.getId())
                return true;
        }
        return false;
    }

    //unit limit
    @Transactional
    public boolean unitLimitBroken(Student s) {
        List<StudentCourse> allCourses = getByStudent(s);
        Semester currentSem = semesterManager.getCurrentSemester();
        int totalUnits=0;
        for(StudentCourse sc:allCourses)
        {
            totalUnits+=sc.getPcsid().getCourseid().getUnit();
            if(totalUnits>20)
                return true;
        }
        return false;
    }

}
