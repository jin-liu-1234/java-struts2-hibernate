package db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MyHibernateSessionFactory {

    private static SessionFactory sessionFactory; //会话工厂属性

    // 构造方法私有化 保证单例模式
    private MyHibernateSessionFactory() {
    }

    // 共有的静态方法 获取会话工厂属性
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return  sessionFactory;

        } else {
            return  sessionFactory;
        }
    }


}
