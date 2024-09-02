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

val solutions = listOf("APPLE", "BERRY", "CHAIR", "DRIVE", "EAGLE", "FIELD", "GIANT", "HOUSE", "IGLOO", "JOKER",
    "KNEEL", "LAYER", "MANGO", "NIGHT", "OCEAN", "PAPER", "QUILT", "RIVER", "SNAKE", "TABLE",
    "UNCLE", "VIVID", "WAGON", "XYLOX", "YACHT", "ZEBRA", "ALONE", "BRAVE", "CHESS", "DAISY",
    "ELDER", "FAIRY", "GHOST", "HORSE", "INDEX", "JUMPY", "KARMA", "LIGHT", "MIGHT", "NEVER",
    "OFFER", "PARTY", "QUEST", "ROYAL", "SHEEP", "TIGER", "URBAN", "VALUE", "WOMAN", "XENON",
    "YOUTH", "ZONED", "ARROW", "BEACH", "CANDY", "DREAM", "EARTH", "FLAME", "GRAIN", "HEART",
    "INPUT", "JOLLY", "KNOCK", "LEMON", "MONEY", "NORTH", "OLIVE", "PLANT", "QUIET", "REACT",
    "SOUND", "TRAIN", "UNDER", "VISIT", "WHISK", "XEBEC", "YEAST", "ZESTY", "ANGEL", "BLOOM",
    "CRISP", "DELTA", "EMBER", "FROST", "GLASS", "HONEY", "INNER", "JELLY", "KNIFE", "LEAFY")


