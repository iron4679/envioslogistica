package com.grupo7.tpi.envioslogistica.repository;


import com.grupo7.tpi.envioslogistica.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {
    Optional<Envio> findByTracking(String tracking);
}