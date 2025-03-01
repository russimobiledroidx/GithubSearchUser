package id.rushdroid.githubusersearch.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.rushdroid.githubusersearch.data.remote.model.DetailUserEntity
import id.rushdroid.githubusersearch.data.remote.model.FollowersEntity
import id.rushdroid.githubusersearch.data.remote.model.FollowingEntity
import id.rushdroid.githubusersearch.data.remote.model.RepoEntity
import id.rushdroid.githubusersearch.data.remote.model.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM detail_users WHERE username = :username LIMIT 1")
    suspend fun getUserDetail(username: String): DetailUserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailUser(user: DetailUserEntity)

    // Ambil daftar followers berdasarkan login pemilik akun
    @Query("SELECT * FROM users WHERE login IN (SELECT follower FROM followers WHERE login = :login)")
    suspend fun getFollowers(login: String): List<UserEntity>

    // Ambil daftar following berdasarkan login pemilik akun
    @Query("SELECT * FROM users WHERE login IN (SELECT following FROM following WHERE login = :login)")
    suspend fun getFollowing(login: String): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFollowers(followers: List<FollowersEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFollowing(following: List<FollowingEntity>)

}

