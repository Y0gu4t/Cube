import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cube{
    Point viewCoord = new Point();
    boolean read_end = false;
    ArrayList<Vertex> vertices;
    ArrayList<Edge> edges;
    ArrayList<Face> faces;

    boolean[] visible;
    int countV, countE, countF;
    EdgeVertices[] ev;
    FaceVertices[] fv;
    public void draw(Graphics2D g2d){ //отрисовка куба
        /*for (int i = 0; i < countE; i++) {
            edges.get(i).drawEdge(g2d,viewCoord.x);
        }*/
        isVisible();
        for(int i = 0; i < faces.size(); i++){
            faces.get(i).drawFace(g2d,visible[i],Window.colors.get(i),viewCoord.x);
        }
    }

    public void isVisible(){
        for(int i=0 ; i < visible.length; i++){
            visible[i] = false;
        }
        for(int i=0; i < visible.length; i++){
            double h = faces.get(i).vertices[0].viewcoord.x * faces.get(i).vertices[2].viewcoord.y * faces.get(i).vertices[1].viewcoord.z +
                    faces.get(i).vertices[1].viewcoord.x * faces.get(i).vertices[0].viewcoord.y * faces.get(i).vertices[2].viewcoord.z +
                    faces.get(i).vertices[2].viewcoord.x * faces.get(i).vertices[1].viewcoord.y * faces.get(i).vertices[0].viewcoord.z -
                    faces.get(i).vertices[0].viewcoord.x * faces.get(i).vertices[1].viewcoord.y * faces.get(i).vertices[2].viewcoord.z -
                    faces.get(i).vertices[1].viewcoord.x * faces.get(i).vertices[2].viewcoord.y * faces.get(i).vertices[0].viewcoord.z -
                    faces.get(i).vertices[2].viewcoord.x * faces.get(i).vertices[0].viewcoord.y * faces.get(i).vertices[1].viewcoord.z;
            visible[i] = h < 0;
        }
    }
    public void makeCube(){
        int a, b, c, d;
        if (!read_end){
            try (Scanner scan = new Scanner(new File("src/in.txt")))
            {
                while (scan.hasNextInt()) {
                    countV = scan.nextInt();
                    countE = scan.nextInt();
                    countF = scan.nextInt();
                    vertices = new ArrayList<>();
                    edges = new ArrayList<>();
                    faces = new ArrayList<>();
                    ev = new EdgeVertices[countE];
                    fv = new FaceVertices[countF];
                    visible = new boolean[countF];
                    for (int i = 0; i < countV; i++) {
                        a = scan.nextInt();
                        b = scan.nextInt();
                        c = scan.nextInt();
                        Point point = new Point((a - 0.5)* Window.Length, (b - 0.5)* Window.Length, (c - 0.5)* Window.Length);
                        Vertex vertex1 = new Vertex(point);
                        vertices.add(vertex1);
                    }
                    for (int i = 0; i < countE; i++) {
                        a = scan.nextInt();
                        b = scan.nextInt();
                        ev[i] = new EdgeVertices(a,b);
                        edges.add(new Edge(vertices.get(ev[i].beginnig), vertices.get(ev[i].end)));
                    }
                    for (int i =0; i < countF; i++){
                        a = scan.nextInt();
                        b = scan.nextInt();
                        c = scan.nextInt();
                        d = scan.nextInt();
                        fv[i] = new FaceVertices(a,b,c,d);
                        faces.add(new Face(vertices.get(fv[i].points[0]),
                                vertices.get(fv[i].points[1]),
                                vertices.get(fv[i].points[2]),
                                vertices.get(fv[i].points[3])));

                    }
                    read_end = true;
                }
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < countV; i++) {
            vertices.get(i).setViewcoord(viewCoord);
        }
    }
    public void setVidPoint(double ro, double tetta, double fi){
        viewCoord.x = ro;
        viewCoord.y = tetta;
        viewCoord.z = fi;
    }
}