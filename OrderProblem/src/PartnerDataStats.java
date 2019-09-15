

public class PartnerDataStats {
	private double totalDistance;
	private int totalTrips;
	private int totalRatings;
	
	public double getTotalDistance() {
		return totalDistance;
	}

	public int getTotalTrips() {
		return totalTrips;
	}

	public int getTotalRatings() {
		return totalRatings;
	}
	
	public PartnerDataStats(double totalDistance, int totalTrips, int totalRatings) {
		this.totalDistance = totalDistance;
		this.totalTrips = totalTrips;
		this.totalRatings = totalRatings;
	}

}
