package id.rushdroid.githubusersearch.data.remote.response

import com.squareup.moshi.Json

data class RepoResponse(
    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "stargazers_count")
    val stargazersCount: Int? = null,

    @Json(name = "topics")
    val topics: List<String?>? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "language")
    val language: String? = null,

    @Json(name = "updated_at")
    val updatedAt: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "forks_count")
    val forksCount: Int? = null
)
