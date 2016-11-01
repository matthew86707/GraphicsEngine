import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;



public class Input extends GLFWKeyCallback{
	
	public static boolean[] keys = new boolean[65536];

	@Override
	public void invoke(long arg0, int key, int arg2, int action, int arg4) {
		// TODO Auto-generated method stub
		 keys[key] = action != GLFW_RELEASE;
	}

	
	public static boolean isKeyDown(int keycode) {
        return keys[keycode];
    }

	@Override
	public void close() {
		try{
			
		}catch(Exception e){
			
		}
		
	}

}
