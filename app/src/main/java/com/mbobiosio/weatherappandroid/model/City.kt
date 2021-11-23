package com.mbobiosio.weatherappandroid.model

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
/*
* A data class holds the data or state for us.
* It does not perform any operation.
*
* Data Class contains internal code which we have to override in Java-like Kotlin generates the equals(), hashCode(), and toString()
* */
data class City(
    val id: Long?,
    val name: String?,
    val icon: String?,
    val country: String?
)
