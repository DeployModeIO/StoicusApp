package com.stoicus.app.core.ui.theme

import androidx.compose.ui.graphics.Color

// ═══════════════════════════════════════════════════════════
// STOICUS PREMIUM PALETTE — "Dark Marble & Champagne Gold"
// Inspired by classical antiquity: bronze statues, marble,
// deep night sky and parchment.
// ═══════════════════════════════════════════════════════════

// 🥂 Champagne Gold (Primary - Luxury accent)
val ChampagneGold       = Color(0xFFD4AF7A)
val ChampagneLight      = Color(0xFFE8CBA0)
val ChampagneDeep       = Color(0xFFB08D57)
val GoldGlow            = Color(0xFFF2D9A8)

// 🌌 Obsidian / Backgrounds (Deep premium dark)
val Obsidian            = Color(0xFF0A0A0B)
val ObsidianElevated    = Color(0xFF121214)
val Charcoal            = Color(0xFF1A1A1D)
val Graphite            = Color(0xFF242428)
val Slate               = Color(0xFF2E2E33)

// 📜 Marble / Surfaces
val MarbleWhite         = Color(0xFFF5F2EC)
val Parchment           = Color(0xFFEAE4D8)
val StoneVeil           = Color(0xFFB8B2A7)

// 🎨 Patina Accent (Secondary - oxidized bronze)
val PatinaGreen         = Color(0xFF4A7C74)
val PatinaLight         = Color(0xFF6FA59C)

// 🍷 Wine Accent (Tertiary - deep roman red)
val RomanWine           = Color(0xFF722F37)
val RomanWineLight      = Color(0xFF9C424B)

// 🌅 Accent Tones per Pillar
val MindColor           = Color(0xFF5B7DB1)   // Sapphire blue
val BodyColor           = Color(0xFFC8893D)   // Amber bronze
val SoulColor           = Color(0xFF8B6BA8)   // Imperial purple

// Virtue Colors
val WisdomColor         = Color(0xFF5B7DB1)
val JusticeColor        = Color(0xFF6FA59C)
val CourageColor        = Color(0xFFC8893D)
val TemperanceColor     = Color(0xFF8B6BA8)

// Text Tones
val TextPrimary         = Color(0xFFF5F2EC)
val TextSecondary       = Color(0xFFB8B2A7)
val TextMuted           = Color(0xFF7A7670)
val TextGold            = Color(0xFFD4AF7A)

// Legacy aliases (keep backwards-compat with existing screens)
val Bronze              = ChampagneGold
val BronzeLight         = ChampagneLight
val BronzeDark          = ChampagneDeep
val DeepBlue            = MindColor
val DeepBlueLight       = Color(0xFF7A9BC9)
val DeepBlueDark        = Color(0xFF3A5578)
val StoicRed            = RomanWine
val StoicRedLight       = RomanWineLight
val Stone               = Slate
val StoneLight          = Graphite
val StoneDark           = Charcoal
val DarkBackground      = Obsidian
val DarkSurface         = ObsidianElevated
val DarkSurfaceVariant  = Charcoal
val TextWhite           = TextPrimary
val TextGrey            = TextSecondary

// Gradients (for backgrounds & hero cards)
val HeroGradientStart   = Color(0xFF1A1A1D)
val HeroGradientEnd     = Color(0xFF0A0A0B)
val CardGradientStart   = Color(0xFF242428)
val CardGradientEnd     = Color(0xFF121214)
val GoldGradientStart   = Color(0xFFE8CBA0)
val GoldGradientEnd     = Color(0xFFB08D57)