package application;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {
		 DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.systemDefault());
		 DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter cliente data:");
		System.out.print("Name: ");
		String clientName = scan.nextLine();
		System.out.print("Email: ");
		String email = scan.nextLine();
		System.out.print("Birth date (DD/MM/YYYY): ");
		LocalDate birthDate = LocalDate.parse(scan.next(), fmt1);
		Client client = new Client(clientName, email, birthDate);
		
		System.out.println("Enter order data: ");
		System.out.print("Status: ");
		String status = scan.next().toUpperCase();
		Order order = new Order(Instant.now(), OrderStatus.valueOf(status), client);
		
		System.out.print("How many items to this order? ");
		int n = scan.nextInt();
		
		for(int i=1; i<=n; i++) {
			System.out.println("Enter #" + i + " item data:");
			scan.nextLine();
			System.out.print("Product name: ");
			String productName = scan.nextLine();
			System.out.print("Product price: ");
			double productPrice = scan.nextDouble();
			System.out.print("Quantity: ");
			int quantity = scan.nextInt();
			
			Product product = new Product(productName, productPrice);
			OrderItem orderItem = new OrderItem(quantity, productPrice, product);
			order.addItem(orderItem);	
		}
		//
		System.out.println();
		System.out.println(order);
			
			
		scan.close();
	}

}
