package id.rushdroid.githubusersearch.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("name") val name: String?
)