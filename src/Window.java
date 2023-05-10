import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Window extends JPanel implements KeyListener{
    public static int wight = 700, height = 700;
    public static final double Length = 300;
    public static double ro = 500, fi = 0, tetta = 0;
    public static final double angle = Math.PI/20;

    public static ArrayList<Color> colors = new ArrayList<>();
    public static double[][] rotateX = {{1,0,0},
                                        {0, Math.cos(angle), (-1)*Math.sin(angle)},
                                        {0, Math.sin(angle), Math.cos(angle)}};
    public static double[][] rotateY = {{Math.cos(angle), 0, Math.sin(angle)},
                                        {0, 1, 0},
                                        {(-1)*Math.sin(angle), 0, Math.cos(angle)}};
    public static double[][] rotateZ = {{Math.cos(angle), (-1)*Math.sin(angle), 0},
                                        {Math.sin(angle), Math.cos(angle), 0},
                                        {0, 0, 1}};

    public Cube cube;
    Window(){
        cube = new Cube();
        cube.setVidPoint(ro,tetta,fi);
        cube.makeCube();
        for(int i = 0; i<6; i++){
            colors.add(new Color(40 * i, 0, 40 * i));
        }
    }
    public static void main(String [] args){
        Window w = new Window();
        JFrame frame = new JFrame("Lab3 Cube");
        frame.setBounds(100, 100, wight, height);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(w);
        frame.setVisible(true);
        frame.add(w);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        cube.setVidPoint(ro,tetta,fi);
        cube.makeCube();
        cube.draw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){
            tetta-=angle;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            tetta+=angle;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            fi+=angle;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            fi-=angle;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_Z){
            for(int i=0;i < cube.countV;i++){
                cube.vertices.get(i).multi(rotateZ);
                repaint();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_X){
            for(int i=0;i < cube.countV;i++){
                cube.vertices.get(i).multi(rotateX);
                repaint();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_Y){
            for(int i=0;i < cube.countV;i++){
                cube.vertices.get(i).multi(rotateY);
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
