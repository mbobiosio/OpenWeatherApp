package com.mbobiosio.weatherappandroid.model.weather

import com.mbobiosio.weatherappandroid.model.APIResponse

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
/*
* A sealed class allows you to represent constrained hierarchies in which an object can only be of one of the given types.
*
* That is, we have a class with a specific number of subclasses. What we get in the end is a concept very similar to an enum.
* The difference is that in the enum we only have one object per type, while in the sealed classes we can have several objects of the same class.
* This difference will allow objects from a sealed class to keep state. This will bring us some advantages that we’ll see in a moment, and also opens the doors to some functional ideas.
* */
sealed class Result<out T> {
    data class Success<out T>(val value: T): Result<T>()
    data class Error(val error: Int? = null, val message: APIResponse? = null): Result<Nothing>()
    data class NetworkError(val error: String? = null): Result<Nothing>()
}