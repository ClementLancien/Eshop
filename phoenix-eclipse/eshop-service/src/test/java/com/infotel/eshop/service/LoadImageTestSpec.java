package com.infotel.eshop.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.infotel.eshop.config.TestConfig;
import com.infotel.eshop.model.ProductImage;

@RunWith(SpringRunner.class) @ContextConfiguration(classes=TestConfig.class)
public class LoadImageTestSpec extends AbstractTest {
	
	@Autowired
	private CatalogService service;
	
	@Test
	public void it_should_get_one_image_from_id_1() {
		ProductImage image = service.findProductImageById(1);
		
		assertThat(image)
			.isNotNull()
			.extracting(ProductImage::getId, img -> getHash(img.getContent()))
			//.isEqualTo(1);
			.containsExactly(1, "V8Te1SAb8cKVUnJw/Lc+DBlsRJk=");
	}
	
	@Test
	public void it_should_get_one_image_from_id_789() {
		ProductImage image = service.findProductImageById(789);
		
		assertThat(image)
			.isNotNull()
			.extracting(ProductImage::getId, img -> getHash(img.getContent()))
			//.isEqualTo(1);
			.containsExactly(789, "qnC45acp16NpDfRM6Q1MeCHE3Go=");
	}
	
	@Test
	public void it_should_get_one_image_from_id_1259() {
		ProductImage image = service.findProductImageById(1259);
		
		assertThat(image)
			.isNotNull()
			.extracting(ProductImage::getId, img -> getHash(img.getContent()))
			//.isEqualTo(1);
			.containsExactly(1259, "SX71dB/ULeFDDjsFTcxdKTHNCZw=");
	}

	@Test
	public void it_should_get_one_image_from_id_2000() {
		ProductImage image = service.findProductImageById(2000);
		
		assertThat(image)
			.isNotNull()
			.extracting(ProductImage::getId, img -> getHash(img.getContent()))
			.containsExactly(2000, "Yc8zRLvLQkalIAIOImI6QIE43o8=");
	}
	
	@Test
	public void it_should_get_null_from_id_minus_1() {
		ProductImage image = service.findProductImageById(-1);
		
		assertThat(image)
			.isNull();
	}
	
	
}
