/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.status.actions;

public class Action implements Runnable {

    // How long to delay intervals.
    protected double intervalTime = 0.0;

    public double getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(double intervalTime) {
        this.intervalTime = intervalTime;
    }

    // If this returns true the action runner thread will stop invoking run on this action.
    public boolean isFinished() {
        return true;
    }

    // How long to delay the action runner thread before calling run again.
    // The thread will have a minium that is enforced if this value is to low.

    // Reset the action. Used when adding to the action runner to reset anything that needs resetting.
    public void reset() {}

    // Every loop of the action runner thread will call this method.
    public void run() {}

}
