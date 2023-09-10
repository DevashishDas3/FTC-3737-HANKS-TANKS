
package org.firstinspires.ftc.teamcode.TANKM2.centerstage;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "QualAutoLeft", group = "Autonomous")
public class QualAutoLeft extends LinearOpMode
{
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;
    Robot robot;

    static final double FEET_PER_METER = 3.28084;



    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 78.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1440 ;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 2.0 ;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0 ;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/(WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;
    static final double TURN_SPEED = 0.45;

    int ID_TAG_OF_INTEREST = 0; // Tag ID 1,2,3 from the 36h11 family

    int LEFT = 7;
    int MIDDLE = 8;
    int RIGHT = 9;

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode()
    {

        robot = new Robot(this);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {
                telemetry.addLine("Rats! Why isn't it working? \n");
            }
        });

        telemetry.setMsTransmissionInterval(50);

        while (!isStarted() && !isStopRequested())
        {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections)
                {
                    if(tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                }
                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null)
                    {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }

            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }

            }

            telemetry.update();
            sleep(20);
        }


        if(tagOfInterest != null)
        {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        }
        else
        {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        /* Actually do something useful */



        if(tagOfInterest == null){
            robot.sR.setPosition(0.45);
            robot.sL.setPosition(0.0);
            initForward();
            encoderDrive(DRIVE_SPEED, 4.5, 4.5, 4.5, 4.5);
            /*
            initLeftStrafe();
            encoderDrive(DRIVE_SPEED, 2.5, 2.5, 2.5, 2.5);
            initForward();
            encoderDrive(DRIVE_SPEED, 1,1,1,1);
            initUp();
            encoderLiftDrive(0.3, 14,14);
            initDown();
            encoderLiftDrive(0.3, 5,5);
            initBackward();
            encoderDrive(DRIVE_SPEED, 2,2,2,2);
            initRightStrafe();
            encoderDrive(DRIVE_SPEED, 2.5, 2.5, 2.5, 2.5);
            robot.sR.setPosition(0.35);
            robot.sL.setPosition(0.1);
            */
        }
        else if(tagOfInterest.id == LEFT){
            robot.sR.setPosition(0.45);
            robot.sL.setPosition(0.0);
            initForward();
            encoderDrive(DRIVE_SPEED, 4.5, 4.5, 4.5, 4.5);
            /*
            initLeftStrafe();
            encoderDrive(DRIVE_SPEED, 2.5, 2.5, 2.5, 2.5);
            initUp();
            encoderLiftDrive(0.3, 14,14);
            initForward();
            encoderDrive(DRIVE_SPEED, 1,1,1,1);
            initDown();
            encoderLiftDrive(0.3, 14,14);
            initBackward();
            encoderDrive(DRIVE_SPEED, 2,2,2,2);
            */
            initRightStrafe();
            encoderDrive(DRIVE_SPEED, 12, 12, 12, 12);
            /*
            robot.sR.setPosition(0.35);
            robot.sL.setPosition(0.1);
             */
        }

        else if(tagOfInterest.id == MIDDLE){
            robot.sR.setPosition(0.45);
            robot.sL.setPosition(0.0);
            initForward();
            encoderDrive(DRIVE_SPEED, 4.5, 4.5, 4.5, 4.5);
            /*
            initLeftStrafe();
            encoderDrive(DRIVE_SPEED, 2.5, 2.5, 2.5, 2.5);
            initForward();
            encoderDrive(DRIVE_SPEED, 1,1,1,1);
            initUp();
            encoderLiftDrive(0.3, 14,14);
            initDown();
            encoderLiftDrive(0.3, 5,5);
            initBackward();
            encoderDrive(DRIVE_SPEED, 2,2,2,2);
            //
            initRightStrafe();
            encoderDrive(DRIVE_SPEED, 2.5, 2.5, 2.5, 2.5);
            robot.sR.setPosition(0.35);
            robot.sL.setPosition(0.1);
            */
        }
        else if(tagOfInterest.id == RIGHT){
            robot.sR.setPosition(0.45);
            robot.sL.setPosition(0.0);
            initForward();
            encoderDrive(DRIVE_SPEED, 4.5, 4.5, 4.5, 4.5);
            initLeftStrafe();
            encoderDrive(DRIVE_SPEED, 2.5, 2.5, 2.5, 2.5);
            /*
            initForward();
            encoderDrive(DRIVE_SPEED, 1,1,1,1);
            initUp();
            encoderLiftDrive(0.3, 14,14);
            initDown();
            encoderLiftDrive(0.3, 5,5);
            initBackward();
            encoderDrive(DRIVE_SPEED, 2,2,2,2);
            */
            initLeftStrafe();
            encoderDrive(DRIVE_SPEED, 2.5, 2.5, 2.5, 2.5);
            /*
            robot.sR.setPosition(0.35);
            robot.sL.setPosition(0.1);
            */
        }
        else{
            initForward();
            encoderDrive(DRIVE_SPEED, 4.5, 4.5, 4.5, 4.5);
        }

        /*
        while (opModeIsActive()) {sleep(20);}
        UNCOMMENT IF NECESSARY
        */
    }


    void initForward(){
        robot.lF.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.rB.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.rF.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.lB.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    void initBackward(){
        robot.lF.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.rB.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.rF.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.lB.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void initRightStrafe(){
        robot.lF.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.rB.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.rF.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.lB.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    void initLeftStrafe(){
        robot.lF.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.rB.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.rF.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.lB.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void initUp(){
        robot.aR.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.aL.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    void initDown(){
        robot.aR.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.aL.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void encoderLiftDrive(double speed, double aL, double aR) {
        int newALTarget;
        int newARTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newALTarget = robot.aL.getCurrentPosition() + (int)(aL * COUNTS_PER_INCH);
            newARTarget = robot.aR.getCurrentPosition() + (int)(aR * COUNTS_PER_INCH);

            robot.aL.setTargetPosition(newALTarget);
            robot.aR.setTargetPosition(newARTarget);

            // Turn On RUN_TO_POSITION
            robot.setArmDrivetrainMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.aL.setPower(Math.abs(speed));
            robot.aR.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (robot.aL.isBusy() && robot.aR.isBusy())) {
            }

            // Stop all motion;
            robot.setMotorPowers(0);

            // Turn off RUN_TO_POSITION
            robot.setArmDrivetrainMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

    public void encoderDrive(double speed, double lF, double rF, double lB, double rB) {
        int newLFTarget;
        int newRFTarget;
        int newLBTarget;
        int newRBTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLFTarget = robot.lF.getCurrentPosition() + (int)(lF * COUNTS_PER_INCH);
            newRFTarget = robot.rF.getCurrentPosition() + (int)(rF * COUNTS_PER_INCH);
            newLBTarget = robot.lB.getCurrentPosition() + (int)(lB * COUNTS_PER_INCH);
            newRBTarget = robot.rB.getCurrentPosition() + (int)(rB * COUNTS_PER_INCH);

            robot.lF.setTargetPosition(newLFTarget);
            robot.rF.setTargetPosition(newRFTarget);
            robot.lB.setTargetPosition(newLBTarget);
            robot.rB.setTargetPosition(newRBTarget);

            // Turn On RUN_TO_POSITION
            robot.setDrivetrainMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.lF.setPower(Math.abs(speed));
            robot.rF.setPower(Math.abs(speed));
            robot.lB.setPower(Math.abs(speed));
            robot.rB.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (robot.lF.isBusy() && robot.rF.isBusy()
                            && robot.lB.isBusy() && robot.rB.isBusy())) {
            }


            // Stop all motion;
            robot.setMotorPowers(0);

            // Turn off RUN_TO_POSITION
            robot.setDrivetrainMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }





            void tagToTelemetry(AprilTagDetection detection)
            {
            telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
            telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
            telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
            telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
            telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
            telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
            telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
            }
}
