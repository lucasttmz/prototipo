package prototipo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.text.Position;

public class Jogo 
{
    private Optional<Integer> idLixeiraSelecionada;
    private Observador observador;
    private final List<Color> lixeiras;
    private final List<Desenhavel> entidades;
    private boolean iniciado = false;
    private boolean fimDeJogo = false;
    
    public Jogo()
    {
        idLixeiraSelecionada = Optional.empty();
        entidades = new ArrayList<>();
        lixeiras = new ArrayList<>(List.of(Color.YELLOW, Color.BLUE, Color.RED, Color.GREEN));
    }
    
    public void setObservador(Observador observador)
    {
        this.observador = observador;
    }
    
    public List<Color> selecionarLixeira( int idLixeira)
    {
        if (idLixeiraSelecionada.isEmpty())
        {
            idLixeiraSelecionada = Optional.of(idLixeira);
            return null;
        }
        return moverLixeira(idLixeiraSelecionada.get(), idLixeira);
    }
    
    private List<Color> moverLixeira(int origem, int destino)
    {
        Color temp = lixeiras.get(origem);
        lixeiras.set(origem, lixeiras.get(destino));
        lixeiras.set(destino, temp);
        
        idLixeiraSelecionada = Optional.empty();
        
        return lixeiras;
    }
    
    public void adicionarEntidade()
    {
        System.out.println("DEBUG:   Lixo adicionado");
        entidades.add(Reciclavel.getReciclavelAleatorio());
        debugEntidades();
        
    }
    
    public List<Desenhavel> getEntidades()
    {
        return entidades;
    }
    
    
    public void moverLixos(int movimento)
    {
        // Move os lixos
        for (Desenhavel entidade : entidades)
        {
            Reciclavel reciclavel = (Reciclavel) entidade;
            reciclavel.mover(0, movimento);
        }
        
        // Usaria um observer na classe Estado para notificar as mudanças
        // incluindo a mudança da pontuação
        observador.atualizar();
    }
    
    
    public void iniciarPartida()
    {
        if (!iniciado)
        {
            System.out.println("DEBUG:   Partida iniciada!");
            iniciarLoop();
            
            new Thread(() -> {
                int delay = 3000;
                while(!fimDeJogo)
                {
                    adicionarEntidade();
                    try
                    {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex)
                    {
                        System.out.println("DEBUG:  Exception ocorreu: " + ex);
                    }
                    if (delay > 500)
                    {
                        if(delay < 1000)
                        {
                            delay -= 10;
                        }
                        else
                        {
                            delay -= 50;
                        }
                    }
                }
            }).start();
        }
        
    }
    
    public void iniciarLoop()
    {
        if (!iniciado)
        {
            System.out.println("DEBUG:   Loop principal iniciado!");
            iniciado = true;
            
            new Thread(() -> {

                while(!fimDeJogo)
                {
                    for(int i = entidades.size() - 1; i >=0; i--)
                    {
                        moverLixos(20);
                        if(entidades.get(i).getY() >= 450)
                        {
                            entidades.remove(i);
                        }
                    }

                    try
                    {
                        Thread.sleep(500);
                    } catch (InterruptedException ex)
                    {
                        System.out.println("DEBUG:  Exception ocorreu: " + ex);
                    }
                }

            }).start();
        }
        else
        {
            System.out.println("DEBUG:   Loop principal já está iniciado.");
        }
    }
    
    private void debugEntidades()
    {
        System.out.printf("DEBUG:   Lixos atuais: [");
        for (Desenhavel entidade : entidades)
        {
            System.out.printf("%s ", entidade);
        }
        System.out.println("]");
    }
}