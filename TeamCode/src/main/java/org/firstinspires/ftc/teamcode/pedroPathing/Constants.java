package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerBuilder;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.drivetrains.Mecanum;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.ftc.localization.localizers.PinpointLocalizer;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    public static FollowerConstants followerConstants = new FollowerConstants()
            .centripetalScaling(0.005)
            .forwardZeroPowerAcceleration(-41.278)
            .lateralZeroPowerAcceleration(-59.7819)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.1,0,0.01,0.015))
            .headingPIDFCoefficients(new PIDFCoefficients(2,0,0.1,0.01))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.1,0,0,0.6,0.01))
            .mass(5);

    public static MecanumConstants driveConstants = new MecanumConstants()
            .xMovement(57.8741)
            .yMovement(52.295)
            .maxPower(1)
            .leftFrontMotorName("lf")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .motorCachingThreshold(0.01)
            .useBrakeModeInTeleOp(false);

    public static PinpointConstants pinpointConstants = new PinpointConstants()
            .forwardY(-5)
            .strafeX(0.5)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 3, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants)
                .setDrivetrain(new Mecanum(hardwareMap, driveConstants))
                .setLocalizer(new PinpointLocalizer(hardwareMap, pinpointConstants))
                .pathConstraints(pathConstraints)
                .build();
    }
}