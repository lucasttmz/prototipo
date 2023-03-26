package prototipo;

public class Presenter {
    ViewSwap view;
    Jogo jogo;
    
    public Presenter(ViewSwap view, Jogo jogo)
    {
        this.view = view;
        view.setVisible(true);
    }
    
    private void moverLixo(int x, int y)
    {
        
    }
    
    public void selecionarLixeira(int id)
    {
        System.out.println(id);
    }
}