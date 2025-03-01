package id.rushdroid.githubusersearch.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "followers")
data class FollowersEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val login: String, // Username pemilik akun
    val follower: String // Username pengikutnya
)