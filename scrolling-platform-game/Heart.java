import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Heart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Heart extends Decoration
{
    private boolean touched = false;
    
    /**
     * Each act cycle, check whether we were hit. If we were, play our sound.
     */
    public void act() 
    {
        Actor body = getOneIntersectingObject(Body.class);
        if (!touched && body != null)   // just being touched now
        {
            touched = true;
            setImage ("monster.png");
            
        }
    }    
}
