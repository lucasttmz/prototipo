package prototipo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class ViewSwap extends JFrame
{
    // Visual
    private JMenuBar mnuBar;
    private JMenu mnuAleatorio;
    private JMenu mnuIniciar;
    private JMenu mnuJogo;
    private Canvas pnlCanvas;
    private JPanel pnlLixeiras;
    
    // Presenter
    private Presenter p;
    

    public ViewSwap()
    {
        super();
        setSize(400, 660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        iniciarComponentes();
    }
    
    public void setPresenter(Presenter p)
    {
        this.p = p;
    }

    private void iniciarComponentes()
    {
        // DEBUG
        System.out.println("LOGGING:");
        
        // Menu
        mnuBar = new JMenuBar();
        
        mnuAleatorio = new JMenu("Lixo Aleatorio");
        mnuAleatorio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                p.adicionarLixo();
            }
        });
        
        mnuIniciar = new JMenu("Iniciar Loop");
        mnuIniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                p.iniciarLoop();
            }
        });
        
        mnuJogo = new JMenu("Iniciar Partida");
        mnuJogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("oie");
                p.iniciarPartida();
            }
        });
        
        // Bindings
        mnuBar.add(mnuJogo);
        mnuBar.add(mnuIniciar);
        mnuBar.add(mnuAleatorio);
        setJMenuBar(mnuBar);
        
        // Container
        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new BorderLayout());
        pnlPrincipal.setSize(400, 600);
        
        // Canvas
        pnlCanvas = new Canvas();
        pnlCanvas.setBackground(Color.LIGHT_GRAY);
        pnlCanvas.setPreferredSize(new Dimension(400,500));
        
        this.add(pnlCanvas, BorderLayout.NORTH);
        
        pnlLixeiras = new JPanel();
        pnlLixeiras.setPreferredSize(new Dimension(400, 100));
        pnlLixeiras.setLayout(new GridLayout(1,4));
        pnlLixeiras.setBackground(Color.RED);
        
        // Lixeiras
        final List<Color> CORES = List.of(Color.YELLOW, Color.BLUE, Color.RED, Color.GREEN);
 
        desenharLixeiras(CORES);
        
        // Mnemonics
        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode() - 49;
                if (keyCode >= 0 && keyCode < 4)
                {
                    p.selecionarLixeira(keyCode);
                }
                else if (e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    p.adicionarLixo();
                }
            }
        });

        this.add(pnlLixeiras, BorderLayout.SOUTH);
        
        // DEBUG
        System.out.println("DEBUG:   Frame: " + this.getBounds());
        System.out.println("DEBUG:   Canvas: " + pnlCanvas.getPreferredSize());
        System.out.println("DEBUG:   Lixeiras: " + pnlLixeiras.getPreferredSize());
    }
    
    public void selecionarLixeira(int idLixeira)
    {
        JPanel pnl = (JPanel) pnlLixeiras.getComponent(idLixeira);
        pnl.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
    }
    
    public void deselecionarLixeira()
    {
        for (Component c : pnlLixeiras.getComponents())
        {
            JPanel pnl = (JPanel) c;
            pnl.setBorder(null);
        }
    }
    
    public void desenharReciclaveis(List<Desenhavel> reciclaveis)
    {
        pnlCanvas.setReciclaveis(reciclaveis);
        pnlCanvas.repaint();
    }
    
    public void desenharLixeiras(List<Color> cores)
    {
        pnlLixeiras.removeAll();
        
        // DEBUG
        System.out.printf("DEBUG:   Lixeiras (rgb): ");
        
        for (Color cor : cores)
        {
            JPanel lixeira = new JPanel();
            lixeira.setBackground(cor);
            
            lixeira.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    p.selecionarLixeira(cores.lastIndexOf(cor));
                }
            });
            
            lixeira.setBorder(BorderFactory.createLineBorder(cor));
            pnlLixeiras.add(lixeira);
            
            // DEBUG
            System.out.printf("[%d, %d, %d] ", cor.getRed(), cor.getGreen(),cor.getBlue());
        }
        // DEBUG
        System.out.println();
    }
}

class Canvas extends JPanel
{
    private List<Desenhavel> reciclaveis;
    private Dimension tamanhoReciclavel = new Dimension(50, 50);

    public void setReciclaveis(List<Desenhavel> entidades)
    {
        this.reciclaveis = entidades;
    }

    public void setTamanhoReciclaveis(Dimension tamanho)
    {
        this.tamanhoReciclavel = tamanho;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        System.out.println("DEBUG:   Canvas redesenhado");
        super.paintComponent(g);
        
        if(reciclaveis != null)
        {
            for (Desenhavel reciclavel : reciclaveis)
            {
                g.setColor(reciclavel.getCor());
                g.fillOval(reciclavel.getX(),
                           reciclavel.getY(),
                        tamanhoReciclavel.width, 
                        tamanhoReciclavel.height
                );
            }
        }
    }
}


