package com.infotel.eshop.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.infotel.eshop.dto.OrderLineDto;
import com.infotel.eshop.dto.PlaceOrderLineDto;
import com.infotel.eshop.model.OrderLine;

public interface OrderLineMapper {

	static OrderLineDto orderLinetoOrderLineDto(OrderLine line) {
		OrderLineDto dto = new OrderLineDto();
		
		dto.setId(line.getId());
		dto.setQuantity(line.getQuantity());
		dto.setProduct(ProductMapper.productToProductLightDto(line.getProduct()));
		
		return dto;
	}
	
	static List<OrderLineDto> orderLinestoOrderLinesDto(List<OrderLine> lines) {
		return lines.stream()
					.map(OrderLineMapper::orderLinetoOrderLineDto)
					.collect(Collectors.toList());
	}
	
	static OrderLine placeOrderLineDtoToOrderLine(PlaceOrderLineDto dto) {
		OrderLine line = new OrderLine();
		line.setProduct(ProductMapper.productIdToProduct(dto.getProductId()));
		line.setQuantity(dto.getQuantity());
		return line;
	}
	
	static List<OrderLine> placeOrderLineDtoesToOrderLines(List<PlaceOrderLineDto> dto) {
		return dto.stream()
				  .map(OrderLineMapper::placeOrderLineDtoToOrderLine)
				  .collect(Collectors.toList());
	}
}
