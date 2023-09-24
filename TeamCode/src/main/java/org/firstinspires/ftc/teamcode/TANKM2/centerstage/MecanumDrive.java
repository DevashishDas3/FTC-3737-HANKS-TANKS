package org.firstinspires.ftc.teamcode.TANKM2.centerstage;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "MecanumDrive", group = "teleOp")
public class MecanumDrive extends LinearOpMode {
    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new Robot(this);

        waitForStart();

        while(opModeIsActive()){
            double drive  = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double twist  = gamepad1.right_stick_x;

            double[] speeds = {
                    (drive + strafe + twist),
                    (drive - strafe - twist),
                    (drive - strafe + twist),
                    (drive + strafe - twist)
            };

            double max = Math.abs(speeds[0]);
            for(int i = 0; i < speeds.length; i++) {
                if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);
            }

            if (max > 1) {
                for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
            }

            //Errors could occur if motor directions are not set properly

            robot.lF.setPower(speeds[0]);
            robot.rF.setPower(speeds[1]);
            robot.lB.setPower(speeds[2]);
            robot.rB.setPower(speeds[3]);

        }
    }
}