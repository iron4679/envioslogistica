package com.grupo7.tpi.envioslogistica.repository;

import com.grupo7.tpi.envioslogistica.model.Tracking;
import com.grupo7.tpi.envioslogistica.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  Repositorio de acceso a datos para la entidad {@link Tracking}.
 * 
 * Esta interfaz extiende {@link JpaRepository}, lo que provee un conjunto
 * de operaciones CRUD (crear, leer, actualizar, eliminar) sobre la entidad
 * Tracking sin necesidad de implementar código adicional.
 * 
 * Además, define consultas específicas relacionadas con el historial de
 * seguimiento de un {@link Envio}.
 */
@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {
    /**
     * Obtiene la lista de registros de seguimiento asociados a un envío
     * específico, ordenados cronológicamente por su marca de tiempo.
     *
     * Este método aprovecha la convención de nombres de Spring Data JPA
     * para generar automáticamente la consulta SQL correspondiente.
     *
     * @param envio el envío del cual se desea obtener el historial de tracking
     * @return una lista de objetos {@link Tracking} ordenados de forma ascendente por timestamp
     */

    List<Tracking> findByEnvioOrderByTimestampAsc(Envio envio);
}