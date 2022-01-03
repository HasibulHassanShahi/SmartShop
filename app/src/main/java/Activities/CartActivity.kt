package Activities

import ApiService.ApiServices
import InterfaceAPI.JsonPlaceHolderApi
import Model.OrderModel
import Model.ProductModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.e.smartshop.R
import android.text.Editable
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CartActivity : AppCompatActivity() {

    lateinit var addressTxt: EditText
    lateinit var itemTxt: TextView
    lateinit var qtnTxt: TextView
    lateinit var amountTxt: TextView
    lateinit var totalTxt: TextView
    lateinit var confirmBtn: Button

    lateinit var itemList: ArrayList<ProductModel>
    lateinit var orderModel: OrderModel
    lateinit var apiServices: ApiServices

    var total: Double = 0.0
    var done: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        addressTxt = findViewById(R.id.addressEdt)
        itemTxt = findViewById(R.id.itemTxt)
        qtnTxt = findViewById(R.id.qtnNpriceTxt)
        amountTxt = findViewById(R.id.amountTxt)
        totalTxt = findViewById(R.id.totalTxt)
        confirmBtn = findViewById(R.id.paymentBtn)

        apiServices = ApiServices()

        var address: String? = addressTxt.text.toString()

        itemList = intent.getSerializableExtra("itemList") as ArrayList<ProductModel>

        var items: String? = null
        var qtn: String? = null
        var amount: String? = null

        if (itemList != null && itemList!!.size > 0){
            for (item in itemList){
                if (item!= null){

                    total += (item.price * item.qtn)

                    items = items + item.name+"\n"
                    qtn = qtn + "   X   "+ item.qtn.toString()+" \n"
                    amount = amount + (item.price * item.qtn).toString() + "\n"
                }
            }

            itemTxt.text = items?.drop(4)
            qtnTxt.text = qtn?.drop(4)
            amountTxt.text = amount?.drop(4)
            totalTxt.text = total.toString()

            orderModel = OrderModel(itemList,address)
        }

        confirmBtn.setOnClickListener {
            cartAPI()
            if (done == 1){
                val intent = Intent(this,SuccessActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun cartAPI(){

        val jsonPlaceHolderApi: JsonPlaceHolderApi = apiServices.getRetrofit()

        val orderCall = jsonPlaceHolderApi.createOrder(orderModel)
        orderCall.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(!response.isSuccessful){
                    Toast.makeText(this@CartActivity,"Cart Error",Toast.LENGTH_LONG).show()
                    return
                }

                done = 1
                Toast.makeText(this@CartActivity,"ADD to cart successful",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@CartActivity,"Cart Fail",Toast.LENGTH_LONG).show()
            }

        })

    }
}