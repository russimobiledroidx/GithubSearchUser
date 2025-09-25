package id.rushdroid.githubusersearch.data.remote.response

import com.squareup.moshi.Json

data class DetailUserResponse(

    @Json(name = "followers")
    val followers: Int? = null,

    @Json(name = "avatar_url")
    val avatarUrl: String? = null,

    @Json(name = "following")
    val following: Int? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "bio")
    val bio: String? = null,

    @Json(name = "company")
    val company: String? = null,

    @Json(name = "location")
    val location: String? = null,

    @Json(name = "public_repos")
    val publicRepos: Int? = null,

    @Json(name = "login")
    val username: String? = null
)
