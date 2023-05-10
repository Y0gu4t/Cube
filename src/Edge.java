import java.awt.*;
import java.awt.geom.Line2D;

public class Edge{
    Vertex startVertex, finishVertex;
    Edge(Vertex sV, Vertex fV){
        startVertex = sV;
        finishVertex = fV;
    }
    void drawEdge(Graphics2D g2d, double d){
        d = d/2;
        double x1 = ((d*startVertex.viewcoord.x)/startVertex.viewcoord.z);
        double y1 = ((d*startVertex.viewcoord.y)/startVertex.viewcoord.z);
        double x2 = ((d*finishVertex.viewcoord.x)/finishVertex.viewcoord.z);
        double y2 = ((d*finishVertex.viewcoord.y)/finishVertex.viewcoord.z);
        double centreX = Window.wight/2.0;
        double centreY = Window.height/2.0;
        g2d.setColor(Color.BLACK);
        g2d.draw(new Line2D.Double(x1+centreX,y1+centreY,x2+centreX,y2+centreY));
    }
}