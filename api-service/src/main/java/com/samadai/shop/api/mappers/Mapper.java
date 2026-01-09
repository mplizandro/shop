package com.samadai.shop.api.mappers;

import java.util.stream.Collectors;

import com.samadai.shop.api.dtos.BranchDTO;
import com.samadai.shop.api.dtos.ProductDTO;
import com.samadai.shop.api.dtos.SaleDTO;
import com.samadai.shop.api.dtos.SaleDetailDTO;
import com.samadai.shop.api.entities.Branch;
import com.samadai.shop.api.entities.Product;
import com.samadai.shop.api.entities.Sale;
import com.samadai.shop.api.entities.SaleDetail;

public class Mapper {
	
	// Mapeo de Producto a ProductoDTO
	public static ProductDTO toDTO (Product product) {
		if (product == null) return null;
		
		return ProductDTO.builder()
				.id(product.getId())
				.name(product.getName())
				.category(product.getCategory())
				.priceUnit(product.getPriceUnit())
				.unit(product.getUnit())
				.stock(product.getStock())
				.build();
	}
	
	// Mapeo de Sucursal a SucursalDTO
	public static BranchDTO toDTO (Branch branch) {
		if (branch == null) return null;
		
		return BranchDTO.builder()
				.id(branch.getId())
				.name(branch.getName())
				.address(branch.getAddress())
				.build();
	}
	
	// Mapeo de Venta a VentaDTO
	public static SaleDTO toDTO (Sale sale) {
		if (sale == null) return null;
		
		var detail = sale.getDetail().stream()
				.map((SaleDetail d) -> SaleDetailDTO.builder()
						.id(d.getId())
						.idProduct(d.getProduct().getId())
						.nameProduct(d.getProduct().getName())
						.priceUnitProduct(d.getPriceUnit())
						.quantity(d.getQuantity())
						.subtotal(d.getPriceUnit() * d.getQuantity())
						.build()
				).collect(Collectors.toList());
		
		var total = detail.stream()
				.map(SaleDetailDTO::getSubtotal)
				.reduce(0.0, Double::sum);
		
		return SaleDTO.builder()
				.id(sale.getId())
				.saleDate(sale.getSaleDate())
				.idBranch(sale.getBranch().getId())
				.state(sale.getState())
				.detail(detail)
				.total(total)
				.build();
	}
}
