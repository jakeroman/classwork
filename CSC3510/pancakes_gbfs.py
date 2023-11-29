# pancakes.py | Jake Roman & Riley Peters | CSC3510 HW3
# Flipping pancakes with greedy best-first search (GBFS).

import argparse
from graphics import *
from matplotlib import cm
import pdb
import numpy as np
from queue import PriorityQueue
import random
import time

parser = argparse.ArgumentParser(
    description="Use greedy best-first search (GBFS) to optimally flip a stack of pancakes"
)
parser.add_argument(
    "-n", "--num", metavar="pancakes", type=int, help="number of pancakes", default=8
)
parser.add_argument(
    "--seed", type=int, help="seed for randomly arranging pancakes initially"
)
parser.add_argument(
    "--verbose",
    help="adds additional print statements to the GBFS function",
    action="store_true",
)
parser.add_argument(
    "--fast",
    help="Sets the delay between automatic flips to 0.05s",
    action="store_true",
)


def main(args):
    # Parse inputs
    n = args.num  # number of pancakes
    stack = list(range(n))
    if args.seed is not None:  # randomly shuffle the pancakes initially
        random.seed(args.seed)
        random.shuffle(stack)

    # Make the graphical user interface
    gui = guisetup(stack)
    # Pancake dictionary
    pancake_dict = {i: pancake for i, pancake in enumerate(gui.items[:-2])}

    # This is ungodly lazy, but it works :P
    flip(gui, stack, 1, pancake_dict)
    gui.items[-1].setText("")
    # Stored path for gbfs
    path = ""

    # Use the graphical user interface
    while True:
        key = gui.checkKey()
        if key:
            if key == "Escape":  # quit the program
                break
            elif key == "d":  # debug the program
                pdb.set_trace()
            elif key == "g":  # run greedy best-first search
                path = gbfs(gui, stack, True if args.verbose else False)
            elif key == "Return":  # execute stored gbfs path
                print("Running stored path: ", path)
                for action in path:
                    stack = flip(gui, stack, int(action), pancake_dict)
                    time.sleep(0.05) if args.fast else time.sleep(0.25)
            elif key in [
                str(i) for i in range(1, n + 1)
            ]:  # manually flip some of the pancakes
                stack = flip(gui, stack, int(key), pancake_dict)

    gui.close()


def guisetup(stack):
    """Create graphical user interface for a stack of n pancakes."""
    n = len(stack)  # number of pancakes in the stack
    thickness = 12  # thickness of each pancake, in pixels
    margin = 40
    wid = margin * 2 + 30 * max(n + 1, 9)  # each successive pancake gets 30 px wider
    hei = margin * 2 + n * thickness  # top/bottom margins of 40 px + 12 px per pancake
    gui = GraphWin("Pancakes", wid, hei)

    cx = wid / 2  # center of width
    cmap = cm.get_cmap("YlOrBr", n + 1)

    # Draw pancakes
    # ***ENTER CODE HERE*** (10 lines)
    for i in range(1, n + 1):
        pos = (
            len(stack) - i + 1
        )  # assign a variable representing the position of this pancake in the stack
        line = Line(
            Point(cx - 15 * (pos), pos * thickness + margin),
            Point(cx + 15 * (pos), pos * thickness + margin),
        )
        line.setWidth(thickness)
        r, g, b, a = cmap(pos)
        line.setFill(color_rgb(int(r * 255), int(g * 255), int(b * 255)))
        line.draw(gui)

    # Add text objects for instructions and status updates
    instructions = Text(
        Point(10, hei - 12),
        "Press a # to flip pancakes, 'g' to run GBFS, Escape to quit, Enter to run path",
    )
    instructions._reconfig("anchor", "w")
    instructions.setSize(8)
    instructions.draw(gui)

    status = Text(Point(cx, 20), "")
    status._reconfig("anchor", "center")
    status.setSize(12)
    status.draw(gui)

    # Return gui object
    return gui


def flip(gui: GraphWin, stack: list[int], p: int, pancake_dict: dict) -> list[int]:
    """Flip p pancakes in an ordered stack."""
    # print("Flipping", p, "pancakes" if p > 1 else "pancake")

    # Get graphics objects from GUI
    obj = gui.items
    pancakes = obj[:-2]
    status = obj[-1]
    # Update status text on GUI
    status.setText(f"Flipping {p} pancake{'s' if p > 1 else ''}")

    # Update the stack (which is separate from the graphics objects)
    # ***ENTER CODE HERE*** (2 lines)

    stack = stack[::-1]  # I have a deep grevience about this

    first_part = stack[:p][::-1]
    second_part = stack[p:]
    stack = first_part + second_part

    stack = stack[::-1]  # Oopsie woopsie

    updated_pancakes = [pancake_dict[i] for i in stack]
    #print(stack)

    # Move pancakes around in the GUI
    # ***ENTER CODE HERE*** (5 lines)
    thickness = 12
    margin = 40
    for i, pancake in enumerate(updated_pancakes):
        target_pos = (len(stack) - i) * thickness + margin
        pancake.move(0, target_pos - pancake.p1.getY())

    return stack


def cost(stack):
    """Compute the cost h(stack) for a given stack of pancakes.
    Here, we define cost as the number of pancakes in the wrong position."""
    # ***MODIFY CODE HERE*** (2 lines)
    return sum([stack[i] != i for i in range(len(stack))])


def gbfs(gui, stack, verbose=False):
    """Run greedy best-first search on a stack of pancakes and return the solution path."""
    print("Running greedy best-first search...")

    # Get graphics objects from GUI
    obj = gui.items
    pancakes = obj[:-2]
    status = obj[-1]

    # Update status text on GUI
    status.setText(f"Running greedy best-first search...")
    time.sleep(0.5)

    # ***MODIFY CODE HERE*** (20-25 lines)
    cnt = 0

    pq = PriorityQueue()
    pq.put((cost(stack), ""))  # add initial state to queue

    visited = []
    gbfs_path = ""

    visited.append(stack)

    while not pq.empty():
        # explore next node in queue
        node = pq.get()
        cnt += 1

        # simulate and calculate cost
        node_path = node[1]
        node_cost = node[0]
        if verbose:
            print(node_cost, node_path)

        # check for correctly sorted stack
        if node_cost == 0:
            # found solution
            gbfs_path = node_path
            break

        for move in range(2, len(pancakes) + 1):  # iterate over options
            option_path = node_path + str(move)
            option_state = simulate(stack, option_path)
            option_cost = cost(option_state)

            if not option_state in visited:  # make sure we haven't been here before
                visited.append(option_state)

                pq.put((option_cost, option_path))

    print(f"searched {cnt} paths")
    print("solution: ", gbfs_path)
    status.setText("...search is complete")
    return gbfs_path


def simulate(stack, path):
    """Simulate the flipping of pancakes to determine the resulting stack."""
    fakestack = stack.copy()  # make a copy so we don't actually change the real stack
    for action in path:
        try:
            p = int(action)  # how many pancakes are we trying to flip?
            for i in range(1, p // 2 + 1):
                fakestack[-i], fakestack[-(p - i + 1)] = (
                    fakestack[-(p - i + 1)],
                    fakestack[-i],
                )
        except:
            print("INVALID ACTION: Check code")

    return fakestack


if __name__ == "__main__":
    main(parser.parse_args())
