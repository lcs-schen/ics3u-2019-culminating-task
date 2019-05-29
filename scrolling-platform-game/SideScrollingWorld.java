import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Template for a side-scrolling platform game.
 * 
 * @author R. Gordon
 * @version May 8, 2019
 */
public class SideScrollingWorld extends World
{
    /**
     * Instance variables
     * 
     * These are available for use in any method below.
     */    
    // Tile size in pixels for world elements (blocks, clouds, etc)
    // TO STUDENTS: Modify if your game's tiles have different dimensions
    private static final int TILE_SIZE = 32;
    private static final int HALF_TILE_SIZE = TILE_SIZE / 2;

    // World size constants
    // TO STUDENTS: Modify only if you're sure
    //              Should be a resolution that's a multiple of TILE_SIZE
    private static final int VISIBLE_WIDTH = 640;
    private static final int VISIBLE_HEIGHT = 480;

    // Additional useful constants based on world size
    public static final int HALF_VISIBLE_WIDTH = VISIBLE_WIDTH / 2;
    private static final int HALF_VISIBLE_HEIGHT = VISIBLE_HEIGHT / 2;

    // Defining the boundaries of the scrollable world
    // TO STUDENTS: Modify SCROLLABLE_WIDTH if you wish to have a longer level
    public static final int SCROLLABLE_WIDTH = VISIBLE_WIDTH;
    private static final int SCROLLABLE_HEIGHT = VISIBLE_HEIGHT;

    // Hero
    Hero theHero;

    //add sound
    GreenfootSound myMusic = new GreenfootSound("Donkey Kong 64 - Angry Aztec.mp3");

    //add counter and timer
    Timer timer = new Timer();
    Counter counter = new Counter();
    private int time;

    // Track whether game is on
    private boolean isGameOver;

    /**
     * Constructor for objects of class SideScrollingWorld.
     */
    public SideScrollingWorld()
    {    
        // Create a new world with 640x480 cells with a cell size of 1x1 pixels.
        // Final argument of 'false' means that actors in the world are not restricted to the world boundary.
        // See: https://www.greenfoot.org/files/javadoc/greenfoot/World.html#World-int-int-int-boolean-
        super(VISIBLE_WIDTH, VISIBLE_HEIGHT, 1, false);

        // Set up the starting scene
        setup();

        // Game on
        isGameOver = false;
    }

    /**
     * Set up the entire world.
     */
    private void setup()
    {
        // TO STUDENTS: Add, revise, or remove methods as needed to define your own game's world
        addGround();
        addFence1();
        addFence2();
        addMetalPlateSteps();
        addLadder();
        addHero();
        addCarriage();
        addBase();
        addFire();
        addObject(counter, 80, 20);
        addObject(timer, 550, 20);
        addObject ( new Ball(), 200, 350 );
        addObject ( new Ball(), 400, 350 );
        addObject ( new Ball(), 600, 350 );
        addObject ( new Pig(), 300, 307 );
    }

    public void act()
    {
        myMusic.play();
    }

    /**
     * Add blocks to create the ground to walk on at bottom-left of scrollable world.
     */
    private void addGround()
    {
        for (int i = 0; i <=19; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 15 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ground Ground = new Ground(x, y);
            addObject(Ground, x, y);
        }   

        for (int i = 1; i <=3; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 9 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ground Ground = new Ground(x, y);
            addObject(Ground, x, y);
        }   

        for (int i = 6; i <=13; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 9 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ground Ground = new Ground(x, y);
            addObject(Ground, x, y);
        }   

        for (int i = 16; i <=19; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 9 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ground Ground = new Ground(x, y);
            addObject(Ground, x, y);
        }   

        for (int i = 7; i <=11; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ground someGround = new Ground(x, y);
            addObject(someGround, x, y);
        }
    }

    /**
     * Add some fences at left and right side.
     */
    private void addFence1()
    {
        for (int i = 0; i <=0; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 12 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Fence1 fence = new Fence1(x, y);
            addObject(fence, x, y);
        }   

        for (int i = 11; i <=11; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 6 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Fence1 fence = new Fence1(x, y);
            addObject(fence, x, y);
        }   

        for (int i = 0; i <=0; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 3 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Fence1 fence = new Fence1(x, y);
            addObject(fence, x, y);
        }   

    }

    /**
     * Add some fences at left and right side.
     */
    private void addFence2()
    {
        for (int i = 19; i <=19; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 12 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Fence2 fence = new Fence2(x, y);
            addObject(fence, x, y);
        }   

        for (int i = 8; i <=8; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 6 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Fence2 fence = new Fence2(x, y);
            addObject(fence, x, y);
        }   

        for (int i = 19; i <=19; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 3 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Fence2 fence = new Fence2(x, y);
            addObject(fence, x, y);
        }   
    }

    /**
     * Add steps made out of metal plates leading to end of world.
     */
    private void addMetalPlateSteps()
    {
        for (int i = 1; i <=18; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 12 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            MetalPlate plate = new MetalPlate(x, y);
            addObject(plate, x, y);
        }   

        for (int i = 0; i <=7; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 6 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            MetalPlate plate = new MetalPlate(x, y);
            addObject(plate, x, y);
        }   

        for (int i = 12; i <=19; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 6 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            MetalPlate plate = new MetalPlate(x, y);
            addObject(plate, x, y);
        } 

        for (int i = 1; i <=18; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 3 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            MetalPlate plate = new MetalPlate(x, y);
            addObject(plate, x, y);
        }
    }

    /**
     * Add the hero to the world.
     */
    private void addHero()
    {
        // Initial horizontal position
        int initialX = TILE_SIZE * 3;

        // Instantiate the hero object
        theHero = new Hero(initialX);

        // Add hero in bottom left corner of screen
        addObject(theHero, initialX, getHeight() / 4 * 3);
    }

    /**
     * Add blocks to create the ground to walk on at top-right of scrollable world.
     */
    private void addLadder()
    {
        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 2 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 13 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        } 

        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 7 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 13 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        } 

        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 12 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 13 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        } 

        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 17 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 13 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        } 

        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 6 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 10 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        } 

        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 13 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 10 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        } 

        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 2 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 7 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        } 

        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 7 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 7 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        } 

        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 12 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 7 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        } 

        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 17 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 7 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        } 

        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 1 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 4 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);

        } 

        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 18 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 4 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        } 

        for (int i = 0; i <=1 ; i ++)
        {
            //Location
            int x = 5 * TILE_SIZE + HALF_TILE_SIZE;
            int y = 1 * TILE_SIZE - HALF_TILE_SIZE + i * TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        } 

        for (int i = 7; i <=7; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 2 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        }   

        for (int i = 11; i <=11; i += 1)
        {
            //Location
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = 2 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Ladder ladder = new Ladder(x, y);
            addObject(ladder, x, y);
        }   
    }

    /**
     * Add the hero to the world.
     */
    private void addCarriage()
    {
        for (int i = 10; i <=10; i += 1)
        {
            //Location
            int x = i * TILE_SIZE;
            int y = 8 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Carriage carriage = new Carriage(x, y);
            addObject(carriage, x, y);
        }   
    }

    /**
     * Add the hero to the world.
     */
    private void addBase()
    {
        for (int i = 10; i <=10; i += 1)
        {
            //Location
            int x = i * TILE_SIZE;
            int y = 7 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Base base = new Base(x, y);
            addObject(base, x, y);
        }   
    }

    /**
     * Add the hero to the world.
     */
    private void addFire()
    {
        for (int i = 10; i <=10; i += 1)
        {
            //Location
            int x = i * TILE_SIZE;
            int y = 6 * TILE_SIZE - HALF_TILE_SIZE;

            //CREATE AND ADD TO WORLD
            Fire fire = new Fire(x, y);
            addObject(fire, x, y);
        }   
    }

    /**
     * Return an object reference to the hero.
     */
    public Hero getHero()
    {
        return theHero;
    }

    /**
     * Get counter in the world.
     */
    public Counter getCounter()
    {
        return counter;
    }

    /**
     * Set game over
     */
    public void setGameOver()
    {
        isGameOver = true;
    }
   
}

