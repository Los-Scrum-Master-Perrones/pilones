package DBUtilitie;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

public interface RegistroCombobox {
     JFXTextField cargar_datos_tabaco ();
    JFXTextField cargar_datos_pilon ();
    JFXTextField cargar_datos_entrada_tabaco ();
    JFXTextField cargar_datos_entrada_pilon ();
    JFXComboBox cargar_datos_tab_control_pilones ();
    JFXComboBox cargar_datos_pilones_control_pilones ();


}
