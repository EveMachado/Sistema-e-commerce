package com.gestaoseries.poo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gestaoseries.poo.entities.Category;
import com.gestaoseries.poo.entities.Order;
import com.gestaoseries.poo.entities.OrderItem;
import com.gestaoseries.poo.entities.Payment;
import com.gestaoseries.poo.entities.Product;
import com.gestaoseries.poo.entities.User;
import com.gestaoseries.poo.entities.enums.OrderStatus;
import com.gestaoseries.poo.repositories.CategoryRepository;
import com.gestaoseries.poo.repositories.OrderItemRepository;
import com.gestaoseries.poo.repositories.OrderRepository;
import com.gestaoseries.poo.repositories.ProductRepository;
import com.gestaoseries.poo.repositories.UserRepository;

//para falar para o spring que essa é uma classe especifica de configuração
@Configuration

//para falar que essa classe ela vai ser uma configuração especifica para o perfil de teste
@Profile("test")


public class TestConfig implements CommandLineRunner{

	//para que o spring consiga resolver essa dependencia e a associar uma instancia do UserReposity no meu TestConfig
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	

	@Override
	//tudo que colocar dentro desse metodo vai ser executado quando a aplicação for iniciada
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, ""); 

		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		p1.getCatetories().add(cat2);
		p2.getCatetories().add(cat1);
		p2.getCatetories().add(cat3);
		p3.getCatetories().add(cat3);
		p4.getCatetories().add(cat3);
		p5.getCatetories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		
		User u1 = new User(null, "Maria", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null,Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.WAITING_PAYMENT, u1);

		
		//salvando os usuarios no banco de dados
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null,Instant.parse("2019-06-20T21:53:07Z"), o1 );
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
		
	}
	
	
}
