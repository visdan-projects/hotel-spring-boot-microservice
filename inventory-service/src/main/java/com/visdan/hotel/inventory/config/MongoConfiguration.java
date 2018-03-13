package com.visdan.hotel.inventory.config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

	@Autowired
	private ServiceConfig serviceConfig;

	@Override
	protected String getDatabaseName() {
		return "inventory";
	}

	@Bean
	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(serviceConfig.getHost(), serviceConfig.getPort());
	}

	@Override
	public String getMappingBasePackage() {
		return "inventory";
	}

	@Bean
	@Override
	public CustomConversions customConversions() {
		List<Converter<?, ?>> converterList = new ArrayList<>();
		converterList.add(new LongToDateTimeConverter());
		converterList.add(new StringToDateTimeConverter());
		return new CustomConversions(converterList);
	}

	@ReadingConverter
	static class LongToDateTimeConverter implements Converter<Long, Date> {

		@Override
		public Date convert(Long source) {
			if (source == null) {
				return null;
			}

			return new Date(source);
		}
	}

	@ReadingConverter
	static class StringToDateTimeConverter implements Converter<String, Date> {

		@Override
		public Date convert(String source) {
			if (source == null) {
				return null;
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dateTime = LocalDate.parse(source, formatter);
			return Date.from(dateTime.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
	}
}
