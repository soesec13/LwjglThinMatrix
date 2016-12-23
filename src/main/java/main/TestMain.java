package main;

import renderEngine.display.DisplayManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;

/**
 * Created by sebi on 019 19/12/2016.
 */
public class TestMain {
    public static void main(String[] args) {
        try {
            DisplayManager.createDisplay();
        } catch (Exception e) {
            e.printStackTrace();
        }

        float[] data = { -0.5f, 0.5f, 0f, -0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, 0.5f, 0f, -0.5f, 0.5f, 0f
        };
        //RawModel renderEngine.model = loader.loadToVAO(vertices);
        //Gen VAO
        //ShaderManager.createShaders();
        int vaoID = GL30.glGenVertexArrays();
        int attribNumber = 0;
        GL30.glBindVertexArray(vaoID);
        //Gen VBO
        int vboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        //Create Data
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        //Insert Data
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER,buffer,GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attribNumber,3, GL11.GL_FLOAT,false,0,0);
        //Unbind Buffer and Vertex
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);
        GL30.glBindVertexArray(0);
        while(!DisplayManager.isCloseRequested())
        {
            //renderer.render(renderEngine.model);
            GL30.glBindVertexArray(vaoID);
            GL20.glEnableVertexAttribArray(0);
            GL11.glDrawArrays(GL11.GL_TRIANGLES,0,data.length);
            GL20.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(0);
            DisplayManager.updateDisplay();
        }
        //loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
