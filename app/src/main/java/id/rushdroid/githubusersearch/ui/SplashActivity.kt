package id.rushdroid.githubusersearch.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import id.rushdroid.githubusersearch.R
import id.rushdroid.githubusersearch.ui.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo: ImageView = findViewById(R.id.github_logo)

        // Jalankan animasi bounce
        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        logo.startAnimation(bounceAnimation)

        // Delay for a few seconds before starting the MainActivity
        Handler().postDelayed({
            // Start MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish SplashActivity so it can't be returned to
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}
