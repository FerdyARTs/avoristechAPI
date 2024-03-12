package com.plexus.fernando.avoristechAPI.persitence.repository;

import com.plexus.fernando.avoristechAPI.persitence.model.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long>{

}
