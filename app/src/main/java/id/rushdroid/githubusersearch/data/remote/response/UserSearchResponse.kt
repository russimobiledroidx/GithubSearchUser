package id.rushdroid.githubusersearch.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserSearchResponse(
    @SerializedName("items") val users: List<UserResponse>
)
