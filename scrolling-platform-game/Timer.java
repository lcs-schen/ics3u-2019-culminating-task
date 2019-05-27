import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Help us know how many time we have left.
 * 
 * @author Sam Chen
 * @version 1.0
 */
public class Timer extends Actor
{
    int time = 1000;
    
    /**
     * Act - do whatever the Timer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setImage(new GreenfootImage("Time : " + time, 24, Color.GREEN, Color.BLACK));
        time--;
        if (time <= 0)
        {
            Greenfoot.stop();
        }
    }    

}
