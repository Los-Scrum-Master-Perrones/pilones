package Objetos_POJO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

public class Clase_tabacos  extends RecursiveTreeObject<Clase_tabacos> {
    public SimpleStringProperty id_tabaco;
    public SimpleStringProperty nombre_tbc;

    public Clase_tabacos(String id_tabaco, String nombre_tbc) {
        this.id_tabaco = new SimpleStringProperty(id_tabaco);
        this.nombre_tbc = new SimpleStringProperty(nombre_tbc);
    }
    public Clase_tabacos() {
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

    @Override
    public String toString() {
        return  getNombre_tbc();
}
}

