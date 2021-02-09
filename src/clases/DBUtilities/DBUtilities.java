package clases.DBUtilities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtilities {

    protected static final String MYSQL_DB = "jdbc:mysql://localhost:3306/myeddb";
    protected static final String MYSQL_USER = "jose";
    protected static final String MYSQL_PASSWORD = "null";


    protected static final String MARIA_DB = "jdbc:mariadb://localhost:3306/comercialrym?";
    protected static final String MARIA_USER = "root";
    protected static final String MARIA_PASSWORD = "12345678";

    protected static final String ORACLE_DB = "";
    protected static final String ORACLE_USER = "x";
    protected static final String ORACLE_PASSWORD =  "x";

    protected static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    protected static final String SQLSERVER_DB = "jdbc:sqlserver://localhost:1433;databaseName=basename";
    protected static final String SQLSERVER_USER = ";user=sa";
    protected static final String SQLSERVER_PASSWORD = ";password=12345678;";


    public static Connection getConnection(DBType dbType) throws SQLException, ClassNotFoundException {
        switch (dbType){
            case MYSQLDB:
                return DriverManager.getConnection(MYSQL_DB,MYSQL_USER,MYSQL_PASSWORD);
            case MARIADB:
                Class.forName("org.mariadb.jdbc.Driver");
                return DriverManager.getConnection(MARIA_DB,MARIA_USER,MARIA_PASSWORD);
            case ORACLEDB:
                return DriverManager.getConnection(ORACLE_DB,ORACLE_USER,ORACLE_PASSWORD);
            case SQLSEVER:
                Class.forName(driver);
                return DriverManager.getConnection(SQLSERVER_DB,SQLSERVER_USER,SQLSERVER_PASSWORD);
            default:
                return null;
        }

    }

    public static void processException(SQLException e){
        System.err.println("Error "+ e.getMessage());
        System.err.println("Code " + e.getErrorCode());
        System.err.println("SQL state" + e.getSQLState());
    }
    public static void processExceptionFilenoFount(ClassNotFoundException e){
        System.err.println("Error: "+ e.getMessage());
        System.err.println("Localización: " + e.getLocalizedMessage());

    }

    public static boolean EDITAR = true;
}
