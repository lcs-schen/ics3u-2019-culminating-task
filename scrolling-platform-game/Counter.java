import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Help us know how many score we have.
 * 
 * @author Sam Chen
 * @version 1.0
 */
public class Counter extends Actor
{
    int score = 0;
    /**
     * Act - do whatever the counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setImage(new GreenfootImage("Score : " + score, 24, Color.GREEN, Color.BLACK));
    }    
    
    /**
     * let counter add score.
     */
    public void addScore()
    {
        score++;
    }
    
    /**
     * let counter remove score.
     */
    public void removeScore()
    {
        score = score - 1;
    }
}
