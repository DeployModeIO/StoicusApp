package com.stoicus.app.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Registro de auditoría para acciones del administrador.
 *
 * Cada acción sensible (reset contador, inyección de licencia simulada,
 * cierre de sesión) se persiste aquí para trazabilidad.
 */
@Entity(
    tableName = "admin_audit_logs",
    indices = [Index("performed_at")]
)
data class AdminAuditLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    /** Tipo de acción realizada. */
    @ColumnInfo(name = "action_type")
    val actionType: String,

    /** Descripción legible del evento. */
    @ColumnInfo(name = "description")
    val description: String,

    /** Timestamp en millis (epoch). */
    @ColumnInfo(name = "performed_at")
    val performedAt: Long,

    /** Email del admin o identificador (si aplica). */
    @ColumnInfo(name = "admin_email")
    val adminEmail: String? = null,

    /** Datos adicionales en formato JSON (opcional). */
    @ColumnInfo(name = "metadata_json")
    val metadataJson: String? = null
)