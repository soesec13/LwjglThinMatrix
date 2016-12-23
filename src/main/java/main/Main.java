package main;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import renderEngine.display.DisplayManager;
import renderEngine.entities.Camera;
import renderEngine.entities.Entity;
import renderEngine.model.*;
import shaders.ShaderProgram;
import shaders.StaticShader;
import util.Maths;

import java.io.IOException;

/**
 * Created by sebi on 018 18/12/2016.
 */
public class Main {
    public static void main(String[] args) {
        try {
            DisplayManager.createDisplay();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
        Camera cam = new Camera();



        float[] vertices = {
                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,0.5f,-0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,-0.5f,0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                0.5f,0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                -0.5f,-0.5f,0.5f,
                -0.5f,0.5f,0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,0.5f,-0.5f,
                0.5f,0.5f,-0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,-0.5f,0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f

        };

        float[] textureCoords = {

                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0


        };

        int[] indices = {
                0,1,3,
                3,1,2,
                4,5,7,
                7,5,6,
                8,9,11,
                11,9,10,
                12,13,15,
                15,13,14,
                16,17,19,
                19,17,18,
                20,21,23,
                23,21,22

        };
        RawModel model = loader.loadToVAO(vertices,indices,textureCoords);
        Texture t = Texture.defaultLoad("crow");

        Entity test = new Entity(model, new Vector3f(0,0,-1),0,0,0,1);
        TexturedModel tm = new TexturedModel(test,t);
        /*try {
            TexturedModel texturedModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("crow")));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        while(!DisplayManager.isCloseRequested())
        {
            DisplayManager.clear();
            shader.start();
            /*test.increasePosition(0,0,-0.01f);*/
            test.increaseRotation(1f,-0.5f,0.5f);
            shader.loadViewMatrix(cam);
            renderer.render(tm,shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
