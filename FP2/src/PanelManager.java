
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hoang Dai
 */
public class PanelManager{
    private static PanelManager instance;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    public static PanelManager getInstance() {
        return instance;
    }

    public PanelManager() {
        if(instance == null)
        {
            instance = this;
            
            cardLayout = new CardLayout();
            cardPanel = new JPanel(cardLayout);
            
            MainMenu menu = new MainMenu();
            cardPanel.add(menu,"Menu");

            JPanel gamePanel = new JPanel();
            gamePanel.setLayout(new GridLayout(1,2));
            
            for(Game player : GameManager.getInstance().player)
            {
                GamePanel gamePanelP = new GamePanel(player);
                gamePanel.add(gamePanelP);
            }         
            cardPanel.add(gamePanel,"Game");
            
            MainFrame.getInstance().add(cardPanel);
            cardLayout.show(cardPanel,"Menu");
        }
    }
    
    public void LoadPanel(int i, String name)
    {
        cardLayout.show(cardPanel, name);
    }
    public void AddPanel(JPanel panel, String name)
    {
        cardPanel.add(panel,name);
    }
}
