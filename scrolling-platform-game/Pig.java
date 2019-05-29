import greenfoot.*; 
import java.util.List;
/**
 * A cloud that moves back and forth between two defined points.
 */
public class Pig extends Enemies
{
    private int idleMove = 1;

    /**
     * Move towards the ball. When there is no ball, go into waiting mode (move slowly
     * back and forth).
     */
    public void act() 
    {
        List pigs = getWorld().getObjects(Pig.class);

        if (pigs.isEmpty()) {
            idleMove();
        }
        else {
            Pig pig = (Pig) pigs.get(0);

            if (getX() < pig.getX()) {
                moveRight();
            }
            else {
                moveLeft();
            }
        }
    }

    /**
     * Move slowly back and forth.
     */
    private void idleMove()
    {
        setLocation(getX() + idleMove, getY());
        if (getX() < 100 || getX() > getWorld().getWidth() - 100) {
            idleMove = -idleMove;
        }
    }
}