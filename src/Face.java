import java.awt.*;

public class Face{
    Vertex[] vertices = new Vertex[4];

    Face(Vertex aVertex, Vertex bVertex, Vertex cVertex, Vertex dVertex){
        this.vertices[0] = aVertex;
        this.vertices[1] = bVertex;
        this.vertices[2] = cVertex;
        this.vertices[3] = dVertex;
    }

    void drawFace(Graphics2D g2d, boolean canDraw, Color color, double d){
        d = d/2;
        if(canDraw) {
            Polygon poly = new Polygon();
            int centreX = Window.wight / 2;
            int centreY = Window.height / 2;
            for(int i = 0; i < 4; i++){
                int x = (int)(d * vertices[i].viewcoord.x / vertices[i].viewcoord.z);
                int y = (int)(d * vertices[i].viewcoord.y / vertices[i].viewcoord.z);
                poly.addPoint(x + centreX, y + centreY);
            }
            g2d.setColor(color);
            g2d.fill(poly);
        }
        }

    }