// Jake Roman's C++ Tetris
#include <iostream>
#include <windows.h>
#include <time.h>
using namespace std;

// Static variables
static int playerblocks[10][20] { { } }; // had to make this array static and global because C++ doesn't let you pass arrays through functions apparently.

bool generatePlayerBlocks(int piece, int x_pos, int y_pos, int rotation) // generates a playfield containing the positions of the blocks the player currently has control of, also returns false if given coordinates would place the piece outside of the playfield
{
    // Reset playerblocks array
    for (int x=0; x<=10; x++) {
        for (int y=0; y<=18; y++) {
            playerblocks[x][y] = 0;
        }
    }

    // Pieces if statements
    int pieceWidth;
    if (piece == 1) { // line piece
        if (rotation == 0 || rotation == 2) {   
            pieceWidth = 1;
            playerblocks[x_pos][y_pos] = 1;
            playerblocks[x_pos][y_pos+1] = 1;
            playerblocks[x_pos][y_pos+2] = 1;
            playerblocks[x_pos][y_pos+3] = 1;
        }
        else {
            pieceWidth = 4;
            playerblocks[x_pos][y_pos] = 1;
            playerblocks[x_pos+1][y_pos] = 1;
            playerblocks[x_pos+2][y_pos] = 1;
            playerblocks[x_pos+3][y_pos] = 1;
        }
    }
    else if (piece == 2) { // square block
        pieceWidth = 2;
        playerblocks[x_pos][y_pos] = 1;
        playerblocks[x_pos][y_pos+1] = 1;
        playerblocks[x_pos+1][y_pos] = 1;
        playerblocks[x_pos+1][y_pos+1] = 1;
    }
    else if (piece == 3) { // t piece
        if (rotation == 0) {
            pieceWidth = 2;
            playerblocks[x_pos][y_pos] = 1;
            playerblocks[x_pos][y_pos+1] = 1;
            playerblocks[x_pos][y_pos+2] = 1;
            playerblocks[x_pos+1][y_pos+1] = 1;
        }
        else if (rotation == 1) {
            pieceWidth = 3;
            playerblocks[x_pos][y_pos] = 1;
            playerblocks[x_pos+1][y_pos] = 1;
            playerblocks[x_pos+2][y_pos] = 1;
            playerblocks[x_pos+1][y_pos+1] = 1;
        }
        else if (rotation == 2) {
            pieceWidth = 2;
            playerblocks[x_pos+1][y_pos] = 1;
            playerblocks[x_pos+1][y_pos+1] = 1;
            playerblocks[x_pos+1][y_pos+2] = 1;
            playerblocks[x_pos][y_pos+1] = 1;
        }
        else if (rotation == 3) {
            pieceWidth = 3;
            playerblocks[x_pos][y_pos+1] = 1;
            playerblocks[x_pos+1][y_pos+1] = 1;
            playerblocks[x_pos+2][y_pos+1] = 1;
            playerblocks[x_pos+1][y_pos] = 1;
        }
    }
    else if (piece == 4) { // inverse l piece
        if (rotation == 0) {
            pieceWidth = 2;
            playerblocks[x_pos][y_pos] = 1;
            playerblocks[x_pos][y_pos+1] = 1;
            playerblocks[x_pos][y_pos+2] = 1;
            playerblocks[x_pos+1][y_pos] = 1;
        }
        else if (rotation == 1) {
            pieceWidth = 3;
            playerblocks[x_pos][y_pos] = 1;
            playerblocks[x_pos+1][y_pos] = 1;
            playerblocks[x_pos+2][y_pos] = 1;
            playerblocks[x_pos+2][y_pos+1] = 1;
        }
        else if (rotation == 2) {
            pieceWidth = 2;
            playerblocks[x_pos+1][y_pos] = 1;
            playerblocks[x_pos+1][y_pos+1] = 1;
            playerblocks[x_pos+1][y_pos+2] = 1;
            playerblocks[x_pos][y_pos+2] = 1;
        }
        else if (rotation == 3) {
            pieceWidth = 3;
            playerblocks[x_pos][y_pos+1] = 1;
            playerblocks[x_pos+1][y_pos+1] = 1;
            playerblocks[x_pos+2][y_pos+1] = 1;
            playerblocks[x_pos][y_pos] = 1;
        }
    }
    else if (piece == 4) { // l piece
        if (rotation == 0) {
            pieceWidth = 2;
            playerblocks[x_pos+1][y_pos] = 1;
            playerblocks[x_pos+1][y_pos+1] = 1;
            playerblocks[x_pos+1][y_pos+2] = 1;
            playerblocks[x_pos][y_pos] = 1;
        }
        else if (rotation == 1) {
            pieceWidth = 3;
            playerblocks[x_pos][y_pos+1] = 1;
            playerblocks[x_pos+1][y_pos+1] = 1;
            playerblocks[x_pos+2][y_pos+1] = 1;
            playerblocks[x_pos+2][y_pos] = 1;
        }
        else if (rotation == 2) {
            pieceWidth = 2;
            playerblocks[x_pos][y_pos] = 1;
            playerblocks[x_pos][y_pos+1] = 1;
            playerblocks[x_pos][y_pos+2] = 1;
            playerblocks[x_pos+1][y_pos+2] = 1;
        }
        else if (rotation == 3) {
            pieceWidth = 3;
            playerblocks[x_pos][y_pos] = 1;
            playerblocks[x_pos+1][y_pos] = 1;
            playerblocks[x_pos+2][y_pos] = 1;
            playerblocks[x_pos][y_pos+1] = 1;
        }
    }
    else if (piece == 5) { // squiggly piece
        if (rotation == 0 || rotation == 2) {
            pieceWidth = 2;
            playerblocks[x_pos][y_pos] = 1;
            playerblocks[x_pos][y_pos+1] = 1;
            playerblocks[x_pos+1][y_pos+1] = 1;
            playerblocks[x_pos+1][y_pos+2] = 1;
        }
        else if (rotation == 1 || rotation == 3) {
            pieceWidth = 3;
            playerblocks[x_pos+1][y_pos] = 1;
            playerblocks[x_pos+2][y_pos] = 1;
            playerblocks[x_pos][y_pos+1] = 1;
            playerblocks[x_pos+1][y_pos+1] = 1;
        }
    }
    else if (piece == 6) { // inverse squiggly piece
        if (rotation == 0 || rotation == 2) {
            pieceWidth = 2;
            playerblocks[x_pos+1][y_pos] = 1;
            playerblocks[x_pos+1][y_pos+1] = 1;
            playerblocks[x_pos][y_pos+1] = 1;
            playerblocks[x_pos][y_pos+2] = 1;
        }
        else if (rotation == 1 || rotation == 3) {
            pieceWidth = 3;
            playerblocks[x_pos+1][y_pos+1] = 1;
            playerblocks[x_pos+2][y_pos+1] = 1;
            playerblocks[x_pos][y_pos] = 1;
            playerblocks[x_pos+1][y_pos] = 1;
        }
    }

    // Position validation
    if (x_pos > -1 && (x_pos+pieceWidth) < 11)
        return true;
    else
        return false;
}

bool keyPressed(int keyID) // returns if the given key is currently held
{
    if (GetAsyncKeyState(keyID) == 0)
        return false;
    else
        return true;
}

// Primary routine
int main()
{
    // Variables
    int score = 0; // score counter
    int map[10][20] { { } }; // 2d array representing already placed pieces
    int piece; // type of piece
    int color = 1; // counter to track current color
    int level = 1; // counter to track current level
    int pos_x = 4; // horizontal position of the piece
    int pos_y = 0; // vertical position of the piece
    int rotation = 0; // rotation of the piece
    int dropSpeed = 8; // number to reset dropcounter to
    int dropCounter; // cycles before dropping piece a pixel
    int lines = 0; // counter for number of lines cleared
    bool placedBlock = false; // tracks if last frame a block was placed
    bool rotationDebounce = false; // variable to keep track of last rotation input so you can actually control it

    // Initialization
    keyPressed(VK_UP); keyPressed(VK_DOWN); keyPressed(VK_LEFT); keyPressed(VK_RIGHT); // this is needed to flush the keypress buffer since it always returns true the first time it is called.
    system("cls");
    piece = (rand()%5)+1;
    dropCounter = dropSpeed;
    srand(time(0));
    system("color f");
    system("title Tetris");

    while (true) {
        // Keyboard input
        bool up = keyPressed(VK_UP);
        bool down = keyPressed(VK_DOWN);
        bool left = keyPressed(VK_LEFT);
        bool right = keyPressed(VK_RIGHT);

        // Process input
        if (left == true) {
            pos_x = pos_x - 1;
        }
        if (right == true) {
            pos_x = pos_x + 1;
        }
        if (up == true && rotationDebounce == false) {
            rotation = rotation + 1;
            if (rotation > 3)
            {
                rotation = 0;
            }
        }
        if (down == true) {
            pos_y = pos_y + 1;
        }
        rotationDebounce = up;

        // Movement validation
        bool revert = false;
        if (generatePlayerBlocks(piece, pos_x, pos_y, rotation) == false) { // validate new block position
            // validation check failed, revert inputs
            revert = true;
        }
        else {
            // initial validation check passed, check for block overlap
            for (int x=0; x<=10; x++) {
                for (int y=0; y<=16; y++) {
                    if (map[x][y] != 0 && playerblocks[x][y] != 0) {
                        // pieces overlap
                        revert = true;
                    }
                }
            }
        }
        if (revert == true) {
            if (left == true) {
                pos_x = pos_x + 1;
            }
            if (right == true) {
                pos_x = pos_x - 1;
            }
            if (up == true) {
                rotation = rotation - 1;
                if (rotation < 0)
                {
                    rotation = 3;
                }
            }
        }

        // Block placement
        dropCounter--;
        if (dropCounter <= 0) {
            if (down == false)
                pos_y = pos_y + 1;
            dropCounter = dropSpeed;
        }
        generatePlayerBlocks(piece, pos_x, pos_y+1, rotation); // predict block position next frame
        bool placeBlock = false;
        for (int x=0; x<=10; x++) { // scan for overlap with map
            for (int y=0; y<=17; y++) {
                if (map[x][y] != 0 && playerblocks[x][y] != 0) {
                    // pieces overlap
                    placeBlock = true;
                }
            }
        }
        for (int x=0; x<=10; x++) { // check for blocks below map
            if (playerblocks[x][17] == 1) {
                // block below map
                placeBlock = true;
            }
        }
        if (placeBlock == true) { // commit block position to map
            for (int x=0; x<=10; x++) {
                for (int y=0; y<=17; y++) {
                    if (playerblocks[x][y] != 0) {
                        map[x][y-1] = 1;
                    }
                }
            }
            pos_x = 4;
            pos_y = 0;
            piece = (rand()%5)+1;
            score += level;
        }
        if (placedBlock && placeBlock) {
            // we placed a block this frame, and last frame, this is probably game over.
            COORD coord;
            coord.X = 5;
            coord.Y = 9;
            SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
            system("color 4");
            std::cout << " [ GAME OVER ] ";
            COORD coord2;
            coord2.X = 0;
            coord2.Y = 21;
            SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord2);
            system("Pause");
            return 0;
        }
        placedBlock = placeBlock;

        // Line clear detection
        for (int y=0; y<=17; y++) {
            int counter = 0;
            for (int x=0; x<=10; x++) {
                if (map[x][y] != 0) {
                    counter++;
                }
            }
            if (counter == 11) {
                // Award player for clearing line
                lines++;
                color++;
                score += level * 10;

                if (lines % 10 == 0) // increment level counter every 10 lines cleared
                {
                    level++; 
                    if (dropSpeed > 1)
                        dropSpeed--;
                }

                if (level == 2) // update level color
                    system("color b");
                else if (level == 3)
                    system("color 3");
                else if (level == 4)
                    system("color 9");
                else if (level == 5)
                    system("color a");
                else if (level == 6)
                    system("color d");
                else if (level == 7)
                    system("color c");

                // Duplicate old map & wipe current map
                int old_map[10][20] { { } };
                for (int ax=0; ax<10; ax++) {
                    for (int ay=0; ay<17; ay++) {
                        old_map[ax][ay] = map[ax][ay];
                        map[ax][ay] = 0;
                    }
                }
                
                // Update current map with new data
                for (int ax=0; ax<10; ax++) {
                    for (int ay=0; ay<17; ay++) {
                        if (y > ay) {
                            map[ax][ay+1] = old_map[ax][ay];
                        }
                        else if (y < ay) {
                            map[ax][ay] = old_map[ax][ay];
                        }
                    }
                }
            }
        }

        // Update player blocks
        generatePlayerBlocks(piece, pos_x, pos_y, rotation);

        // Draw screen
        COORD coord;
        coord.X = 0;
        coord.Y = 0;
        SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord); // reset cursor position to 0,0 so that we can overwrite the last frame

        for (int draw_y=0; draw_y<21; draw_y++) { // Line drawing loop
            for (int draw_x=0; draw_x<45; draw_x++) { // Pixel drawing loop
                int map_x = draw_x - 3;
                int map_y = draw_y - 2;
                if (map_x >= 0 && map_y >= 0 && map_x <= 19 && map_y <= 16) {
                    // Playfield rendering
                    if (map_x % 2 == 0) {
                        if (playerblocks[map_x/2][map_y] == 1) {
                            std::cout << "##";
                        }
                        else if (map[map_x/2][map_y] == 1) {
                            std::cout << "[]";
                        }
                        else {
                            std::cout << "  ";
                        }
                    }
                }
                else {
                    // UI rendering
                    if (draw_x >= 26 && ((draw_y >= 2 && draw_y <= 5) || (draw_y == 18))) {
                        if (draw_x == 26) {
                            if (draw_y == 18)
                                std::cout << ".";
                            else
                                std::cout << " ";
                            // Draw content
                            if (draw_y == 18) {
                                std::cout << "... Jake Roman";
                            }
                            else if (draw_y == 3)
                            {
                                std::cout << " SCORE  ";
                                std::cout << score;
                            }
                            else if (draw_y == 4)
                            {
                                std::cout << " LINES  ";
                                std::cout << lines;
                            }
                            else if (draw_y == 5)
                            {
                                std::cout << " LEVEL  ";
                                std::cout << level;
                            }

                            // Draw border
                            COORD coord;
                            coord.X = 42;
                            coord.Y = draw_y;
                            SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
                            std::cout << "...";
                        }
                    }
                    else {
                        std::cout << ".";
                    }
                }
            }
            std::cout << "\n";
        }

        // Delay
        Sleep(30);
    }
}
