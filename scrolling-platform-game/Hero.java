import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * This is the class for the "main character" in the action.
 * 
 * @author S. Chen
 * @version May 29, 2019
 */
public class Hero extends Actor
{
    /**
     * Instance variables
     * 
     * These are available for use in any method below.
     */    

    
    // Horizontal speed (change in horizontal position, or delta X)
    private int deltaX = 4;

    // Vertical speed (change in vertical position, or delta Y)
    private int deltaY = 4;

    // Acceleration for falls
    private int acceleration = 2;

    // Strength of a jump
    private int jumpStrength = -15;

    // Track current theoretical position in wider "scrollable" world
    private int currentScrollableWorldXPosition;
    private int currentScrollableWorldYPosition;

    // Track whether game is over or not
    private boolean isGameOver;

    // Constants to track vertical direction
    private static final String JUMPING_UP = "up";
    private static final String JUMPING_DOWN = "down";
    private String verticalDirection;

    // Constants to track horizontal direction
    private static final String FACING_RIGHT = "right";
    private static final String FACING_LEFT = "left";
    private String horizontalDirection;

    // Constants to track vertical direction
    private static final String CLIMBING_UP = "up1";
    private static final String CLIMBING_DOWN = "down1";
    private String vertical1Direction;

    // For walking animation
    private GreenfootImage walkingRightImages[];
    private GreenfootImage walkingLeftImages[];
    private static final int WALK_ANIMATION_DELAY = 8;
    private static final int COUNT_OF_WALKING_IMAGES = 2;
    private int walkingFrames;

    /**
     * Constructor
     * 
     * This runs once when the Hero object is created.
     */
    Hero(int startingX)
    {
        // Set where hero begins horizontally
        currentScrollableWorldXPosition = startingX;

        // Game on
        isGameOver = false;

        // First jump will be in 'down' direction
        verticalDirection = JUMPING_DOWN;

        // Facing right to start
        horizontalDirection = FACING_RIGHT;

        // Set image
        setImage("hero-jump-down-right.png");

        // Initialize the 'walking' arrays
        walkingRightImages = new GreenfootImage[COUNT_OF_WALKING_IMAGES];
        walkingLeftImages = new GreenfootImage[COUNT_OF_WALKING_IMAGES];

        // Load walking images from disk
        for (int i = 0; i < walkingRightImages.length; i++)
        {
            walkingRightImages[i] = new GreenfootImage("hero-walk-right-" + i + ".png");

            // Create left-facing images by mirroring horizontally
            walkingLeftImages[i] = new GreenfootImage(walkingRightImages[i]);
            walkingLeftImages[i].mirrorHorizontally();
        }

        // Track animation frames for walking
        walkingFrames = 0;
    }

    /**
     * Act - do whatever the Hero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkKeys();
        checkFall();
        encounterMonster();
        encounterDonkeykong();
        encounterPrinces();
        if (!isGameOver)
        {
            touchUfo();
        }

        if (!isGameOver)
        {
            checkGameOver();
        }
    }

    /**
     * Respond to keyboard action from the user.
     */
    private void checkKeys()
    {
        // Walking keys
        if (Greenfoot.isKeyDown("left") && !isGameOver)
        {
            moveLeft();
        }
        else if (Greenfoot.isKeyDown("right") && !isGameOver)
        {
            moveRight();
        }
        else if (Greenfoot.isKeyDown("up") && !isGameOver)
        {
            moveUp();
        }
        else
        {
            // Standing still; reset walking animation
            walkingFrames = 0;
        }

        // Jumping
        if (Greenfoot.isKeyDown("space") && !isGameOver)
        {
            // Only able to jump when on a solid object
            if (onPlatform())
            {
                jump();
            }
        }
    }

    /**
     * Should the hero be falling right now?
     */
    public void checkFall()
    {
        if (onPlatform())
        {
            // Stop falling
            deltaY = 0;

            // Set image
            if (horizontalDirection == FACING_RIGHT && Greenfoot.isKeyDown("right") == false)
            {
                setImage("hero-right.png");
            }
            else if (horizontalDirection == FACING_LEFT && Greenfoot.isKeyDown("left") == false)
            {
                setImage("hero-left.png");
            }

            // Get a reference to any object that's created from a subclass of Platform,
            // that is below (or just below in front, or just below behind) the hero
            Actor directlyUnder = getOneObjectAtOffset(0, getImage().getHeight() / 2, Platform.class);
            Actor frontUnder = getOneObjectAtOffset(getImage().getWidth() / 3, getImage().getHeight() / 2, Platform.class);
            Actor rearUnder = getOneObjectAtOffset(0 - getImage().getWidth() / 3, getImage().getHeight() / 2, Platform.class);

            // Bump the hero back up so that they are not "submerged" in a platform object
            if (directlyUnder != null)
            {
                int correctedYPosition = directlyUnder.getY() - directlyUnder.getImage().getHeight() / 2 - this.getImage().getHeight() / 2;
                setLocation(getX(), correctedYPosition);
            }
            if (frontUnder != null)
            {
                int correctedYPosition = frontUnder.getY() - frontUnder.getImage().getHeight() / 2 - this.getImage().getHeight() / 2;
                setLocation(getX(), correctedYPosition);
            }
            if (rearUnder != null)
            {
                int correctedYPosition = rearUnder.getY() - rearUnder.getImage().getHeight() / 2 - this.getImage().getHeight() / 2;
                setLocation(getX(), correctedYPosition);
            }
        }
        else
        {
            fall();
        }
    }

    /**
     * Is the hero currently touching a solid object? (any subclass of Platform)
     */
    public boolean onPlatform()
    {
        // Get an reference to a solid object (subclass of Platform) below the hero, if one exists
        Actor directlyUnder = getOneObjectAtOffset(0, getImage().getHeight() / 2, Platform.class);
        Actor frontUnder = getOneObjectAtOffset(getImage().getWidth() / 3, getImage().getHeight() / 2, Platform.class);
        Actor rearUnder = getOneObjectAtOffset(0 - getImage().getWidth() / 3, getImage().getHeight() / 2, Platform.class);

        // If there is no solid object below (or slightly in front of or behind) the hero...
        if (directlyUnder == null && frontUnder == null && rearUnder == null)
        {
            return false;   // Not on a solid object
        }
        else
        {
            return true;
        }
    }

    /**
     * Is the hero currently touching a solid object? (any subclass of Platform)
     */
    public boolean onLadder()
    {
        // Get an reference to a solid object (subclass of Platform) below the hero, if one exists
        Actor directlyUnder = getOneObjectAtOffset(0, getImage().getHeight() / 2, Ladder.class);
        Actor frontUnder = getOneObjectAtOffset(getImage().getWidth() / 3, getImage().getHeight() / 2, Ladder.class);
        Actor rearUnder = getOneObjectAtOffset(0 - getImage().getWidth() / 3, getImage().getHeight() / 2, Ladder.class);

        // If there is no solid object below (or slightly in front of or behind) the hero...
        if (directlyUnder == null && frontUnder == null && rearUnder == null)
        {
            return false;   // Not on a solid object
        }
        else
        {
            return true;
        }
    }

    /**
     * Make the hero jump.
     */
    public void jump()
    {
        // Track vertical direction
        verticalDirection = JUMPING_UP;

        // Set image
        if (horizontalDirection == FACING_RIGHT)
        {
            setImage("hero-jump-up-right.png");
        }
        else
        {
            setImage("hero-jump-up-left.png");
        }

        // Change the vertical speed to the power of the jump
        deltaY = jumpStrength;
        jumpStrength = -15;
        // Make the character move vertically 
        fall();
    }

    /**
     * Make the hero fall.
     */
    public void fall()
    {
        // See if direction has changed
        if (deltaY > 0)
        {
            verticalDirection = JUMPING_DOWN;

            // Set image
            if (horizontalDirection == FACING_RIGHT)
            {
                setImage("hero-jump-down-right.png");
            }
            else
            {
                setImage("hero-jump-down-left.png");
            }
        }

        // Fall (move vertically)
        int newVisibleWorldYPosition = getY() + deltaY;
        setLocation(getX(), newVisibleWorldYPosition );

        // Accelerate (fall faster next time)
        deltaY = deltaY + acceleration;
    }

    /**
     * Animate walking
     */
    private void animateWalk(String direction)
    {
        // Track walking animation frames
        walkingFrames += 1;

        // Get current animation stage
        int stage = walkingFrames / WALK_ANIMATION_DELAY;

        // Animate
        if (stage < walkingRightImages.length)
        {
            // Set image for this stage of the animation
            if (direction == FACING_RIGHT)
            {
                setImage(walkingRightImages[stage]);
            }
            else
            {
                setImage(walkingLeftImages[stage]);
            }
        }
        else
        {
            // Start animation loop from beginning
            walkingFrames = 0;
        }
    }

    /**
     * Move the hero to the right.
     */
    public void moveRight()
    {
        // Track direction
        horizontalDirection = FACING_RIGHT;

        // Set image 
        if (onPlatform())
        {
            animateWalk(horizontalDirection);
        }
        else
        {
            // Set appropriate jumping image
            if (verticalDirection == JUMPING_UP)
            {
                setImage("hero-jump-up-right.png");
            }
            else
            {
                setImage("hero-jump-down-right.png");
            }
        }

        // Get object reference to world
        SideScrollingWorld world = (SideScrollingWorld) getWorld(); 

        // Decide whether to actually move, or make world's tiles move
        if (currentScrollableWorldXPosition < world.HALF_VISIBLE_WIDTH)
        {
            // HERO IS WITHIN EXTREME LEFT PORTION OF SCROLLABLE WORLD
            // So... actually move the actor within the visible world.

            // Move to right in visible world
            int newVisibleWorldXPosition = getX() + deltaX;
            setLocation(newVisibleWorldXPosition, getY());

            // Track position in wider scrolling world
            currentScrollableWorldXPosition = getX();
        }
        else if (currentScrollableWorldXPosition + deltaX * 2 > world.SCROLLABLE_WIDTH - world.HALF_VISIBLE_WIDTH)
        {
            // HERO IS WITHIN EXTREME RIGHT PORTION OF SCROLLABLE WORLD
            // So... actually move the actor within the visible world.

            // Allow movement only when not at edge of world
            if (currentScrollableWorldXPosition < world.SCROLLABLE_WIDTH - this.getImage().getWidth() / 2)
            {
                // Move to right in visible world
                int newVisibleWorldXPosition = getX() + deltaX;
                setLocation(newVisibleWorldXPosition, getY());

                // Track position in wider scrolling world
                currentScrollableWorldXPosition += deltaX;
            }

        }

    }

    /**
     * Move the hero to the left.
     */
    public void moveLeft()
    {
        // Track direction
        horizontalDirection = FACING_LEFT;

        // Set image 
        if (onPlatform())
        {
            animateWalk(horizontalDirection);
        }
        else
        {
            // Set appropriate jumping image
            if (verticalDirection == JUMPING_UP)
            {
                setImage("hero-jump-up-left.png");
            }
            else
            {
                setImage("hero-jump-down-left.png");
            }
        }

        // Get object reference to world
        SideScrollingWorld world = (SideScrollingWorld) getWorld(); 

        // Decide whether to actually move, or make world's tiles move
        if (currentScrollableWorldXPosition - deltaX < world.HALF_VISIBLE_WIDTH)
        {
            // HERO IS WITHIN EXTREME LEFT PORTION OF SCROLLABLE WORLD
            // So... actually move the actor within the visible world.

            // Don't let hero go off left edge of scrollable world 
            // (Allow movement only when not at left edge)
            if (currentScrollableWorldXPosition > 0)
            {
                // Move left in visible world
                int newVisibleWorldXPosition = getX() - deltaX;
                setLocation(newVisibleWorldXPosition, getY());

                // Track position in wider scrolling world
                currentScrollableWorldXPosition = getX();
            }            
        }
        else if (currentScrollableWorldXPosition + deltaX * 2 > world.SCROLLABLE_WIDTH - world.HALF_VISIBLE_WIDTH)
        {
            // HERO IS WITHIN EXTREME RIGHT PORTION OF SCROLLABLE WORLD
            // So... actually move the actor within the visible world.

            // Move left in visible world
            int newVisibleWorldXPosition = getX() - deltaX;
            setLocation(newVisibleWorldXPosition, getY());

            // Track position in wider scrolling world
            currentScrollableWorldXPosition -= deltaX;
        }   
    }

    /**
     * When the hero falls off the bottom of the screen,
     * game is over. We must remove them.
     */
    public void checkGameOver()
    {
        // Get object reference to world
        SideScrollingWorld world = (SideScrollingWorld) getWorld(); 

        // Vertical position where hero no longer visible
        int offScreenVerticalPosition = (world.getHeight() + this.getImage().getHeight() / 2);

        // Off bottom of screen?
        if (this.getY() > offScreenVerticalPosition)
        {
            // Remove the hero
            isGameOver = true;
            world.setGameOver();
            world.removeObject(this);

            // Tell the user game is over
            world.showText("GAME OVER", world.getWidth() / 2, world.getHeight() / 2);
        }
    }

    public void moveUp()
    {
        // set vertical move speed
        deltaY = -4;
        if ( isTouching(Ladder.class) ) 
        {
            //set image
            if (verticalDirection == CLIMBING_UP)
            {
                setImage("hero-climb-up.png");
            }
            else
            {
                setImage("hero-climb-up.png");
            }

            int newVisibleWorldYPosition = getY() + deltaY;
            setLocation(getX(), newVisibleWorldYPosition);

            // Track position in wider scrolling world
            currentScrollableWorldYPosition = getY();
        }

    }

    public void touchUfo()
    {
        if ( isTouching(Ufo.class) ) 
        {
            //things do after touch ufo
            World myWorld = getWorld();
            removeTouching(Ufo.class);
            SideScrollingWorld world = (SideScrollingWorld)myWorld;
            Counter counter = world.getCounter();
            counter.removeScore();
            GreenfootSound removeScore = new GreenfootSound("Donkey Kong eat ufo sound effect.wav");
            removeScore.play();
        }
    }

    public void encounterMonster()
    {
        if (isTouching(Monster.class))
        {
            //End the game when the hero touches the monster
            removeTouching(Monster.class);
            SideScrollingWorld sidescrollingworld = (SideScrollingWorld)getWorld();
            sidescrollingworld.setGameOver();
            isGameOver = true;
            sidescrollingworld.showText("GAME OVER", sidescrollingworld.getWidth() / 2, sidescrollingworld.getHeight() / 2);
            GreenfootSound Dieing = new GreenfootSound("die music.wav");
            Dieing.play();
        }    
    }

    public void encounterDonkeykong()
    {
        if (isTouching(Donkeykong.class))
        {
            //End the game when the hero touches the donkeykong
            removeTouching(Donkeykong.class);
            SideScrollingWorld sidescrollingworld = (SideScrollingWorld)getWorld();
            sidescrollingworld.setGameOver();
            isGameOver = true;
            sidescrollingworld.showText("GAME OVER", sidescrollingworld.getWidth() / 2, sidescrollingworld.getHeight() / 2);
            GreenfootSound Dieing = new GreenfootSound("die music.wav");
            Dieing.play();
            Greenfoot.stop();
        }    
    }

    public void encounterPrinces()
    {
        if (isTouching(Princes.class))
        {
            //things do after touch princes, success
            removeTouching(Princes.class);
            SideScrollingWorld sidescrollingworld = (SideScrollingWorld)getWorld();
            sidescrollingworld.setGameOver();
            isGameOver = true;
            sidescrollingworld.showText("NEXT LEVEL", sidescrollingworld.getWidth() / 2, sidescrollingworld.getHeight() / 2);
            GreenfootSound Dieing = new GreenfootSound("die music.wav");
            Dieing.play();
        }    
    }
}    

