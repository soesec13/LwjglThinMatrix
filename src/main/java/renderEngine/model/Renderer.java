package renderEngine.model;

import org.joml.Matrix4f;
import org.lwjgl.opengl.*;
import renderEngine.display.DisplayManager;
import renderEngine.entities.Entity;
import shaders.ShaderProgram;
import shaders.StaticShader;
import util.Maths;

/**
 * Created by sebi on 018 18/12/2016.
 */
public class Renderer {
    private static final float FOV = 70;
    private static final float Z_NEAR = 0.1f;
    private static final float Z_FAR = 1000;
    private static Matrix4f projectionMatrix;

    public Renderer(StaticShader shader){
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(Entity e, StaticShader shader){
        GL30.glBindVertexArray(e.getModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        Matrix4f transformation = Maths.createTransformationMatrix(
                e.getPosition(),
                e.getRotX(),e.getRotY(),e.getRotZ(),
                e.getScale());
        shader.loadTransformationMatrix(transformation);
        GL11.glDrawElements(GL11.GL_TRIANGLES, e.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    private void createProjectionMatrix(){
        float aspectRatio = (float) DisplayManager.WIDTH / DisplayManager.HEIGHT;
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio,
                Z_NEAR, Z_FAR);
        projectionMatrix.identity();
        projectionMatrix.perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
    }

    /*

    public void render(TexturedModel model){
        GL30.glBindVertexArray(model.getModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D,model.getTexture().getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }*/
}
