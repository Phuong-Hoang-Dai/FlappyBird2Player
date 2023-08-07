import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hoang Dai
 */


public final class MainFrame extends JFrame {
    private static MainFrame instance;

    
    public static MainFrame getInstance() {
        return instance;
        
    }
    
    public MainFrame() {
        if(instance == null)
        {
            instance = this;
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            new GameManager();
            new PanelManager();
            Keyboard keyboard = Keyboard.getInstance();
            addKeyListener(keyboard);

            setResizable(false);
            setSize(App.WIDTH, App.HEIGHT);
        }
    }
    
        
}
