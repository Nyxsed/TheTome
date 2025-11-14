package ru.nyxsed.thetome.core.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val id : Int,
    val name : String?,
    val role : Role?,
    val tokens: List<Token> = emptyList(),
    val isAlive : Boolean = true,
    val isDrunk : Boolean = false,
)
