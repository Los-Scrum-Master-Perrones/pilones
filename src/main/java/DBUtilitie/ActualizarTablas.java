package DBUtilitie;

import Objetos_POJO.*;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

public interface ActualizarTablas {
    JFXTreeTableView<Clase_pilones_nombre> traer_jt_pilones();
    JFXTreeTableView<Clase_tabacos> traer_jt_clase_tabaco();
    JFXTreeTableView<Clase_remisiones> traer_jt_remisiones();

    JFXTreeTableView<Clase_control_temperatura> traer_jt_control_temp();
    JFXTreeTableView<Clase_pilones_nombre> traer_jt_pilon_control_temp();
    JFXTreeTableView<Clase_en_sa_proceso_pilon> traer_jt_en_sa_proceso_pilon();
    JFXTreeTableView<Clase_en_sa_proceso_pilon> traer_jt_en_sa_pilon();
    JFXTreeTableView<Clase_entradas_pilones> traer_jt_entra_pilones();


    JFXDrawer traer_menu_lateral();
    HamburgerBackArrowBasicTransition traer_transiscion();
    JFXListView<Clase_pilones_nombre> lista_temperatura();
}
