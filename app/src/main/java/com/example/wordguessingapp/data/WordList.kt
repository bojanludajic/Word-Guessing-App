package com.example.wordguessingapp.data

import android.content.Context
import com.example.wordguessingapp.R

const val WORD_LENGTH = 5

fun parse(context: Context): List<String> {
    val inputStream = context.resources.openRawResource(R.raw.words)
    return inputStream.bufferedReader().useLines { lines ->
        lines.map { it.toUpperCase() }.toList()
    }
}

val solutions = listOf(
    "APPLE", "MANGO", "GRAPE", "BERRY", "PEACH", "LEMON", "MELON", "KIWI", "OLIVE", "PLUM",
    "GUAVA", "LIME", "FIG", "PEAR", "DATES", "CHILI", "SQUAD", "BRAVE", "HAPPY", "CRISP",
    "TANGO", "PEARL", "SLATE", "WATER", "SOLVE", "CANDY", "BREAD", "CRATE", "SHINY", "PEACH",
    "SHINE", "FLASH", "PRISM", "SWEET", "GRIND", "SUGAR", "PLUMB", "COVER", "SPOON", "SWEEP",
    "CLOUD", "FRUIT", "MINTY", "DRIFT", "MOOSE", "STORM", "BLOWS", "BRUSH", "SPICE", "FLAKE",
    "GRASS", "LEAFY", "GLINT", "PRONG", "STONE", "CLOVE", "CHIVE", "GARLIC", "THYME", "ONION",
    "PLANT", "STALK", "MOUSE", "CRISP", "BRISK", "TIDAL", "SPARE", "WINDY", "FLUSH", "GLEAM",
    "SPLIT", "SPLAT", "CRISP", "CRANK", "CLASP", "SNACK", "TREAT", "BLINK", "GHOST", "PRIDE",
    "GRAVE", "BRUSH", "SHORE", "SWIFT", "FLOCK", "PLUCK", "GLARE", "SPARK", "FROST", "TULIP",
    "GLOVE", "STEEP", "QUICK", "THORN", "CRACK", "PLUMB", "SPICE", "CLOVE", "FRANK", "SHOCK"
)


