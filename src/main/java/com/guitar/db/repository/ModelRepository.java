package com.guitar.db.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.guitar.db.model.Model;

@Repository
public class ModelRepository {
	@PersistenceContext
	private EntityManager entityManager;

	private ModelJpaRepository modelJpaRepository;

	public ModelRepository(ModelJpaRepository modelJpaRepository){
		this.modelJpaRepository = modelJpaRepository;
	}

	/**
	 * Create
	 */
	public Model create(Model mod) {
		return modelJpaRepository.saveAndFlush(mod);
	}

	/**
	 * Update
	 */
	public Model update(Model mod) {
		return modelJpaRepository.saveAndFlush(mod);
	}

	/**
	 * Delete
	 */
	public void delete(Model mod) {
		modelJpaRepository.delete(mod);
	}

	/**
	 * Find
	 */
	public Model find(Long id) {
		return modelJpaRepository.getOne(id);
	}

	/**
	 * Custom finder
	 */
	public List<Model> getModelsInPriceRange(BigDecimal lowest, BigDecimal highest) {
		@SuppressWarnings("unchecked")
		List<Model> mods = entityManager
				.createQuery("select m from Model m where m.price >= :lowest and m.price <= :highest")
				.setParameter("lowest", lowest)
				.setParameter("highest", highest).getResultList();
		return mods;
	}

	/**
	 * Custom finder
	 */
	public Page<Model> getModelsByPriceRangeAndWoodType(BigDecimal lowest, BigDecimal highest, String wood) {
		Pageable page = PageRequest.of(0, 2);
		return modelJpaRepository.queryByPriceRangeAndWoodType(lowest, highest, "%" + wood + "%", page);
	}

	/**
	 * NamedQuery finder
	 */
	public List<Model> getModelsByType(String modelType) {
		return modelJpaRepository.findAllModelsByType(modelType);
	}

	/**
	 * Count
	 */
	public Long getModelCount() {
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Model.class)));
		return entityManager.createQuery(cq).getSingleResult();
	}
}
