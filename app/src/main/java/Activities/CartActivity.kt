package Activities

import Model.ProductModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.e.smartshop.R
import android.content.Intent




class CartActivity : AppCompatActivity() {

    lateinit var addressTxt: TextView
    lateinit var itemTxt: TextView
    lateinit var qtnTxt: TextView
    lateinit var amountTxt: TextView
    lateinit var totalTxt: TextView
    lateinit var confirmBtn: Button

    lateinit var itemList: ArrayList<ProductModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        addressTxt = findViewById(R.id.addressEdt)
        itemTxt = findViewById(R.id.itemTxt)
        qtnTxt = findViewById(R.id.qtnNpriceTxt)
        amountTxt = findViewById(R.id.amountTxt)
        totalTxt = findViewById(R.id.totalTxt)
        confirmBtn = findViewById(R.id.paymentBtn)

        val intent = intent
        //val args = intent.getBundleExtra("BUNDLE")
        //val cartList: ArrayList<ProductModel>? = intent.getParcelableArrayExtra("itemList")!!
        itemList = getIntent().getSerializableExtra("itemList") as ArrayList<ProductModel>

        var items: String? = null
        var qtn: String? = null
        var amount: String? = null

        if (itemList != null && itemList!!.size > 0){
            for (item in itemList){
                if (item!= null){
                    items = items + item.name+"\n"
                    qtn = qtn + item.qtn.toString()+" X \n"
                    amount = amount + (item.price * item.qtn).toString() + "\n"
                }
            }

            itemTxt.text = items?.drop(4)
            qtnTxt.text = qtn?.drop(4)
            amountTxt.text = amount?.drop(4)
        }
    }
}