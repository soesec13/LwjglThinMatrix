#version 400 core

in vec3 position;

out vec3 colour;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main(void){
    gl_Position = projectionMatrix*viewMatrix*transformationMatrix*vec4(position,1.0);

    colour = vec3(position.x+0.5,position.y+0.5,position.z+0.5);
}