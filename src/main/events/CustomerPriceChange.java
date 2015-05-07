package main.events;

public class CustomerPriceChange extends BusinessEvent {


	private int oldPricePerGram;
	private int newPricePerGram;

	private int oldPricePerVolume;
	private int newPricePerVolume;


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
