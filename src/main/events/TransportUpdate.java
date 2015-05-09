package main.events;

import java.util.List;

import main.logic.Route;

public class TransportUpdate extends BusinessEvent {

	private double oldPricePerGram;
	private double newPricePerGram;

	private double oldPricePerVolume;
	private double newPricePerVolume;

	public TransportUpdate( double og, double ng, double ov, double nv, List<Route> routes ) {
		oldPricePerGram = og;
		newPricePerGram = ng;
		oldPricePerVolume = ov;
		newPricePerVolume = nv;
		this.routes = routes;
	}


	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNewPricePerGram( int i) {
		oldPricePerGram = newPricePerGram;
		newPricePerGram = i;
	}

	public void setNewPricePerVolume( int i ) {
		oldPricePerVolume = newPricePerVolume;
		newPricePerVolume = i;
	}

	public double getOldPricePerGram() {
		return oldPricePerGram;
	}

	public double getNewPricePerGram() {
		return newPricePerGram;
	}

	public double getOldPricePerVolume() {
		return oldPricePerVolume;
	}

	public double getNewPricePerVolume() {
		return newPricePerVolume;
	}


	@Override
	public String toString() {
		return "TransportUpdate [oldPricePerGram=" + oldPricePerGram
				+ ", newPricePerGram=" + newPricePerGram
				+ ", oldPricePerVolume=" + oldPricePerVolume
				+ ", newPricePerVolume=" + newPricePerVolume + "]" + stringRoutes();
	}

}
