package com.infotel.eshop.jaxb;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

	@Override // transformer une chaine de caractere en LocaldateTime
	public LocalDateTime unmarshal(String v) throws Exception {
		return LocalDateTime.parse(v);
	}

	@Override // transformer une LocalDateTime en chaine de caractere
	public String marshal(LocalDateTime v) throws Exception {
		return v.toString();
	}

}
