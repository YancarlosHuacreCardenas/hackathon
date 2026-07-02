-- ===================================================
-- SCRIPT DE BASE DE DATOS: MATRICULA CLOUD 360
-- SGBD: SQL SERVER (T-SQL) - COMPATIBLE CON TU HACKATHON
-- ===================================================

USE master;
GO

IF EXISTS (SELECT * FROM sys.databases WHERE name = 'MatriculaCloud360')
BEGIN
    ALTER DATABASE MatriculaCloud360 SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE MatriculaCloud360;
END
GO

CREATE DATABASE MatriculaCloud360;
GO

USE MatriculaCloud360;
GO

-- 1. TABLA MAESTRA: SEDES
CREATE TABLE Sedes (
    SedeID VARCHAR(10) PRIMARY KEY,
    NombreSede VARCHAR(100) NOT NULL,
    Ciudad VARCHAR(50) NOT NULL
);

-- 2. TABLA MAESTRA: CARRERAS
CREATE TABLE Carreras (
    CarreraID VARCHAR(10) PRIMARY KEY,
    NombreCarrera VARCHAR(100) NOT NULL,
    DuracionCiclos INT NOT NULL,
    Inversion DECIMAL(10,2) NOT NULL
);

-- 3. TABLA MAESTRA: CURSOS
CREATE TABLE Cursos (
    CursoID VARCHAR(10) PRIMARY KEY,
    NombreCurso VARCHAR(100) NOT NULL,
    Creditos INT NOT NULL
);

-- 4. TABLA MAESTRA: DOCENTES
CREATE TABLE Docentes (
    DocenteID INT PRIMARY KEY,
    NombresApellidos VARCHAR(150) NOT NULL,
    Especialidad VARCHAR(100) NOT NULL,
    CorreoCorporativo VARCHAR(100) NOT NULL
);

-- 5. TABLA MAESTRA: PROMOTORES / ASESORES COMERCIALES
CREATE TABLE Promotores (
    DNI VARCHAR(8) PRIMARY KEY,
    NombresApellidos VARCHAR(150) NOT NULL,
    SedeBaseID VARCHAR(10) FOREIGN KEY REFERENCES Sedes(SedeID),
    EmailCorporativo VARCHAR(100) NOT NULL
);

-- 6. TABLA TRANSACCIONAL DETALLE: CARGA DOCENTE / ASIGNACIÓN
CREATE TABLE CargaDocente (
    CarreraID VARCHAR(10) FOREIGN KEY REFERENCES Carreras(CarreraID),
    CursoID VARCHAR(10) FOREIGN KEY REFERENCES Cursos(CursoID),
    DocenteID INT FOREIGN KEY REFERENCES Docentes(DocenteID),
    Periodo VARCHAR(10) NOT NULL,
    PRIMARY KEY (CarreraID, CursoID, Periodo)
);

-- 7. TABLA MAESTRA: ESTUDIANTES / ALUMNOS
CREATE TABLE Estudiantes (
    DNI VARCHAR(8) PRIMARY KEY,
    Nombres VARCHAR(100) NOT NULL,
    Apellidos VARCHAR(100) NOT NULL,
    CorreoPersonal VARCHAR(100) NOT NULL,
    Telefono VARCHAR(15) NOT NULL
);

-- 8. TABLA TRANSACCIONAL CABECERA: MATRÍCULAS
CREATE TABLE Matriculas (
    MatriculaID VARCHAR(15) PRIMARY KEY,
    EstudianteDNI VARCHAR(8) FOREIGN KEY REFERENCES Estudiantes(DNI) NOT NULL,
    CarreraID VARCHAR(10) FOREIGN KEY REFERENCES Carreras(CarreraID) NOT NULL,
    SedeID VARCHAR(10) FOREIGN KEY REFERENCES Sedes(SedeID) NOT NULL,
    PromotorDNI VARCHAR(8) FOREIGN KEY REFERENCES Promotores(DNI) NOT NULL,
    Monto DECIMAL(10,2) NOT NULL,
    Periodo VARCHAR(10) NOT NULL,
    FechaRegistro DATETIME DEFAULT GETDATE() NOT NULL,
    deleted_at DATETIME NULL
);
GO

CREATE UNIQUE INDEX UX_Matriculas_Estudiante_Carrera_Periodo 
ON Matriculas (EstudianteDNI, CarreraID, Periodo) 
WHERE deleted_at IS NULL;
GO

-- ===================================================
-- POBLADO DE DATOS
-- ===================================================

INSERT INTO Sedes (SedeID, NombreSede, Ciudad) VALUES 
('SED-001', 'San Carlos', 'Chincha'),
('SED-002', 'Central', 'Cañete'),
('SED-003', 'Parque Empresarial', 'Ica'),
('SED-004', 'Miraflores', 'Lima');

INSERT INTO Carreras (CarreraID, NombreCarrera, DuracionCiclos, Inversion) VALUES
('CAR-101', 'Desarrollo de Software', 6, 12000.00),
('CAR-102', 'Administración de Empresas', 6, 15000.00),
('CAR-103', 'Marketing y Publicidad Digital', 4, 18000.00),
('CAR-104', 'Animación Digital 3D', 6, 24000.00);

INSERT INTO Cursos (CursoID, NombreCurso, Creditos) VALUES
('C-001', 'Lógica de Programación', 4),
('C-002', 'Diseño de Base de Datos', 3),
('C-003', 'Contabilidad General', 3),
('C-004', 'Comportamiento del Consumidor', 3),
('C-005', 'Modelado 3D Básico', 4),
('C-006', 'Ética Profesional', 2);

INSERT INTO Docentes (DocenteID, NombresApellidos, Especialidad, CorreoCorporativo) VALUES
(1, 'Alan Turing', 'Ingeniería de Software', 'aturing@edufuturo.edu.pe'),
(2, 'Philip Kotler', 'Marketing y Ventas', 'pkotler@edufuturo.edu.pe'),
(3, 'Peter Drucker', 'Gestión Empresarial', 'pdrucker@edufuturo.edu.pe'),
(4, 'Ed Catmull', 'Animación y VFX', 'ecatmull@edufuturo.edu.pe');

INSERT INTO Promotores (DNI, NombresApellidos, SedeBaseID, EmailCorporativo) VALUES
('45889922', 'Carlos Mendoza', 'SED-001', 'cmendoza@edufuturo.edu.pe'),
('71223344', 'Lucía Ramirez', 'SED-003', 'lramirez@edufuturo.edu.pe'),
('42551188', 'Jorge Valladolid', 'SED-004', 'jvalladolid@edufuturo.edu.pe');

INSERT INTO CargaDocente (CarreraID, CursoID, DocenteID, Periodo) VALUES
('CAR-101', 'C-001', 1, '2026-I'),
('CAR-101', 'C-002', 1, '2026-I'),
('CAR-101', 'C-006', 3, '2026-I'),
('CAR-102', 'C-003', 3, '2026-I'),
('CAR-102', 'C-006', 3, '2026-I'),
('CAR-103', 'C-004', 2, '2026-I'),
('CAR-104', 'C-005', 4, '2026-I');

INSERT INTO Estudiantes (DNI, Nombres, Apellidos, CorreoPersonal, Telefono) VALUES
('75112233', 'Ana María', 'Pérez Gómez', 'ana.perez@gmail.com', '987654321'),
('48996655', 'Luis Felipe', 'Torres Rivas', 'luisf.torres@outlook.com', '912345678'),
('70114477', 'Carmen', 'Rojas Silva', 'crojas_99@yahoo.com', '945612378');

INSERT INTO Matriculas (MatriculaID, EstudianteDNI, CarreraID, SedeID, PromotorDNI, Monto, Periodo, deleted_at) VALUES
('MAT-10001', '75112233', 'CAR-101', 'SED-001', '45889922', 4500.00, '2026-I', NULL),
('MAT-10002', '48996655', 'CAR-104', 'SED-003', '71223344', 4800.00, '2026-I', NULL),
('MAT-10003', '70114477', 'CAR-103', 'SED-004', '42551188', 3200.00, '2026-I', NULL);
GO

CREATE VIEW V_Dashboard_Comisiones AS
SELECT 
    p.DNI AS CodigoPromotor,
    p.NombresApellidos AS Promotor,
    s.NombreSede AS Sede,
    COUNT(m.MatriculaID) AS TotalMatriculasActivas,
    SUM(m.Monto) AS VentasTotales,
    SUM(m.Monto) * 0.10 AS ComisionesAcumuladas
FROM Promotores p
JOIN Sedes s ON p.SedeBaseID = s.SedeID
LEFT JOIN Matriculas m ON p.DNI = m.PromotorDNI AND m.deleted_at IS NULL
GROUP BY p.DNI, p.NombresApellidos, s.NombreSede;
GO
