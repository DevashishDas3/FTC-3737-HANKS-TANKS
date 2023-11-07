// Mark I
// Devashish Das 2022-2023
// Rohin Sharma 2022-2023

package org.firstinspires.ftc.teamcode.TANKM2.centerstage;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {
    LinearOpMode ln;

    DcMotor lF;
    String lFInit = "lF";

    DcMotor lB;
    String lBInit = "lB";

    DcMotor rF;
    String rFInit = "rF";

    DcMotor rB;
    String rBInit = "rB";

    DcMotor shoulder;
    String shoulderInit = "shoulder";

    DcMotor elbow;
    String elbowInit = "elbow";

    Servo wrist;
    String wristInit = "wrist";

    Servo finger;
    String fingerInit = "finger";
    

    public Robot(LinearOpMode ln){
        lF = ln.hardwareMap.dcMotor.get(lFInit);
        lB = ln.hardwareMap.dcMotor.get(lBInit);
        rF = ln.hardwareMap.dcMotor.get(rFInit);
        rB = ln.hardwareMap.dcMotor.get(rBInit);
        shoulder = ln.hardwareMap.dcMotor.get(shoulderInit);
        elbow = ln.hardwareMap.dcMotor.get(elbowInit);
        wrist = ln.hardwareMap.servo.get(wristInit);
        finger = ln.hardwareMap.servo.get(fingerInit);
    }

    public void setDrivetrainMode(DcMotor.RunMode mode) {
        lF.setMode(mode);
        rF.setMode(mode);
        lB.setMode(mode);
        rB.setMode(mode);
    }

    public void setArmDrivetrainMode(DcMotor.RunMode mode) {
//        aL.setMode(mode);
//        aR.setMode(mode);
    }

    public void setMotorPowers(double LFPower, double RFPower, double LBPower, double RBPower) {
        lF.setPower(LFPower);
        rF.setPower(RFPower);
        lB.setPower(LBPower);
        rB.setPower(RBPower);
    }

    public void setMotorPowers(double allPower) {
        setMotorPowers(allPower, allPower, allPower, allPower);
    }

    public void setArmPowers(double LFPower, double RFPower) {
        lF.setPower(LFPower);
        rF.setPower(RFPower);
    }

    public void setArmPowers(double allPower) {
        setArmPowers(allPower, allPower);
    }

}
