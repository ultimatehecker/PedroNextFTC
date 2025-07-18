package org.firstinspires.ftc.teamcode.subsystems;

import com.bylazar.ftcontrol.panels.configurables.annotations.IgnoreConfigurable;
import com.bylazar.ftcontrol.panels.integration.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.util.PoseHistory;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.utilities.constants.DrivetrainConstants;

public class Drivetrain extends Subsystem {

    private static Drivetrain instance = null;
    public static synchronized Drivetrain getInstance(HardwareMap aHardwareMap, TelemetryManager telemetryManager) {
        if(instance == null) {
            instance = new Drivetrain(aHardwareMap, telemetryManager);
        }

        return instance;
    }

    private DcMotorEx leftFront;
    private DcMotorEx rightFront;
    private DcMotorEx leftRear;
    private DcMotorEx rightRear;

    public Follower follower;
    private GoBildaPinpointDriver pinpoint;

    @IgnoreConfigurable
    private static PoseHistory poseHistory;

    @IgnoreConfigurable
    private static TelemetryManager telemetryManager;

    private Drivetrain(HardwareMap aHardwareMap, TelemetryManager telemetryManager) {
        leftFront = aHardwareMap.get(DcMotorEx.class, DrivetrainConstants.fLMotorID);
        rightFront = aHardwareMap.get(DcMotorEx.class, DrivetrainConstants.fRMotorID);
        leftRear = aHardwareMap.get(DcMotorEx.class, DrivetrainConstants.bLMotorID);
        rightRear = aHardwareMap.get(DcMotorEx.class, DrivetrainConstants.bRMotorID);

        pinpoint = aHardwareMap.get(GoBildaPinpointDriver.class, DrivetrainConstants.pinpointID);

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        follower = Constants.createFollower(aHardwareMap);
        poseHistory = follower.getPoseHistory();
        this.telemetryManager = telemetryManager;
    }

    @Override
    public void periodic() {
        telemetryManager.debug("Follower Pose X: " + follower.getPose().getX());
        telemetryManager.debug("Follower Pose Y: " + follower.getPose().getY());
        telemetryManager.debug("Follower Pose θ: " + follower.getPose().getHeading());
    }
}
