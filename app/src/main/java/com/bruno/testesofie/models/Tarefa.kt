package com.bruno.testesofie.models

import java.io.Serializable

data class Tarefa(
    val title: String?,
    val email: String?,
    val description: String?,
    val _id: String?,
    val `when`: String?
) : Serializable