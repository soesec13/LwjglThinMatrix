#version 400 core

in vec2 pass_TextureCoords;

out vec4 out_Colour;

uniform sampler2D textureSampler;

void main(void){
    out_Colour = texture2D(textureSampler,pass_TextureCoords);
}
