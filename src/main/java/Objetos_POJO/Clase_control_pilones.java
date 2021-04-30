package Objetos_POJO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

public class Clase_control_pilones extends RecursiveTreeObject<Clase_control_pilones> {

    public SimpleStringProperty id_control_pilon;
    public SimpleStringProperty clase_tabaco_control;
    public SimpleStringProperty fecha_control;
    public SimpleStringProperty numero_pilon_control;
    public SimpleStringProperty entrada_tabaco_pilon;
    public SimpleStringProperty salida_tabaco_pilon;
    public SimpleStringProperty total_actual;
    public SimpleStringProperty existencia_total;

    public Clase_control_pilones(String id_control_pilon, String clase_tabaco_control,
                                 String fecha_control, String numero_pilon_control,
                                 String entrada_tabaco_pilon, String salida_tabaco_pilon,
                                 String total_actual, String existencia_total) {

        this.id_control_pilon = new SimpleStringProperty(id_control_pilon);
        this.clase_tabaco_control = new SimpleStringProperty(clase_tabaco_control);
        this.fecha_control = new SimpleStringProperty(fecha_control);
        this.numero_pilon_control = new SimpleStringProperty(numero_pilon_control);
        this.entrada_tabaco_pilon = new SimpleStringProperty(entrada_tabaco_pilon);
        this.salida_tabaco_pilon = new SimpleStringProperty(salida_tabaco_pilon);
        this.total_actual = new SimpleStringProperty(total_actual);
        this.existencia_total = new SimpleStringProperty(existencia_total);
    }

    public Clase_control_pilones(){}

    public String getId_control_pilon() {
        return id_control_pilon.get();
    }

    public SimpleStringProperty id_control_pilonProperty() {
        return id_control_pilon;
    }

    public void setId_control_pilon(String id_control_pilon) {
        this.id_control_pilon.set(id_control_pilon);
    }

    public String getClase_tabaco_control() {
        return clase_tabaco_control.get();
    }

    public SimpleStringProperty clase_tabaco_controlProperty() {
        return clase_tabaco_control;
    }

    public void setClase_tabaco_control(String clase_tabaco_control) {
        this.clase_tabaco_control.set(clase_tabaco_control);
    }

    public String getFecha_control() {
        return fecha_control.get();
    }

    public SimpleStringProperty fecha_controlProperty() {
        return fecha_control;
    }

    public void setFecha_control(String fecha_control) {
        this.fecha_control.set(fecha_control);
    }

    public String getNumero_pilon_control() {
        return numero_pilon_control.get();
    }

    public SimpleStringProperty numero_pilon_controlProperty() {
        return numero_pilon_control;
    }

    public void setNumero_pilon_control(String numero_pilon_control) {
        this.numero_pilon_control.set(numero_pilon_control);
    }

    public String getEntrada_tabaco_pilon() {
        return entrada_tabaco_pilon.get();
    }

    public SimpleStringProperty entrada_tabaco_pilonProperty() {
        return entrada_tabaco_pilon;
    }

    public void setEntrada_tabaco_pilon(String entrada_tabaco_pilon) {
        this.entrada_tabaco_pilon.set(entrada_tabaco_pilon);
    }

    public String getSalida_tabaco_pilon() {
        return salida_tabaco_pilon.get();
    }

    public SimpleStringProperty salida_tabaco_pilonProperty() {
        return salida_tabaco_pilon;
    }

    public void setSalida_tabaco_pilon(String salida_tabaco_pilon) {
        this.salida_tabaco_pilon.set(salida_tabaco_pilon);
    }

    public String getTotal_actual() {
        return total_actual.get();
    }

    public SimpleStringProperty total_actualProperty() {
        return total_actual;
    }

    public void setTotal_actual(String total_actual) {
        this.total_actual.set(total_actual);
    }

    public String getExistencia_total() {
        return existencia_total.get();
    }

    public SimpleStringProperty existencia_totalProperty() {
        return existencia_total;
    }

    public void setExistencia_total(String existencia_total) {
        this.existencia_total.set(existencia_total);
    }
}
