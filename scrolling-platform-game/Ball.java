import greenfoot.*; 
import java.util.List;
/**
 * A cloud that moves back and forth between two defined points.
 */
public class Ball extends Enemies
{
    private int idleMove = 1;

    /**
     * Move towards the ball. When there is no ball, go into waiting mode (move slowly
     * back and forth).
     */
    public void act() 
    {
        List balls = getWorld().getObjects(Ball.class);

        if (balls.isEmpty()) {
            idleMove();
        }
        else {
            Ball ball = (Ball) balls.get(0);

            if (getX() < ball.getX()) {
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
