package com.example.devopsdemo1.repository;

/**
*
* @author TANUSREE
*/

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.devopsdemo1.entity.TableEntity;


@Repository
public interface TableRepository extends CrudRepository<TableEntity, Integer>{	
	List<TableEntity> findByTableType(String tableType);

}
