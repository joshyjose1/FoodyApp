import java.util.ArrayList;
import java.util.List;

/*
 * This will filter out all the Partners who cannot deliver on time, from the 
 * ordered(based on eligibility advantage ) list of Partners.
 */
public class PartnerSelectorImplOnTime extends PartnerSelectorBase{
	private final int AVERAGE_SPEED = 10; // KM/hr
	private final int MAX_ALLOWED_TIME = 1; // hr
	

	public PartnerSelectorImplOnTime(List<DeliveryPartner> availablePartners) {
		super(availablePartners);
	}



	@Override
	List<DeliveryPartner> orderByCriteria(List<DeliveryPartner> deliveryPartners, Order order) {
		List<DeliveryPartner> retList = new ArrayList<>();
		for(DeliveryPartner partner:deliveryPartners) {
			int partnerDist = getPartnerRunningDistance(partner, order);
			if( (partnerDist/AVERAGE_SPEED) < MAX_ALLOWED_TIME) {
				retList.add(partner);
			}
		}
		return retList;
	}
	
}
