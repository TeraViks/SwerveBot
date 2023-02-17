// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase {
  private static boolean stubOut = true;
  CANSparkMax m_armMotor = new CANSparkMax(ArmConstants.kArmMotorPort, MotorType.kBrushless);
  SparkMaxLimitSwitch m_forwardLimitSwitch = m_armMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
  SparkMaxLimitSwitch m_reverseLimitSwitch = m_armMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);

  public enum InitState {
    UNINITIALIZED,
    INITIALIZED,
  }

  InitState m_initState = InitState.UNINITIALIZED;
  
  DoubleSolenoid m_wristPiston = new DoubleSolenoid(
    PneumaticsModuleType.REVPH,
    ArmConstants.kWristSolenoidForwardChannel,
    ArmConstants.kWristSolenoidBackwardChannel);
    
  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {}

  private DoubleSolenoid.Value getWristPosition() {
    return m_wristPiston.get();
  }

  public Command moveHome() {
    return runOnce(
      () -> {
        DoubleSolenoid.Value wristPosition = getWristPosition();
        if (wristPosition == ArmConstants.kWristExtended) {
          m_wristPiston.set(ArmConstants.kWristRetracted);
        }
        m_armMotor.getPIDController().setReference(ArmInterp.cyclesToHeight(ArmConstants.kHomePosition),
        ControlType.kPosition);
      }
    );
  }

  public Command moveToBottom() {
    return runOnce(
      () -> {
        m_armMotor.getPIDController().setReference(ArmInterp.cyclesToHeight(ArmConstants.k1stRowPosition),
        ControlType.kPosition);
      }
    );
  }

  public Command moveToOffFloor() {
    return runOnce(
      () -> {
        m_armMotor.getPIDController().setReference(ArmInterp.cyclesToHeight(ArmConstants.kOffFloorPosition),
        ControlType.kPosition);
      }
    );
  }

  public Command moveToMiddle() {
    return runOnce(
      () -> {
        m_armMotor.getPIDController().setReference(ArmInterp.cyclesToHeight(ArmConstants.k2ndRowPosition),
        ControlType.kPosition);
      }
    );
  }

  public Command moveToTop() {
    return runOnce(
      () -> {
        m_armMotor.getPIDController().setReference(ArmInterp.cyclesToHeight(ArmConstants.k3rdRowPosition),
        ControlType.kPosition);
      }
    );
  }

  public Command moveVHeight(double metersPerSecond) {
    return runOnce(
      () -> {
        double height = ArmInterp.cyclesToHeight(getCycles());
        double velocity = ArmInterp.vheightToRPM(metersPerSecond, height);
        m_armMotor.getPIDController().setReference(velocity, ControlType.kVelocity);
      }
    );
  }

  public void setCycles(double cycles) {
    m_armMotor.getEncoder().setPosition(cycles);
  }

  public double getCycles() {
    return m_armMotor.getEncoder().getPosition();
  }

  public Command initCommand() {
    if (stubOut) {
      return Commands.none();
    }
    return 
      runOnce(
        () -> {
          m_armMotor.getPIDController().setReference(-0.5, ControlType.kCurrent);
        }
      )
      .andThen(() -> {}, this).until(m_forwardLimitSwitch::isPressed)
      .andThen(
        () -> {
          setCycles(0.0);
          m_initState = InitState.INITIALIZED;
        }
      );
  }

  @Override
  public void periodic() {
    if (m_initState != InitState.INITIALIZED) return;
    DoubleSolenoid.Value wristPosition = getWristPosition();
    double armPosition = getCycles();
    assert(ArmConstants.kWristRetractionCycles < ArmConstants.kWristExtensionCycles);
    if (wristPosition == ArmConstants.kWristExtended && armPosition <= ArmConstants.kWristRetractionCycles) {
      m_wristPiston.set(ArmConstants.kWristRetracted);
    }
    else if (wristPosition == ArmConstants.kWristRetracted && armPosition >= ArmConstants.kWristExtensionCycles) {
      m_wristPiston.set(ArmConstants.kWristExtended);
    }
  }
}
