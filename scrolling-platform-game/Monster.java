import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlueMonster here.
 * 
 * @@author S. Chen
 * @version May 29, 2019
 */

public class Monster extends Enemies
{
    /**
     * Constructor
     * 
     * Called once when object is created.
     */
    Monster(int scrollableWorldX, int scrollableWorldY)
    {
        super(scrollableWorldX, scrollableWorldY);
    }

    /**
     * Act - do whatever the Pig wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    //set up speed and frame
    private int speed = 1;
    private int frames = 0;

    public void act() 
    {
        setLocation ( getX() + speed, getY() );
        frames += 1;
        if(frames == 160)
        {
            //Turn around and go back
            speed = -speed;
            frames = 0;
        }
    }
}