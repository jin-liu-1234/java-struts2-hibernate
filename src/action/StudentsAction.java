package action;


import entity.Students;
import service.StudentsDAO;
import service_impl.StudentsDAOImpl;

import java.text.SimpleDateFormat;
import java.util.List;

public class StudentsAction extends SuperAction {

    private static final long serialVersionUID = 1L;

    // 所有学生
    public String query() {

        StudentsDAO studentsDAO = new StudentsDAOImpl();

        List<Students> list = studentsDAO.queryAllStudents();


        // 放进session
        if (list != null && list.size() > 0) {
            session.setAttribute("studentsList", list);
        }

        return "query_success";
    }

    // 删除学生
    public String delete() {
        StudentsDAO studentsDAO = new StudentsDAOImpl();
        String sid = request.getParameter("sid");

        studentsDAO.deleteStudents(sid);

        return "delete_success";
    }

    // add
    public String add() throws Exception {
        Students s = new Students();
        s.setSname(request.getParameter("sname"));
        s.setGender(request.getParameter("gender"));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        s.setBirthday(sdf.parse(request.getParameter("birthday")));

        s.setAddress(request.getParameter("address"));

        StudentsDAO sdao = new StudentsDAOImpl();

        sdao.addStudents(s);

        return "add_success";
    }

    //
    public String modify() {
        String sid = request.getParameter("sid");

        StudentsDAO sdao = new StudentsDAOImpl();

        Students s = sdao.queryStudentsBySid(sid);

        session.setAttribute("modify_students", s);

        return "modify_success";
    }

    // update
    public String save() throws Exception {
        Students s = new Students();

        s.setSid(request.getParameter("sid"));

        s.setSname(request.getParameter("sname"));
        s.setGender(request.getParameter("gender"));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        s.setBirthday(sdf.parse(request.getParameter("birthday")));

        s.setAddress(request.getParameter("address"));

        StudentsDAO sdao = new StudentsDAOImpl();

        sdao.updateStudents(s);

        return "save_success";
    }

}
