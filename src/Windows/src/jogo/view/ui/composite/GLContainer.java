package jogo.view.ui.composite;

import jogo.view.ui.GLElementComposite;
import jogo.view.ui.IContainer;

public class GLContainer extends GLElementComposite implements IContainer{
	
	public GLContainer(String id, float width,float height) {
		super(id,null,0,0,width,height);
	}

}
