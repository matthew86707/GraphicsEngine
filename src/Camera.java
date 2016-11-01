import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;


public class Camera {
	
	private static float pitch = 0, yaw = 0, roll = 0;
	private static float PosX = 0, PosY = 0, PosZ = 0;
	public static Matrix4f viewMatrix = MathStuff.createTransformationMatrix(new Vector3f(PosX, PosY, PosZ), pitch, roll, yaw, 1f);
	
	public Camera(){
		
	}
	
	public static void move(){
		if(Input.isKeyDown(GLFW.GLFW_KEY_D)){
			PosX += 0.01f;
		}
		if(Input.isKeyDown(GLFW.GLFW_KEY_A)){
			PosX -= 0.01f;
		}
		if(Input.isKeyDown(GLFW.GLFW_KEY_W)){
			
			PosZ -= 0.01f;
		}
		if(Input.isKeyDown(GLFW.GLFW_KEY_S)){
			PosZ += 0.01f;
		}
		
		pitch += MouseInput.deltaY * 0.1;
		yaw += MouseInput.deltaX * 0.1;
		MouseInput.resetDeltas();
		
		updateMatrix();
	}
	
	public static void updateMatrix(){
		viewMatrix = MathStuff.createViewMatrix(new Vector3f(PosX, PosY, PosZ), pitch, roll, yaw);
	}

}
