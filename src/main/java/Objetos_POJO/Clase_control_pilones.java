package Objetos_POJO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

public class Clase_control_pilones extends RecursiveTreeObject<Clase_control_pilones> {

    private final SimpleStringProperty lbl_id_control_pilon;
    private final SimpleStringProperty txt_clase_tabaco_control;
    private final SimpleStringProperty jtxt_variedad_tabaco;
    private final SimpleStringProperty jtxt_finca_pilon;
    private final SimpleStringProperty jdate_fecha_control;
    private final SimpleStringProperty txt_numero_pilon_control;
    private final SimpleStringProperty jtxt_entrada_tabaco_pilon;
    private final SimpleStringProperty jtxt_salida_tabaco_pilon;
    private final SimpleStringProperty jtxt_total_actual;
    private final SimpleStringProperty jtxt_existencia_total;

    public Clase_control_pilones(String lbl_id_control_pilon, String txt_clase_tabaco_control,
                                 String jtxt_variedad_tabaco, String jtxt_finca_pilon,
                                 String jdate_fecha_control, String txt_numero_pilon_control,
                                 String jtxt_entrada_tabaco_pilon, String jtxt_salida_tabaco_pilon,
                                 String jtxt_total_actual, String jtxt_existencia_total) {

        this.lbl_id_control_pilon = new SimpleStringProperty(lbl_id_control_pilon);
        this.txt_clase_tabaco_control = new SimpleStringProperty(txt_clase_tabaco_control);
        this.jtxt_variedad_tabaco = new SimpleStringProperty(jtxt_variedad_tabaco);
        this.jtxt_finca_pilon = new SimpleStringProperty(jtxt_finca_pilon);
        this.jdate_fecha_control = new SimpleStringProperty(jdate_fecha_control);
        this.txt_numero_pilon_control = new SimpleStringProperty(txt_numero_pilon_control);
        this.jtxt_entrada_tabaco_pilon = new SimpleStringProperty(jtxt_entrada_tabaco_pilon);
        this.jtxt_salida_tabaco_pilon = new SimpleStringProperty(jtxt_salida_tabaco_pilon);
        this.jtxt_total_actual = new SimpleStringProperty(jtxt_total_actual);
        this.jtxt_existencia_total = new SimpleStringProperty(jtxt_existencia_total);
    }

    public String getLbl_id_control_pilon() {
        return lbl_id_control_pilon.get();
    }

    public SimpleStringProperty lbl_id_control_pilonProperty() {
        return lbl_id_control_pilon;
    }

    public void setLbl_id_control_pilon(String lbl_id_control_pilon) {
        this.lbl_id_control_pilon.set(lbl_id_control_pilon);
    }

    public String getTxt_clase_tabaco_control() {
        return txt_clase_tabaco_control.get();
    }

    public SimpleStringProperty txt_clase_tabaco_controlProperty() {
        return txt_clase_tabaco_control;
    }

    public void setTxt_clase_tabaco_control(String txt_clase_tabaco_control) {
        this.txt_clase_tabaco_control.set(txt_clase_tabaco_control);
    }

    public String getJtxt_variedad_tabaco() {
        return jtxt_variedad_tabaco.get();
    }

    public SimpleStringProperty jtxt_variedad_tabacoProperty() {
        return jtxt_variedad_tabaco;
    }

    public void setJtxt_variedad_tabaco(String jtxt_variedad_tabaco) {
        this.jtxt_variedad_tabaco.set(jtxt_variedad_tabaco);
    }

    public String getJtxt_finca_pilon() {
        return jtxt_finca_pilon.get();
    }

    public SimpleStringProperty jtxt_finca_pilonProperty() {
        return jtxt_finca_pilon;
    }

    public void setJtxt_finca_pilon(String jtxt_finca_pilon) {
        this.jtxt_finca_pilon.set(jtxt_finca_pilon);
    }

    public String getJdate_fecha_control() {
        return jdate_fecha_control.get();
    }

    public SimpleStringProperty jdate_fecha_controlProperty() {
        return jdate_fecha_control;
    }

    public void setJdate_fecha_control(String jdate_fecha_control) {
        this.jdate_fecha_control.set(jdate_fecha_control);
    }

    public String getTxt_numero_pilon_control() {
        return txt_numero_pilon_control.get();
    }

    public SimpleStringProperty txt_numero_pilon_controlProperty() {
        return txt_numero_pilon_control;
    }

    public void setTxt_numero_pilon_control(String txt_numero_pilon_control) {
        this.txt_numero_pilon_control.set(txt_numero_pilon_control);
    }

    public String getJtxt_entrada_tabaco_pilon() {
        return jtxt_entrada_tabaco_pilon.get();
    }

    public SimpleStringProperty jtxt_entrada_tabaco_pilonProperty() {
        return jtxt_entrada_tabaco_pilon;
    }

    public void setJtxt_entrada_tabaco_pilon(String jtxt_entrada_tabaco_pilon) {
        this.jtxt_entrada_tabaco_pilon.set(jtxt_entrada_tabaco_pilon);
    }

    public String getJtxt_salida_tabaco_pilon() {
        return jtxt_salida_tabaco_pilon.get();
    }

    public SimpleStringProperty jtxt_salida_tabaco_pilonProperty() {
        return jtxt_salida_tabaco_pilon;
    }

    public void setJtxt_salida_tabaco_pilon(String jtxt_salida_tabaco_pilon) {
        this.jtxt_salida_tabaco_pilon.set(jtxt_salida_tabaco_pilon);
    }

    public String getJtxt_total_actual() {
        return jtxt_total_actual.get();
    }

    public SimpleStringProperty jtxt_total_actualProperty() {
        return jtxt_total_actual;
    }

    public void setJtxt_total_actual(String jtxt_total_actual) {
        this.jtxt_total_actual.set(jtxt_total_actual);
    }

    public String getJtxt_existencia_total() {
        return jtxt_existencia_total.get();
    }

    public SimpleStringProperty jtxt_existencia_totalProperty() {
        return jtxt_existencia_total;
    }

    public void setJtxt_existencia_total(String jtxt_existencia_total) {
        this.jtxt_existencia_total.set(jtxt_existencia_total);
    }
}
