package com.cg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cg.eshop.dto.BasketDto;
import com.cg.eshop.entity.Basket;
import com.cg.eshop.service.IBasketService;

@SpringBootApplication
public class ElectronicsShopApplication implements CommandLineRunner {

	@Autowired
	private IBasketService bktser;
	
	public static void main(String[] args){
		SpringApplication.run(ElectronicsShopApplication.class, args);
		
	}
	@Override
	public void run(String... args) throws Exception {
		/*System.out.println("Adding item to basket...");
		BasketDto bktdto = new BasketDto(1001, 1001);
		System.out.println(bktser.addItem(bktdto));*/
		
		/*System.out.println("View Basket items ..");
		List<Basket> bkt = bktser.viewItems(1001);
		for(Basket b: bkt)
			System.out.println(b.getBasketId()+" "+b.getCustomer().getCustomerName()+" "+ b.getProductDetails().getName());
		
		*/
		
		System.out.println("Remove items");
		System.out.println(bktser.removeItem(1001,1001));
		
	}

}
