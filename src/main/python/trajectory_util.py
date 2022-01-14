import numpy as np
import pylab as plt
import matplotlib as mpl
import json
import math

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
    p0 = (x+(length/2)*cos-(width/2)*sin, y+(width/2)*cos+(length/2)*sin)
    p1 = (x-(length/2)*cos-(width/2)*sin, y+(width/2)*cos-(length/2)*sin)
    p2 = (x+(length/2)*cos+(width/2)*sin, y-(width/2)*cos+(length/2)*sin)
    p3 = (x-(length/2)*cos+(width/2)*sin, y-(width/2)*cos-(length/2)*sin)
    return [[p0, p1], [p0, p2], [p1, p3], [p2, p3]]

def draw_robot(ax, pose, drive):
    lines = mpl.collections.LineCollection(solve_corners(pose,drive),color="black",lw=1)
    ax.add_collection(lines)

def draw_field():
    plt.style.use("ggplot")
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
    plt.xlabel("X Position (ft)")
    plt.ylabel("y Position (ft)")
    plt.ylim(0,15)
    plt.xlim(0,30)
    plt.gca().set_aspect("equal", adjustable="box")
    return fig, ax

def draw_trajectory(x_coords, y_coords, angular_coords, obstacles, drive, title):
    fig, ax = draw_field()
    # draw_robot(ax,[x_coords[0],y_coords[0],angular_coords[0]],drive)
    # draw_robot(ax,[x_coords[-1],y_coords[-1],angular_coords[-1]],drive)
    plt.plot(x_coords,y_coords,color="r")
    plt.title(title)
    draw_robot(zip(x_coords, y_coords, angular_coords)[0])
    draw_robot(zip(x_coords, y_coords, angular_coords)[-1])

def load_init_trajectory(init_json, initial_heading, final_heading, num_states):
    x_init = []
    y_init = []
    theta_init = []

    file = json.load(open(init_json))

    ls = []
    ls.append([file[0][0],file[0][1],initial_heading,0])
    for i in range(len(file)-2):
        ls.append([file[i+1][0],file[i+1][1],ls[i][2]+short(ls[i][2], math.atan2(file[i+2][1]-file[i+1][1],file[i+2][0]-file[i+1][0])),ls[-1][3]+math.hypot(file[i],file[i+1])])
    ls.append([file[-1][0],file[-1][1],final_heading,ls[-1][3]+math.hypot(file[-1],file[-2])])

    ds = ls[-1][3]/(num_states-1)
    index = 0
    for i in range(num_states):
        s = i * ds
        while (s-0.0001>ls[index+1][3]):
            index += 1
        t = (s - ls[index][3]) / (ls[index+1][3]-ls[index][3])
        x_init.append((ls[index+1][0]-ls[index][0]) * t + ls[index][0])
        y_init.append((ls[index+1][1]-ls[index][1]) * t + ls[index][1])
        theta_init.append(short(ls[index][2],ls[index+1][2]) * t + ls[index][2])
    return x_init,y_init,theta_init