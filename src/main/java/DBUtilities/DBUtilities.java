package DBUtilities;


import com.jfoenix.controls.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;

public class DBUtilities {

    protected static final String MYSQL_DB = "jdbc:mysql://localhost:3306/db_taopar_pilones";
    protected static final String MYSQL_USER = "root";
    protected static final String MYSQL_PASSWORD = "";


    protected static final String MARIA_DB = "jdbc:mariadb://localhost:3306/db_taopar_pilones";
    protected static final String MARIA_USER = "root";
    protected static final String MARIA_PASSWORD = "";

    protected static final String ORACLE_DB = "";
    protected static final String ORACLE_USER = "x";
    protected static final String ORACLE_PASSWORD =  "x";

    protected static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    protected static final String SQLSERVER_DB = "jdbc:sqlserver://localhost:1433;databaseName=db_taopar_pilones";
    protected static final String SQLSERVER_USER = ";user=sa";
    protected static final String SQLSERVER_PASSWORD = ";password=12345678;";
    private final DBType dbType;


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
    public static void CargarId(Label label, String consulta) {

        String ultimoValor = null;
        int valor;
        String id = null;

        try {
            PreparedStatement stmtr = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(consulta);
            ResultSet rsr = stmtr.executeQuery();
            if (rsr != null && rsr.next()) {
                ultimoValor = rsr.getString(1);
                valor = Integer.parseInt(ultimoValor);
                valor = valor + 1;
                id = String.valueOf(valor);
                label.setText(id);
            } else {
                label.setText("1");
            }

            stmtr.close();
            rsr.close();
            DBUtilities.getConnection(DBType.MARIADB).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DBUtilities(DBType dbType){
        this.dbType = dbType;
    }

    private String preparar_consulta(String nombre_store_procedure,String[] datos){
        String PROCEDURE="CALL ";
        String PARENTESIS_IZQUIERDO="(";
        String PARENTESIS_DERECHO=")";

        PROCEDURE = PROCEDURE+nombre_store_procedure+PARENTESIS_IZQUIERDO;

        for(String dato : datos){
            PROCEDURE =  PROCEDURE + "?,";
        }

        return PROCEDURE.substring(0, PROCEDURE.length()-1)+PARENTESIS_DERECHO;
    }

    String[] datos_salida = new String[2];


    public String[] insert(String nombre_store_procedure,Object[] campos){


        String[] datos = new String[campos.length];
        int contador = 0;

        for (Object o: campos){
            if (o instanceof JFXTextField){
                datos[contador] = ((JFXTextField)o).getText();
            }else if(o instanceof Label){
                datos[contador]= ((Label)o).getText();
            }else if(o instanceof Integer){
                datos[contador]= String.valueOf((int)o);
            }else if(o instanceof JFXComboBox){
                datos[contador]= ((JFXComboBox)o).getSelectionModel().getSelectedItem().toString();
            }else if(o instanceof JFXDatePicker){
                datos[contador]= ((JFXDatePicker)o).getValue().toString();
            }else if(o instanceof JFXTextArea){
                datos[contador]= ((JFXTextArea)o).getText();
            }else if(o instanceof JFXPasswordField){
                datos[contador]= ((JFXPasswordField)o).getText();
            }
            contador++;
        }

        try {
            PreparedStatement s = DBUtilities.getConnection(dbType).
                    prepareStatement(preparar_consulta(nombre_store_procedure,datos));
            for (int i = 1;i <= datos.length;i++) {
                s.setString(i, datos[i-1]);
            }
            ResultSet r = s.executeQuery();
            if(r.next()){
                datos_salida[0] = r.getString(1);
                datos_salida[1] = r.getString(2);
            }

            return datos_salida;

        }catch (Exception e){
            e.printStackTrace();
            datos_salida[0]="Fallo";
            return datos_salida;
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
