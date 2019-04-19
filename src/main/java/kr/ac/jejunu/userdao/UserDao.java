package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext = new JdbcContext();

    public UserDao(DataSource dataSource) {
        this.jdbcContext.dataSource = dataSource;
    }


    public User get(Long id) throws ClassNotFoundException, SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};
        return jdbcContext.get(sql, params);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        String sql = "insert into userinfo (name, password) values (?,?)";
        return jdbcContext.add(params, sql);
    }


    public void update(User user) throws SQLException {
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        String sql = "update userinfo set name =? , password =? where id =?";
        jdbcContext.update(params, sql);

    }

    public void delete(Long id) throws SQLException {
        Object[] params = new Object[]{id};
        String sql = "delete from userinfo where id =?";
        jdbcContext.update(params, sql);
    }
}