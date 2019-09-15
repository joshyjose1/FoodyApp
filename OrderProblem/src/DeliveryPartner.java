
public class DeliveryPartner {
	Address address;
	String name;
	int tripCount;
	int rating;
	public DeliveryPartner(String name, int tripCount, int rating) {
		this.name = name;
		this.tripCount = tripCount;
		this.rating = rating;
	}
	public int getTripCount() {
		return tripCount;
	}
	public void setTripCount(int tripCount) {
		this.tripCount = tripCount;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}


	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
