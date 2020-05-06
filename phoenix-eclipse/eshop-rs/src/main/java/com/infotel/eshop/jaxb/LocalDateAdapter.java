package com.infotel.eshop.jaxb;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override // transformer une chaine de caractere en Localdate
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	}

	@Override // transformer une LocalDate en chaine de caractere
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}

}
