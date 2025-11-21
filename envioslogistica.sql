CREATE DATABASE IF NOT EXISTS envioslogistica;
USE envioslogistica;

CREATE TABLE envio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    orden_id VARCHAR(50) NOT NULL UNIQUE,
    direccion VARCHAR(255) NOT NULL,
    modalidad VARCHAR(20) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    tracking_id BIGINT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_estado CHECK (estado IN ('PENDIENTE', 'EN_TRANSITO', 'ENTREGADO', 'CANCELADO')),
    CONSTRAINT chk_modalidad CHECK (modalidad IN ('NORMAL', 'EXPRESS', 'RETIRO_EN_SUCURSAL'))
);

CREATE TABLE tracking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    envio_id BIGINT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    timestamp DATETIME NOT NULL,
    FOREIGN KEY (envio_id) REFERENCES envio(id) ON DELETE CASCADE,
    INDEX idx_envio_id_timestamp (envio_id, timestamp),
    INDEX idx_estado (estado),
    INDEX idx_timestamp (timestamp)
);

ALTER TABLE envio
    ADD CONSTRAINT fk_tracking_id FOREIGN KEY (tracking_id) REFERENCES tracking(id);
