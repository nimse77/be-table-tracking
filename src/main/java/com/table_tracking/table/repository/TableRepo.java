package com.table_tracking.table.repository;

import com.table_tracking.table.model.TableEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TableRepo extends MongoRepository<TableEntity, String> {
}