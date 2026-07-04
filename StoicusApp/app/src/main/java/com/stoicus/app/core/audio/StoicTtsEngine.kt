package com.stoicus.app.core.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import java.util.Locale
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Motor de Text-to-Speech para narrar citas filosóficas.
 *
 * Asigna distintas voces/pitch/speed según el filósofo para simular
 * que "cada filósofo habla su cita" con personalidad propia:
 *   - Marco Aurelio: voz grave, pausada (emperador reflexivo)
 *   - Epicteto: voz media, firme (maestro estoico)
 *   - Séneca: voz grave, teatral (orador romano)
 *   - Spinoza: voz muy grave, lenta (filósofo contemplativo)
 *   - Otros: voz media por defecto
 *
 * Usa Android TextToSpeech nativo (open source, sin dependencias externas).
 */
class StoicTtsEngine {

    private var tts: TextToSpeech? = null
    private val ready = AtomicBoolean(false)
    private var onDone: (() -> Unit)? = null

    fun init(context: Context) {
        if (tts != null) return
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val tts = this.tts ?: return@TextToSpeech
                val res = tts.setLanguage(Locale("es", "ES"))
                if (res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED) {
                    tts.setLanguage(Locale.US)
                }
                ready.set(true)
                tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {}
                    override fun onDone(utteranceId: String?) { onDone?.invoke() }
                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String?) { onDone?.invoke() }
                })
                Log.d(TAG, "TTS ready")
            } else {
                Log.e(TAG, "TTS init failed: $status")
            }
        }
    }

    /**
     * Narra una cita con la "voz" del filósofo.
     * @param text texto de la cita
     * @param author nombre del filósofo (define pitch/speed)
     * @param onDone callback al terminar
     */
    fun speakQuote(text: String, author: String, onDone: (() -> Unit)? = null) {
        val tts = this.tts
        if (!ready.get() || tts == null) {
            Log.w(TAG, "TTS not ready, initializing...")
            onDone?.invoke(); return
        }
        if (tts.isSpeaking) tts.stop()

        val profile = voiceProfileFor(author)
        tts.setSpeechRate(profile.rate)
        tts.setPitch(profile.pitch)

        // Prefacio: "Dice [Autor]: ..."
        val fullText = "${author}. $text"
        this.onDone = onDone
        tts.speak(fullText, TextToSpeech.QUEUE_FLUSH, null, "stoic_quote_${System.currentTimeMillis()}")
    }

    fun stop() {
        tts?.stop()
        onDone?.invoke()
        onDone = null
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
        ready.set(false)
    }

    private data class VoiceProfile(val pitch: Float, val rate: Float)

    private fun voiceProfileFor(author: String): VoiceProfile {
        val key = author.lowercase()
        return when {
            "aurelio" in key || "marcus" in key -> VoiceProfile(0.75f, 0.85f)   // emperador grave, pausado
            "epicteto" in key || "epictetus" in key -> VoiceProfile(0.90f, 0.95f) // maestro firme
            "séneca" in key || "seneca" in key -> VoiceProfile(0.72f, 0.90f)     // orador grave
            "spinoza" in key -> VoiceProfile(0.65f, 0.80f)                       // contemplativo
            "zenón" in key || "zeno" in key -> VoiceProfile(0.82f, 0.90f)         // fundador
            "cleantes" in key -> VoiceProfile(0.80f, 0.88f)                       // poeta
            "crisipo" in key || "chrysippus" in key -> VoiceProfile(0.85f, 1.0f)  // lógico
            "cicero" in key || "cicerón" in key -> VoiceProfile(0.78f, 0.95f)     // orador
            "pitágoras" in key || "pythagoras" in key -> VoiceProfile(0.70f, 0.85f)
            "heráclito" in key || "heraclitus" in key -> VoiceProfile(0.68f, 0.82f) // oscuro
            else -> VoiceProfile(0.85f, 0.92f)
        }
    }

    companion object { private const val TAG = "StoicTtsEngine" }
}