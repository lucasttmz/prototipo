package prototipo;

import javax.swing.JOptionPane;

public class Prototipo
{

    public static void main(String[] args)
    {
        int ANTIGO = 0;
        int MVP = 1;
        
        String[] options = new String[]
        {
            "Drag/Drop", "Swap"
        };

        int option = JOptionPane.showOptionDialog(
                null,
                "Escolha o tipo",
                "Aviso",
                JOptionPane.NO_OPTION, 
                JOptionPane.WARNING_MESSAGE,
                null, options, options[1]);

        if (option == ANTIGO)
        {
            ViewDragDrop v = new ViewDragDrop();
            v.setVisible(true);
        } 
        else if (option == MVP)
        {
            Jogo      m = new Jogo();
            ViewSwap  v = new ViewSwap();
            Presenter p = new Presenter(v, m);
        }
    }
}
