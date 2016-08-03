
import com.fazecast.jSerialComm.*;

public class RobotInterface{

	public RobotInterface(){

		SerialPort ports[] = SerialPort.getCommPorts();

		for(int i=0; i < ports.length; i++){

			System.out.println(ports[i].getSystemPortName());
		}

	}

	public static void main(String args[]){

		new RobotInterface();

	}

}
