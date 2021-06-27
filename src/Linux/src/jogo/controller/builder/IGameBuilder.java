package jogo.controller.builder;

public interface IGameBuilder
	extends IRBoardModelBuilder,IRScreenManager,
	IRUIManager,IRMouse,IRBoard3DManager,IREventManager,
	IRPlayerController,IRControllerBuilder
	{
	public void buildGame();
	
}
