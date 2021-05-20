#version 330

layout (location=0) in vec3 position;
layout (location=1) in vec2 texCoord;

uniform vec2 camera_pos;
uniform float scale_factor;

out vec2 outTexCoord;

void main()
{
    gl_Position = vec4(position, 1.0);
    gl_Position.x -= camera_pos.x;
    gl_Position.y -= camera_pos.y;
    gl_Position.x /= scale_factor;
    gl_Position.y /= scale_factor;
    outTexCoord = texCoord;
}
