-- ===========================
-- V1 - Esquema y tablas
-- ===========================

-- 1) Crear esquema lógico del microservicio
CREATE SCHEMA IF NOT EXISTS solicitudes;

-- 2) Catálogo de tipos de préstamo
CREATE TABLE IF NOT EXISTS solicitudes.tipos_prestamo (
    id          BIGSERIAL PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL UNIQUE,
    descripcion VARCHAR(200)
);

-- 3) Solicitudes de crédito
CREATE TABLE IF NOT EXISTS solicitudes.solicitudes (
    id                BIGSERIAL PRIMARY KEY,
    tipo_documento    VARCHAR(5)   NOT NULL,
    numero_documento  VARCHAR(20)  NOT NULL,
    monto             NUMERIC      NOT NULL CHECK (monto > 0),
    plazo             INT          NOT NULL CHECK (plazo > 0),
    tipo_prestamo_id  BIGINT       NOT NULL,
    estado            VARCHAR(50)  NOT NULL DEFAULT 'Pendiente de revisión',
    fecha_creacion    TIMESTAMP    NOT NULL,

    CONSTRAINT fk_solicitud_tipo_prestamo
        FOREIGN KEY (tipo_prestamo_id)
        REFERENCES solicitudes.tipos_prestamo (id)
);

-- 4) Índices recomendados
-- Búsquedas por cliente (tipo y número de documento)
CREATE INDEX IF NOT EXISTS idx_solicitudes_cliente
    ON solicitudes.solicitudes (tipo_documento, numero_documento);

-- Búsquedas por estado
CREATE INDEX IF NOT EXISTS idx_solicitudes_estado
    ON solicitudes.solicitudes (estado);
