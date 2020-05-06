package com.infotel.eshop.search.mapper;

import com.infotel.eshop.search.dto.SuggestDto;
import com.infotel.eshop.search.model.SuggestItem;

public interface SuggestMapper {

	static SuggestDto suggestItemToSuggestDto(SuggestItem suggest) {
		SuggestDto dto = new SuggestDto();
		dto.setId(suggest.getId());
		dto.setName(suggest.getName());
		dto.setType(suggest.getName());
		
		return dto;
	}
}
