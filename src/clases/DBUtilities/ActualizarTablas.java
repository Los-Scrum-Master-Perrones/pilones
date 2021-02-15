package clases.DBUtilities;

import clases.Objetos_POJO.Clase_pilones;
import clases.Objetos_POJO.Clase_tabacos;
import com.jfoenix.controls.JFXTreeTableView;

public interface ActualizarTablas {
    JFXTreeTableView<Clase_pilones> traer_jt_pilones();
    JFXTreeTableView<Clase_tabacos> traer_jt_clase_tabaco();
}
