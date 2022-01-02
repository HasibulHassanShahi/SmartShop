package Activities

import InterfaceAPI.JsonPlaceHolderApi
import Model.ShopModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.e.smartshop.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductActivity : AppCompatActivity() {

    lateinit var shopName: TextView
    lateinit var rating: TextView
    lateinit var openTime: TextView
    lateinit var closeTime: TextView

    var shopArrayList: ArrayList<ShopModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        shopName = findViewById(R.id.shopName) as TextView
        rating = findViewById(R.id.rating)
        openTime = findViewById(R.id.openTxt)
        closeTime = findViewById(R.id.closeTxt)

        shopArrayList = ArrayList()

        val retrofit = Retrofit.Builder().baseUrl("https://c8d92d0a-6233-4ef7-a229-5a91deb91ea1.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val shopInfoCall = jsonPlaceHolderApi.shop()
        shopInfoCall?.enqueue(object : Callback<ShopModel> {
            override fun onResponse(
                call: Call<ShopModel>,
                response: Response<ShopModel>
            ) {
                if(!response.isSuccessful){
                    Toast.makeText(this@ProductActivity,"Response Error",Toast.LENGTH_LONG).show()
                    return
                }

                val shops = response.body()!!
                shopName.setText(shops.name)
                rating.setText(shops.rating.toString()+" out of 5")
                openTime.setText(shops.openTime)
                closeTime.setText(shops.closeTime)

            }

            override fun onFailure(call: Call<ShopModel>, t: Throwable) {
                Toast.makeText(this@ProductActivity,"Api Call failed",Toast.LENGTH_LONG).show()
                Log.d("API","onFailur: "+t.message)
            }

        })
    }
}