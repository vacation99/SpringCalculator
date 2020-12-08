package com.daniil.SpringCalculator.Repo;

import com.daniil.SpringCalculator.Models.History;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepo extends CrudRepository<History, Long> {
}
