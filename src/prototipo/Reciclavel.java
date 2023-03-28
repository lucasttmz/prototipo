package prototipo;

import java.awt.Color;
import java.util.List;
import java.util.Random;

public class Reciclavel implements Desenhavel
{
    private static final List<Color> CORES = List.of(Color.YELLOW, Color.BLUE, Color.RED, Color.GREEN);
    private static List<Integer> posPossiveis = List.of(20, 120, 220, 320);
    
    private int x;
    private int y;
    private final Color cor;
    
    private Reciclavel(int x, int y, Color cor)
    {
        this.x = x;
        this.y = y;
        this.cor = cor;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public Color getCor()
    {
        return cor;
    }
    
    public void mover(int x, int y)
    {
        this.x += x;
        this.y += y;
        // notify
    }
    
    public void setPosicoesPossiveis(List<Integer> posicoes)
    {
        Reciclavel.posPossiveis = posicoes;
    }
    
    public static Reciclavel getReciclavelAleatorio()
    {
        int rng = new Random().nextInt(4);
        
        return getReciclavel(Tipo.values()[rng]);
    }
    
    public static Reciclavel getReciclavel(Tipo tipo)
    {
        int posAleatoria = posPossiveis.get(new Random().nextInt(posPossiveis.size()));
        
        return new Reciclavel(posAleatoria, 0, CORES.get(tipo.id));
    }
    
    @Override
    public String toString()
    {
        return String.format("(x: %d, y:%d)", x, y);
    }
    
    enum Tipo
    {
        METAL(0), PAPEL(1), PLASTICO(2), VIDRO(3);
        
        public int id;
        
        Tipo(int id)
        {
            this.id = id;
        }
    }
    
}
