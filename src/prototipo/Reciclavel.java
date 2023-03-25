package prototipo;

import java.awt.Color;
import java.util.List;
import java.util.Random;

public class Reciclavel 
{
    private static final List<Color> CORES = List.of(Color.YELLOW, Color.BLUE, Color.RED, Color.GREEN);
    private static List<Integer> posPossiveis = List.of(20, 120, 220, 320);
    
    public int x;
    public int y;
    public Color cor;
    
    private Reciclavel(int x, int y, Color cor)
    {
        this.x = x;
        this.y = y;
        this.cor = cor;
    }
    
    public void setPosicoesPossiveis(List<Integer> posicoes)
    {
        Reciclavel.posPossiveis = posicoes;
    }
    
    public static Reciclavel getReciclavel(Tipo tipo)
    {
        int posAleatoria = posPossiveis.get(new Random().nextInt(posPossiveis.size()));
        
        return new Reciclavel(posAleatoria, 0, CORES.get(tipo.id));
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
