package jogo.model.boardmodel;

public interface IBoardModelBuilder {
	public Player getPlayer();
	
	public IBoardController getBoardController();
	
	public IBoardEvent getBoardEvent();
}
