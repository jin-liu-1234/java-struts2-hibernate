package service;

import entity.Students;
import org.junit.Assert;
import org.junit.Test;
import service_impl.StudentsDAOImpl;

import java.util.Date;
import java.util.List;

public class TestStudentsDAOImpl {

    @Test
    public void testQueryAllStudents(){

        StudentsDAO studentsDAO = new StudentsDAOImpl();

        List<Students> list = studentsDAO.queryAllStudents();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void testGetNewSid(){
        StudentsDAOImpl sdao = new StudentsDAOImpl();
//        System.out.println(sdao.getNewSid());
    }

    @Test
    public void testAddStudent(){
        Students s = new Students();
        s.setSname("AA");
        s.setGender("男");
        s.setBirthday(new Date());
        s.setAddress("武当山");

        StudentsDAO sdao = new StudentsDAOImpl();

        Assert.assertEquals(true, sdao.addStudents(s));
    }

}
