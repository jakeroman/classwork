import java.lang.Math;
import java.util.Scanner;

class Maze {
    static double STEP_SIZE = 0.1;
    static int DISPLAY_SIZE_X = 120;
    static int DISPLAY_SIZE_Y = 20;

    public static double castRay(World world, double startX, double startY, double angle) {
        double angleRadians = Math.toRadians(angle);
        double stepX = Math.cos(angleRadians) * STEP_SIZE;
        double stepY = Math.sin(angleRadians) * STEP_SIZE;
        double x = startX;
        double y = startY;

        for (int i=0; i < 100; i++) {
            x += stepX;
            y += stepY;

            int rx = (int)Math.round(x);
            int ry = (int)Math.round(y);

            int block = world.getBlock(rx,ry);
            if (block > 0) {
                // stop
                return i*STEP_SIZE;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        // Variables
        double posX = (World.WORLD_SIZE/2);
        double posY = (World.WORLD_SIZE/2);
        double facing = 0;

        // Setup Scanner
        Scanner scanner = new Scanner(System.in);

        // Generate World
        World world = new World();
        for (int x=0; x<World.WORLD_SIZE; x++) {
            for (int y=0; y<World.WORLD_SIZE; y++) {
                int val = 0;
                if (x%2 == 0 && y%2 == 0) {
                    val = 1;
                }
                if ((x%2 == 1 && y%2 == 0) || (x%2 == 0 && y%2 == 1)) {
                    val = (int)(Math.random()*1.5);
                }

                if (x>100 && x<156 && y>100 && y<156) { // only generate blocks within 28 square of spawn
                    world.setBlock(x, y, val);
                }
            }
        }

        double deltaFacing = 0;
        while (true) {
            // Create Display Buffer
            char[][] display = new char[DISPLAY_SIZE_X][DISPLAY_SIZE_Y];
            
            // Render
            for (int x = 0; x < DISPLAY_SIZE_X; x++) {
                double distance = castRay(world, posX, posY, facing + (x-(DISPLAY_SIZE_X/2)));
    
                for (int y = 0; y < DISPLAY_SIZE_Y; y++) {
                    char pixel = ' ';

                    if (distance > 0 && Math.abs(y-(DISPLAY_SIZE_Y/2)) <= ((DISPLAY_SIZE_Y/2)-distance)) {
                        if (distance > DISPLAY_SIZE_Y*0.3)
                            pixel = '-';
                        else if (distance > DISPLAY_SIZE_Y*0.15)
                            pixel = '=';
                        else
                            pixel = '#';
                    }
                    else if (y > DISPLAY_SIZE_Y/2) {
                        pixel = '_';
                    }
    
                    display[x][y] = pixel;
                }
            }
    
            // Display
            for (int y = 0; y < DISPLAY_SIZE_Y; y++) {
                String ln = "";
                for (int x = 0; x < DISPLAY_SIZE_X; x++) {
                    ln = ln + display[x][y];
                }
                System.out.println(ln);
            }
            
            // Target
            int targetX = (int)Math.round(posX + Math.cos(Math.toRadians(facing)));
            int targetY = (int)Math.round(posY + Math.sin(Math.toRadians(facing)));
            int revTargetX = (int)Math.round(posX + Math.cos(Math.toRadians(facing+180)));
            int revTargetY = (int)Math.round(posY + Math.sin(Math.toRadians(facing+180)));

            // User Input
            if (Math.abs(deltaFacing) < 1) {
                String input = scanner.next();
                if (input.compareTo("a") == 0) {
                    deltaFacing -= 90;
                } 
                else if (input.compareTo("d") == 0) {
                    deltaFacing += 90;
                }
                else if (input.compareTo("w") == 0 && (world.getBlock(targetX,targetY) == 0)) {
                    double angleRadians = Math.toRadians(facing);
                    posX += Math.cos(angleRadians);
                    posY += Math.sin(angleRadians);
                } 
                else if (input.compareTo("s") == 0 && (world.getBlock(revTargetX,revTargetY) == 0)) {
                    double angleRadians = Math.toRadians(facing);
                    posX -= Math.cos(angleRadians);
                    posY -= Math.sin(angleRadians);
                }
            }
            else {
                if (deltaFacing > 0) {
                    deltaFacing -= 15;
                    facing += 15;
                }
                else {
                    deltaFacing += 15;
                    facing -= 15;
                }
                // this is a bad way to make delay but it works
                long endTime = System.currentTimeMillis() + 30;
                while (System.currentTimeMillis() < endTime) 
                {
                    // do nothing
                }
            }
        }
    }
}
