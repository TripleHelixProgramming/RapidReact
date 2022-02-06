// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.indexer.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.indexer.Indexer;

public class ShootIndexer extends CommandBase {
  Indexer indexer;

  public ShootIndexer(Indexer indexer) {
    this.indexer = indexer;
  }

  @Override
  public void initialize() {
    indexer.rollerIn();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
