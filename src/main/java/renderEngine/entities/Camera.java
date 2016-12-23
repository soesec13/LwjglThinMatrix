package renderEngine.entities;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import renderEngine.display.DisplayManager;

/**
 * Created by sebi on 022 22/12/2016.
 */
public class Camera {
    private Vector3f position = new Vector3f(0,0,0);
    private float pitch,yaw,roll;

    public Camera(){
        DisplayManager.register(GLFW.GLFW_KEY_W,this::move);
        DisplayManager.register(GLFW.GLFW_KEY_D,this::move);
        DisplayManager.register(GLFW.GLFW_KEY_S,this::move);
        DisplayManager.register(GLFW.GLFW_KEY_A,this::move);
        DisplayManager.register(GLFW.GLFW_KEY_X,this::move);
        DisplayManager.register(GLFW.GLFW_KEY_Z,this::move);
    }

    public void move(int key){
        //System.out.println("Move "+key);
        switch(key)
        {
            case GLFW.GLFW_KEY_X:
                position.z -= 0.02f;
                break;
            case GLFW.GLFW_KEY_D:
                position.x += 0.02f;
                break;
            case GLFW.GLFW_KEY_A:
                position.x -= 0.02f;
                break;
            case GLFW.GLFW_KEY_Z:
                position.z += 0.02f;
                break;
            case GLFW.GLFW_KEY_W:
                position.y += 0.02f;
                break;
            case GLFW.GLFW_KEY_S:
                position.y -= 0.02f;
                break;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
