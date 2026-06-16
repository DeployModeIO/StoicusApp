package com.stoicus.app.core.domain.usecase

import com.stoicus.app.core.domain.model.Pillar
import com.stoicus.app.core.domain.model.RitualExercise
import javax.inject.Inject

class GetRitualExercisesUseCase @Inject constructor() {
    
    private val morningExercises = listOf(
        RitualExercise(
            id = "previsualisatio",
            title = "Previsualisatio",
            description = "Anticipa los obstáculos del día y prepárate mentalmente",
            durationMinutes = 5,
            pillar = Pillar.MIND,
            philosopher = "Séneca",
            steps = listOf(
                "Cierra los ojos y respira profundamente 3 veces",
                "Visualiza los desafíos que enfrentarás hoy",
                "Para cada obstáculo, imagina tu respuesta ideal",
                "Repite: 'Estoy preparado para lo que venga'"
            ),
            reflectionPrompts = listOf(
                "¿Qué situaciones me generan ansiedad hoy?",
                "¿Cómo puedo responder con virtud?"
            )
        ),
        RitualExercise(
            id = "dichotomy_control",
            title = "Dicotomía del Control",
            description = "Identifica qué está bajo tu control y qué no",
            durationMinutes = 3,
            pillar = Pillar.MIND,
            philosopher = "Epicteto",
            steps = listOf(
                "Escribe 5 cosas que te preocupan hoy",
                "Clasifica cada una: ¿Está bajo mi control?",
                "Para las que NO controlas: acepta y suelta",
                "Para las que SÍ controlas: define una acción concreta"
            ),
            reflectionPrompts = listOf(
                "¿Estoy gastando energía en cosas que no puedo cambiar?",
                "¿Qué acción concreta puedo tomar?"
            )
        ),
        RitualExercise(
            id = "cold_exposure",
            title = "Exposición al Frío",
            description = "Fortalece tu cuerpo y mente con agua fría",
            durationMinutes = 5,
            pillar = Pillar.BODY,
            philosopher = "Estoicos modernos",
            steps = listOf(
                "Comienza con 30 segundos de agua fría",
                "Respira profundamente durante la exposición",
                "Observa la sensación sin resistirte",
                "Aumenta gradualmente cada semana"
            ),
            reflectionPrompts = listOf(
                "¿Cómo respondí al discomfort?",
                "¿Qué aprendí sobre mi tolerancia?"
            )
        ),
        RitualExercise(
            id = "negative_visualization",
            title = "Memento Mori",
            description = "Visualiza la pérdida para apreciar lo presente",
            durationMinutes = 5,
            pillar = Pillar.SOUL,
            philosopher = "Marco Aurelio",
            steps = listOf(
                "Piensa en algo que valoras profundamente",
                "Visualiza perderlo temporalmente",
                "Observa cómo cambia tu perspectiva",
                "Agradece por tenerlo hoy"
            ),
            reflectionPrompts = listOf(
                "¿Qué daría por tener este día de nuevo?",
                "¿Estoy viviendo como si fuera mi último día?"
            )
        )
    )

    operator fun invoke(pillar: Pillar? = null): List<RitualExercise> {
        return if (pillar != null) {
            morningExercises.filter { it.pillar == pillar }
        } else {
            morningExercises
        }
    }

    fun getExerciseById(id: String): RitualExercise? {
        return morningExercises.find { it.id == id }
    }
}
