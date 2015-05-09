package main.events;

import java.util.List;

import main.logic.Route;

public class CustomerPriceChange extends BusinessEvent {


	private int oldPricePerGram;
	private int newPricePerGram;

	private int oldPricePerVolume;
	private int newPricePerVolume;


	/**
	 * Constructor for a Customer Price Change event
	 * @param og: old price per gram
	 * @param ng: new price per gram
	 * @param ov: old price per volume
	 * @param nv: new price per volume
	 */
	public CustomerPriceChange( int og, int ng, int ov, int nv, List<Route> routes) {
		oldPricePerGram = og;
		newPricePerGram = ng;
		oldPricePerVolume = ov;
		newPricePerVolume = nv;
		this.routes=routes;
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

	public int getOldPricePerGram() {
		return oldPricePerGram;
	}

	public int getNewPricePerGram() {
		return newPricePerGram;
	}

	public int getOldPricePerVolume() {
		return oldPricePerVolume;
	}

	public int getNewPricePerVolume() {
		return newPricePerVolume;
	}

	@Override
	public String toString() {
		return "CustomerPriceChange [oldPricePerGram=" + oldPricePerGram
				+ ", newPricePerGram=" + newPricePerGram
				+ ", oldPricePerVolume=" + oldPricePerVolume
				+ ", newPricePerVolume=" + newPricePerVolume + "]";
	}

}
