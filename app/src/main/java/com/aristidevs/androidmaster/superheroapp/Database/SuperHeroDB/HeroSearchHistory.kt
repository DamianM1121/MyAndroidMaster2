package com.aristidevs.androidmaster.superheroapp.Database.SuperHeroDB

import java.time.LocalDateTime
import java.util.Date

data class HeroSearchHistory(
    val heroName: String,
    val queryTime: Date
)
