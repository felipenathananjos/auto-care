package com.github.felipenathananjos.autocare.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var brand: String = "",
    var model: String = "",
    var year: Int = 0
)