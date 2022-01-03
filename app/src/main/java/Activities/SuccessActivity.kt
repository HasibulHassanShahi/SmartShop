package Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.e.smartshop.R

class SuccessActivity : AppCompatActivity() {

    lateinit var backToHomeBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        backToHomeBtn = findViewById(R.id.backToHome)

        backToHomeBtn.setOnClickListener {
            val intent = Intent(this,ProductActivity::class.java)
            startActivity(intent)
        }
    }
}