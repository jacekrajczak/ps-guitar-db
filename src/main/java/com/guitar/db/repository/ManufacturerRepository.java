package com.guitar.db.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guitar.db.model.Manufacturer;

@Repository
public class ManufacturerRepository {
	@PersistenceContext
	private EntityManager entityManager;

	ManufacturerJpaRepository manufacturerJpaRepository;

	@Autowired
	ManufacturerRepository(ManufacturerJpaRepository manufacturerJpaRepository){
		this.manufacturerJpaRepository = manufacturerJpaRepository;
	}

	/**
	 * Create
	 */
	public Manufacturer create(Manufacturer man) {
		return manufacturerJpaRepository.saveAndFlush(man);
	}

	/**
	 * Update
	 */
	public Manufacturer update(Manufacturer man) {
		return manufacturerJpaRepository.saveAndFlush(man);
	}

	/**
	 * Delete
	 */
	public void delete(Manufacturer man) {
		manufacturerJpaRepository.delete(man);
	}

	/**
	 * Find
	 */
	public Manufacturer find(Long id) {
		return manufacturerJpaRepository.getOne(id);
	}

	/**
	 * Custom finder
	 */
	public List<Manufacturer> getManufacturersFoundedBeforeDate(Date date) {
		return manufacturerJpaRepository.findByFoundedDateBefore(date);
	}

	/**
	 * Custom finder
	 */
	public Manufacturer getManufacturerByName(String name) {
		return manufacturerJpaRepository.findByNameLike(name);
	}

	/**
	 * Native Query finder
	 */
	/*public List<Manufacturer> getManufacturersThatSellModelsOfType(String modelType) {
		return manufacturerJpaRepository.findByModelsLike(modelType);
	}*/
}
