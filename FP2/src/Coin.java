
import java.awt.Image;
import java.util.Random;
public class Coin extends Item{
    Sound sound = new Sound();
    public Coin() {
        this.name = "coin";
    }
    
    @Override
    public void CollisionAction(Game player) {
        player.score++;
        playSE(1);
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
