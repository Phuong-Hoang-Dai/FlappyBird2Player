
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author acer
 */
public final class MainMenu extends JPanel{
    int i = 0;
    Sound sound = new Sound();
    JButton chosePlayer1Button = new JButton("Player 1");
    JButton chosePlayer2Button = new JButton("Player 2");
    public MainMenu() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        //setLayout(null);
        setBackground(Color.white);

        JLabel titleLabel = new JLabel("Flappy Bird 2 NGƯỜI CHƠI", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(titleLabel, BorderLayout.NORTH);
       
        JButton startButton = new JButton("Start Game");
        JButton optionsButton = new JButton("Choose Character");
        JButton exitButton = new JButton("Exit");
        playMusic(0);
        add(startButton);
        add(optionsButton);
        add(exitButton);
        ImageIcon menuBackgroundImage= new ImageIcon( Util.loadImage("lib/banner.png"));
        JLabel menuBackground = new JLabel(menuBackgroundImage);
        add(menuBackground);
        
        //tạo sự kiện nút bắt đầu chơi
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code here to handle the "Start Game" button click
                // For example, start the game or switch to the game screen.
                PanelManager.getInstance().LoadPanel(1,"Game");
                stopMusic();
            }
        });

        //Nút chọn nhân vật
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code here to handle the "Options" button click
                // For example, open an options menu dialog.
                CharacterSelectionScreen();
            }
        });
        //Nút cài đặt
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code here to handle the "Exit" button click
                // For example, exit the game gracefully.
                int option = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
                        "Are you sure you want to exit?",
                        "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }); 
    }
    public void ChooseCharac(String img,ImageIcon icon){
            Game player = GameManager.getInstance().player.get(i);
            player.img = img;
            if(i==0 && chosePlayer1Button.getIcon()==null){
                JOptionPane.showMessageDialog(MainFrame.getInstance()
                    , "This character has been choose for Player " 
                            + String.valueOf(i+1));
                chosePlayer1Button.setIcon(icon);
            }
            else if(i==0 && chosePlayer1Button.getIcon()!=null)
                JOptionPane.showMessageDialog(MainFrame.getInstance()
                    , "Player 1 has been choose character" );
                            
            if(i==1&& chosePlayer2Button.getIcon()==null){
                JOptionPane.showMessageDialog(MainFrame.getInstance()
                    , "This character has been choose for Player " 
                            + String.valueOf(i+1));
                chosePlayer2Button.setIcon(icon);}
            else if(i==1&& chosePlayer2Button.getIcon()!=null)
                JOptionPane.showMessageDialog(MainFrame.getInstance()
                    , "Player 2 has been choose character" );
        }
        //Màn hình chọn nhân vật
        public void CharacterSelectionScreen() {
                JFrame frame = new JFrame("Character Selection Screen");
                frame.setSize(400, 300);
                frame.setLayout(new BorderLayout());
                JPanel mainPanel = new JPanel(new BorderLayout());
                mainPanel.setBackground(Color.WHITE);
                JLabel titleLabel = new JLabel("Select Your Character", SwingConstants.CENTER);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
                mainPanel.add(titleLabel, BorderLayout.NORTH);

                JPanel chosePlayerPanel = new JPanel(new GridLayout(1, 2));
                
                
                chosePlayer1Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        i = 0;
                    }
                });
                chosePlayer2Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        i = 1;
                    }
                });
                
                chosePlayerPanel.add(chosePlayer1Button);
                chosePlayerPanel.add(chosePlayer2Button);
                
                mainPanel.add(chosePlayerPanel,BorderLayout.CENTER);
                
                JPanel characterPanel = new JPanel(new GridLayout(3, 1, 10, 10));
                characterPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

                ImageIcon character1Icon = new ImageIcon( Util.loadImage("lib/maybay.png"));
                ImageIcon character2Icon = new ImageIcon( Util.loadImage("lib/maybay1.png"));
                ImageIcon character3Icon = new ImageIcon( Util.loadImage("lib/siunhan.png"));
                ImageIcon character4Icon = new ImageIcon( Util.loadImage("lib/batman.png"));

                JButton character1Button = new JButton(character1Icon);
                JButton character2Button = new JButton(character2Icon);
                JButton character3Button = new JButton(character3Icon);
                JButton character4Button = new JButton(character4Icon);
                
     

                
                character1Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Add code here to handle the selection of character 1      
                        ChooseCharac("maybay.png",character1Icon);
                        
                    }
                });

                character2Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ChooseCharac("maybay1.png",character2Icon);

                    }
                });

                character3Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ChooseCharac("siunhan.png",character3Icon);
                    }
                });
                
                character4Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ChooseCharac("batman.png",character4Icon);
                    }
                });

                characterPanel.add(character1Button);
                characterPanel.add(character2Button);
                characterPanel.add(character3Button);
                characterPanel.add(character4Button);

                mainPanel.add(characterPanel, BorderLayout.SOUTH);

                frame.add(mainPanel);
                frame.setVisible(true);
            }
        public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
        public void stopMusic(){
            sound.stop();
        }
        
} 
