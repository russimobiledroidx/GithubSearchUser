package id.rushdroid.githubusersearch.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_users")
data class DetailUserEntity(
    @PrimaryKey val username: String,
    val avatarUrl: String,
    val name: String,
    val bio: String,
    val company: String,
    val location: String,
    val publicRepos: Int,
    val followers: Int,
    val following: Int
)