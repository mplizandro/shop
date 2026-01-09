package com.samadai.shop.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samadai.shop.api.dtos.SaleDTO;
import com.samadai.shop.api.dtos.SaleDetailDTO;
import com.samadai.shop.api.entities.Branch;
import com.samadai.shop.api.entities.Product;
import com.samadai.shop.api.entities.Sale;
import com.samadai.shop.api.entities.SaleDetail;
import com.samadai.shop.api.exceptions.NotFoundException;
import com.samadai.shop.api.mappers.Mapper;
import com.samadai.shop.api.respositories.BranchRepository;
import com.samadai.shop.api.respositories.ProductRepository;
import com.samadai.shop.api.respositories.SaleRepository;
import com.samadai.shop.api.services.ISaleService;

@Service
public class SaleServiceImpl implements ISaleService{

	@Autowired
	private SaleRepository repository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private ProductRepository productoRepository;
	
	@Override
	public List<SaleDTO> findAll() {
		List<Sale> sales = repository.findAll();
		List<SaleDTO> salesDTO = new ArrayList<SaleDTO>();
		
		for (Sale s: sales) {
			salesDTO.add(Mapper.toDTO(s));
		}
		
		return salesDTO;
	}

	@Override
	public SaleDTO findById(Long id) {
		Sale s = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("No existe la venta"));
		
		return Mapper.toDTO(s);
	}

	@Override
	public SaleDTO save(SaleDTO dto) {
		// Validaciones
		if (dto == null) throw new RuntimeException("VentaDTO es null");
		if (dto.getIdBranch() == null) throw new RuntimeException("Debe especificar una sucursal");
		if (dto.getDetail() == null || dto.getDetail().isEmpty()) 
			throw new RuntimeException("Debe incluir al menos un producto");
		
		// Buscar la sucursal
		Branch b = branchRepository.findById(dto.getIdBranch()).orElse(null);
		if (b == null)
			throw new NotFoundException("Sucursal no encontrada");
		
		// Creamos la venta
		Sale sale = new Sale();
		sale.setSaleDate(dto.getSaleDate());
		sale.setState(dto.getState());
		sale.setBranch(b);
		sale.setTotal(dto.getTotal());
		
		// La lista de detalles
		// -- ava esta los pruductos
		List<SaleDetail> detail = new ArrayList<SaleDetail>();
		Double total = 0.0;
		
		for (SaleDetailDTO detailDTO: dto.getDetail()) {
			Product p = productoRepository.findById(detailDTO.getIdProduct()).orElse(null);
			if (p == null)
				throw new NotFoundException("El producto " + detailDTO.getNameProduct() + " no existe");
			
			// creamos el detalle
			SaleDetail saleDetail = new SaleDetail();
			saleDetail.setProduct(p);
			saleDetail.setPriceUnit(detailDTO.getPriceUnitProduct());
			saleDetail.setQuantity(detailDTO.getQuantity());
			saleDetail.setSale(sale);
			
			detail.add(saleDetail);
			total += (detailDTO.getPriceUnitProduct() * detailDTO.getQuantity());
		}
		
		sale.setTotal(total);
		sale.setDetail(detail);
		
		// Guardamos la venta
		sale = repository.save(sale);
		
		return Mapper.toDTO(sale);
	}

	@Override
	public SaleDTO update(Long id, SaleDTO dto) {
		//buscar si la venta existe para actualizarla
        Sale s = repository.findById(id).orElse(null);
        if (s == null) throw new RuntimeException("Venta no encontrada");

        if (dto.getSaleDate()!=null) {
            s.setSaleDate(dto.getSaleDate());
        }
        if(dto.getState()!=null) {
            s.setState(dto.getState());
        }

        if (dto.getTotal()!=null) {
            s.setTotal(dto.getTotal());
        }

        if (dto.getIdBranch() != null) {
            Branch b = branchRepository.findById(dto.getIdBranch()).orElse(null);
            if (b == null) throw new NotFoundException("Sucursal no encontrada");
            s.setBranch(b);
        }
        
        s = repository.save(s);

        SaleDTO saleOut = Mapper.toDTO(s);

        return saleOut;
	}

	@Override
	public void delete(Long id) {
		if ( !repository.existsById(id) )
			throw new NotFoundException("No existe la venta para eliminar");
		
		repository.deleteById(id);
	}

}
