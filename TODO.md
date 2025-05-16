## ✅ Funcionalidades principales (mínimas)

- [x] **Lectura de archivos por lotes**  
  - [x] Leer todos los archivos en un directorio
  - [x] Leer su contenido como stream (inmutable)

- [x] **Validación funcional de datos**  
  - [x] Validar formato por archivo (`isValidCSV`, `isValidJSON`)  
  - [x] Validar contenido por línea o registro  

- [x] **Transformación funcional**  
  - [x] Convertir contenido (ej. JSON a objeto, CSV a JSON)  
  - [ ] Aplicar reglas de negocio como funciones puras

- [x] **Procesamiento por tipo de archivo**  
  - [x] Crear un *dispatcher funcional* según extensión

- [-] **Almacenamiento o persistencia**  
  - [-] Exportar los datos procesados (base de datos, archivos)  
  - [ ] Pasar funciones de persistencia como argumentos (FP)

- [ ] **Reporte funcional**  
  - [ ] Generar un reporte como un `Map<FileName, Result>` o similar  
  - [ ] Separar éxito y fallo sin estructuras mutables

---

## 🚀 Funcionalidades avanzadas

- [x] **Procesamiento paralelo o asincrónico funcional**  
  - [x] Usar librerías como `RxJS` (JS) o `Streams` / `CompletableFuture` (Java) sin romper la inmutabilidad

- [ ] **Detección de duplicados o registros inválidos**  
  - [ ] Aplicar funciones puras de filtro y reducción

- [ ] **Historial funcional del procesamiento**  
  - [ ] Guardar una lista de transformaciones hechas por archivo (`PipelineLog`)

- [ ] **Sistema de plugins funcionales**  
  - [ ] Permitir funciones externas del tipo `(data) => transformedData` como plugins

- [x] **Pipelines composables**  
  - [x] Componer pasos del procesamiento como un pipeline

- [ ] **Manejo funcional de errores**  
  - [ ] Usar estructuras como `Result`, `Either`, etc. para control de flujo

- [x] **Modo observador funcional (stream de cambios)**  
  - [x] Observar una carpeta y disparar procesamiento cuando hay nuevos archivos  
  - [x] Encapsular efectos secundarios en funciones puras
