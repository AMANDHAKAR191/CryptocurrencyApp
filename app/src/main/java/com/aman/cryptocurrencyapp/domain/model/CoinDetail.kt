package com.aman.cryptocurrencyapp.domain.model

import com.aman.cryptocurrencyapp.data.remote.dto.Links
import com.aman.cryptocurrencyapp.data.remote.dto.LinksExtended
import com.aman.cryptocurrencyapp.data.remote.dto.Tag
import com.aman.cryptocurrencyapp.data.remote.dto.TeamMember
import com.aman.cryptocurrencyapp.data.remote.dto.Whitepaper

data class CoinDetail(
    val coinId:String,
    val name:String,
    val description:String,
    val symbol:String,
    val rank:Int,
    val isActive:Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)