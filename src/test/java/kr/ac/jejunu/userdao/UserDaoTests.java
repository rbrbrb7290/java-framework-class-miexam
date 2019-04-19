package kr.ac.jejunu.userdao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class UserDaoTests {
    private UserDao userDao;
    @Before
    public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "오현규";
        String password = "1234";

        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
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

    @Test
    public void testUpdate() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "ㅎㄹㅎ=";
        String password = "1234";

        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);

        user.setId(id);

        String changeName = "그루트";
        String changePassword = "8888";
        user.setName(changeName);
        user.setPassword(changePassword);

        userDao.update(user);

        User resultUser = userDao.get(id);
        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(changeName));
        assertThat(resultUser.getPassword(), is(changePassword));
    }

    @Test
    public void testDelete() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "ㅎㄹㅎ=";
        String password = "1234";

        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);
        user.setId(id);

        userDao.delete(id);

        user = userDao.get(id);
        assertThat(user, nullValue());
    }

}
