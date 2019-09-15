import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PartnerSelectorImplFair extends PartnerSelectorBase{
	private final int DISTANCE_WEIGHTAGE = 3;
	private final int COUNT_TRIPS_WEIGHTAGE = 5;
	private final int RATING_WEIGHTAGE = 2;
	
	private TreeMap<Double, DeliveryPartner> sortedByEligibilty;
	
	public PartnerSelectorImplFair(List<DeliveryPartner> availablePartners) {
		super(availablePartners);
	}

	@Override
	List<DeliveryPartner> orderByCriteria(List<DeliveryPartner> deliveryPartners, Order order) {
		this.partnerDataStats = getStats(order);
		sortedByEligibilty = deliveryPartners.stream().map(deliPartner -> {
			return new AbstractMap.SimpleEntry<Double, DeliveryPartner>(getEligibilityNumber(deliPartner, order), deliPartner);
		}).collect( Collectors.toMap(out -> out.getKey(), out->out.getValue() ,(key1, key2)->key1, TreeMap<Double, DeliveryPartner>::new ) );
		sortedByEligibilty = new TreeMap<Double, DeliveryPartner>(sortedByEligibilty);
		TreeMap<Double, DeliveryPartner> workingSort = new TreeMap<>((o1, o2) -> {
			if(o2.doubleValue()> o1.doubleValue())
				return 1;
			else if(o2.doubleValue()< o1.doubleValue())
				return -1;
			return 0;
		});
		workingSort.putAll(sortedByEligibilty);
		sortedByEligibilty = workingSort;
		// this will return list of delivery partners ordered by eligibility criteria. 
		return new ArrayList<>(sortedByEligibilty.values());	
	}
	

	/**
	 * Allocating weightage for each of selection parameters, to treat fairly
	 */
	private Double getEligibilityNumber(DeliveryPartner deliveryPartner, Order order) {
		// This would be the total distance that all partners be running, if all of them were to 
		// deliver the same order, in consideration.
		// lesser distance should get bigger output, +ve
		double totalDistance = this.partnerDataStats.getTotalDistance();		
		double distanceForThisPartner = getPartnerRunningDistance(deliveryPartner, order);
		double distanceAdvantage = 1-(distanceForThisPartner/totalDistance); 
		
		// lesser rating should get bigger output, -ve
		double totalRating = this.partnerDataStats.getTotalRatings();
		double ratingForThisPartner = deliveryPartner.getRating();
		double ratingAdvantage = 1-(ratingForThisPartner/totalRating);
		// lesser trips should get bigger output, +ve
		double totalTrips = this.partnerDataStats.getTotalTrips(); // sum total of all the trips all partners have done
		double tripsForThisPartner = deliveryPartner.getTripCount();
		double tripAdvantage = 1-(tripsForThisPartner/totalTrips);
		ratingAdvantage = ratingAdvantage*(tripsForThisPartner/totalTrips); // factoring in no. of trips 
		// enhance numbers with weightage
		double eligibilityAdvantage = (distanceAdvantage*DISTANCE_WEIGHTAGE)-(ratingAdvantage*RATING_WEIGHTAGE)+(tripAdvantage*COUNT_TRIPS_WEIGHTAGE);
		System.out.println("deliveryPartner::"+deliveryPartner.name+" distanceForThisPartner::"+distanceForThisPartner+" eligibilityAdvantage::"+eligibilityAdvantage+ " distanceAdvantage::"+distanceAdvantage+ " ratingAdvantage::"+ratingAdvantage+ " tripAdvantage::"+tripAdvantage);
		return eligibilityAdvantage;
	}

	
}
