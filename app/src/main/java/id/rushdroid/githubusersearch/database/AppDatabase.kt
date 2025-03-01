package id.rushdroid.githubusersearch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.rushdroid.githubusersearch.data.remote.model.DetailUserEntity
import id.rushdroid.githubusersearch.data.remote.model.FollowersEntity
import id.rushdroid.githubusersearch.data.remote.model.FollowingEntity
import id.rushdroid.githubusersearch.data.remote.model.RepoEntity
import id.rushdroid.githubusersearch.data.remote.model.UserEntity
import id.rushdroid.githubusersearch.utils.Converters


@Database(
    entities = [UserEntity::class, DetailUserEntity::class, FollowersEntity::class, FollowingEntity::class, RepoEntity::class], // Pastikan UserEntity ada
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun repoDao(): RepoDao // Tambahkan jika ada Dao untuk RepoEntity

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "github_users.db"
                ).fallbackToDestructiveMigration() // Pastikan ini ada agar tidak crash jika ada perubahan schema
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
