package org.firstinspires.ftc.teamcode.TANKM2.centerstage;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "JoystickDrive", group = "teleOp")
public class JoystickDrive extends LinearOpMode {
    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new Robot(this);

        waitForStart();

        while(opModeIsActive()){
//            if(gamepad1.left_stick_x != 0 || gamepad1.left_stick_y != 0){
//
//            }
            telemetry.addLine(gamepad1.left_stick_x + ", " + gamepad1.left_stick_y + "|| Hypotenuse: " + Math.sqrt(gamepad1.left_stick_x*gamepad1.left_stick_x)+(gamepad1.left_stick_y*gamepad1.left_stick_y));

        }
    }
}
