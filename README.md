# 🎓 MatriculaCloud360 — Backend API

Backend REST desarrollado con **Spring Boot 3** + **SQL Server**, empaquetado en Docker y publicado en Docker Hub.

---

## 🐳 Levantar con Docker

### 1. Red compartida (solo la primera vez)
```bash
docker network create my-network
```

### 2. Base de datos
```bash
docker-compose -f docker-compose-yancarlos/docker-compose-db.yml up -d
```

### 3. Backend
```bash
docker-compose -f docker-compose-yancarlos/docker-compose-be.yml up -d
```

> 📌 Swagger UI: [http://localhost:5000/swagger-ui.html](http://localhost:5000/swagger-ui.html)

---

## 🗂️ Imagen Docker Hub

| Imagen | Tag | Descripción |
|--------|-----|-------------|
| `yancarlos18/matricula-sqlserver-db` | `2022` | SQL Server con script inicial |
| `yancarlos18/matricula-sqlserver` | `2.0` | Backend Spring Boot (última versión) |

---

## 📋 Tablas Maestras

### 🏫 Sedes — `GET /api/sede`

```json
{
  "sedeID": "SED-001",
  "nombreSede": "San Carlos",
  "ciudad": "Chincha"
}
```

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/sede` | Listar todas las sedes |
| `GET` | `/api/sede/{id}` | Buscar sede por ID |
| `POST` | `/api/sede` | Crear nueva sede |
| `PUT` | `/api/sede/{id}` | Actualizar sede |
| `DELETE` | `/api/sede/{id}` | Eliminar sede |

---

### 📚 Carreras — `GET /api/carrera`

```json
{
  "carreraID": "CAR-101",
  "nombreCarrera": "Desarrollo de Software",
  "duracionCiclos": 6,
  "inversion": 12000.00
}
```

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/carrera` | Listar todas las carreras |
| `GET` | `/api/carrera/{id}` | Buscar carrera por ID |
| `POST` | `/api/carrera` | Crear nueva carrera |
| `PUT` | `/api/carrera/{id}` | Actualizar carrera |
| `DELETE` | `/api/carrera/{id}` | Eliminar carrera |

---

### 📖 Cursos — `GET /api/curso`

```json
{
  "cursoID": "C-001",
  "nombreCurso": "Lógica de Programación",
  "creditos": 4
}
```

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/curso` | Listar todos los cursos |
| `GET` | `/api/curso/{id}` | Buscar curso por ID |
| `POST` | `/api/curso` | Crear nuevo curso |
| `PUT` | `/api/curso/{id}` | Actualizar curso |
| `DELETE` | `/api/curso/{id}` | Eliminar curso |

---

### 👨‍🏫 Docentes — `GET /api/docente`

```json
{
  "docenteID": 1,
  "nombresApellidos": "Alan Turing",
  "especialidad": "Ingeniería de Software",
  "correoCorporativo": "aturing@edufuturo.edu.pe"
}
```

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/docente` | Listar todos los docentes |
| `GET` | `/api/docente/{id}` | Buscar docente por ID |
| `POST` | `/api/docente` | Crear nuevo docente |
| `PUT` | `/api/docente/{id}` | Actualizar docente |
| `DELETE` | `/api/docente/{id}` | Eliminar docente |

---

### 📣 Promotores — `GET /api/promotor`

```json
{
  "dni": "45889922",
  "nombresApellidos": "Carlos Mendoza",
  "emailCorporativo": "cmendoza@edufuturo.edu.pe",
  "sedeBase": {
    "sedeID": "SED-001",
    "nombreSede": "San Carlos",
    "ciudad": "Chincha"
  }
}
```

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/promotor` | Listar todos los promotores |
| `GET` | `/api/promotor/{dni}` | Buscar promotor por DNI |
| `POST` | `/api/promotor` | Crear nuevo promotor |
| `PUT` | `/api/promotor/{dni}` | Actualizar promotor |
| `DELETE` | `/api/promotor/{dni}` | Eliminar promotor |

---

### 🎓 Estudiantes — `GET /api/estudiante`

```json
{
  "dni": "75112233",
  "nombres": "Ana María",
  "apellidos": "Pérez Gómez",
  "correoPersonal": "ana.perez@gmail.com",
  "telefono": "987654321"
}
```

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/estudiante` | Listar todos los estudiantes |
| `GET` | `/api/estudiante/{dni}` | Buscar estudiante por DNI |
| `POST` | `/api/estudiante` | Crear nuevo estudiante |
| `PUT` | `/api/estudiante/{dni}` | Actualizar estudiante |
| `DELETE` | `/api/estudiante/{dni}` | Eliminar estudiante |

---

## 📋 Tablas Transaccionales

### 📝 Matrículas — `GET /api/matricula` *(Transaccional Cabecera)*

```json
{
  "matriculaID": "MAT-10001",
  "monto": 4500.00,
  "periodo": "2026-I",
  "fechaRegistro": "2026-07-02T10:00:00",
  "estudiante": {
    "dni": "75112233",
    "nombres": "Ana María",
    "apellidos": "Pérez Gómez"
  },
  "carrera": {
    "carreraID": "CAR-101",
    "nombreCarrera": "Desarrollo de Software"
  },
  "sede": {
    "sedeID": "SED-001",
    "nombreSede": "San Carlos"
  },
  "promotor": {
    "dni": "45889922",
    "nombresApellidos": "Carlos Mendoza"
  }
}
```

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/matricula` | Listar todas las matrículas activas |
| `GET` | `/api/matricula/{id}` | Buscar matrícula por ID |
| `POST` | `/api/matricula` | Crear nueva matrícula |
| `PUT` | `/api/matricula/{id}` | Actualizar matrícula |
| `DELETE` | `/api/matricula/{id}` | Soft delete (borrado lógico) |
| `GET` | `/api/matricula/{id}/pdf` | Exportar matrícula en PDF |

---

### 📋 Carga Docente — `GET /api/carga-docente` *(Transaccional Detalle)*

```json
{
  "carreraID": "CAR-101",
  "cursoID": "C-001",
  "docenteID": 1,
  "periodo": "2026-I"
}
```

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/carga-docente` | Listar todas las asignaciones |
| `POST` | `/api/carga-docente` | Crear nueva asignación docente |
| `DELETE` | `/api/carga-docente` | Eliminar asignación |

---

### 📊 Dashboard Comisiones — `GET /api/dashboard/comisiones`

```json
{
  "codigoPromotor": "45889922",
  "promotor": "Carlos Mendoza",
  "sede": "San Carlos",
  "totalMatriculasActivas": 2,
  "ventasTotales": 9000.00,
  "comisionesAcumuladas": 900.00
}
```

---

## 🛠️ Tecnologías

| Tecnología | Versión |
|-----------|---------|
| Java | 25 |
| Spring Boot | 3.5.13 |
| Spring Data JPA | 3.5 |
| SQL Server | 2022 |
| Docker | latest |
| Maven | 3.9 |

---

## 👤 Autor

**Yancarlos Huacre Cardenas** — [@YancarlosHuacreCardenas](https://github.com/YancarlosHuacreCardenas)
