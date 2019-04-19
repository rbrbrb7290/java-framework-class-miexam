package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetStatement implements StatementStrategy {
    private Long id;
    public GetStatement(Long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement MakePreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);

        return preparedStatement;
    }
}