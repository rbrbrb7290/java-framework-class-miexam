package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext = new JdbcContext();

    public UserDao(DataSource dataSource) {
        this.jdbcContext.dataSource = dataSource;
    }


    public User get(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new GetStatement(id);
        return jdbcContext.JdbcContextForGet(statementStrategy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new AddStatement(user);
        return jdbcContext.JdbcContextForAdd(statementStrategy);
    }


    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatement(user);
        jdbcContext.JdbcContextForUpdate(statementStrategy);

    }


    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DelteStatement(id);
        jdbcContext.JdbcContextForUpdate(statementStrategy);

    }





}