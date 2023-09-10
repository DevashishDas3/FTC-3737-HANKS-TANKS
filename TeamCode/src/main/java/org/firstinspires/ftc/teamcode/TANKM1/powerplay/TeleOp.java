package org.firstinspires.ftc.teamcode.TANKM1.powerplay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.TANKM1.powerplay.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "teleOp")
public class TeleOp extends LinearOpMode {
    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new Robot(this);

        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Left servo: ", robot.sL.getPosition());
            telemetry.addData("Right servo: ", robot.sR.getPosition());

            if(gamepad1.a) {
                robot.lF.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.rB.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.rF.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.lB.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.lF.setPower(1);
                robot.lB.setPower(1);
                robot.rF.setPower(1);
                robot.rB.setPower(1);
            }else{
                robot.lF.setPower(0);
                robot.lB.setPower(0);
                robot.rF.setPower(0);
                robot.rB.setPower(0);
            }

            if(gamepad1.dpad_right) {
                robot.lF.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.rB.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.rF.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.lB.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.lF.setPower(1);
                robot.lB.setPower(1);
                robot.rF.setPower(1);
                robot.rB.setPower(1);
            }else{
                robot.lF.setPower(0);
                robot.lB.setPower(0);
                robot.rF.setPower(0);
                robot.rB.setPower(0);
            }

            if(gamepad1.dpad_left) {
                robot.lF.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.rB.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.rF.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.lB.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.lF.setPower(1);
                robot.lB.setPower(1);
                robot.rF.setPower(1);
                robot.rB.setPower(1);
            }else{
                robot.lF.setPower(0);
                robot.lB.setPower(0);
                robot.rF.setPower(0);
                robot.rB.setPower(0);
            }

            if(gamepad1.y) {
                robot.lF.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.rB.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.rF.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.lB.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.lF.setPower(1);
                robot.lB.setPower(1);
                robot.rF.setPower(1);
                robot.rB.setPower(1);
            }else{
                robot.lF.setPower(0);
                robot.lB.setPower(0);
                robot.rF.setPower(0);
                robot.rB.setPower(0);
            }

            if(gamepad1.x) {
                robot.lF.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.rB.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.rF.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.lB.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.lF.setPower(1);
                robot.lB.setPower(1);
                robot.rF.setPower(1);
                robot.rB.setPower(1);
            }else{
                robot.lF.setPower(0);
                robot.lB.setPower(0);
                robot.rF.setPower(0);
                robot.rB.setPower(0);
            }

            if(gamepad1.b) {
                robot.lF.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.rB.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.rF.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.lB.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.lF.setPower(1);
                robot.lB.setPower(1);
                robot.rF.setPower(1);
                robot.rB.setPower(1);
            }else{
                robot.lF.setPower(0);
                robot.lB.setPower(0);
                robot.rF.setPower(0);
                robot.rB.setPower(0);
            }

            if(gamepad1.left_bumper){
                robot.sR.setPosition(0.35);
                robot.sL.setPosition(0.1);
            }

            if(gamepad1.right_bumper){
                robot.sR.setPosition(0.45);
                robot.sL.setPosition(0.0);
            }

            if(gamepad1.left_trigger > 0){
                robot.aR.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.aL.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.aR.setPower(0.7);
                robot.aL.setPower(0.7);
            } else {
                robot.aR.setPower(0);
                robot.aL.setPower(0);
            }

            if(gamepad1.right_trigger > 0){
                robot.aR.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.aL.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.aR.setPower(0.25);
                robot.aL.setPower(0.25);
            } else {
                robot.aR.setPower(0);
                robot.aL.setPower(0);
            }

            if(gamepad1.dpad_down){
                robot.aR.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.aL.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.aR.setPower(0.35);
                robot.aL.setPower(0.35);
            } else {
                robot.aR.setPower(0);
                robot.aL.setPower(0);
            }
        }
    }
}
