package com.infotel.eshop.rs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.dto.OrderListDto;
import com.infotel.eshop.dto.PlaceOrderDto;
import com.infotel.eshop.mapper.OrderMapper;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.service.OrderService;

@RestController @RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public OrderListDto getOrdersXml(@RequestParam("cust") String username) throws Exception {
		List<Order> orders = service.findOrdersByCustomer(username);
		OrderListDto dtoes  = new OrderListDto();
		dtoes.setOrders(OrderMapper.orderToOrderDtoes(orders));
		
		return dtoes;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDto> getOrdersJson(@RequestParam("cust") String username) throws Exception {
		List<Order> orders = service.findOrdersByCustomer(username);
		
		return OrderMapper.orderToOrderDtoes(orders);
	}
	
//	@PostMapping("/place")
//	@RequestMapping(path="/place", method=RequestMethod.POST,
//					consumes={MediaType.APPLICATION_JSON_VALUE,
//					MediaType.APPLICATION_XML_VALUE})
//	@RequestMapping(path="/place", 
//					method=RequestMethod.POST,
//					consumes= {
//								MediaType.APPLICATION_XML_VALUE,
//								MediaType.APPLICATION_JSON_VALUE
//								}
//	)
	@PostMapping("/place")
	public ResponseEntity<Void> placeOrder(@RequestBody PlaceOrderDto dto) {
		try {
			service.placeOrder(OrderMapper.orderDtoToOrder(dto));
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	

//	<PlaceOrder>
//	<Customer>roger@gmail.com</Customer>
//	<Lines>
//		<Line>
//			<ProductId>1</ProductId>
//			<Quantity>2</Quantity>
//		</Line>
//		<Line>
//			<ProductId>100</ProductId>
//			<Quantity>5</Quantity>
//		</Line>
//	</Lines>
//</PlaceOrder>
}
