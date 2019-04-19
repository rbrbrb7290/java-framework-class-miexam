package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStatement implements StatementStrategy {
    private User user;
    public UpdateStatement(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement MakePreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("update userinfo set name =? , password =? where id =?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2,  user.getPassword());
        preparedStatement.setLong(3,  user.getId());

        return preparedStatement;
    }
}
