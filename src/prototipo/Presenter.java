package prototipo;

import java.awt.Color;
import java.util.List;

public class Presenter implements Observador
{
    private final ViewSwap view;
    private final Jogo jogo;
    
    public Presenter(ViewSwap view, Jogo jogo)
    {
        this.view = view;
        this.jogo = jogo;
        
        setObservavel();
        view.setVisible(true);
    }
    
    public void adicionarLixo()
    {
        jogo.adicionarEntidade();
        view.desenharReciclaveis(jogo.getEntidades());
    }
    
    public void iniciarLoop()
    {
        jogo.iniciarLoop();
    }
    
    public void iniciarPartida()
    {
        jogo.iniciarPartida();
        
    }
    
    private void moverLixo(List<Desenhavel> reciclaveis)
    {
        view.desenharReciclaveis(reciclaveis);
    }
    
    public void selecionarLixeira(int id)
    {
        System.out.println("DEBUG:   Lixeira ID: "+ id +" selecionada.");
        
        List<Color> lixeiras = jogo.selecionarLixeira(id);
        if (lixeiras == null)
        {
            view.selecionarLixeira(id);
        }
        else
        {
            view.deselecionarLixeira();
            view.desenharLixeiras(lixeiras);
        }
    }

    @Override
    public void atualizar()
    {
        // Receberia tipo por parametro
        // Ex: 1 seria para atualizar lixos, 2 para pontuação, etc...
        moverLixo(jogo.getEntidades());
    }

    @Override
    public void setObservavel()
    {
        jogo.setObservador(this);
    }
}