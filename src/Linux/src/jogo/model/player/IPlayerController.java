package jogo.model.player;

import jogo.model.boardmodel.IBoardPlayer;

public interface IPlayerController {
	
	public void connect(IBoardPlayer board);
	
	public int getPopulationValue();
	
	public int getPopulationLimitValue();
	
	public int getProductionValue();
	
	public int getFoodValue();
	
	public int getFoodTargetValue();
	
	public void addModifier(int modifier[]);
	
	public void claim(int x,int y);

	public  void constructComponent(String comp_name,int x, int y);
}
