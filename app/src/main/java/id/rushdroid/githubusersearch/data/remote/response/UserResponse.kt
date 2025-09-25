package id.rushdroid.githubusersearch.data.remote.response

import com.squareup.moshi.Json

data class UserResponse(
    @Json(name = "login") val login: String,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "name") val name: String?
)