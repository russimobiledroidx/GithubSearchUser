package id.rushdroid.githubusersearch.data.repository

import id.rushdroid.githubusersearch.api.GithubApiService
import id.rushdroid.githubusersearch.data.remote.model.DetailUserEntity
import id.rushdroid.githubusersearch.data.remote.model.RepoEntity
import id.rushdroid.githubusersearch.data.remote.model.UserEntity
import id.rushdroid.githubusersearch.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val db: AppDatabase, private val api: GithubApiService) {

    suspend fun getUsers(): List<UserEntity> {
        return withContext(Dispatchers.IO) {
            val cachedUsers = db.userDao().getAllUsers()
            if (cachedUsers.isNotEmpty()) return@withContext cachedUsers

            val response = api.getUsers()
            if (response.isSuccessful) {
                response.body()?.let { users ->
                    val userEntities = users.map {
                        UserEntity(
                            login = it.login ?: "",
                            avatarUrl = it.avatarUrl ?: "",
                            name = it.name ?: "" // Handle nullable 'name'
                        )
                    }
                    db.userDao().insertUsers(userEntities)
                    return@withContext userEntities
                }
            }
            emptyList()
        }
    }

    suspend fun getUserDetail(username: String): DetailUserEntity? {
        return withContext(Dispatchers.IO) {
            val cachedUser = db.userDao().getUserDetail(username)
            if (cachedUser != null) return@withContext cachedUser

            val response = api.getUserDetail(username)
            if (response.isSuccessful) {
                response.body()?.let { userResponse ->
                    val userEntity = DetailUserEntity(
                        username = userResponse.username ?: "",
                        avatarUrl = userResponse.avatarUrl ?: "",
                        name = userResponse.name ?: "",
                        bio = userResponse.bio ?: "",
                        company = userResponse.company ?: "",
                        location = userResponse.location ?: "",
                        publicRepos = userResponse.publicRepos ?: 0,
                        followers = userResponse.followers ?: 0,
                        following = userResponse.following ?: 0
                    )
                    db.userDao().insertDetailUser(userEntity)
                    return@withContext userEntity
                }
            }
            null
        }
    }

    suspend fun getFollowers(username: String): List<UserEntity> {
        return withContext(Dispatchers.IO) {
            val cachedFollowers = db.userDao().getFollowers(username)
            if (cachedFollowers.isNotEmpty()) return@withContext cachedFollowers

            val response = api.getFollowers(username)
            if (response.isSuccessful) {
                response.body()?.let { users ->
                    val followersEntities = users.map {
                        UserEntity(
                            login = it.login ?: "",
                            avatarUrl = it.avatarUrl ?: "",
                            name = it.name ?: "" // Handle nullable 'name'
                        )
                    }
                    db.userDao().insertUsers(followersEntities)
                    return@withContext followersEntities
                }
            }
            emptyList()
        }
    }

    suspend fun getFollowing(username: String): List<UserEntity> {
        return withContext(Dispatchers.IO) {
            val cachedFollowing = db.userDao().getFollowing(username)
            if (cachedFollowing.isNotEmpty()) return@withContext cachedFollowing

            val response = api.getFollowing(username)
            if (response.isSuccessful) {
                response.body()?.let { users ->
                    val followingEntities = users.map {
                        UserEntity(
                            login = it.login ?: "",
                            avatarUrl = it.avatarUrl ?: "",
                            name = it.name ?: "" // Handle nullable 'name'
                        )
                    }
                    db.userDao().insertUsers(followingEntities)
                    return@withContext followingEntities
                }
            }
            emptyList()
        }
    }

    suspend fun getRepos(username: String): List<RepoEntity> {
        return withContext(Dispatchers.IO) {
            val cachedRepos = db.repoDao().getReposByUsername(username) // Fix: Use repoDao()
            if (cachedRepos.isNotEmpty()) return@withContext cachedRepos

            val response = api.getRepos(username)
            if (response.isSuccessful) {
                response.body()?.let { repos ->
                    val repoEntities = repos.map { repoResponse ->
                        RepoEntity(
                            id = repoResponse.id ?: 0,
                            login = username,
                            name = repoResponse.name ?: "Unknown",
                            description = repoResponse.description ?: "",
                            language = repoResponse.language ?: "Unknown",
                            stars = repoResponse.stargazersCount ?: 0,
                            forks = repoResponse.forksCount ?: 0,
                            topics = repoResponse.topics ?: emptyList(),
                            updatedAt = repoResponse.updatedAt ?: ""
                        )
                    }
                    db.repoDao().insertRepos(repoEntities) // Fix: Use repoDao()
                    return@withContext repoEntities
                }
            }
            emptyList()
        }
    }


    suspend fun searchUsers(query: String): List<UserEntity> {
        return withContext(Dispatchers.IO) {
            val response = api.searchUsers(query)
            if (response.isSuccessful) {
                response.body()?.users?.map {
                    UserEntity(
                        login = it.login ?: "",
                        avatarUrl = it.avatarUrl ?: "",
                        name = it.name ?: ""
                    )
                } ?: emptyList()
            } else {
                emptyList()
            }
        }
    }



}


