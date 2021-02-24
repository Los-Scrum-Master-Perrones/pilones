package clases.Objetos_POJO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

public class Clase_remisiones extends RecursiveTreeObject<Clase_remisiones> {
    private final SimpleStringProperty id_remision;
    private final SimpleStringProperty codigo_remision;
    private final SimpleStringProperty fecha_remision;
    private final SimpleStringProperty destino_remision;
    private final SimpleStringProperty origen_remision;
    private  String[] tabacos_descrip_remision;
    private  String[] total_descrip_remision;
    private final SimpleStringProperty total_remision;
    private SimpleStringProperty descripcion_remision;

    public String getDescripcion_remision() {
        return descripcion_remision.get();
    }

    public void setDescripcion_remision(String descripcion_remision) {
        this.descripcion_remision=new SimpleStringProperty(descripcion_remision);
    }

    public void descripcion(){
        String dtos="";
        int i = 0;
        for (String d : tabacos_descrip_remision){
            dtos = dtos + d+ "  "+total_descrip_remision[i]+"\n";
            i++;
        }
        System.out.println(dtos);
        setDescripcion_remision(dtos);
    }

    private long[] id_tabaco;

    public long[] getId() {
        return id_tabaco;
    }

    public void setId(long[] id) {
        this.id_tabaco = id;
    }

    public Clase_remisiones(String id_remision, String codigo_remision,
                            String fecha_remision, String destino_remision,
                            String origen_remision, String total_remision) {

        this.id_remision = new SimpleStringProperty(id_remision);
        this.codigo_remision = new SimpleStringProperty(codigo_remision);
        this.fecha_remision = new SimpleStringProperty(fecha_remision);
        this.destino_remision = new SimpleStringProperty(destino_remision);
        this.origen_remision = new SimpleStringProperty(origen_remision);
        this.total_remision = new SimpleStringProperty(total_remision);
    }

    public String getId_remision() {
        return id_remision.get();
    }

    public SimpleStringProperty id_remisionProperty() {
        return id_remision;
    }

    public void setId_remision(String id_remision) {
        this.id_remision.set(id_remision);
    }

    public String getCodigo_remision() {
        return codigo_remision.get();
    }

    public SimpleStringProperty codigo_remisionProperty() {
        return codigo_remision;
    }

    public void setCodigo_remision(String codigo_remision) {
        this.codigo_remision.set(codigo_remision);
    }

    public String getFecha_remision() {
        return fecha_remision.get();
    }

    public SimpleStringProperty fecha_remisionProperty() {
        return fecha_remision;
    }

    public void setFecha_remision(String fecha_remision) {
        this.fecha_remision.set(fecha_remision);
    }

    public String getDestino_remision() {
        return destino_remision.get();
    }

    public SimpleStringProperty destino_remisionProperty() {
        return destino_remision;
    }

    public void setDestino_remision(String destino_remision) {
        this.destino_remision.set(destino_remision);
    }

    public String getOrigen_remision() {
        return origen_remision.get();
    }

    public SimpleStringProperty origen_remisionProperty() {
        return origen_remision;
    }

    public void setOrigen_remision(String origen_remision) {
        this.origen_remision.set(origen_remision);
    }

    public String[] getTabacos_descrip_remision() {
        return tabacos_descrip_remision;
    }

    public void setTabacos_descrip_remision(String[] tabacos_descrip_remision) {
        this.tabacos_descrip_remision = tabacos_descrip_remision;
    }

    public String[] getTotal_descrip_remision() {
        return total_descrip_remision;
    }

    public void setTotal_descrip_remision(String[] total_descrip_remision) {
        this.total_descrip_remision = total_descrip_remision;
    }

    public String getTotal_remision() {
        return total_remision.get();
    }

    public SimpleStringProperty total_remisionProperty() {
        return total_remision;
    }

    public void setTotal_remision(String total_remision) {
        this.total_remision.set(total_remision);
    }
}
