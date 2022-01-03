package Activities

import Adapter.ProductAdapter
import ApiService.ApiServices
import InterfaceAPI.ItemClickListener
import InterfaceAPI.JsonPlaceHolderApi
import Model.ProductModel
import Model.ShopModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import java.io.Serializable

class ProductActivity : AppCompatActivity(), ProductAdapter.OnItemClickListener {

    lateinit var shopName: TextView
    lateinit var rating: TextView
    lateinit var openTime: TextView
    lateinit var closeTime: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var qtnTxt: TextView
    lateinit var cartBtn: Button
    lateinit var prograssBar: ProgressBar

    var shopArrayList: ArrayList<ShopModel>? = null
    var productList: ArrayList<ProductModel>? = null
    var cartList: ArrayList<ProductModel>? = null
    var itemCount = 0

    lateinit var apiServices: ApiServices

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
        qtnTxt = findViewById(R.id.qtnTxt)
        cartBtn = findViewById(R.id.cartBtn)
        prograssBar = findViewById(R.id.progreesBarApi)

        apiServices = ApiServices()

        shopInfoFromAPI()
        productInfoFromAPI()

        cartBtn.setOnClickListener {
            if (cartList!!.size > 0){
                var intent = Intent(this@ProductActivity, CartActivity::class.java)
                intent.putExtra("itemList", cartList)
                startActivity(intent)
            }
        }
    }

    private fun shopInfoFromAPI(){
        shopArrayList = ArrayList()

        prograssBar.visibility = View.VISIBLE

        val jsonPlaceHolderApi: JsonPlaceHolderApi = apiServices.getRetrofit()

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
                shopName.text = shops.name
                rating.text = shops.rating.toString()+" out of 5"
                openTime.text = "Open: "+shops.openTime?.dropLast(8)
                closeTime.text = "Close: "+shops.closeTime?.dropLast(8)
            }

            override fun onFailure(call: Call<ShopModel>, t: Throwable) {
                Toast.makeText(this@ProductActivity,"Shop Api Call failed",Toast.LENGTH_LONG).show()
                Log.d("API_Shop","onFailur: "+t.message)
            }
        })
    }

    private fun productInfoFromAPI(){

        productList = ArrayList()
        cartList = ArrayList()

        val jsonPlaceHolderApi: JsonPlaceHolderApi = apiServices.getRetrofit()

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
                        productList!!.add(ProductModel(product.name,product.price,product.imageUrl, itemCount))
                    }
                }

                prograssBar.visibility = View.GONE
                //Toast.makeText(this@ProductActivity,productList?.size.toString(),Toast.LENGTH_LONG).show()
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
            adapter = ProductAdapter(this,itemList,this)
            recyclerView.adapter = adapter
        }else{

        }
    }

    override fun OnItemClick(position: Int) {
        itemCount++
        qtnTxt.text = itemCount.toString()

        productList!![position].qtn = productList!![position].qtn + 1

        if (productList!![position].qtn > 1){
            cartList!![position].qtn = productList!![position].qtn
        }else{
            cartList!!.add(
                ProductModel(productList!![position].name,
                    productList!![position].price,
                    productList!![position].imageUrl,
                    productList!![position].qtn))
        }
    }

}