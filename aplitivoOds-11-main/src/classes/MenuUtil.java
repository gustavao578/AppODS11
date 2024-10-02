package classes;

import javax.swing.JMenuItem;
//@author Ryan S. Santos

public class MenuUtil {

    public static void esconderMenuItem(JMenuItem menuItem, Usuario usuario) {
        if (usuario == null || !"gerente".equalsIgnoreCase(usuario.getTipo())) {
            menuItem.setVisible(false); // Esconde o item se não for gerente ou se o usuário não estiver logado
        } else {
            menuItem.setVisible(true); // Exibe o item se for gerente
        }
    }
}

