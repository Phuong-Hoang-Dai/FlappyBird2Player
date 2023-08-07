
import java.awt.Image;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hoang Dai
 */
public abstract class Item {
    public int x;
    public int y;
    public int width;
    public int height;
    public static int speed = 3;
    private Image image;
    protected String name = "item";
    
    public Item() {
        reset();
    }
    public static void speedup (){
            speed+=1;
    }
    public void reset() {
       width = 40;
       height = 40;
       x = App.WIDTH/2 + 150;    
       y = (int)(Math.random() * 320) ;
    }   
    public void delete() {
       x = 0 - width;
    }  
    public void update() {
            x -= speed;
    }
    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("lib/" + name + ".png");     
        }
        r.image = image;

        return r;
    }
    public boolean collides(int _x, int _y, int _width, int _height) {

        int margin = 2;

        if (_x + _width - margin > x && _x + margin < x + width && _y > y && _y < y + height) {
            delete();
            return true;
        }
        return false;
    }
    public abstract void CollisionAction(Game player);
}
