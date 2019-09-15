import java.util.List;

public abstract class PartnerSelectorBase implements PartnerSelector {
	
	protected PartnerDataStats partnerDataStats;
	private List<DeliveryPartner> availablePartners;
	
	public PartnerSelectorBase(List<DeliveryPartner> availablePartners) {
		this.availablePartners = availablePartners;
	}
	
	
	
	@Override
	public List<DeliveryPartner> orderByCriteria(Order order) {
		return orderByCriteria(availablePartners, order);
	}
	
	
	
	/*
	 * pre- calculation
	 * extract & calculate the stats from the available Partner list 
	 */
	protected PartnerDataStats getStats(Order order) {
		PartnerDataStats statsFinal = this.availablePartners.stream().map(partner -> {
			// calculate the distance for partner to deliver order
			return new PartnerDataStats(getPartnerRunningDistance(partner, order), partner.getTripCount(), partner.getRating());
		})
		.reduce(new PartnerDataStats(0,0,0),  (stat1, stat2)-> 
			new PartnerDataStats(stat1.getTotalDistance()+stat2.getTotalDistance(), stat1.getTotalTrips()+stat2.getTotalTrips(), stat1.getTotalRatings()+stat2.getTotalRatings()));
		return statsFinal;
	}
	
	/*
	 *Algorithm to generate eligibility number 
	 */
	abstract List<DeliveryPartner> orderByCriteria(List<DeliveryPartner> deliveryPartner, Order order);
	
	/*
	 * This is the disance this partner should run to deliver this order
	 */
	protected int getPartnerRunningDistance(DeliveryPartner partner, Order order) {
		int distanceNewRest = order.getRestaurantAddress().getDistance(partner.getAddress());
		int distanceRestToCust = order.getRestaurantAddress().getDistance(order.getAddress());
		int distanceForThisPartner = distanceNewRest + distanceRestToCust;
		return distanceForThisPartner;
	}
	
}

