
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hoang Dai
 */
public class GameManager {
    private static GameManager instance;
    public ArrayList<Game> player;
    
    
    public static GameManager getInstance() {
        return instance;
    }

    public GameManager() {
        if(instance == null)
        {
            instance = this;
            player = new ArrayList<>();
            player.add(new Game(KeyEvent.VK_UP,"batman.png"));
            player.add(new Game(KeyEvent.VK_W,"siunhan.png"));
        }
    }
    public void StunBy(Game player)
    {
        for(Game p : this.player)
        {
            if(p != player)
            {
                String originalImage = p.img;
                p.setImg("freezing" + originalImage);
                p.setIsStun(Boolean.TRUE);
                Refresh(p, 2000, originalImage);
            }
        }
    }
    public void Refresh(Game player, int timeDeplay, String originalImage)
    {
        Timer timer = new Timer(timeDeplay, (ActionEvent e) -> {
            player.setImg(originalImage);
            player.setIsStun(Boolean.FALSE);
            System.out.println("is Refreshed");
        });

        // Bắt đầu đếm thời gian và kích hoạt công việc sau khi 5 giây
        timer.setRepeats(false); // Đảm bảo chỉ thực hiện công việc một lần
        timer.start();
    }
}
