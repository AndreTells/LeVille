package jogo.model.boardmodel.components;

public abstract class ConstructableComponent extends Component{
	
	public boolean construct(int production_value) {
		if(this.cost > production_value) {
			return false;
		}
		return true;
	}
	
}

