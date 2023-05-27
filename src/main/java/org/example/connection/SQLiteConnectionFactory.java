package org.example.connection;

import lombok.RequiredArgsConstructor;
import org.example.annotation.Inject;
import org.example.annotation.Injectable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RequiredArgsConstructor
@Injectable
public class SQLiteConnectionFactory implements ConnectionFactory {

    private final String URL;

    @Override
    public Connection create() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
