package Objetos_POJO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

import java.util.Arrays;

public class Clase_pilones_nombre extends RecursiveTreeObject<Clase_pilones_nombre> {
    private final SimpleStringProperty id_pilon;
    private final SimpleStringProperty nombre_pilon;


    private long[] id_tabaco;

    public long[] getId() {
        return id_tabaco;
    }

    public void setId(long[] id) {
        this.id_tabaco = id;
    }

    public Clase_pilones_nombre(String id_pilon, String nombre_pilon) {

        this.id_pilon = new SimpleStringProperty(id_pilon);
        this.nombre_pilon = new SimpleStringProperty(nombre_pilon);
    }

    public String getId_pilon() {
        return id_pilon.get();
    }

    public SimpleStringProperty id_pilonProperty() {
        return id_pilon;
    }

    public void setId_pilon(String id_pilon) {
        this.id_pilon.set(id_pilon);
    }

    public String getNombre_pilon() {
        return nombre_pilon.get();
    }

    public SimpleStringProperty nombre_pilonProperty() {
        return nombre_pilon;
    }

    public void setNombre_pilon(String nombre_pilon) {
        this.nombre_pilon.set(nombre_pilon);
    }

    @Override
    public String toString() {
        return "Pilón #  " + getNombre_pilon();
    }
}
