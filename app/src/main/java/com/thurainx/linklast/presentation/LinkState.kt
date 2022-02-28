package com.thurainx.linklast.presentation

import com.thurainx.linklast.persistance.LinkEntity

data class LinkState(
    var linkList : List<LinkEntity> = emptyList()
)