package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 所有Action动作的父类
public class SuperAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ServletContextAware {

    protected HttpServletRequest request; // 请求对象
    protected HttpServletResponse response; // 响应对象
    protected HttpSession session; // 会话对象
    protected ServletContext application; // 全局对象

    private static final long serialVersionUID = 1L;

    @Override
    public void setServletRequest(javax.servlet.http.HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(javax.servlet.http.HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setServletContext(javax.servlet.ServletContext application) {
        this.application = application;
        this.session = this.request.getSession();
    }


}
