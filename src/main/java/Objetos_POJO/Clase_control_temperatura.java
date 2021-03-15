package Objetos_POJO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

public class Clase_control_temperatura extends RecursiveTreeObject <Clase_control_temperatura> {

    private final SimpleStringProperty id_control_temp;
    private final SimpleStringProperty id_pilon_temp;
    private final SimpleStringProperty temperatura;
    private final SimpleStringProperty fecha_revision_temp;
    private final SimpleStringProperty mantenimiento_temp;
    private SimpleStringProperty nombre_pilon;

    public Clase_control_temperatura(String id_control_temp, String id_pilon_temp,String temperatura,
                                     String fecha_revision_temp, String mantenimiento_temp) {
        this.id_control_temp = new SimpleStringProperty(id_control_temp);
        this.id_pilon_temp = new SimpleStringProperty(id_pilon_temp);
        this.temperatura = new SimpleStringProperty(temperatura);
        this.fecha_revision_temp = new SimpleStringProperty(fecha_revision_temp);
        this.mantenimiento_temp = new SimpleStringProperty(mantenimiento_temp);
    }
    public String getId_control_temp() { return id_control_temp.get(); }

    public SimpleStringProperty id_control_tempProperty() {
        return id_control_temp;
    }

    public void setId_control_temp(String id_control_temp) {
        this.id_control_temp.set(id_control_temp);
    }

    public String getId_pilon_temp() { return id_pilon_temp.get(); }

    public SimpleStringProperty id_pilon_tempProperty() {
        return id_pilon_temp;
    }

    public void setId_pilon_temp(String id_pilon_temp) { this.id_pilon_temp.set(id_pilon_temp); }

    public String getTemperatura() { return temperatura.get(); }

    public SimpleStringProperty temperaturaProperty() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura.set(temperatura);
    }

    public String getFecha_revision_temp() { return fecha_revision_temp.get(); }

    public SimpleStringProperty fecha_revision_tempProperty() {
        return fecha_revision_temp;
    }

    public void setFecha_revision_temp(String fecha_revision_temp) { this.fecha_revision_temp.set(fecha_revision_temp); }

    public String getMantenimiento_temp() { return mantenimiento_temp.get(); }

    public SimpleStringProperty mantenimiento_tempProperty() {
        return mantenimiento_temp;
    }

    public void setMantenimiento_temp(String mantenimiento_temp) { this.mantenimiento_temp.set(mantenimiento_temp); }

    public String getNombre_pilon() {
        return nombre_pilon.get();
    }

    public SimpleStringProperty nombre_pilonProperty() {
        return nombre_pilon;
    }

    public void setNombre_pilon(String nombre_pilon) {
        this.nombre_pilon.set(nombre_pilon);
    }
}
