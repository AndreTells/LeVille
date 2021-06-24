package jogo.view.ui;

import com.jogamp.opengl.GL2;

import jogo.exceptions.DimensoesInvalidas;
import jogo.exceptions.IdInvalido;
import jogo.exceptions.PosicaoInvalida;
import jogo.view.mouse.IMouse;

public abstract class GLElementComponent{
	protected static IMouse mouse;
	protected float pos_x;
	protected float pos_y;

	protected String id;
	protected GLElementComposite parent;

	protected float width;
	protected float height;
	
	GLElementComponent(String id,GLElementComposite parent,float pos_x,float pos_y,float width,float height){
		this.setPosition(pos_x, pos_y);
		this.setDims(width, height);
		
		this.parent = parent;
		this.setId(id);
	}
	

	public static void setMouse(IMouse mouse_i) {
		mouse = mouse_i;
	}

	abstract public void draw(GL2 gl);

	private void setId(String id) throws IdInvalido{
        if(id == null){
            throw new IdInvalido("the id is null");
        }
        
		this.id = id;
	}
	
	public void setPosition(float pos_x,float pos_y) throws PosicaoInvalida{
        if(pos_x<-1 || pos_x>1){
            throw new PosicaoInvalida("the x passed is outside the screen");
        }
        if(pos_y<-1 || pos_y>1){
            throw new PosicaoInvalida("the y passed is outside the screen");
        }
        
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	public void setDims(float width,float height) throws DimensoesInvalidas{
        if(width < 0){
            throw new DimensoesInvalidas("the width passed has a negative value");
        }
        if(height < 0){
            throw new DimensoesInvalidas("the height passed has a negative value");
        }
        
		this.width = width;
		this.height = height;
	}

	public String getID() {
		return this.id;
	}

	abstract public void dispose();

	//returns a vector of length 2 {pos_x,pos_y}
	public float[] getPos() {
		return new float[] {pos_x,pos_y};
	}

	//returns a vector of length 2 {width,height}
	public float[] getDims() {
		return new float [] {width,height};
	}

	public GLElementComposite getParent() {
		return parent;
	}

	public GLElementComposite getTop() {
		GLElementComposite top = this.parent;
		while(top.getParent() != null) {
			top=top.getParent();
		}
		
		return top;
	}

	public float[] getAbsolutePos() {
		float[] element_pos = this.getPos();
		GLElementComposite parent = this.getParent();
		if(parent!=null) {
			float[] parent_pos = parent.getAbsolutePos();
			element_pos[0] += parent_pos[0];
			element_pos[1] += parent_pos[1];
			return element_pos;
		}
		else {
			return element_pos;
		}
	}
}