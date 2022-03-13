package beans;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 15.04.2004
 * Time: 13:51:26
 * To change this template use Options | File Templates.
 */
public class BasicDataSourceDB extends BasicDataSource {
    public Connection getConnection(String dbname) throws SQLException {
        String use_dbname="use "+dbname;
        Connection conn = createDataSource().getConnection();
        Statement stmt=conn.createStatement();
        stmt.execute(use_dbname);
        stmt.close();
        return conn;
    }
}

