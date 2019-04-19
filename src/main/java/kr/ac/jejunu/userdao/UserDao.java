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
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i <params.length; i++)
                preparedStatement.setObject(i+1, params[i]);
            preparedStatement.setLong(1, id);

            return preparedStatement;
        };
        return jdbcContext.JdbcContextForGet(statementStrategy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name, password) values (?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            return preparedStatement;
        };
        return jdbcContext.JdbcContextForAdd(statementStrategy);
    }


    public void update(User user) throws SQLException {
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        String sql = "update userinfo set name =? , password =? where id =?";
        update(params, sql);

    }


    public void delete(Long id) throws SQLException {
        Object[] params = new Object[]{id};
        String sql = "delete from userinfo where id =?";
        update(params, sql);

    }

    private void update(Object[] params, String sql) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++)
                preparedStatement.setObject(i + 1, params[i]);

            return preparedStatement;
        };
        jdbcContext.JdbcContextForUpdate(statementStrategy);
    }


}