package Activities

import Adapter.ProductAdapter
import InterfaceAPI.JsonPlaceHolderApi
import Model.ProductModel
import Model.ShopModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.smartshop.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProductActivity : AppCompatActivity() {

    private val baseUrl: String = "https://c8d92d0a-6233-4ef7-a229-5a91deb91ea1.mock.pstmn.io/"

    lateinit var shopName: TextView
    lateinit var rating: TextView
    lateinit var openTime: TextView
    lateinit var closeTime: TextView
    lateinit var recyclerView: RecyclerView

    var shopArrayList: ArrayList<ShopModel>? = null
    var productList: ArrayList<ProductModel>? = null

    lateinit var retrofit: Retrofit
    lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        shopName = findViewById(R.id.shopName)
        rating = findViewById(R.id.rating)
        openTime = findViewById(R.id.openTxt)
        closeTime = findViewById(R.id.closeTxt)
        recyclerView = findViewById(R.id.recyclerView)

        retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        shopInfoFromAPI()
        productInfoFromAPI()
    }

    private fun shopInfoFromAPI(){
        shopArrayList = ArrayList()

        val shopInfoCall = jsonPlaceHolderApi.shop()
        shopInfoCall?.enqueue(object : Callback<ShopModel> {
            override fun onResponse(
                call: Call<ShopModel>,
                response: Response<ShopModel>
            ) {
                if(!response.isSuccessful){
                    Toast.makeText(this@ProductActivity,"Shop Response Error",Toast.LENGTH_LONG).show()
                    return
                }

                val shops = response.body()!!
                shopName.setText(shops.name)
                rating.setText(shops.rating.toString()+" out of 5")
                openTime.setText(shops.openTime?.dropLast(8))
                closeTime.setText(shops.closeTime?.dropLast(8))

            }

            override fun onFailure(call: Call<ShopModel>, t: Throwable) {
                Toast.makeText(this@ProductActivity,"Shop Api Call failed",Toast.LENGTH_LONG).show()
                Log.d("API_Shop","onFailur: "+t.message)
            }
        })
    }

    private fun productInfoFromAPI(){

        productList = ArrayList()

        val productCall = jsonPlaceHolderApi.products()
        productCall.enqueue(object : Callback<List<ProductModel>> {
            override fun onResponse(
                call: Call<List<ProductModel>>,
                response: Response<List<ProductModel>>
            ) {
                if(!response.isSuccessful){
                    Toast.makeText(this@ProductActivity,"Product Response Error",Toast.LENGTH_LONG).show()
                    return
                }

                val products = response.body()
                if (products != null) {
                    for (product in products){
                        productList!!.add(ProductModel(product.name,product.price,product.imageUrl))
                    }
                }

                Toast.makeText(this@ProductActivity,productList?.size.toString(),Toast.LENGTH_LONG).show()
                loadRecyclerView(productList)
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                Toast.makeText(this@ProductActivity,"Product Api Call failed",Toast.LENGTH_LONG).show()
                Log.d("API_Product","ProductFailur: "+t.message)
            }
        })
    }

    private fun loadRecyclerView(itemList: ArrayList<ProductModel>?){
        if (itemList?.size!! > 0){
            layoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = layoutManager
            adapter = ProductAdapter(this,itemList)
            recyclerView.adapter = adapter
        }else{

        }
    }
}