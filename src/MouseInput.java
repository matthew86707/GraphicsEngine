import org.lwjgl.glfw.GLFWCursorPosCallback;


public class MouseInput extends GLFWCursorPosCallback{
	
	public static double deltaX = 0, deltaY = 0;
	public static double lastX = 0, lastY = 0;
	public static boolean first = false;;

	@Override
	public void invoke(long arg0, double arg1, double arg2) {
		// TODO Auto-generated method stub
		if(first){
			lastX = arg1;
			lastY = arg2;
			first = false;
			return;
		}else{
			deltaX += arg1 - lastX;
			deltaY += arg2 - lastY;
			lastX = arg1;
			lastY = arg2;
		}
	}
	
	public static void resetDeltas(){
		deltaX = 0;
		deltaY = 0;
	}

}
