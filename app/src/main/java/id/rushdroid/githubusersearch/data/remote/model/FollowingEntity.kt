package id.rushdroid.githubusersearch.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "following")
data class FollowingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val login: String, // Username pemilik akun
    val following: String // Username yang diikuti
)