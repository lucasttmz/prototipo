package prototipo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class ViewSwap extends JFrame
{
    // Visual
    private JMenuBar mnuBar;
    private JMenu mnuAleatorio;
    private JMenu mnuTodos;
    private JPanel pnlCanvas;
    private JPanel pnlLixeiras;
    private JPanel pnlPrimeiraLixeira;
    private JPanel pnlSegundaaLixeira;
    private JPanel pnlTerceiraLixeira;
    private JPanel pnlQuartaLixeira;
    
    // Presenter
    private Presenter p;
    

    public ViewSwap()
    {
        super();
        setSize(400, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        iniciarComponentes();
    }
    
    public void setPresenter(Presenter p)
    {
        this.p = p;
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
        pnlCanvas = new Canvas();
        pnlLixeiras = new JPanel();
        pnlLixeiras.setLayout(new GridLayout(1,4));

        pnlCanvas.setSize(400, 600);
        pnlCanvas.setMinimumSize(new Dimension(400,600));
        pnlCanvas.setBackground(Color.LIGHT_GRAY);

        pnlLixeiras.setSize(400, 100);
        pnlLixeiras.setBackground(Color.RED);
        
        // Lixeiras
        final List<Color> CORES = List.of(Color.YELLOW, Color.BLUE, Color.RED, Color.GREEN);
 
        for (Color cor : CORES)
        {
            JPanel lixeira = new JPanel();
            lixeira.setBackground(cor);
            
            lixeira.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    p.selecionarLixeira(CORES.lastIndexOf(cor));
                }
            });
            
            pnlLixeiras.add(lixeira);
        }
        
        // Mnemonics
        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode() - 49;
                if (keyCode >= 0 && keyCode < 4)
                {
                    p.selecionarLixeira(keyCode);
                }
            }
        });

        // Bindings
        add(pnlCanvas);
        add(pnlLixeiras);
        
        // DEBUG
        System.out.println("Frame: " + this.getSize());
        System.out.println("Canvas: " + pnlCanvas.getSize());
        System.out.println("Lixeiras: " + pnlLixeiras.getSize());
    }
    
    public void moverLixeira()
    {
        
    }
    
//    public void desenharLixeiras(List<Color> cores)
//    {
//        pnlLixeiras.removeAll();
// 
//        for (Color cor : cores)
//        {
//            JPanel lixeira = new JPanel();
//            lixeira.setBackground(cor);
//            
//            lixeira.addMouseListener(new MouseAdapter(){
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    p.selecionarLixeira(cores.lastIndexOf(cor));
//                }
//            });
//            
//            pnlLixeiras.add(lixeira);
//        }
//    }
    
    private void setListener(JPanel pnl)
    {
        
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


