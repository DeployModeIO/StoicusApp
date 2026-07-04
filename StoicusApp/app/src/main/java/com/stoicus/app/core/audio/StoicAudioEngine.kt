package com.stoicus.app.core.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.util.Log
import kotlinx.coroutines.*
import kotlin.math.PI
import kotlin.math.sin
import kotlin.math.cos
import kotlin.random.Random

/**
 * Motor de audio ambiental procedural para Stoicus.
 *
 * Combina dos capas sintetizadas en tiempo real (sin assets externos):
 *   1. DRONE tonal (pad) según categoría.
 *   2. SONIDO DE NATURALEZA opcional (rain, wind, ocean, fire, forest, birds, stream).
 *
 * Todo se genera vía AudioTrack + PCM 16-bit mono 44.1kHz con:
 *   - Fade-in 2s
 *   - LFO sub-audio 0.1 Hz (vibrato natural)
 *   - Filtros one-pole para colorear ruido (rojo/rosado/marrón)
 *
 * Fuentes de sonido open source emuladas:
 *   - Lluvia (rain):   ruido blanco filtrado paso-bajo + envolvente aleatoria.
 *   - Viento (wind):   ruido rosa modulado por LFO lento (0.3 Hz).
 *   - Océano (ocean):  ruido marrón con envolvente de "olas" (periodo ~6s).
 *   - Fuego (fire):    ruido blanco agudo + bursts aleatorios (crepitar).
 *   - Bosque (forest): ruido suave + chirridos esporádicos (grillos).
 *   - Pájaros (birds): tonos senoidales aleatorios con envolvente (trinos).
 *   - Arroyo (stream): ruido blanco filtrado medio con modulación continua.
 */
class StoicAudioEngine {

    private var audioTrack: AudioTrack? = null
    private var generateJob: Job? = null
    @Volatile private var isPlaying = false

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val sampleRate = 44100

    fun play(category: String, mood: String = "calm", volume: Float = 0.7f, natureLayer: String? = null) {
        stop()

        val profile = profileFor(category, mood)
        val bufferSize = try {
            AudioTrack.getMinBufferSize(
                sampleRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT
            ).coerceAtLeast(8192)
        } catch (e: Exception) {
            Log.e(TAG, "minBufferSize fail", e); return
        }

        try {
            audioTrack = AudioTrack(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build(),
                AudioFormat.Builder()
                    .setSampleRate(sampleRate)
                    .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                    .build(),
                bufferSize,
                AudioTrack.MODE_STREAM,
                AudioManager.AUDIO_SESSION_ID_GENERATE
            )
        } catch (e: Exception) { Log.e(TAG, "AudioTrack init fail", e); return }

        audioTrack?.setVolume(volume.coerceIn(0f, 1f))
        try { audioTrack?.play() } catch (e: Exception) { Log.e(TAG, "play() fail", e); return }

        isPlaying = true
        val layer = natureLayer

        generateJob = scope.launch {
            val buffer = ShortArray(bufferSize)
            var phase = 0.0
            var lfoPhase = 0.0
            val lfoFreq = 0.1
            val lfoInc = 2.0 * PI * lfoFreq / sampleRate
            val baseInc = 2.0 * PI / sampleRate

            // Estado para sonido de naturaleza
            val nature = NatureSynth(sampleRate, layer)
            val rng = Random(System.currentTimeMillis())

            var fadeIn = 0f
            val fadeInc = 1f / (sampleRate * 2)

            while (isActive && isPlaying) {
                val lfo = sin(lfoPhase) * 0.02
                for (i in buffer.indices) {
                    // Capa 1: drone tonal
                    var sample = 0.0
                    for ((freq, amp) in profile.harmonics) {
                        val f = freq * (1.0 + lfo)
                        sample += sin(phase * f) * amp
                    }
                    // Capa 2: naturaleza
                    sample += nature.nextSample(rng)

                    sample *= fadeIn * profile.gain
                    val v = (sample * Short.MAX_VALUE).toInt()
                        .coerceIn(-Short.MAX_VALUE.toInt(), Short.MAX_VALUE.toInt())
                    buffer[i] = v.toShort()

                    phase += baseInc
                    lfoPhase += lfoInc
                    if (fadeIn < 1f) fadeIn = (fadeIn + fadeInc).coerceAtMost(1f)
                }
                if (!isPlaying) break
                audioTrack?.write(buffer, 0, buffer.size)
            }
        }
    }

    fun pause() { try { audioTrack?.pause() } catch (_: Exception) {} }
    fun resume() { try { audioTrack?.play() } catch (_: Exception) {} }
    fun setVolume(v: Float) { try { audioTrack?.setVolume(v.coerceIn(0f, 1f)) } catch (_: Exception) {} }

    fun stop() {
        isPlaying = false
        generateJob?.cancel(); generateJob = null
        try { audioTrack?.stop() } catch (_: Exception) {}
        try { audioTrack?.release() } catch (_: Exception) {}
        audioTrack = null
    }

    private data class Profile(val baseFreq: Double, val harmonics: List<Pair<Double, Double>>, val gain: Double)

    private fun profileFor(category: String, mood: String): Profile {
        val bright = if (mood == "energetic") 1.15 else if (mood == "melancholic") 0.92 else 1.0
        return when (category) {
            "meditation" -> Profile(110.0 * bright, listOf(110.0 to 0.40, 165.0 to 0.18, 220.0 to 0.12, 330.0 to 0.06), gain = 0.26)
            "ambient" -> Profile(146.83 * bright, listOf(146.83 to 0.38, 220.0 to 0.16, 293.66 to 0.10, 440.0 to 0.05), gain = 0.24)
            "focus" -> Profile(130.81 * bright, listOf(130.81 to 0.34, 196.0 to 0.20, 261.63 to 0.08, 392.0 to 0.04), gain = 0.28)
            "sleep" -> Profile(98.0 * bright, listOf(98.0 to 0.45, 130.0 to 0.16, 196.0 to 0.08), gain = 0.22)
            "nature" -> Profile(0.0, emptyList(), gain = 0.30) // solo naturaleza
            else -> Profile(110.0, listOf(110.0 to 0.40, 220.0 to 0.16), gain = 0.24)
        }
    }

    companion object { private const val TAG = "StoicAudioEngine" }
}

/**
 * Sintetizador de sonidos de naturaleza. Genera la capa 2 del audio.
 */
private class NatureSynth(val sr: Int, val type: String?) {
    private var lpState = 0.0
    private var lpState2 = 0.0
    private var oceanPhase = 0.0
    private var windPhase = 0.0
    private var fireTimer = 0
    private var fireEnv = 0.0
    private var birdTimer = (sr * 2)
    private var birdEnv = 0.0
    private var birdFreq = 2000.0
    private var birdPhase = 0.0
    private var cricketTimer = 0
    private var cricketEnv = 0.0

    fun nextSample(rng: Random): Double {
        if (type == null) return 0.0
        val white = rng.nextDouble() * 2.0 - 1.0
        return when (type) {
            "rain" -> {
                // Ruido blanco filtrado paso-bajo (lluvia suave)
                lpState = lpState * 0.96 + white * 0.04
                lpState2 = lpState2 * 0.92 + lpState * 0.08
                lpState2 * 1.8
            }
            "stream" -> {
                // Ruido filtrado medio (agua fluyendo)
                lpState = lpState * 0.85 + white * 0.15
                val mod = 0.7 + 0.3 * sin(oceanPhase)
                oceanPhase += 2.0 * PI * 0.5 / sr
                lpState * 1.4 * mod
            }
            "wind" -> {
                // Ruido rosa modulado por LFO lento
                lpState = lpState * 0.99 + white * 0.01
                windPhase += 2.0 * PI * 0.3 / sr
                val mod = 0.5 + 0.5 * sin(windPhase)
                lpState * 3.0 * mod
            }
            "ocean" -> {
                // Ruido marrón con envolvente de olas (6s)
                lpState = lpState * 0.997 + white * 0.003
                oceanPhase += 2.0 * PI * (1.0 / 6.0) / sr
                val wave = (sin(oceanPhase) * 0.6 + 0.4).coerceIn(0.0, 1.0)
                lpState * 4.0 * wave
            }
            "fire" -> {
                // Crepitar: bursts aleatorios + fondo
                fireTimer--
                if (fireTimer <= 0) {
                    fireEnv = rng.nextDouble() * 0.8 + 0.2
                    fireTimer = rng.nextInt(sr / 20, sr / 3) // cada 0.05s–0.33s
                }
                fireEnv *= 0.97
                lpState = lpState * 0.7 + white * 0.3
                lpState * 0.4 + white * fireEnv * 0.5
            }
            "forest" -> {
                // Fondo suave + grillos esporádicos
                lpState = lpState * 0.98 + white * 0.02
                cricketTimer--
                if (cricketTimer <= 0 && rng.nextDouble() < 0.3) {
                    cricketEnv = 0.4
                    cricketTimer = rng.nextInt(sr, sr * 3)
                }
                cricketEnv *= 0.9995
                val cricket = if (cricketEnv > 0.01) sin(oceanPhase * 40) * cricketEnv * 0.3 else 0.0
                oceanPhase += 2.0 * PI * 2000.0 / sr
                lpState * 1.2 + cricket
            }
            "birds" -> {
                // Trinos: tonos senoidales aleatorios
                birdTimer--
                if (birdTimer <= 0) {
                    birdEnv = 0.5
                    birdFreq = rng.nextDouble(1800.0, 3200.0)
                    birdTimer = rng.nextInt(sr / 2, sr * 3)
                }
                birdEnv *= 0.999
                birdPhase += 2.0 * PI * birdFreq / sr
                val trill = if (birdEnv > 0.01) sin(birdPhase) * birdEnv * 0.25 else 0.0
                lpState = lpState * 0.97 + white * 0.03
                lpState * 0.8 + trill
            }
            else -> 0.0
        }
    }
}