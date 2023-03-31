// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WristConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WristSubsystem extends SubsystemBase {

  private DoubleSolenoid m_wristPiston = new DoubleSolenoid(
    PneumaticsModuleType.REVPH,
     WristConstants.kWristSolenoidForwardChannel, 
     WristConstants.kWristSolenoidBackwardChannel);
  
  private boolean m_isInitialized = false;
  
  public DoubleSolenoid.Value getWristPosition() {
    return m_wristPiston.get();
  }
  
  public void extendWrist() {
    DoubleSolenoid.Value wristPosition = getWristPosition();
    if (wristPosition != WristConstants.kWristExtended) {
      m_wristPiston.set(WristConstants.kWristExtended);
    } 
  }

  public Command extendWristCommand() {
    return runOnce(
      () -> {
        extendWrist();
      }
    );
  }

  public void retractWrist() {
    DoubleSolenoid.Value wristPosition = getWristPosition();
    if (wristPosition != WristConstants.kWristRetracted) {
      m_wristPiston.set(WristConstants.kWristRetracted);
    } 
  }

  public Command retractWristCommand() {
    return runOnce(
      () -> {
        retractWrist();
      }
    );
  }

  public Command initCommand() {
    return runOnce(
      () -> {
        m_wristPiston.set(WristConstants.kWristRetracted);
        m_isInitialized = true;
      }
    );
  }

  public boolean isInitialized() {
    return m_isInitialized;
  }

  /** Creates a new WristSubsystem. */
  public WristSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // SmartDashboard.putBoolean("Wrist Initialized", isInitialized());
  }
}
