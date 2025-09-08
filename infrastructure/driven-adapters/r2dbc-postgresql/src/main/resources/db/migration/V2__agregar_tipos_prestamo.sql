-- Migración V2: Agregar dos tipos de préstamo de prueba

INSERT INTO solicitudes.tipos_prestamo (id, nombre, descripcion)
VALUES (1, 'Consumo', 'Préstamo de consumo para gastos personales');

INSERT INTO solicitudes.tipos_prestamo (id, nombre, descripcion)
VALUES (2, 'Vehículo', 'Préstamo para adquisición de vehículo');

