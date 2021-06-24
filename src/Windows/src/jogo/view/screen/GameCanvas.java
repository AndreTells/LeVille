package jogo.view.screen;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.util.FPSAnimator;

import jogo.view.boardview3d.Game3DObjectManager;
import jogo.view.boardview3d.IBoard3DManager;
import jogo.view.ui.IContainer;
import jogo.view.ui.composite.GLContainer;

public class GameCanvas implements GLEventListener {  
	private GLU glu = new GLU();
	private GLCanvas gc;
	
	private IBoard3DManager board;
	private IContainer container_2d; 
	
	public GameCanvas(int frame_width, int frame_height){
		Game3DObjectManager.loadModels();
		
		this.setUpCanvas(frame_width, frame_height);
		
		//this.mouse = new GLMouse(gc);
		
	   //	this.setUpCamera();
	   	
	   	//3d space
	   //	setUpCellMatrix();
	   	
	   	//2d space
	 	//GLElementComponent.setMouse(mouse);
	   	
	   	this.container_2d = new GLContainer("container",frame_width,frame_height);
	   	
	 //  	this.ui = new UI(container_2d);
	   		   	
	//   	this.setUpPicker();
	   	//necessary for JOGL
	   	gc.addGLEventListener( this );
	   	
	}
	
	private void setUpCanvas(int width, int height) {
		final GLProfile gp = GLProfile.get( GLProfile.GL2 );  
	   	GLCapabilities cap = new GLCapabilities(gp );
	   	this.gc = new GLCanvas(cap);
	   	gc.setSize( width,height);
	   	  
	   	final FPSAnimator animator = new FPSAnimator(gc, 200,true);  
	   	animator.start();
	}
	
	public GLCanvas getCanvas() {
		return gc;
	} 
	
	public void display( GLAutoDrawable drawable ) {
		
	   final GL2 gl = drawable.getGL().getGL2();
	   gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
	   gl.glLoadIdentity();
	   gl.glClearColor(0.08f, 0.13f, 0.17f, 0.5f);
	   //draw 3d objects
	   board.positionCamera(gl, glu);
	   
	   board.drawBoard(gl);
	   
	   
	   //setting picker
	   Matrix4 i_view_matrix = getMatrix(GL2.GL_MODELVIEW_MATRIX,gl); 
	   Matrix4 i_projection_matrix = getMatrix(GL2.GL_PROJECTION_MATRIX,gl);
	   i_view_matrix.invert();
	   i_projection_matrix.invert();
	   
	   board.updatePicker(i_view_matrix, i_projection_matrix);
	   
	   //set up for 2d objects
	   gl.glMatrixMode(GL2.GL_PROJECTION);
	   gl.glPushMatrix();
	   gl.glLoadIdentity();
	   gl.glMatrixMode(GL2.GL_MODELVIEW);
	   gl.glLoadIdentity();
	   disable3D(gl);
	   gl.glClear(GL2.GL_DEPTH_BUFFER_BIT);

	   //draw 2d objects
	   
	   container_2d.draw(gl);

	   
	   // Making sure we can render 3d again
	   gl.glMatrixMode(GL2.GL_PROJECTION);
	   gl.glPopMatrix();
	   enable3D(gl);
	   gl.glMatrixMode(GL2.GL_MODELVIEW);
	   	
	   gl.glFlush();
   }  

	public void dispose( GLAutoDrawable drawable ) {}  

	private void enable3D(GL2 gl) {
		gl.glEnable(GL2.GL_CULL_FACE);//not loading stuff that cant be seen
		gl.glEnable(GL2.GL_LIGHTING);//lighting
		gl.glEnable(GL2.GL_LIGHT0);
	}
	
	private void disable3D(GL2 gl) {
		gl.glDisable(GL2.GL_CULL_FACE);//not loading stuff that cant be seen
		gl.glDisable(GL2.GL_LIGHTING);//lighting
		gl.glDisable(GL2.GL_LIGHT0);
	}
	
	public void init( GLAutoDrawable drawable ) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glShadeModel( GL2.GL_SMOOTH );
		gl.glClearColor( 0f, 0f, 0f, 0f );
		gl.glClearDepth( 1.0f );
		gl.glDepthFunc( GL2.GL_LEQUAL );//depth so 3D can be used
		gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
		gl.glCullFace(GL2.GL_BACK);gl.glEnable( GL2.GL_DEPTH_TEST );
		gl.glEnable(GL2.GL_FRAMEBUFFER_SRGB);//basic gamma correction
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc (GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		enable3D(gl);
	}
	
	private Matrix4 getMatrix(int code,GL2 gl) {
		float[] f_matrix = new float[16];
		gl.glGetFloatv( code, f_matrix,0);
		Matrix4 matrix = new Matrix4();
		matrix.loadIdentity();
		matrix.multMatrix(f_matrix);
		return matrix;
	}
	
	public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {  
		final GL2 gl = drawable.getGL().getGL2();  
		if( height <= 0 )
			height = 1;  
              
		float aspect_ratio = ( float ) width / ( float ) height;  
		gl.glViewport( 0, 0, width, height );  
		gl.glMatrixMode( GL2.GL_PROJECTION );  
		gl.glLoadIdentity();
		glu.gluPerspective( 45.0f, aspect_ratio, 1.0, 60.0 );  
		gl.glMatrixMode( GL2.GL_MODELVIEW );  
		gl.glLoadIdentity();
	   
   }

	//----------- setter methods
	public void set2DContainter(IContainer container) {
		this.container_2d = container;
		
	}
	
	public void set3DBoard(IBoard3DManager board) {
		this.board = board;
	}
	
}
