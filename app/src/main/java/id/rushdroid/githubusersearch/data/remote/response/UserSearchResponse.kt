package id.rushdroid.githubusersearch.data.remote.response

import com.squareup.moshi.Json

data class UserSearchResponse(
    @Json(name = "items") val users: List<UserResponse>
)
