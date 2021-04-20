package DBUtilitie;


import Objetos_POJO.*;
import com.jfoenix.controls.*;
import javafx.scene.control.Label;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import java.sql.*;
import java.util.Arrays;

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
                datos[contador] = ((JFXTextField)o).getText()==null?"":((JFXTextField)o).getText();
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
            }if(o instanceof String){
                datos[contador]= o.toString();
            }
            contador++;
        }

        System.out.println(Arrays.toString(datos));

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


    public void datos_tabla_control_pilones(JFXTreeTableView tabla,String consulta_select, Object o,String[] datos_busqueda) throws SQLException, ClassNotFoundException {

        //TODO Control de pilones Query
        PreparedStatement consulta_control_pilones = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                consulta_select);
        if (datos_busqueda.length > 0){
            for (int i = 0; i<datos_busqueda.length ; i++) {
                consulta_control_pilones.setString(i+1, datos_busqueda[i]);
            }
        }

        ResultSet resultSe = consulta_control_pilones.executeQuery();

        ObservableList data_control_pilones = FXCollections.observableArrayList();
        while (resultSe.next()){
            if (o instanceof Clase_control_pilones) {
                data_control_pilones.add(new Clase_control_pilones(resultSe.getString(1),
                        resultSe.getString(2), resultSe.getString(3),
                        resultSe.getString(4), resultSe.getString(5),
                        resultSe.getString(6), resultSe.getString(7),
                        resultSe.getString(8), resultSe.getString(9),
                        resultSe.getString(10)

                ));
            }else if(o instanceof Clase_remisiones){
                String[] desStrings = new String[5];
                String[] canStrings = new String[5];

                Clase_remisiones remision = new Clase_remisiones(resultSe.getString(1),
                        resultSe.getString(2),
                        resultSe.getString(3),
                        resultSe.getString(4),
                        resultSe.getString(5),
                        resultSe.getString(16));

                desStrings[0] =  resultSe.getString(6);
                canStrings[0] =  resultSe.getString(7);

                if(resultSe.getString(8)!=null) {
                    desStrings[1] = resultSe.getString(8);
                    canStrings[1] = resultSe.getString(9);
                }else{
                    desStrings[1] = "";
                    canStrings[1] = "";
                }

                if(resultSe.getString(10)!=null) {
                    desStrings[2] =  resultSe.getString(10);
                    canStrings[2] =  resultSe.getString(11);
                }else{
                    desStrings[2] = "";
                    canStrings[2] = "";
                }

                if(resultSe.getString(12)!=null) {
                    desStrings[3] =  resultSe.getString(12);
                    canStrings[3] =  resultSe.getString(13);
                }else{
                    desStrings[3] = "";
                    canStrings[3] = "";
                }

                if(resultSe.getString(14)!=null) {
                    desStrings[4] =  resultSe.getString(14);
                    canStrings[4] =  resultSe.getString(15);
                }else{
                    desStrings[4] = "";
                    canStrings[4] = "";
                }

                System.out.println(Arrays.toString(desStrings)
                        +" "+Arrays.toString(canStrings));

                remision.setTabacos_descrip_remision(desStrings);
                remision.setTotal_descrip_remision(canStrings);

                remision.descripcion();

                data_control_pilones.add(remision);
            }else if(o instanceof Clase_tabacos){
                data_control_pilones.add(new Clase_tabacos(resultSe.getString(1),
                        resultSe.getString(2)
                ));
            }
            else if(o instanceof Clase_entradas_pilones){
                data_control_pilones.add(new Clase_entradas_pilones(resultSe.getString(1),
                        resultSe.getString(2),resultSe.getString(3),
                        resultSe.getString(4),resultSe.getString(5),
                        resultSe.getString(6),resultSe.getString(7),
                        resultSe.getString(8),resultSe.getString(9)
                ));
            }
            else if(o instanceof Clase_en_sa_proceso_pilon){
                data_control_pilones.add(new Clase_en_sa_proceso_pilon(resultSe.getString(1),
                        resultSe.getString(2),resultSe.getString(3),
                        resultSe.getString(4),resultSe.getString(5),
                        resultSe.getString(6),resultSe.getString(7),
                        resultSe.getString(8),resultSe.getString(9)
                ));
            }else if(o instanceof Clase_control_temperatura){
                data_control_pilones.add(new Clase_control_temperatura(resultSe.getString(1),
                        resultSe.getString(2),resultSe.getString(3),
                        resultSe.getString(4),resultSe.getString(5)
                ));
            }else if(o instanceof Clase_pilones_nombre){
                data_control_pilones.add(new Clase_pilones_nombre(resultSe.getString(1),
                        resultSe.getString(2)));
            }
        }
        TreeItem root3 = new RecursiveTreeItem<>(data_control_pilones, RecursiveTreeObject::getChildren);

        tabla.setRoot(root3);
        tabla.setShowRoot(false);
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
