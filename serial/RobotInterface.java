package serial;

import com.fazecast.jSerialComm.*;
import java.io.OutputStream;

public class RobotInterface{

	private SerialPort comPort;

	public RobotInterface(){

		comPort = SerialPort.getCommPorts()[1];
		comPort.openPort();
		
	}

	public void write(char data){
		OutputStream os = comPort.getOutputStream();

		try{

			os.write((int)data);
			os.close();
		}catch(Exception ex){

		}
	}

	public void close(){
		try{
			comPort.closePort();
		}catch(Exception ex){

		}
	}

}
