import numpy as np
import pylab as plt
import matplotlib as mpl
import json
import math
import os
import matplotlib.animation as animation

def short(theta0, theta1):
    diff = (theta1 - theta0 + math.pi) % (2*math.pi) - math.pi
    if diff < -math.pi:
        return diff + 2*math.pi
    else:
        return diff

def solve_corners(pose, drive):
    x, y, theta = pose
    width = drive.width
    length = drive.length
    sin = np.sin(theta)
    cos = np.cos(theta)
    p0 = (x+(width/2)*cos-(length/2)*sin, y+(length/2)*cos+(width/2)*sin)
    p1 = (x-(width/2)*cos-(length/2)*sin, y+(length/2)*cos-(width/2)*sin)
    p2 = (x+(width/2)*cos+(length/2)*sin, y-(length/2)*cos+(width/2)*sin)
    p3 = (x-(width/2)*cos+(length/2)*sin, y-(length/2)*cos-(width/2)*sin)
    return [[p0, p1], [p0, p2], [p1, p3], [p2, p3]]

def draw_robot(ax, pose, drive):
    lines = mpl.collections.LineCollection(solve_corners(pose,drive),color="black",lw=1)
    ax.add_collection(lines)

def draw_field():
    plt.style.use("classic")
    fig, ax = plt.subplots()
    ax.add_patch(mpl.patches.Rectangle(
        (0, 0),
        30,
        15,
        lw=4,
        edgecolor="black",
        facecolor="none",
    ))
    plt.title("Trajectory")
    plt.xlabel("X Position (meters)")
    plt.ylabel("y Position (meters)")
    plt.ylim(0,8.23)
    plt.xlim(0,16.46)
    plt.gca().set_aspect("equal", adjustable="box")
    return fig, ax

def draw_trajectory(x_coords, y_coords, angular_coords, waypoints, drive, title):
    fig, ax = draw_field()
    draw_robot(ax,[x_coords[0],y_coords[0],angular_coords[0]],drive)
    draw_robot(ax,[x_coords[-1],y_coords[-1],angular_coords[-1]],drive)
    plt.plot(x_coords,y_coords,color="b")
    plt.title(title)
    for waypoint in waypoints:
        draw_robot(ax, waypoint, drive)
    # for pose in zip(x_coords, y_coords, angular_coords):
    #     draw_robot(ax,pose, drive)
        
def animate_trajectory(
    x_coords,
    y_coords,
    angular_coords,
    waypoints,
    drive,
    dt,
    title
):
    
    fig, ax = draw_field()

    for waypoint in waypoints:
        draw_robot(ax, waypoint, drive)

    num_states = len(x_coords)
    plt.plot(x_coords, y_coords)

    def animate(i):
        pose = list(zip(x_coords, y_coords, angular_coords))[i]
        ax.collections = ax.collections[:7]

        draw_robot(ax, pose, drive)
        return ax.collections

    anim = animation.FuncAnimation(
        fig, animate, frames=num_states, interval=dt, blit=True, repeat=True
    )

    if not os.path.exists("animations"):
        os.makedirs("animations")
    anim.save(
        os.path.join("animations", "{}.gif".format(title)),
        writer="pillow",
        dpi=100,
        fps=(int)(1 / dt),
    )
    return anim

def generate_initial_trajectory(waypoints, num_states):
    x, y, theta = [], [], []

    lengths = [0]
    for k in range(len(waypoints)-1):
        lengths.append(lengths[k] + math.hypot(waypoints[k+1][0]-waypoints[k][0],waypoints[k+1][1]-waypoints[k][1]))
    ds = lengths[-1] / (num_states - 1)

    index = 0
    for k in range(num_states):
        s = ds * k
        while (lengths[index + 1] + 0.000001 < s):
            index += 1
        t = (s - lengths[index]) / (lengths[index + 1] - lengths[index])
        x.append((waypoints[index + 1][0] - waypoints[index][0]) * t + waypoints[index][0])
        y.append((waypoints[index + 1][1] - waypoints[index][1]) * t + waypoints[index][1])
        theta.append((waypoints[index + 1][2] - waypoints[index][2]) * t + waypoints[index][2])
    return x, y, theta