package prototipo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ViewSwap extends JFrame
{
    // Visual
    private JMenuBar mnuBar;
    private JMenu mnuAleatorio;
    private JMenu mnuTodos;
    private JPanel pnlCanvas;
    private JPanel pnlLixeiras;
    
    // Click mouse
    private int posClickX;
    private int posClickY;

    public ViewSwap()
    {
        super();
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iniciarComponentes();
    }

    private void iniciarComponentes()
    {
        // Menu
        mnuBar = new JMenuBar();
        mnuAleatorio = new JMenu("Novo");
        mnuTodos = new JMenu("Aleatório");
        
        // Eventos
        
        // Bindings
        mnuBar.add(mnuAleatorio);
        mnuBar.add(mnuTodos);
        setJMenuBar(mnuBar);
        
        // Paineis
        pnlCanvas = new JPanel();
        pnlLixeiras = new JPanel();

        pnlCanvas.setSize(400, 500);
        pnlCanvas.setBackground(Color.LIGHT_GRAY);

        pnlLixeiras.setSize(400, 200);
        pnlLixeiras.setBackground(Color.WHITE);

        // Bindings
        add(pnlCanvas);
        add(pnlLixeiras);
        
        // DEBUG
        System.out.println("Frame: " + this.getSize());
        System.out.println("Canvas: " + pnlCanvas.getSize());
        System.out.println("Lixeiras: " + pnlLixeiras.getSize());
    }
    
}

class Canvas extends JPanel
{
    private List<Reciclavel> reciclaveis;
    private Dimension tamanhoReciclavel = new Dimension(50, 50);

    public void setReciclaveis(List<Reciclavel> entidades)
    {
        this.reciclaveis = entidades;
    }

    public void setTamanhoReciclaveis(Dimension tamanho)
    {
        this.tamanhoReciclavel = tamanho;
    }
    
    protected void paintComponent(Graphics g, int x)
    {
        super.paintComponent(g);
        
        for (Reciclavel reciclavel : reciclaveis)
        {
            g.drawRect(reciclavel.x,
                       reciclavel.y,
                    tamanhoReciclavel.width, 
                    tamanhoReciclavel.height
            );
        }
    }
}


