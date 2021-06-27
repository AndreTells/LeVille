package jogo.model.boardmodel;

public class BoardManager implements IBoardModelBuilder{
	private static BoardModel board;
	
	public IBoardPlayer getBoardPlayer() {
		if(board ==null) {
			board = new BoardModel();
		}
		return board;
	}
	
	public IBoardController getBoardController() {
		if(board ==null) {
			board = new BoardModel();
		}
		return board;
	}
	
	public IBoardEvent getBoardEvent() {
		if(board ==null) {
			board = new BoardModel();
		}
		return board;
	}
}
