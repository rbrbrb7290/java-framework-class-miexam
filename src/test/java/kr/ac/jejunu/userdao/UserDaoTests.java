package kr.ac.jejunu.userdao;

import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "오현규";
        String password = "1234";
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();

        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();
        User user = new User();

        String name = "ㅎㄹㅎ=";
        String password = "1234";

        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);

        User resultUser = userDao.get(id);
        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
    }

}
