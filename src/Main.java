import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;


public class Main {
	
	public static final int WIDTH = 1280, HEIGHT = 720;
	 private GLFWErrorCallback errorCallback;
	public Model testModel;
	
	public Main(){
		Model.createProjectionMatrix();
		glfwInit();
		GLFWErrorCallback.createPrint(System.err).set();

		long windowID = glfwCreateWindow(WIDTH, HEIGHT, "Test", 0, 0);
		
		GLFWKeyCallback keyCallback = new Input();
		glfwSetKeyCallback(windowID, keyCallback);
		
		GLFWCursorPosCallback posCallback = new MouseInput();
		glfwSetCursorPosCallback(windowID, posCallback);
		
		glfwShowWindow(windowID);
		
		glfwMakeContextCurrent(windowID);
		GL.createCapabilities();
		
		GL11.glEnable(GL_DEPTH_TEST);
		
		glClearColor(0.1f, 0.25f, 0.25f, 1f);
		
		float[] norms = new float[]{
				 0.0f,  0.0f, -1.0f,
				 0.0f,  0.0f, -1.0f, 
				 0.0f,  0.0f, -1.0f,
				 0.0f,  0.0f, -1.0f,
				 0.0f,  0.0f, -1.0f,
				 0.0f,  0.0f, -1.0f,
				 
				 0.0f,  0.0f, 1.0f,
				 0.0f,  0.0f, 1.0f,
				 0.0f,  0.0f, 1.0f,
				 0.0f,  0.0f, 1.0f,
				 0.0f,  0.0f, 1.0f,
				 0.0f,  0.0f, 1.0f,
				 
				 -1.0f,  0.0f,  0.0f,
				 -1.0f,  0.0f,  0.0f,
				 -1.0f,  0.0f,  0.0f,
				 -1.0f,  0.0f,  0.0f,
				 -1.0f,  0.0f,  0.0f,
				 -1.0f,  0.0f,  0.0f,
				 
				 1.0f,  0.0f,  0.0f,
				 1.0f,  0.0f,  0.0f,
				 1.0f,  0.0f,  0.0f,
				 1.0f,  0.0f,  0.0f,
				 1.0f,  0.0f,  0.0f,
				 1.0f,  0.0f,  0.0f,
				 
				 0.0f, -1.0f,  0.0f,
				 0.0f, -1.0f,  0.0f,
				 0.0f, -1.0f,  0.0f,
				 0.0f, -1.0f,  0.0f,
				 0.0f, -1.0f,  0.0f,
				 0.0f, -1.0f,  0.0f,
				 
				 0.0f,  1.0f,  0.0f,
				 0.0f,  1.0f,  0.0f,
				 0.0f,  1.0f,  0.0f,
				 0.0f,  1.0f,  0.0f,
				 0.0f,  1.0f,  0.0f,
				 0.0f,  1.0f,  0.0f,
		};
		
//		float[] ver = new float[]{
//		
//		-0.5f, -0.5f, -0.5f, 
//	     0.5f, -0.5f, -0.5f,  
//	     0.5f,  0.5f, -0.5f,   
//	     0.5f,  0.5f, -0.5f,   
//	    -0.5f,  0.5f, -0.5f,   
//	    -0.5f, -0.5f, -0.5f,  
//
//	    -0.5f, -0.5f,  0.5f,  
//	     0.5f, -0.5f,  0.5f, 
//	     0.5f,  0.5f,  0.5f, 
//	     0.5f,  0.5f,  0.5f, 
//	    -0.5f,  0.5f,  0.5f, 
//	    -0.5f, -0.5f,  0.5f, 
//
//	    -0.5f,  0.5f,  0.5f, 
//	    -0.5f,  0.5f, -0.5f, 
//	    -0.5f, -0.5f, -0.5f, 
//	    -0.5f, -0.5f, -0.5f, 
//	    -0.5f, -0.5f,  0.5f,
//	    -0.5f,  0.5f,  0.5f, 
//
//	     0.5f,  0.5f,  0.5f,  
//	     0.5f,  0.5f, -0.5f,  
//	     0.5f, -0.5f, -0.5f,  
//	     0.5f, -0.5f, -0.5f,  
//	     0.5f, -0.5f,  0.5f,  
//	     0.5f,  0.5f,  0.5f,  
//
//	    -0.5f, -0.5f, -0.5f,  
//	     0.5f, -0.5f, -0.5f,  
//	     0.5f, -0.5f,  0.5f,  
//	     0.5f, -0.5f,  0.5f, 
//	    -0.5f, -0.5f,  0.5f,  
//	    -0.5f, -0.5f, -0.5f,  
//
//	    -0.5f,  0.5f, -0.5f,  
//	     0.5f,  0.5f, -0.5f, 
//	     0.5f,  0.5f,  0.5f,  
//	     0.5f,  0.5f,  0.5f,  
//	    -0.5f,  0.5f,  0.5f,  
//	    -0.5f,  0.5f, -0.5f,  };
		
		/*
		float[] ver = new float[]{
				-0.5f, 0.5f, 0,
				0.5f, 0.5f, 0,
				0.5f, -0.5f, 0
		};
		
		float[] norms = new float[]{
				-0.5f, 0.5f, 0,
				0.5f, 0.5f, 0,
				0.5f, -0.5f, 0
		};
		*/
		
		
		float[] ver = null;
		try {
			ver = OBJLoader.loadObj("models/text.obj");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		testModel = new Model(ver,  new ShaderProgram("shaders/vert.txt", "shaders/frag.txt").programId);
		
		glfwSetInputMode(windowID, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		
		//Main Loop
		while(glfwWindowShouldClose(windowID) != true){
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			//Other Events
			Camera.move();
			renderAll();
			//End Other Events
			glfwSwapBuffers(windowID);
		}
	}
	
	public void renderAll(){
		testModel.render();
	}

	public static void main(String[] args) {
		
		new Main();

	}

}
