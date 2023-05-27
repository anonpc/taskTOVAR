package org.example.connection;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection create();
}
