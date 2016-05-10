package rocketServer;

import java.io.IOException;

import netgame.common.Hub;


public class RocketHub extends Hub {

	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof String) {
			resetOutput();
			sendToAll(message);
		}
	}
}
