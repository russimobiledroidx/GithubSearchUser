package id.rushdroid.githubusersearch.utils

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import id.rushdroid.githubusersearch.worker.SyncWorker
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        scheduleSyncWork()
    }

    private fun scheduleSyncWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val syncWorkRequest = PeriodicWorkRequestBuilder<SyncWorker>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "SyncUserData",
            androidx.work.ExistingPeriodicWorkPolicy.KEEP,
            syncWorkRequest
        )
    }
}

//    companion object {
//        lateinit var appContext: Context
//        lateinit var repository: UserRepository
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        appContext = this
//
//        val database = AppDatabase.getInstance(appContext) // Dapatkan instance database
//        val apiService = GithubApiClient.apiService // Ambil instance API service (Retrofit)
//
//        repository = UserRepository(database, apiService) // Gunakan AppDatabase & GitHubApiService
//    }


//}