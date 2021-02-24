package clases.DBUtilities;

import clases.Objetos_POJO.Clase_control_temperatura;
import clases.Objetos_POJO.Clase_pilones_nombre;
import clases.Objetos_POJO.Clase_remisiones;
import clases.Objetos_POJO.Clase_tabacos;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

public interface ActualizarTablas {
    JFXTreeTableView<Clase_pilones_nombre> traer_jt_pilones();
    JFXTreeTableView<Clase_tabacos> traer_jt_clase_tabaco();
    JFXTreeTableView<Clase_remisiones> traer_jt_remisiones();

    JFXTreeTableView<Clase_control_temperatura> traer_jt_control_temp();
    JFXTreeTableView<Clase_pilones_nombre> traer_jt_pilon_control_temp();

    JFXDrawer traer_menu_lateral();
    HamburgerBackArrowBasicTransition traer_transiscion();
}
