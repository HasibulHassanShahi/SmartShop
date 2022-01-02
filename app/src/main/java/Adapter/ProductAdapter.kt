package Adapter

import Model.ProductModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.smartshop.R
import com.squareup.picasso.Picasso

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    var productList: ArrayList<ProductModel>? = null
    lateinit var context: Context
    lateinit var layoutInflater: LayoutInflater

    constructor(context: Context, products: ArrayList<ProductModel>?) {
        this.layoutInflater = LayoutInflater.from(context)
        if (products != null) {
            this.productList = products
        }
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        var view: View = layoutInflater.inflate(R.layout.layout_product_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.nameTxt.text = productList!![position].name
        holder.priceTxt.text = productList!![position].price.toString()
        var imgUrl: String = productList!![position].imageUrl.toString()
        holder.setImage(imgUrl)
    }

    override fun getItemCount(): Int {
        return productList!!.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var nameTxt: TextView
        lateinit var priceTxt: TextView
        lateinit var qtnTxt: TextView
        lateinit var minusBtn: Button
        lateinit var plsBtn: Button
        lateinit var img: ImageView

        init {
            nameTxt = itemView.findViewById(R.id.itemNameTxt)
            priceTxt = itemView.findViewById(R.id.itemPriceTxt)
            img = itemView.findViewById(R.id.image)
            qtnTxt = itemView.findViewById(R.id.itemQtnTxt)
            minusBtn = itemView.findViewById(R.id.minusBtn)
            plsBtn = itemView.findViewById(R.id.plsBtn)
        }

        public fun setImage(imageUrl: String){
            Picasso.get().load(imageUrl).resize(100,100).into(img)
        }
    }

}