package prototipo;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Prototipo
{
    private static final int ARRASTAR = 0;
    private static final int TROCAR = 1;
    
    public Prototipo()
    {
        String[] options = new String[]
        {
            "Arrastar", "Trocar"
        };

        int option = JOptionPane.showOptionDialog(
                null,
                "Versão",
                "Prototipo",
                JOptionPane.NO_OPTION, 
                JOptionPane.WARNING_MESSAGE,
                null, options, options[1]);
        
        if (option == ARRASTAR)
        {
            ViewDragDrop view = new ViewDragDrop();
            view.setVisible(true);
        } 
        else if (option == TROCAR)
        {
            Jogo      model     = new Jogo();
            ViewSwap  view      = new ViewSwap();
            Presenter presenter = new Presenter(view, model);
            view.setPresenter(presenter);
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Prototipo::new);
    }
}
