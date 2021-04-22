package Objetos_POJO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

public class Clase_en_sa_proceso_pilon extends RecursiveTreeObject<Clase_en_sa_proceso_pilon> {

    public SimpleStringProperty id_en_sa_proceso_pilon;
    public SimpleStringProperty fecha_en_sa_proceso_pilon;
    public SimpleStringProperty remision_en_sa_proceso_pilon;
    public SimpleStringProperty en_sa_proceso_pilon;
    public SimpleStringProperty nombre_tab_en_sa_proceso_pilon;
    public SimpleStringProperty subtotal_en_sa_proceso_pilon;
    public SimpleStringProperty total_lbs_en_sa_proceso_pilon;
    public SimpleStringProperty total_remision_en_sa_proceso_pilon;

    public Clase_en_sa_proceso_pilon(){

    }

    public Clase_en_sa_proceso_pilon(String id_en_sa_proceso_pilon, String fecha_en_sa_proceso_pilon,
                                     String remision_en_sa_proceso_pilon, String en_sa_proceso_pilon,
                                     String nombre_tab_en_sa_proceso_pilon,
                                     String subtotal_en_sa_proceso_pilon, String total_lbs_en_sa_proceso_pilon,
                                     String total_remision_en_sa_proceso_pilon){
        this.id_en_sa_proceso_pilon = new SimpleStringProperty(id_en_sa_proceso_pilon);
        this.fecha_en_sa_proceso_pilon = new SimpleStringProperty(fecha_en_sa_proceso_pilon);
        this.remision_en_sa_proceso_pilon = new SimpleStringProperty(remision_en_sa_proceso_pilon);
        this.en_sa_proceso_pilon = new SimpleStringProperty(en_sa_proceso_pilon);
        this.nombre_tab_en_sa_proceso_pilon = new SimpleStringProperty(nombre_tab_en_sa_proceso_pilon);
        this.subtotal_en_sa_proceso_pilon = new SimpleStringProperty(subtotal_en_sa_proceso_pilon);
        this.total_lbs_en_sa_proceso_pilon = new SimpleStringProperty(total_lbs_en_sa_proceso_pilon);
        this.total_remision_en_sa_proceso_pilon = new SimpleStringProperty(total_remision_en_sa_proceso_pilon);


    }

    public String getId_en_sa_proceso_pilon() { return id_en_sa_proceso_pilon.get(); }

    public SimpleStringProperty id_en_sa_proceso_pilonProperty() { return id_en_sa_proceso_pilon; }

    public void setId_en_sa_proceso_pilon(String id_en_sa_proceso_pilon) { this.id_en_sa_proceso_pilon.set(id_en_sa_proceso_pilon);
    }

    public String getFecha_en_sa_proceso_pilon() {
        return fecha_en_sa_proceso_pilon.get();
    }

    public SimpleStringProperty fecha_en_sa_proceso_pilonProperty() {
        return fecha_en_sa_proceso_pilon;
    }

    public void setFecha_en_sa_proceso_pilon(String fecha_en_sa_proceso_pilon) {
        this.fecha_en_sa_proceso_pilon.set(fecha_en_sa_proceso_pilon);
    }

    public String getRemision_en_sa_proceso_pilon() {
        return remision_en_sa_proceso_pilon.get();
    }

    public SimpleStringProperty remision_en_sa_proceso_pilonProperty() {
        return remision_en_sa_proceso_pilon;
    }

    public void setRemision_en_sa_proceso_pilon(String remision_en_sa_proceso_pilon) {
        this.remision_en_sa_proceso_pilon.set(remision_en_sa_proceso_pilon);
    }

    public String getEn_sa_proceso_pilon() {
        return en_sa_proceso_pilon.get();
    }

    public SimpleStringProperty en_sa_proceso_pilonProperty() {
        return en_sa_proceso_pilon;
    }

    public void setEn_sa_proceso_pilon(String en_sa_proceso_pilon) {
        this.en_sa_proceso_pilon.set(en_sa_proceso_pilon);
    }

    public String getNombre_tab_en_sa_proceso_pilon() {
        return nombre_tab_en_sa_proceso_pilon.get();
    }

    public SimpleStringProperty nombre_tab_en_sa_proceso_pilonProperty() {
        return nombre_tab_en_sa_proceso_pilon;
    }

    public void setNombre_tab_en_sa_proceso_pilon(String nombre_tab_en_sa_proceso_pilon) {
        this.nombre_tab_en_sa_proceso_pilon.set(nombre_tab_en_sa_proceso_pilon);
    }

    public String getSubtotal_en_sa_proceso_pilon() {
        return subtotal_en_sa_proceso_pilon.get();
    }

    public SimpleStringProperty subtotal_en_sa_proceso_pilonProperty() {
        return subtotal_en_sa_proceso_pilon;
    }

    public void setSubtotal_en_sa_proceso_pilon(String subtotal_en_sa_proceso_pilon) {
        this.subtotal_en_sa_proceso_pilon.set(subtotal_en_sa_proceso_pilon);
    }

    public String getTotal_lbs_en_sa_proceso_pilon() {
        return total_lbs_en_sa_proceso_pilon.get();
    }

    public SimpleStringProperty total_lbs_en_sa_proceso_pilonProperty() {
        return total_lbs_en_sa_proceso_pilon;
    }

    public void setTotal_lbs_en_sa_proceso_pilon(String total_lbs_en_sa_proceso_pilon) {
        this.total_lbs_en_sa_proceso_pilon.set(total_lbs_en_sa_proceso_pilon);
    }

    public String getTotal_remision_en_sa_proceso_pilon() {
        return total_remision_en_sa_proceso_pilon.get();
    }

    public SimpleStringProperty total_remision_en_sa_proceso_pilonProperty() {
        return total_remision_en_sa_proceso_pilon;
    }

    public void setTotal_remision_en_sa_proceso_pilon(String total_remision_en_sa_proceso_pilon) {
        this.total_remision_en_sa_proceso_pilon.set(total_remision_en_sa_proceso_pilon);
    }

}
