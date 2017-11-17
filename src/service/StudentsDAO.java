package service;

import entity.Students;

import java.util.List;

public interface StudentsDAO {

    // 列表
    public List<Students> queryAllStudents();

    // 单个
    public Students queryStudentsBySid(String sid);

    // add
    public boolean addStudents(Students s);

    // update
    public boolean updateStudents(Students s);

    // delete
    public boolean deleteStudents(String sid);



}
