package Objetos_POJO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

public class Clase_entradas_pilones extends RecursiveTreeObject<Clase_entradas_pilones> {

    private final SimpleStringProperty id_entrada_pilones;
    private final SimpleStringProperty nombre_tabaco_entradas_pilones;
    private final SimpleStringProperty numero_pilon_entradas_pilones;
    private final SimpleStringProperty fecha_entradas_pilones;
    private final SimpleStringProperty tiempo_adelanto_entradas_pilones;
    private final SimpleStringProperty fecha_estima_salida_entradas_pilones;
    private final SimpleStringProperty cantidad_libras_entradas_pilones;

    public Clase_entradas_pilones(String id_entrada_pilones, String nombre_tabaco_entradas_pilones,
                                  String numero_pilon_entradas_pilones, String fecha_entradas_pilones,
                                  String tiempo_adelanto_entradas_pilones, String fecha_estima_salida_entradas_pilones,
                                  String cantidad_libras_entradas_pilones) {
        this.id_entrada_pilones = new SimpleStringProperty(id_entrada_pilones);
        this.nombre_tabaco_entradas_pilones = new SimpleStringProperty(nombre_tabaco_entradas_pilones);
        this.numero_pilon_entradas_pilones = new SimpleStringProperty(numero_pilon_entradas_pilones);
        this.fecha_entradas_pilones = new SimpleStringProperty(fecha_entradas_pilones);
        this.tiempo_adelanto_entradas_pilones = new SimpleStringProperty(tiempo_adelanto_entradas_pilones);
        this.fecha_estima_salida_entradas_pilones = new SimpleStringProperty(fecha_estima_salida_entradas_pilones);
        this.cantidad_libras_entradas_pilones = new SimpleStringProperty(cantidad_libras_entradas_pilones);
    }

    public String getId_entrada_pilones() {
        return id_entrada_pilones.get();
    }

    public SimpleStringProperty id_entrada_pilonesProperty() {
        return id_entrada_pilones;
    }

    public void setId_entrada_pilones(String id_entrada_pilones) {
        this.id_entrada_pilones.set(id_entrada_pilones);
    }

    public String getNombre_tabaco_entradas_pilones() {
        return nombre_tabaco_entradas_pilones.get();
    }

    public SimpleStringProperty nombre_tabaco_entradas_pilonesProperty() {
        return nombre_tabaco_entradas_pilones;
    }

    public void setNombre_tabaco_entradas_pilones(String nombre_tabaco_entradas_pilones) {
        this.nombre_tabaco_entradas_pilones.set(nombre_tabaco_entradas_pilones);
    }

    public String getNumero_pilon_entradas_pilones() {
        return numero_pilon_entradas_pilones.get();
    }

    public SimpleStringProperty numero_pilon_entradas_pilonesProperty() {
        return numero_pilon_entradas_pilones;
    }

    public void setNumero_pilon_entradas_pilones(String numero_pilon_entradas_pilones) {
        this.numero_pilon_entradas_pilones.set(numero_pilon_entradas_pilones);
    }

    public String getFecha_entradas_pilones() {
        return fecha_entradas_pilones.get();
    }

    public SimpleStringProperty fecha_entradas_pilonesProperty() {
        return fecha_entradas_pilones;
    }

    public void setFecha_entradas_pilones(String fecha_entradas_pilones) {
        this.fecha_entradas_pilones.set(fecha_entradas_pilones);
    }

    public String getTiempo_adelanto_entradas_pilones() {
        return tiempo_adelanto_entradas_pilones.get();
    }

    public SimpleStringProperty tiempo_adelanto_entradas_pilonesProperty() {
        return tiempo_adelanto_entradas_pilones;
    }

    public void setTiempo_adelanto_entradas_pilones(String tiempo_adelanto_entradas_pilones) {
        this.tiempo_adelanto_entradas_pilones.set(tiempo_adelanto_entradas_pilones);
    }

    public String getFecha_estima_salida_entradas_pilones() {
        return fecha_estima_salida_entradas_pilones.get();
    }

    public SimpleStringProperty fecha_estima_salida_entradas_pilonesProperty() {
        return fecha_estima_salida_entradas_pilones;
    }

    public void setFecha_estima_salida_entradas_pilones(String fecha_estima_salida_entradas_pilones) {
        this.fecha_estima_salida_entradas_pilones.set(fecha_estima_salida_entradas_pilones);
    }

    public String getCantidad_libras_entradas_pilones() {
        return cantidad_libras_entradas_pilones.get();
    }

    public SimpleStringProperty cantidad_libras_entradas_pilonesProperty() {
        return cantidad_libras_entradas_pilones;
    }

    public void setCantidad_libras_entradas_pilones(String cantidad_libras_entradas_pilones) {
        this.cantidad_libras_entradas_pilones.set(cantidad_libras_entradas_pilones);
    }
}
