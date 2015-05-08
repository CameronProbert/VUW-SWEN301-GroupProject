package main.events;

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
	public CustomerPriceChange( int og, int ng, int ov, int nv ) {
		oldPricePerGram = og;
		newPricePerGram = ng;
		oldPricePerVolume = ov;
		newPricePerVolume = nv;
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

}
