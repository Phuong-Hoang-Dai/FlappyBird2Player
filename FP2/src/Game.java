import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    public static final int PIPE_DELAY = 100;
    public static final int ITEM_DELAY = 100;
    private Boolean paused;
    Sound sound = new Sound();
    private int pauseDelay;
    private int restartDelay;
    private int pipeDelay;
    private int itemDeplay;
    private int key;
    private boolean chuyencanh=true;
    private Bird bird;
    private Heart heart;
    private ArrayList<Pipe> pipes;
    Class<?>[] itemClasses = {Coin.class, FreezingItem.class};
    private ArrayList<Item> items;
    private Random random;
    private Keyboard keyboard;
    public int score;
    public int lives;
    public Boolean gameover;
    public Boolean started;
    public Boolean isStun;
    public String img;

    public Boolean getIsStun() {
        return isStun;
    }

    public void setIsStun(Boolean isStun) {
        this.isStun = isStun;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
      
    public Game(int key,String img) {
        this.img = img;
        keyboard = Keyboard.getInstance();
        this.key = key;
        restart();
    }
    
    public void restart() {
        paused = false;
        started = false;
        gameover = false;
        isStun = false;
        lives = 3;
        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        pipeDelay = 0;
        itemDeplay = 0;
        Pipe.speed=3;
        Item.speed=3;
        random = new Random();
        bird = new Bird();
        pipes = new ArrayList<Pipe>();
        items = new ArrayList<Item>();
    }

    public void update() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        watchForStart();
        if (!started)
            return;
        watchForPause();
        watchForReset();

        if (paused)
            return;

        if(!isStun)
            bird.update(key);

        if (gameover)
            return;
        moveItems();
        movePipes();
        checkForCollisions();
    }

    public ArrayList<Render> getRenders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        renders.add(new Render(0, 0, "lib/background.png"));
        if(score > 3){
            renders.add(new Render(0, 0, "lib/darkbackground.png"));                   
        }
        if(score ==4 && chuyencanh){
            Item.speedup();
            Pipe.speedup();
            chuyencanh=false;
        }
        for (Pipe pipe : pipes)
            renders.add(pipe.getRender());
        for (Item item : items)
            renders.add(item.getRender());
        renders.add(new Render(0, 0, "lib/foreground.png"));
        bird.setImg(img);
        renders.add(bird.getRender());  
        return renders;
    }

    private void watchForStart() {
        if (!started && keyboard.isDown(KeyEvent.VK_SPACE)) {
            started = true;
            playMusic(2);
        }
}
    
    private void watchForPause() {
        if (pauseDelay > 0)
            pauseDelay--;
        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }
    }

    private void watchForReset() {
        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0 ) {
            stopMusic();
            restart();
            restartDelay = 10;
            return;
        }
    }
    private void movePipes() {
        pipeDelay--;

        if (pipeDelay < 0) {
            pipeDelay = PIPE_DELAY;
            Pipe northPipe = null;
            Pipe southPipe = null;

            // Look for pipes off the screen
            for (Pipe pipe : pipes) {
                if (pipe.x - pipe.width < 0) {
                    if (northPipe == null) {
                        northPipe = pipe;
                    } else if (southPipe == null) {
                        southPipe = pipe;
                        break;
                    }
                }
            }

            if (northPipe == null) {
                Pipe pipe = new Pipe("north");
                pipes.add(pipe);
                northPipe = pipe;
            } else {
                northPipe.reset();
            }

            if (southPipe == null) {
                Pipe pipe = new Pipe("south");
                pipes.add(pipe);
                southPipe = pipe;
            } else {
                southPipe.reset();
            }

            northPipe.y = southPipe.y + southPipe.height + 75;
        }

        for (Pipe pipe : pipes) {
            pipe.update();
        }
    }

    private void moveItems() throws NoSuchMethodException, InstantiationException, 
            IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
        itemDeplay--;

        if (itemDeplay < 0) {
            itemDeplay = ITEM_DELAY;
            int index = random.nextInt(itemClasses.length);
            Item newItem = null;
            // Look for pipes off the screen
            for (Item item : items) {
                if (item.x < 0 && item.getClass() == itemClasses[index]) {
                    newItem = item;
                }
            }

            if (newItem == null) {
                Item item = (Item) itemClasses[index].getDeclaredConstructor().newInstance();
                items.add(item);
                newItem = item;
            } else {
                newItem.reset();
            }
            int id = key == KeyEvent.VK_UP? 1 : 2;
        }
        
        for (Item item : items) {
            item.update();
        }
    }

    private void checkForCollisions() {

        for (Pipe pipe : pipes) {
            if (pipe.collides(bird.x, bird.y, bird.width, bird.height)) {
                
                if(!bird.isColliding)
                {
                    bird.isColliding = true;
                    lives -= 1;
                    playSE(4);
                    if(lives == 0)
                    {
                        gameover = true;
                        bird.dead = true;
                        stopMusic();
                        playSE(5);

                    }
                    
                }
            }else if(bird.x > pipe.x + pipe.width && bird.isColliding)
            {
                bird.isColliding = false;
            }
        }
        for(Item item : items)
        {
            if (item.collides(bird.x, bird.y, bird.width, bird.height)) {
                item.CollisionAction(this);
            }
        }

        // Ground + Bird collision
        if (bird.y + bird.height > App.HEIGHT - 80) {
            gameover = true;
            bird.dead = true;
            lives = 0;
            playSE(5);
            bird.y = App.HEIGHT - 80 - bird.height;
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
