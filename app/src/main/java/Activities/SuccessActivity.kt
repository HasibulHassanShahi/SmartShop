package Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.e.smartshop.R

class SuccessActivity : AppCompatActivity() {

    lateinit var backToHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        backToHome = findViewById(R.id.backToHome)

        backToHome.setOnClickListener {
            val intent = Intent(this,ProductActivity::class.java)
            startActivity(intent)
        }
    }
}