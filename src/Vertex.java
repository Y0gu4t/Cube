import java.util.ArrayList;
import java.util.Arrays;

public class Vertex{
    Point worldcoord;
    Point viewcoord;
    Vertex(Point worldcoord){
        this.worldcoord = worldcoord;
    }
    public void setViewcoord(Point viewpoint){
        viewcoord = new Point(Window.ro, Window.tetta, Window.fi);
        viewcoord.x = -worldcoord.x*Math.sin(Window.tetta)+worldcoord.y*Math.cos(Window.tetta);
        viewcoord.y = -worldcoord.x*Math.cos(Window.fi)*Math.cos(Window.tetta)- worldcoord.y* Math.cos(Window.fi)*Math.sin(Window.tetta)+worldcoord.z*Math.sin(Window.fi);
        viewcoord.z = -worldcoord.x*Math.sin(Window.fi)*Math.cos(Window.tetta)- worldcoord.y*Math.sin(Window.fi)*Math.sin(Window.tetta)-worldcoord.z*Math.cos(Window.fi)+ Window.ro;
    }
    public void multi(double[][] other){
        ArrayList<Double> p = new ArrayList<>();
        p.add(worldcoord.x);
        p.add(worldcoord.y);
        p.add(worldcoord.z);
        ArrayList<Double> result = new ArrayList<>(Arrays.asList(0.0,0.0,0.0));
        if (other.length == p.size()) {
            for (int i = 0;i < other.length;i++) {
                for (int k = 0;k < other[i].length;k++) {
                    result.set(i, result.get(i) + p.get(k) * other[k][i]);
                }
            }
            Point res = new Point(result.get(0), result.get(1), result.get(2));
            worldcoord = res;
        }
    }
}