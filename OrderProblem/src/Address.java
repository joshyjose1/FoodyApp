
public class Address {
	public int x;
	public int y;
	public Address(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getDistance(Address addr) {
		int xdiff = this.x - addr.x;
		int ydiff = this.y - addr.y;
		int temp = xdiff*xdiff +ydiff*ydiff;
		return (int)Math.sqrt(temp);
	}

}
