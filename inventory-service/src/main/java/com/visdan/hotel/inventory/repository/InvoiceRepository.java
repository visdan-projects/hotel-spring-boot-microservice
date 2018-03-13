package com.visdan.hotel.inventory.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.visdan.hotel.inventory.model.Invoice;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, String> {
	public List<Invoice> findByUsername(@Param("username") String username);
}
