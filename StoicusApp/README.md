# Stoicus - Entrenador Personal Estoico

App Android que convierte la filosofía estoica en un sistema de entrenamiento diario con tres pilares: mente, cuerpo y alma.

## Arquitectura

- **Jetpack Compose** + Material 3
- **Room** (offline-first)
- **Hilt** (DI)
- **Clean Architecture** (data/domain/presentation)
- **Coil** (image loading)

## Features

### Ritual Diario
- **Onboarding personalizado** - Configura tu viaje estoico
- **Ritual matutino guiado** - Previsualisatio, Dicotomía del Control, etc.
- **Micro-acciones durante el día** - Tareas para mente, cuerpo y alma
- **Ritual nocturno** - Check-in de ánimo y reflexión

### Biblioteca de Filosofía
- **40+ citas de filósofos estoicos**:
  - Marco Aurelio (10 citas)
  - Epicteto (10 citas)
  - Séneca (10 citas)
  - Baruch Spinoza (10 citas)
- Filtrado por pilar (Mente, Cuerpo, Alma)
- Citas favoritas
- Información del era filosófica

### Galería Estoica 🆕
- Estatuas de filósofos estoicos
- Arte y representaciones históricas
- Símbolos estoicos
- Manuscritos antiguos
- Filtrado por categoría
- Sistema de favoritos

### Música Estoica 🆕
- Música ambiental para meditación
- Música de enfoque para estudio/trabajo
- Música para dormir
- Reproductor en segundo plano
- Control de reproducción (play/pause/stop)
- Sistema de favoritos

### Analytics
- Dashboard de analytics profundo
- Seguimiento de rachas
- Progreso por pilar
- Estadísticas de rituales

### Tareas Diarias Mejoradas 🆕
- **Mente**: Lectura de meditaciones, dicotomía del control, reformulación cognitiva, visualización
- **Cuerpo**: Ejercicio funcional, caminata consciente, duchas frías, alimentación frugal
- **Alma**: Gratitud, actos de bondad, meditación, journaling, sympatheia, memento mori
- Filtrado por categoría
- Sistema de progreso visual

### Notificaciones
- Notificaciones programadas para rituales
- Recordatorios de tareas diarias
- Motivación estoica diaria

## Estructura del Proyecto

```
app/
├── core/
│   ├── data/
│   │   └── local/
│   │       ├── dao/
│   │       ├── entity/
│   │       └── StoicusDatabase.kt
│   ├── domain/
│   │   ├── model/
│   │   └── usecase/
│   ├── notifications/
│   └── ui/
│       ├── components/
│       └── theme/
├── di/
│   └── AppModule.kt
├── feature/
│   ├── analytics/
│   ├── gallery/ (🆕)
│   ├── morning/
│   ├── micro/
│   ├── music/ (🆕)
│   ├── evening/
│   ├── onboarding/
│   └── philosophy/
├── navigation/
│   ├── NavGraph.kt
│   └── Screen.kt
├── MainActivity.kt
└── StoicusApp.kt
```

## Base de Datos

La app utiliza Room con las siguientes entidades:

- `UserProfile` - Información del usuario
- `RitualSession` - Sesiones de rituales completadas
- `MicroAction` / `DailyGoal` - Tareas diarias
- `MoodEntry` - Registro de ánimo
- `PhilosophyQuote` - Citas filosóficas (40+ citas)
- `StreakRecord` - Rachas de días consecutivos
- `StoicImage` - Imágenes de la galería (🆕)
- `StoicMusicTrack` - Pistas de música (🆕)

## Compilar

1. Abrir en Android Studio
2. Gradle Sync
3. Run en emulador o dispositivo

## Requisitos

- Android 8.0 (API 26) o superior
- Android Studio Hedgehog o superior

## Permisos

- `POST_NOTIFICATIONS` - Notificaciones de rituales
- `SCHEDULE_EXACT_ALARM` - Programación de recordatorios
- `INTERNET` - Carga de imágenes y música
- `FOREGROUND_SERVICE_MEDIA_PLAYBACK` - Reproducción de música en segundo plano

## Próximas Características

- [ ] Reproducción real de audio con archivos MP3
- [ ] Imágenes reales con URLs de la galería
- [ ] Modo oscuro/claro dinámico
- [ ] Widgets de pantalla de inicio
- [ ] Exportar datos de progreso
- [ ] Comunidad estoica
- [ ] Más filósofos (Zenón, Crisipo, etc.)

## Licencia

MIT License

## Contribución

¡Las contribuciones son bienvenidas! Si eres estoico o desarrollador Android, me encantaría tu ayuda para hacer esta app aún mejor.
