import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TeleOpCode extends OpMode {
    DcMotor RFMotor;
    DcMotor LFMotor;
    DcMotor RBMotor;
    DcMotor LBMotor;
    DcMotor liftMotorL;
    DcMotor liftMotorR;

    boolean move = false;

    private static final double SLIDE_POWER = 0.9; // Adjust as needed
    private static final int POSITION_A = 1000;   // Adjust these positions as needed
    private static final int POSITION_B = 2000;
    private static final int POSITION_X = 0;
    private static final int POSITION_Y = 2500;
    public float speedMultiplier = 0.5f;
    public float speedLimiter = 0.5f;
    @Override
    public void init() {
        RFMotor = hardwareMap.get(DcMotor.class, "RFMotor");
        LFMotor = hardwareMap.get(DcMotor.class, "LFMotor");
        RBMotor = hardwareMap.get(DcMotor.class, "RBMotor");
        LBMotor = hardwareMap.get(DcMotor.class, "LBMotor");

        RFMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RBMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        liftMotorL = hardwareMap.get(DcMotor.class, "liftMotorL");
        liftMotorR = hardwareMap.get(DcMotor.class, "liftMotorR");

        int positionL = liftMotorL.getCurrentPosition();
        int positionR = liftMotorR.getCurrentPosition();

        liftMotorR.setZeroPowerBehavior((DcMotor.ZeroPowerBehavior.BRAKE));
        liftMotorL.setZeroPowerBehavior((DcMotor.ZeroPowerBehavior.BRAKE));

        liftMotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftMotorR.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    @Override
    public void loop(){
        moveDriveTrain();

    }


    public void moveDriveTrain() {
        double y = gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double fl = y + x + rx;
        double bl = y - x + rx;
        double fr = y - x - rx;
        double br = y + x - rx;

        LFMotor.setPower(fl*speedMultiplier);
        LBMotor.setPower(bl*speedMultiplier);
        RFMotor.setPower(fr*speedMultiplier);
        RBMotor.setPower(br*speedMultiplier);

        telemetry.addData("y",y);
        telemetry.addData("x",x);
        telemetry.addData("fl",fl);
        telemetry.addData("bl",bl);
        telemetry.addData("fr",fr);
        telemetry.addData("br",br);
        telemetry.addData("fl*speedMultiplier",fl*speedMultiplier);
        telemetry.addData("bl*speedMultiplier",bl*speedMultiplier);
        telemetry.addData("fr*speedMultiplier",fr*speedMultiplier);
        telemetry.addData("br*speedMultiplier",br*speedMultiplier);

        telemetry.update();


    }
}

