package com.infotel.eshop.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.dto.PlaceOrderDto;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderLine;

public interface OrderMapper {

	static OrderDto orderToOrderDto(Order order) {
		OrderDto dto = new OrderDto();
		
		dto.setId(order.getId());
		dto.setOrderNumber(order.getOrderNumber());
		dto.setDateTime(order.getDateTime());
		dto.setStatus(order.getStatus().name());
		dto.setLines(OrderLineMapper.orderLinestoOrderLinesDto(order.getLines()));
		dto.calculTotalAmount();
		
		CustomerDto cust = new CustomerDto();
		cust.setUsername(order.getCustomer().getUsername());
		cust.setFirstName(order.getCustomer().getFirstName());
		cust.setLastName(order.getCustomer().getLastName());
		
		dto.setCustomer(cust);
		
		return dto;
	}
	
	static List<OrderDto> orderToOrderDtoes(List<Order> orders) {
		return orders.stream()
					 .map(OrderMapper::orderToOrderDto)
					 .collect(Collectors.toList());
	}
	
	static Order orderDtoToOrder(PlaceOrderDto dto) {
		Order order = new Order();

		order.setCustomer(UserMapper.customerDtoToCustomer(dto.getCustomer()));
		List<OrderLine> lines = OrderLineMapper.placeOrderLineDtoesToOrderLines(dto.getLines());
		
		for (OrderLine line : lines) {
			order.addLine(line.getProduct(), line.getQuantity());
		}
		//order.addLine(OrderLineMapper.placeOrderLineDtoesToOrderLines(dto.getLines()));
		return order;
	}
}
