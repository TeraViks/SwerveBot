// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    public static final int kFrontLeftDriveMotorPort = 1;
    public static final int kRearLeftDriveMotorPort = 2;
    public static final int kRearRightDriveMotorPort = 3;
    public static final int kFrontRightDriveMotorPort = 4;

    public static final int kFrontLeftTurningMotorPort = 5;
    public static final int kRearLeftTurningMotorPort = 6;
    public static final int kRearRightTurningMotorPort = 7;
    public static final int kFrontRightTurningMotorPort = 8;

    public static final int kFrontLeftTurningEncoderPort = 9;
    public static final int kRearLeftTurningEncoderPorts = 10;
    public static final int kRearRightTurningEncoderPorts = 11; 
    public static final int kFrontRightTurningEncoderPorts = 12;


    public static final boolean kFrontLeftTurningEncoderReversed = false;
    public static final boolean kRearLeftTurningEncoderReversed = false;
    public static final boolean kFrontRightTurningEncoderReversed = false;
    public static final boolean kRearRightTurningEncoderReversed = false;

    public static final boolean kFrontLeftDriveReversed = false;
    public static final boolean kRearLeftDriveReversed = false;
    public static final boolean kFrontRightDriveReversed = false;
    public static final boolean kRearRightDriveReversed = false;

    public static final double kFrontLeftEncoderOffset = 275.273;
    public static final double kFrontRightEncoderOffset = 341.016;
    public static final double kRearLeftEncoderOffset = 191.426;
    public static final double kRearRightEncoderOffset = 189.404;

    public static final double kTrackWidth = 0.431;
    // Distance between centers of right and left wheels on robot
    public static final double kWheelBase = 0.681;
    // Distance between front and back wheels on robot
    public static final SwerveDriveKinematics kDriveKinematics =
        new SwerveDriveKinematics(
            new Translation2d(kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

    public static final boolean kGyroReversed = false;

    // These are example values only - DO NOT USE THESE FOR YOUR OWN ROBOT!
    // These characterization values MUST be determined either experimentally or theoretically
    // for *your* robot's drive.
    // The SysId tool provides a convenient method for obtaining these values for your robot.
    public static final double ksVolts = 1;
    public static final double kvVoltSecondsPerMeter = 0.8;
    public static final double kaVoltSecondsSquaredPerMeter = 0.15;

    public static final double kMaxSpeedMetersPerSecond = 3;
    public static int kFrontLeftTurningEncoderPorts;
  }

  public static final class GripperConstants {
    public static final int kLeftSolenoidForwardChannel = 1;
    public static final int kLeftSolenoidBackwardChannel = 2;

    public static final int kRightSolenoidForwardChannel = 3;
    public static final int kRightSolenoidBackwardChannel = 4;
  }

  public static final class ArmConstants {
    public static final int kArmMotorPort = 13;

    public static final double kHomePosition = 0.0;
    public static final double k1stRowPosition = 0.0;
    public static final double kOffFloorPosition = 0.0;
    public static final double k2ndRowPosition = 0.0;
    public static final double k3rdRowPosition = 0.0;

    public static final DoubleSolenoid.Value kWristExtended = Value.kForward;
    public static final DoubleSolenoid.Value kWristRetracted = Value.kReverse;


    public static final double kAdjustVelocity = 0.8;

    public static final int kWristSolenoidForwardChannel = 5;
    public static final int kWristSolenoidBackwardChannel = 6;
    public static final int kWristExtensionCycles = 2000;
    public static final int kWristRetractionCycles = 1500;
  }

  public static final class SwerveModuleConstants {
    public static final double kMaxModuleAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxModuleAngularAccelerationRadiansPerSecondSquared = 2 * Math.PI;

    public static final double kDriveEncoderCPR = 2048.0;
    public static final double kAbsoluteEncoderCPR = 4096.0;
    public static final double kWheelDiameterMeters = 0.09525;


    public static final double kDriveEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterMeters * Math.PI) / (double) kDriveEncoderCPR;


    public static final double kPModuleTurningController = 0.5;
    public static final double kIModuleTurningController = 0.01;
    public static final double kDModuleTurningController = 0.02;

    public static final double kPModuleDriveController = 0.5;
    public static final double kIModuleDriveController = 0.001;
    public static final double kDModuleDriveController = 0.008;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final double kMaxRadPerSec = Math.PI;
    public static final double kMaxMetersPerSec = 1;

    public static final int kDpadUp = 12;
    public static final int kDpadRight = 13;
    public static final int kDpadDown = 14;
    public static final int kDpadLeft = 15;
    public static final int kLeftTrigger = 4;
    public static final int kRightTrigger = 5;

    public static final int kArmDown = kDpadDown;
    public static final int kArmUp = kDpadUp;
    public static final int kCubeButtonPressed = kLeftTrigger;
    public static final int kConeButtonPressed = kRightTrigger;
    public static final int kOpenButtonPressed = XboxController.Button.kLeftBumper.value;

    public static final int kHomeButtonPressed = XboxController.Button.kA.value;
    public static final int k1stRowButtonPressed = XboxController.Button.kB.value;
    public static final int kPickOffFloorButtonPressed = XboxController.Button.kY.value;
    public static final int k2ndRowButtonPressed = XboxController.Button.kX.value;
    public static final int k3rdRowButtonPressed = XboxController.Button.kRightBumper.value;

    public static final double kDebounceSeconds = 0.01;
  }

  public static final class AutoConstants {
    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 1;
    public static final double kPYController = 1;
    public static final double kPThetaController = 1;

    public static final TrajectoryConfig kDriveTrajectoryConfig =
        new TrajectoryConfig(
                AutoConstants.kMaxSpeedMetersPerSecond,
                AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(DriveConstants.kDriveKinematics);

    // Constraint for the motion profiled robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
        new TrapezoidProfile.Constraints(
            kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }
}
