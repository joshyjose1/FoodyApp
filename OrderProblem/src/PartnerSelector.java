import java.util.List;

public interface PartnerSelector {

	public List<DeliveryPartner> orderByCriteria(Order order);
}
