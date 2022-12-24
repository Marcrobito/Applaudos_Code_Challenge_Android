package com.applaudostudios.core.data.mapper

import com.applaudostudios.core.data.dto.CardDTO
import com.applaudostudios.core.domain.model.Card

fun CardDTO.mapToCard() = Card(id, name, urlImg, popularity)
fun List<CardDTO>.mapToCard() = this.map { it.mapToCard() }