package com.grupo7.tpi.envioslogistica.repository;

import com.grupo7.tpi.envioslogistica.model.Tracking;
import com.grupo7.tpi.envioslogistica.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {
    List<Tracking> findByEnvioOrderByTimestampAsc(Envio envio);
}