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

    DcMotor aR;
    String aRInit = "aR";

    DcMotor aL;
    String aLInit = "aL";

    Servo sL;
    String sLInit = "sL";

    Servo sR;
    String sRInit = "sL";

    public Robot(LinearOpMode ln){
        lF = ln.hardwareMap.dcMotor.get(lFInit);
        lB = ln.hardwareMap.dcMotor.get(lBInit);
        rF = ln.hardwareMap.dcMotor.get(rFInit);
        rB = ln.hardwareMap.dcMotor.get(rBInit);
        aR = ln.hardwareMap.dcMotor.get(aRInit);
        aL = ln.hardwareMap.dcMotor.get(aLInit);
        sL = ln.hardwareMap.servo.get(sLInit);
        sR = ln.hardwareMap.servo.get(sRInit);
    }

    public void setDrivetrainMode(DcMotor.RunMode mode) {
        lF.setMode(mode);
        rF.setMode(mode);
        lB.setMode(mode);
        rB.setMode(mode);
    }

    public void setArmDrivetrainMode(DcMotor.RunMode mode) {
        aL.setMode(mode);
        aR.setMode(mode);
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
