package Objetos_POJO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

public class Clase_control_pilones extends RecursiveTreeObject<Clase_control_pilones> {

    private final SimpleStringProperty id_control_pilon;
    private final SimpleStringProperty clase_tabaco_control;
    private final SimpleStringProperty variedad_tabaco;
    private final SimpleStringProperty finca_pilon;
    private final SimpleStringProperty fecha_control;
    private final SimpleStringProperty numero_pilon_control;
    private final SimpleStringProperty entrada_tabaco_pilon;
    private final SimpleStringProperty salida_tabaco_pilon;
    private final SimpleStringProperty total_actual;
    private final SimpleStringProperty existencia_total;

    public Clase_control_pilones(String id_control_pilon, String clase_tabaco_control,
                                 String variedad_tabaco, String finca_pilon,
                                 String fecha_control, String numero_pilon_control,
                                 String entrada_tabaco_pilon, String salida_tabaco_pilon,
                                 String total_actual, String existencia_total) {

        this.id_control_pilon = new SimpleStringProperty(id_control_pilon);
        this.clase_tabaco_control = new SimpleStringProperty(clase_tabaco_control);
        this.variedad_tabaco = new SimpleStringProperty(variedad_tabaco);
        this.finca_pilon = new SimpleStringProperty(finca_pilon);
        this.fecha_control = new SimpleStringProperty(fecha_control);
        this.numero_pilon_control = new SimpleStringProperty(numero_pilon_control);
        this.entrada_tabaco_pilon = new SimpleStringProperty(entrada_tabaco_pilon);
        this.salida_tabaco_pilon = new SimpleStringProperty(salida_tabaco_pilon);
        this.total_actual = new SimpleStringProperty(total_actual);
        this.existencia_total = new SimpleStringProperty(existencia_total);
    }

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

    public String getVariedad_tabaco() {
        return variedad_tabaco.get();
    }

    public SimpleStringProperty variedad_tabacoProperty() {
        return variedad_tabaco;
    }

    public void setVariedad_tabaco(String variedad_tabaco) {
        this.variedad_tabaco.set(variedad_tabaco);
    }

    public String getFinca_pilon() {
        return finca_pilon.get();
    }

    public SimpleStringProperty finca_pilonProperty() {
        return finca_pilon;
    }

    public void setFinca_pilon(String finca_pilon) {
        this.finca_pilon.set(finca_pilon);
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
