import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemies extends Actor
{
    /**
     * Move to the left.
     */
    protected void moveLeft()
    {
        setLocation (getX()-2, getY());
    }    

    /**
     * Move to the right.
     */
    protected void moveRight()
    {
        setLocation (getX()+2, getY());
    }    
}
