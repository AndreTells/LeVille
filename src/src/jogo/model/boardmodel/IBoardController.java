package jogo.model.boardmodel;

import java.util.List;

public interface IBoardController {
	public int getMapLength();
	
	public int getMapHeight();
	
	public String getCellHighestComponents(int x,int y);
	
	public int[] getModifier();
	
	public String getCellInfo(int x, int y);
	
	public List<String> getPossibleActions(int x,int y);
	
	public void addModifier(int[] modifier);

	public boolean isClaimed(int x,int y);
}
