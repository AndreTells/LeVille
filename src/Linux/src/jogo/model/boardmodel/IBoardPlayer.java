package jogo.model.boardmodel;

public interface IBoardPlayer {
	public boolean addComponent(String comp_name,int x,int y,int player_production);
	
	public void claim(int x,int y);
	
}
