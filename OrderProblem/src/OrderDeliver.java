import java.util.List;

public class OrderDeliver {
	public DeliveryPartner allocateDeliveryPartner(List<DeliveryPartner> partners, Order order) {
		PartnerSelector partnerSelectorForEligibility = new PartnerSelectorImplFair(partners);
		List<DeliveryPartner> orderedByEligibityAdv = partnerSelectorForEligibility.orderByCriteria(order);
		orderedByEligibityAdv.forEach(tt -> System.out.println("before filter::"+tt.name));
		PartnerSelector partnerFilterForOnTime = new PartnerSelectorImplOnTime(orderedByEligibityAdv);
		List<DeliveryPartner> filteredByTime = partnerFilterForOnTime.orderByCriteria(order);
		filteredByTime.forEach(tt -> System.out.println("after filter::"+tt.name));
		// most eligible one would be the first in the List
		if(filteredByTime.size() >0) {
			DeliveryPartner selected = filteredByTime.get(0);
			selected.setTripCount(selected.getTripCount()+1);
			return selected;
		}
		return null;
	}

}
