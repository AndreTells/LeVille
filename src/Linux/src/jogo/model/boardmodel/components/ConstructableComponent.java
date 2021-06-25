package jogo.model.boardmodel.components;

import jogo.model.boardmodel.Player;

public abstract class ConstructableComponent extends Component{
	
	public boolean construct(Player player) {
		if(this.cost > player.getProductionValue()) {
			System.out.println(this.cost+" "+  player.getProductionValue());
			return false;
		}
		player.useProduction(cost);
		return true;
	}
	
}

