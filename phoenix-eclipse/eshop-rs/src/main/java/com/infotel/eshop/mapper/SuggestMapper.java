package com.infotel.eshop.mapper;

import com.infotel.eshop.dto.SuggestDto;
import com.infotel.eshop.model.SuggestItem;

public interface SuggestMapper {

	static SuggestDto suggestItemToSuggestDto(SuggestItem suggest) {
		SuggestDto dto = new SuggestDto();
		dto.setId(suggest.getId());
		dto.setName(suggest.getName());
		dto.setType(suggest.getName());
		
		return dto;
	}
}
