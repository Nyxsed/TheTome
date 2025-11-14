package ru.nyxsed.thetome.core.domain.models

import kotlinx.serialization.Serializable

@Serializable
sealed class Token(
    val name: String,
) {
    @Serializable
    data object WasherwomanTownsfolk : Token("Townsfolk")
    @Serializable
    data object WasherwomanWrong : Token("Wrong")

    @Serializable
    data object LibrarianOutsider : Token("Outsider")
    @Serializable
    data object LibrarianWrong : Token("Wrong")
}