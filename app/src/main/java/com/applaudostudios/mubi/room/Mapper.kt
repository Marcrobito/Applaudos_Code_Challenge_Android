package com.applaudostudios.mubi.room

import com.applaudostudios.core.data.dto.CardDTO
import com.applaudostudios.core.domain.model.Card
import com.applaudostudios.mubi.room.entity.CardEntity

fun CardDTO.toCardEntity() = CardEntity(id, name, urlImg ?: poster ?: "", popularity / 2)
fun CardEntity.toCard() = Card(id, name, urlImg, rating)