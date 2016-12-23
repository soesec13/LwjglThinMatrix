package util;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import renderEngine.entities.Camera;


/**
 * Created by sebi on 021 21/12/2016.
 */
public class Maths {
    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale)
    {
        Matrix4f matrix = new Matrix4f();
        matrix.identity()
                .translate(translation)
                .rotateX(toRad(rx))
                .rotateY(toRad(ry))
                .rotateZ(toRad(rz))
                .scale(scale);
        //System.out.println(matrix.toString());
        return matrix;
    }
    public static Matrix4f createViewMatrix(Vector3f position,Vector3f rotation)
    {
        Matrix4f viewMatrix = new Matrix4f();

        viewMatrix.identity();
        // First do the rotation so camera rotates over its position
        viewMatrix.rotate(toRad(rotation.x), new Vector3f(1, 0, 0))
                .rotate(toRad(rotation.y), new Vector3f(0, 1, 0));
        // Then do the translation
        viewMatrix.translate(-position.x, -position.y, -position.z);
        return viewMatrix;
    }
    public static Matrix4f createViewMatrix(Camera cam)
    {
        return createViewMatrix(cam.getPosition(),new Vector3f(cam.getPitch(),cam.getYaw(),0));
    }
    private static float toRad(float a)
    {
        return (float)Math.toRadians(a);
    }
}
