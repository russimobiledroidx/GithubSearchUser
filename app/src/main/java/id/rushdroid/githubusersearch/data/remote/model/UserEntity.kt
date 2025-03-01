package id.rushdroid.githubusersearch.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val login: String,
    val avatarUrl: String,
    val name: String
)
