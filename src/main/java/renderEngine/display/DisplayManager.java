package renderEngine.display;

import org.lwjgl.glfw.GLFWVidMode;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by sebi on 018 18/12/2016.
 */
public class DisplayManager {
    public static final int WIDTH = 1280, HEIGHT = 720;
    private static long window;
    private static GLFWKeyCallback keyCallback;
    private static final Map<Integer,List<KeyAction>> keymap = new HashMap<>();
    public static void createDisplay() throws Exception {
        GLFWErrorCallback.createPrint(System.err).set();


        if(!glfwInit())
            throw new Exception("Failed to init Display");
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in our rendering loop
        });
        GLFWVidMode vidmode = glfwGetVideoMode(
                glfwGetPrimaryMonitor());
        glfwSetWindowPos(
                window,
                (vidmode.width() - WIDTH) / 2,
                (vidmode.height() - HEIGHT) / 2
        );
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST|GL11.GL_DEPTH_BUFFER_BIT);
        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glfwSetKeyCallback(window, keyCallback = new KeyboardHandler());
    }
    public static void register(int key, KeyAction invoked)
    {
        if(!keymap.containsKey(key))
            keymap.put(key,new LinkedList<KeyAction>());
        keymap.get(key).add(invoked);
    }
    public static boolean isCloseRequested()
    {
        return glfwWindowShouldClose(window);
    }
    public static void clear()
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    public static void updateDisplay()
    {
        glfwSwapBuffers(window);
        glfwPollEvents();
        updateKeys();
    }
    public static void closeDisplay()
    {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    private static void updateKeys()
    {
        for(int key:keymap.keySet())
        {
            if(KeyboardHandler.isKeyDown(key))
                keymap.get(key).forEach(f -> f.invoke(key));
        }
    }
}
