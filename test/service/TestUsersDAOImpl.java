package service;

import entity.Users;
import org.junit.Assert;
import org.junit.Test;
import service_impl.UsersDAOImpl;

public class TestUsersDAOImpl {

    @Test
    public void testUserLogin(){
        Users users = new Users(1, "aloneliu", "123456");

        UsersDAOImpl usersDAO = new UsersDAOImpl();

        Assert.assertEquals(true, usersDAO.userLogin(users));
    }
}
