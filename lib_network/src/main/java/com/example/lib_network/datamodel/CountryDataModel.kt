package com.example.lib_network.datamodel

import com.google.gson.annotations.SerializedName

data class CountryDataModel(
    @SerializedName("name")
    val nameWrapper: NameWrapper,
    @SerializedName("cca2")
    val countryCode: String,
    @SerializedName("capital")
    val capital: List<String>,
    @SerializedName("capitalInfo")
    val capitalInfo: CapitalInfo
) {
    val country: String get() = nameWrapper.common
    val city: String get() = capital.firstOrNull() ?: ""
    val latitude: Double get() = capitalInfo.latlng.getOrNull(0) ?: 0.0
    val longitude: Double get() = capitalInfo.latlng.getOrNull(1) ?: 0.0
}

data class NameWrapper(
    @SerializedName("common")
    val common: String
)

data class CapitalInfo(
    @SerializedName("latlng")
    val latlng: List<Double>
)