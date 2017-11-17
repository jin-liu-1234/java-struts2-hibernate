package entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;

import java.util.Date;
import java.util.EnumSet;

public class TestStudents {

    @Test
    public void testSchemaExport() {
        // 创建配置对象
        Configuration configuration = new Configuration().configure();
        // 创建服务注册对象
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();

        SchemaExport schemaExport = new SchemaExport();

        schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
    }

    // 添加测试数据
    @Test
    public void testSaveStutends() {
        // 创建配置对象
        Configuration configuration = new Configuration().configure();
        // 创建服务注册对象
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

        // 创建sessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        // 创建session对象
        Session session = sessionFactory.getCurrentSession();

        // 创建事务对象
        Transaction tx = session.beginTransaction();

        //
        Students s1 = new Students("001", "张三丰", "男", new Date(), "武当山");
        Students s2 = new Students("002", "郭靖", "男", new Date(), "桃花岛");
        Students s3 = new Students("003", "黄蓉", "女", new Date(), "桃花岛");

        session.save(s1);
        session.save(s2);
        session.save(s3);

        tx.commit();
        sessionFactory.close();
    }

}
