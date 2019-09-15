
public class Order {
	public Order(Address address, Address restaddress) {
		this.address = address;
		this.restaurantAddress = restaddress;
	}
	private Address address;
	private Address restaurantAddress;
	

	public Address getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(Address restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}


	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	

}
