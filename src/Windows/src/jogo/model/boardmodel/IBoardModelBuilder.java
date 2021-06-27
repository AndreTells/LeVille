package jogo.model.boardmodel;


public interface IBoardModelBuilder {
	
	public IBoardController getBoardController();
	
	public IBoardEvent getBoardEvent();
	
	public IBoardPlayer getBoardPlayer();
}
