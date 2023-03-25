package prototipo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewDragDrop extends JFrame
{
    private int posClickX;
    private int posClickY;
    
    private List<JPanel> entidades = new ArrayList<>();
    
    public ViewDragDrop()
    {
        super();
        setLayout(new BorderLayout());
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        iniciarComponentes();
    }
    
    private void iniciarComponentes()
    {
        JButton btnCair = new JButton("Cair");
        
        
        JPanel pnlCores = new JPanel();
        pnlCores.setSize(400, 300);
        pnlCores.setBackground(Color.LIGHT_GRAY);
        pnlCores.setLayout(null);
        
        
        List<Color> cores = List.of(Color.BLUE, Color.RED, Color.GREEN);
        int posAtual = 50;
        
        for (Color cor : cores)
        {
            JPanel pnl = new JPanel();
            
            pnl.setSize(75, 75);
            pnl.setBackground(cor);
            pnl.setLocation(posAtual, 50);
            
            pnl.addMouseMotionListener(new java.awt.event.MouseMotionAdapter(){
                @Override
                public void mouseDragged(java.awt.event.MouseEvent evt){
                    int X = evt.getX() + pnl.getX();
                    int Y = evt.getY() + pnl.getY();

                    pnl.setLocation(X - posClickX, Y - posClickY);
                }
            });
            pnl.addMouseListener(new java.awt.event.MouseAdapter()
            {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt)
                {
                    posClickX = evt.getX();
                    posClickY = evt.getY();
                }
            });
            
            posAtual += 100;
            entidades.add(pnl);
            pnlCores.add(pnl);
            
        }
        
        posAtual = 50;
        for (Color cor : cores)
        {
            JPanel pnl = new JPanel();
            
            pnl.setSize(75, 75);
            pnl.setBackground(cor);
            pnl.setLocation(posAtual, 500);
            
            pnl.addMouseMotionListener(new java.awt.event.MouseMotionAdapter(){
                @Override
                public void mouseDragged(java.awt.event.MouseEvent evt){
                    int X = evt.getX() + pnl.getX();
                    int Y = evt.getY() + pnl.getY();

                    pnl.setLocation(X - posClickX, Y - posClickY);
                }
            });
            pnl.addMouseListener(new java.awt.event.MouseAdapter()
            {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt)
                {
                    posClickX = evt.getX();
                    posClickY = evt.getY();
                }
            });
            
            
            
            posAtual += 75;
            pnlCores.add(pnl);
            
        }
        
        btnCair.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                new Thread(() -> {
                    int novaPos = 0;
                    while (novaPos <= 550)
                    {
                        for (JPanel entidade : entidades)
                        {
                            novaPos = entidade.getY() + 25;
                            entidade.setLocation(entidade.getX(), novaPos);
                        }
                        
                        try
                        {
                            Thread.sleep(500);
                        } catch (InterruptedException ex)
                        {
                            System.out.println(ex);
                        }
                    }
                }).start();
            }
        });
        
        this.add(btnCair, BorderLayout.NORTH);
        this.add(pnlCores, BorderLayout.CENTER);
    }
}
