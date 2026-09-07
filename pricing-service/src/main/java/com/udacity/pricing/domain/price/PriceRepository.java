package com.udacity.pricing.domain.price;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// @Repository
@RepositoryRestResource(path = "prices")
public interface PriceRepository extends CrudRepository<Price, Long>{
}
