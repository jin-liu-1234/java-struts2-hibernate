package service_impl;

import db.MyHibernateSessionFactory;
import entity.Students;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.StudentsDAO;

import java.util.List;

public class StudentsDAOImpl implements StudentsDAO {

    @Override
    public List<Students> queryAllStudents() {

        Transaction tx = null;
        List<Students> list = null;
        String hql = "";

        try {
            // 会话对象
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "from Students";
            Query query = session.createQuery(hql);

            list = query.list();
            tx.commit();

            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.commit();

            return list;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }

    @Override
    public Students queryStudentsBySid(String sid) {
        Transaction tx = null;
        Students s = null;

        try {
            // 会话对象
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            s = session.get(Students.class, sid);

            tx.commit();

            return s;
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.commit();

            return s;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }

    @Override
    public boolean addStudents(Students s) {
        s.setSid(getNewSid());

        Transaction tx = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            session.save(s);
            tx.commit();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.commit();

            return false;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }

    @Override
    public boolean updateStudents(Students s) {
        Transaction tx = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            session.update(s);

            tx.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();

            tx.commit();

            return false;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }

    @Override
    public boolean deleteStudents(String sid) {
        Transaction tx = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Students s = (Students) session.get(Students.class, sid);
            session.delete(s);

            tx.commit();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();

            tx.commit();

            return false;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }

    // 生成学号
    private String getNewSid() {

        Transaction tx = null;
        String hql = "";
        String sid = null;

        try {
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            // 获取当前学生的最大编号
            hql = "select max(sid) from Students";

            Query query = session.createQuery(hql);

            sid = (String) query.uniqueResult();

            if (sid == null || "".equals(sid)) {
                sid = "S0000001";
            } else {
                String tmp = sid.substring(1); // 截掉第一位
                int i = Integer.parseInt(tmp); // 转数字

                i++;

                tmp = String.valueOf(i);
                int len = tmp.length();

                for (int j = 0; j < 7 - len; j++) {
                    tmp = "0" + tmp;
                }
                sid = "S" + tmp;
            }

            tx.commit();
            return sid;
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.commit();
            return null;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }


}
