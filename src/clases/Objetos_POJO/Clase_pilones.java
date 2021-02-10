package clases.Objetos_POJO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

public class Clase_pilones extends RecursiveTreeObject<Clase_pilones> {
    private final SimpleStringProperty id_pilon;
    private final SimpleStringProperty nombre_pilon;
    private final SimpleStringProperty id_tabaco;
    private final SimpleStringProperty nombre_tbc;

    public Clase_pilones(String id_pilon, String nombre_pilon, String id_tabaco, String nombre_tbc) {
        this.id_pilon = new SimpleStringProperty(id_pilon);
        this.nombre_pilon = new SimpleStringProperty(nombre_pilon);
        this.id_tabaco = new SimpleStringProperty(id_tabaco);
        this.nombre_tbc = new SimpleStringProperty(nombre_tbc);
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

    public String getId_tabaco() {
        return id_tabaco.get();
    }

    public SimpleStringProperty id_tabacoProperty() {
        return id_tabaco;
    }

    public void setId_tabaco(String id_tabaco) {
        this.id_tabaco.set(id_tabaco);
    }

    public String getNombre_tbc() {
        return nombre_tbc.get();
    }

    public SimpleStringProperty nombre_tbcProperty() {
        return nombre_tbc;
    }

    public void setNombre_tbc(String nombre_tbc) {
        this.nombre_tbc.set(nombre_tbc);
    }
}
