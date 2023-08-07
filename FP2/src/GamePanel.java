import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    ArrayList<Game> player = GameManager.getInstance().player;
    private Heart heart;
    
    Game player1 = player.get(0);
    Game player2 = player.get(1);



    private Game game;
    Sound sound = new Sound();
    
    public GamePanel(Game game) {
        this.game = game;
        new Thread(this).start();
    }

    public void update() throws NoSuchMethodException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        game.update();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i = 1;
        heart = new Heart (50,445,50,50);
       // System.out.println(game.img  + "Rendering freeze");           
        //if("freezesiunhan.png".equals(game.img) || "freezebatman.png".equals(game.img))
            //System.out.println(game.img  + "Rendering freeze");           
        Graphics2D g2D = (Graphics2D) g;
        for (Render r : game.getRenders())
            if (r.transform != null)
                g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.x, r.y, null);


        g2D.setColor(Color.BLACK);
        
        if (!game.started) {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press SPACE to start", 150, 240);
            
        } else {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            g2D.drawString("Scores: " + Integer.toString(game.score), 10, 465);  
            ArrayList<Render> renders = new ArrayList<Render>();
             int x = 390;
            for (int j = 0; j < game.lives; j++) {
            renders.add(new Render(x,445,"lib/heart_full.png"));
            x += 25;
        }
             for (Render r : renders  )
            if (r.transform != null)
                g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.x, r.y, null);
        }

       if (player1.gameover && player2.gameover && player1.score > player2.score) {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press R to restart", 150, 240);
            g2D.drawString("Player 1 Win", 150, 180);
        }else if(player1.gameover && player2.gameover && player1.score < player2.score){
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press R to restart", 150, 240);
            g2D.drawString("Player 2 Win", 150, 180);
        } else if(player1.gameover && player2.gameover && player1.score == player2.score){
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press R to restart", 150, 240);
            g2D.drawString("DRAW", 150, 180);

        }        
    }
        

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(25);
                

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void playMusic(int i ) {
            sound.setFile(i);
            sound.play();
            sound.loop();
        }
    public void stopMusic(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
