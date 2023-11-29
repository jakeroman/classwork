# CSC 3510 Intro to Artifical Intelligence
# Jake Roman, Joseph DeMarco
# Use breadth-first search (BFS) to help Robby the Robot pick up cans without running out of battery.

import argparse
from collections import deque
import pdb
from queue import Queue
from robby.graphics import *
from robby import World
import time

# Use argparse to allow user to enter command line arguments for:
#   *file - a text file containing the world design (required)
#   *actions - a string defining the order of actions to search (optional, default='GNESW')
#   *battery - an integer defining the full battery power (optional, default=7)
#   *verbose - a flag to display details about the search

# Argparse setup
parser = argparse.ArgumentParser(description="Use breadth-first search (BFS) to help Robby the Robot pick up cans without running out of battery")
parser.add_argument("file", help = "text file containing the world design")
parser.add_argument('-a',"--actions", dest = "actions", default="GNESW", help="string defining the order of actions to search")
parser.add_argument('-b',"--battery", dest = "battery", default=7, type=int, help="integer defining the full battery power")
parser.add_argument('-v',"--verbose", action="store_true", dest = "verbose", default=False, help="flag to display details about the search")

def main(file, actions, battery, verbose):
    # Read world parameters (size, location of Robby, and contents) from file
    rows, cols = 1, 1
    r0, c0 = 0, 0
    contents = 'E'
    
    with open(file,"r") as f:
        rows, cols = map(int, f.readline().split()) # map first line to rows and columns
        r0, c0 = map(int, f.readline().split()) # map second line to robby position
        contents = "" # reset contents variable
        for i in range(rows):
            contents += f.readline().strip() # add line to contents
    contents = contents.replace('.','E')

    # Create Robby's world
    rw = World(rows, cols)
    rw.graphicsOn()
    rw.load(contents) # load in the contents we received from the file
    rw.goto(r0, c0) # position robby in the requested place
    rw.setFullBattery(battery) # update battery level to battery argument

    # Play in Robby's world
    path = ''
    while True:
        # Check to see if Robby has picked up all the cans
        if rw.getState().count("C") == 0:
            rw.graphicsOff("Robby wins!")

        # Handle key presses
        key = rw.checkKey()
        if key:
            if key == "Escape":
                break
            elif key == "Up":
                rw.north()
            elif key == "Down":
                rw.south()
            elif key == "Right":
                rw.east()
            elif key == "Left":
                rw.west()
            elif key == "space":
                rw.grab()
            elif key == "d": # debug
                pdb.set_trace()
            elif key == "r": # reset the world
                rw.reset()
                rw.goto(r0, c0)
                rw.graphicsOn()
            elif key == "s": # display the current world at the command line
                rw.show()
            elif key == "b": # BFS
                print('Running breadth-first search...')
                time.sleep(0.5)
                path = bfs(rw, contents, actions, verbose=verbose)
                if len(path) > 0:
                    print(path)
                else:
                    print("No solution found.")
            elif key == "Return":
                # Use the discovered path (from bfs) to actually move robby through
                # the world! Add a small time delay with time.sleep() so that robby does not move too fast.
                rw.reset()
                rw.goto(r0, c0)
                time.sleep(0.5)

                # Simulate actions using the sequence stored in the path variable
                for action in path:
                    if action == 'N':
                        rw.north()
                    elif action == 'E':
                        rw.east()
                    elif action == 'S':
                        rw.south()
                    elif action == 'W':
                        rw.west()
                    elif action == 'G':
                        rw.grab()
                    time.sleep(0.5)


def bfs(rw, state, actions, verbose=False):
    '''Perform breadth-first search on the world state given an ordered string of actions to check (e.g. 'GNESW').'''
    count = 0 # counter to see how many paths we explored

    # BFS Attempt #1
    queue = Queue() # create a new queue for BFS
    queue.put('') # add empty sequence (to indicate starting position)

    while True:
        if queue.empty():
            # we have no nodes to explore :(
            if verbose: print('--> searched {} paths'.format(count))
            return ''

        node = queue.get() # pop next node off queue
        print("> " + node + " removed from queue")
        
        count += 1
        if issolved(rw, state, node): # check if the current node is the solution   
            if verbose: print('--> searched {} paths'.format(count))
            return node # if it is, update the path variable and break

        for action in actions: # for each action in our action order, try adding that to our sequence
            newNode = node + action
            if (isvalid(rw, state, newNode)): # check if that sequence is valid
                queue.put(newNode) # awesome, add it to the queue
                print(newNode + " added to queue")

def issolved(rw, state, path):
    '''Check whether a series of actions (path) taken in Robby's world results in a solved problem.'''
    rows, cols = rw.numRows, rw.numCols # size of the world
    row, col = rw.getCurrentPosition() # Robby's current (starting) position
    state = list(state) # convert the string to a list so we can update it
    battery = rw.fullBattery

    # Simulate path and check if it solves the maze
    for action in path:
        battery -= 1 # use up one point of battery
        if action == 'N':
            row -= 1; # move robby up 1
        elif action == 'S':
            row += 1; # move robby down 1
        elif action == 'W':
            col -= 1; # move robby left 1
        elif action == 'E':
            col += 1; # move robby right 1
        elif action == 'G':
            strpos = (cols*row)+col
            if state[strpos] == 'C':
                # robby picks up a can
                state[strpos] = 'E' # update space to empty
            elif state[strpos] == 'B':
                # robby picks up a battery
                state[strpos] = 'E' #update space to empty
                battery = rw.fullBattery # fully fill robby's battery

        # Did Robby run out of battery?
        if battery <= 0:
            return False

        # Did Robby grab all the cans?
        if state.count("C") == 0:
            return True

    return False # if we made it this far, we did not complete the goal

def isvalid(rw, state, path):
    '''Check whether a series of actions (path) taken in Robby's world is valid.'''
    rows, cols = rw.numRows, rw.numCols  # size of the maze
    row, col = rw.getCurrentPosition()  # robby's current (starting) position
    state = list(state)
    memory = []  # keep track of where robby has been to prohibit "loops"
    battery = rw.fullBattery

    # Simulate path and check if it is valid
    for action in path:
        # move robby depending on action value
        battery -= 1 # use up one point of battery
        if action == 'N':
            row -= 1; # move robby up 1
        elif action == 'S':
            row += 1; # move robby down 1
        elif action == 'W':
            col -= 1; # move robby left 1
        elif action == 'E':
            col += 1; # move robby right 1
        elif action == 'G':
            strpos = (cols*row)+col
            if state[strpos] == 'C':
                # robby picks up a can
                state[strpos] = 'E' # update space to empty
            elif state[strpos] == 'B':
                # robby picks up a battery
                state[strpos] = 'E' #update space to empty
                battery = rw.fullBattery # fully fill robby's battery
            else:
                return False #not allowed to pickup on empty squares
        
        # Path is invalid if Robby has run out of battery
        if battery <= 0:
            return False

        # Path is invalid if Robby's goes "out of bounds"
        if row >= rows or col >= cols or row < 0 or col < 0:
            return False

        # Path is invalid if Robby runs into a wall
        strpos = (cols*row)+col
        if state[strpos] == 'W':
            return False

        # Path is invalid if robby repeats a state in memory
        if (row, col, "".join(state)) in memory:
            return False
        memory.append((row, col, "".join(state)))  # add the new state to memory

    return True  # if we made it this far, the path is valid

if __name__ == '__main__':
    args = parser.parse_args()
    main(args.file, args.actions, args.battery, args.verbose)
