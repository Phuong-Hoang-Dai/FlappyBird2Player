/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hoang Dai
 */
public class FreezingItem extends Item{
    Sound sound = new Sound();
    public FreezingItem() {
        this.name = "freeze";
    }

    
    @Override
    public void CollisionAction(Game player) {
        GameManager.getInstance().StunBy(player);
        playSE(3);
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
