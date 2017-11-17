package service_impl;

import db.MyHibernateSessionFactory;
import entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.UsersDAO;

import java.util.List;

public class UsersDAOImpl implements UsersDAO {

    @Override
    public boolean userLogin(Users users) {
        // 事务对象
        Transaction transaction = null;
        String hql = "";
        try {
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();

            transaction = session.beginTransaction();

            hql = "from Users where username = ? and password = ? ";
            Query query = session.createQuery(hql);
            query.setParameter(0, users.getUsername());
            query.setParameter(1, users.getPassword());

            List list = query.list();
            transaction.commit();

            if (list.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (transaction != null) {
                transaction = null;
            }
        }
    }
}
