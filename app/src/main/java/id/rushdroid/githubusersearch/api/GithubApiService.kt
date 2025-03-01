package id.rushdroid.githubusersearch.api

import id.rushdroid.githubusersearch.data.remote.response.UserResponse
import id.rushdroid.githubusersearch.data.remote.response.RepoResponse
import id.rushdroid.githubusersearch.data.remote.response.DetailUserResponse
import id.rushdroid.githubusersearch.data.remote.response.UserSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    @GET("users")
    suspend fun getUsers(@Query("since") since: Int = 0): Response<List<UserResponse>>

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): Response<DetailUserResponse>

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): Response<List<UserResponse>>

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): Response<List<UserResponse>>

    @GET("users/{username}/repos")
    suspend fun getRepos(@Path("username") username: String): Response<List<RepoResponse>>

    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): Response<UserSearchResponse>

}