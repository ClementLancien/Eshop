package com.infotel.eshop.mapper;

import com.infotel.eshop.dto.FigureDto;
import com.infotel.eshop.model.Figure;

public interface FigureMapper {

	static FigureDto figuretoFigureDto(Figure figure) {
		FigureDto dto = new FigureDto();
		if (figure == null) return dto;
		dto.setId(figure.getId());
		dto.setName(figure.getName());
		
		return dto;
	}
}
