package Objetos_POJO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

public class Clase_pilones extends RecursiveTreeObject<Clase_pilones> {
    public SimpleStringProperty id_pilon;
    public SimpleStringProperty nombre_pilon;
    public SimpleStringProperty tabaco;
    public SimpleStringProperty tabaco_2;
    public SimpleStringProperty tabaco_3;
    public SimpleStringProperty tabaco_4;
    public SimpleStringProperty tabaco_5;

    private long[] id_tabaco;

    public long[] getId() {
        return id_tabaco;
    }

    public void setId(long[] id) {
        this.id_tabaco = id;
    }

    public Clase_pilones(){

    }

    public Clase_pilones(String id_pilon, String nombre_pilon, String tabaco,
                         String tabaco_2, String tabaco_3,
                         String tabaco_4, String tabaco_5) {

        this.id_pilon = new SimpleStringProperty(id_pilon);
        this.nombre_pilon = new SimpleStringProperty(nombre_pilon);
        this.tabaco = new SimpleStringProperty(tabaco);
        this.tabaco_2 = new SimpleStringProperty(tabaco_2);
        this.tabaco_3 = new SimpleStringProperty(tabaco_3);
        this.tabaco_4 = new SimpleStringProperty(tabaco_4);
        this.tabaco_5 = new SimpleStringProperty(tabaco_5);
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

    public String getTabaco() {
        return tabaco.get();
    }

    public SimpleStringProperty tabacoProperty() {
        return tabaco;
    }

    public void setTabaco(String tabaco) {
        this.tabaco.set(tabaco);
    }

    public String getTabaco_2() {
        return tabaco_2.get();
    }

    public SimpleStringProperty tabaco_2Property() {
        return tabaco_2;
    }

    public void setTabaco_2(String tabaco_2) {
        this.tabaco_2.set(tabaco_2);
    }

    public String getTabaco_3() {
        return tabaco_3.get();
    }

    public SimpleStringProperty tabaco_3Property() {
        return tabaco_3;
    }

    public void setTabaco_3(String tabaco_3) {
        this.tabaco_3.set(tabaco_3);
    }

    public String getTabaco_4() {
        return tabaco_4.get();
    }

    public SimpleStringProperty tabaco_4Property() {
        return tabaco_4;
    }

    public void setTabaco_4(String tabaco_4) {
        this.tabaco_4.set(tabaco_4);
    }

    public String getTabaco_5() {
        return tabaco_5.get();
    }

    public SimpleStringProperty tabaco_5Property() {
        return tabaco_5;
    }

    public void setTabaco_5(String tabaco_5) {
        this.tabaco_5.set(tabaco_5);
    }

}
