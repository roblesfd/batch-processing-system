## ✅ Funcionalidades principales (mínimas)

- [-] **Lectura de archivos por lotes**  
  - [-] Leer todos los archivos en un directorio
  - [-] Leer su contenido como stream (inmutable)

- [-] **Validación funcional de datos**  
  - [-] Validar formato por archivo (`isValidCSV`, `isValidJSON`)  
  - [-] Validar contenido por línea o registro  

- [-] **Transformación funcional**  
  - [-] Convertir contenido (ej. JSON a objeto, CSV a JSON)  
  - [ ] Aplicar reglas de negocio como funciones puras

- [-] **Procesamiento por tipo de archivo**  
  - [-] Crear un *dispatcher funcional* según extensión

- [-] **Almacenamiento o persistencia**  
  - [-] Exportar los datos procesados (base de datos, archivos)  
  - [ ] Pasar funciones de persistencia como argumentos (FP)

- [ ] **Reporte funcional**  
  - [ ] Generar un reporte como un `Map<FileName, Result>` o similar  
  - [ ] Separar éxito y fallo sin estructuras mutables

---

## 🚀 Funcionalidades avanzadas

- [ ] **Procesamiento paralelo o asincrónico funcional**  
  - [ ] Usar librerías como `RxJS` (JS) o `Streams` / `CompletableFuture` (Java) sin romper la inmutabilidad

- [ ] **Detección de duplicados o registros inválidos**  
  - [ ] Aplicar funciones puras de filtro y reducción

- [ ] **Historial funcional del procesamiento**  
  - [ ] Guardar una lista de transformaciones hechas por archivo (`PipelineLog`)

- [ ] **Sistema de plugins funcionales**  
  - [ ] Permitir funciones externas del tipo `(data) => transformedData` como plugins

- [ ] **Pipelines composables**  
  - [ ] Componer pasos del procesamiento como un pipeline

- [ ] **Manejo funcional de errores**  
  - [ ] Usar estructuras como `Result`, `Either`, etc. para control de flujo

- [-] **Modo observador funcional (stream de cambios)**  
  - [-] Observar una carpeta y disparar procesamiento cuando hay nuevos archivos  
  - [ ] Encapsular efectos secundarios en funciones puras
