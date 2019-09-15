import java.util.ArrayList;
import java.util.List;

public class AllocateOrderMain {

	public static void main(String[] args) {
		System.out.println("AllocateOrderMain.....start");
		Order order = new Order(new Address(2,3), new Address(1,3));
		
		List<DeliveryPartner> partners = new ArrayList<>(); 
		
		DeliveryPartner deliveryPartner1 = new DeliveryPartner("deliveryPartner1",5, 3);
		deliveryPartner1.setAddress(new Address(8,9));
		partners.add(deliveryPartner1);
		
	
		DeliveryPartner deliveryPartner2 = new DeliveryPartner("deliveryPartner2", 1, 4);
		deliveryPartner2.setAddress(new Address(5,5));
		partners.add(deliveryPartner2);
		

		DeliveryPartner deliveryPartner3 = new DeliveryPartner("deliveryPartner3", 7, 2);
		deliveryPartner3.setAddress(new Address(4,5));
		partners.add(deliveryPartner3);
		

		DeliveryPartner deliveryPartner4 = new DeliveryPartner("deliveryPartner4", 15, 3);
		deliveryPartner4.setAddress(new Address(2,5));
		partners.add(deliveryPartner4);
		
		
		OrderDeliver orderDeliver = new OrderDeliver();
		DeliveryPartner selectedPartner = orderDeliver.allocateDeliveryPartner(partners, order);
		System.out.println("Allocated Partner::"+selectedPartner.toString());
		
	}
	

}
