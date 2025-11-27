package com.grupo7.tpi.envioslogistica.repository;


import com.grupo7.tpi.envioslogistica.model.Envio;
import com.grupo7.tpi.envioslogistica.model.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

    // Buscar por estado del envío
    List<Envio> findByEstado(String estado);

    // Buscar por ordenId
    Optional<Envio> findByOrdenId(String ordenId);

    // Buscar por tracking actual
    Optional<Envio> findByTrackingActual(Tracking tracking);

    boolean existsByOrdenId(String ordenId);
}