package action;

import com.opensymphony.xwork2.ModelDriven;
import entity.Users;
import service.UsersDAO;
import service_impl.UsersDAOImpl;

public class UsersAction extends SuperAction implements ModelDriven<Users> {

    private static final long serialVersionUID = 1L;
    private Users user = new Users();

    // 用户登录动作
    public String login() {
        UsersDAO udao = new UsersDAOImpl();

        if (udao.userLogin(user)) {
            session.setAttribute("loginUserName", user.getUsername());
            return "login_success";
        } else {
            return "login_failure";
        }
    }

    // 用户注销
    public String logout() {
        if (session.getAttribute("loginUserName") != null) {
            session.removeAttribute("loginUserName");
        }

        return "logout_success";
    }


    @Override
    public Users getModel() {
        return user;
    }
}
