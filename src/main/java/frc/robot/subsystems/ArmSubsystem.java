// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.lang.reflect.Array;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.subsystems.ArmInterp;

import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase {
  CANSparkMax m_armMotor = new CANSparkMax(ArmConstants.kArmMotorPort, MotorType.kBrushless);
  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {
    setCycles(0);
  }

  public void moveHome() {
    m_armMotor.getPIDController().setReference(ArmInterp.cyclesToHeight(ArmConstants.kHomePosition), ControlType.kPosition);
  }

  public void moveToBottom() {
    m_armMotor.getPIDController().setReference(ArmInterp.cyclesToHeight(ArmConstants.k1stRowPosition), ControlType.kPosition);
  }

  public void moveToMiddle() {
    m_armMotor.getPIDController().setReference(ArmInterp.cyclesToHeight(ArmConstants.k2ndRowPosition), ControlType.kPosition);
  }

  public void moveToTop() {
    m_armMotor.getPIDController().setReference(ArmInterp.cyclesToHeight(ArmConstants.k3rdRowPosition), ControlType.kPosition);
  }

  public void moveVHeight(double metersPerSecond) {
    double height = ArmInterp.cyclesToHeight(getCycles());
    double velocity = ArmInterp.vheightToRPM(metersPerSecond, height);
    m_armMotor.getPIDController().setReference(velocity, ControlType.kVelocity);
  }

  public void setCycles(double cycles) {
    m_armMotor.getEncoder().setPosition(cycles);
  }

  public double getCycles() {
    return m_armMotor.getEncoder().getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}