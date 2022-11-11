package entities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {
	
	private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());

	
	
	private Instant moment;
	private OrderStatus status;
	
	private Client client;
	private List<OrderItem> items = new ArrayList<>();
	
	public Order() {
	}

	public Order(Instant moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	//
	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public void removeItem(OrderItem item) {
		items.remove(item);
	}
	public Double total() {
		double sum = 0;
		for(OrderItem item: items) {
			sum += item.subTotal();
		}
		return sum;
	}
	//
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//Dados do cliente
		sb.append("ORDER SUMMARY:\n");
		sb.append("Order moment: " + fmt.format(getMoment()) + "\n");
		sb.append("Order Status: " + status.toString().substring(0, 1).toUpperCase() + status.toString().substring(1).toLowerCase() + "\n");
		sb.append("Client: " + client + "\n");
		sb.append("Order items:\n");
		//Dados dos items
		for(OrderItem item: items) {
			sb.append(item + "\n");
		}
		sb.append(String.format("Total price: $%.2f", total()));
		return sb.toString();
	}
}