package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverAccessor {

    //private final static String DRIVER_NAME = "com.mysql.jdbc.Driver"; // mysql5.7
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver"; // mysql8.0
    // 自分のユーザ名
    private final static String USER_NAME = "test";
    //自分のmysqlのパスワード
    private final static String PASSWORD = "test";

    public Connection createConnection() {
        String DRIVER_URL = "jdbc:mysql://localhost:3306/dbName?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";
        String JAVA_ENV = System.getenv("JAVA_ENV");

        if(JAVA_ENV.equals("development")){ // docker-composeで立ち上げるdevelop環境の場合
            DRIVER_URL = "jdbc:mysql://local_se00g0_db:3306/db?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";
        } else if (JAVA_ENV.equals("production")) { // docker-composeで立ち上げるproduction環境の場合
            DRIVER_URL = "jdbc:mysql://se00g0_db/db?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";
        }
        try {
            Class.forName(DRIVER_NAME);
            Connection con = DriverManager.getConnection(DRIVER_URL, USER_NAME, PASSWORD);
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("Can't Find JDBC Driver.\n");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    public void closeConnection(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}
