package Adapter

import InterfaceAPI.ItemClickListener
import Model.ProductModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.e.smartshop.R
import com.squareup.picasso.Picasso

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    var productList: ArrayList<ProductModel>? = null
    lateinit var context: Context
    lateinit var layoutInflater: LayoutInflater

    lateinit var onItemClickListener: OnItemClickListener

    var qtn = 0

    constructor(context: Context, products: ArrayList<ProductModel>?, onItemClickListener: OnItemClickListener) {
        this.layoutInflater = LayoutInflater.from(context)
        if (products != null) {
            this.productList = products
        }
        this.context = context
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        var view: View = layoutInflater.inflate(R.layout.layout_product_item, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
//        holder.nameTxt.text = productList!![position].name
//        holder.priceTxt.text = productList!![position].price.toString()
//        var imgUrl: String = productList!![position].imageUrl.toString()
//
//        holder.setImage(imgUrl)

        holder.bidnData(productList!![position])

        holder.plsBtn.setOnClickListener (View.OnClickListener(){
            var qtn = productList!![position].qtn + 1
            holder.qtnTxt.text = qtn.toString()
        })
    }

    override fun getItemCount(): Int {
        return productList!!.size
    }

    inner class ViewHolder(itemView: View, onItemClickListener: OnItemClickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        lateinit var nameTxt: TextView
        lateinit var priceTxt: TextView
        lateinit var qtnTxt: TextView
        lateinit var minusBtn: Button
        lateinit var plsBtn: Button
        lateinit var img: ImageView
        lateinit var itemCheck: CheckBox

        lateinit var onItemClickListener: OnItemClickListener

        init {
            nameTxt = itemView.findViewById(R.id.itemNameTxt)
            priceTxt = itemView.findViewById(R.id.itemPriceTxt)
            img = itemView.findViewById(R.id.image)
            qtnTxt = itemView.findViewById(R.id.itemQtnTxt)
            minusBtn = itemView.findViewById(R.id.minusBtn)
            plsBtn = itemView.findViewById(R.id.plsBtn)
            itemCheck = itemView.findViewById(R.id.itemCheck)

            this.onItemClickListener = onItemClickListener

            itemView.setOnClickListener(this)
        }

        fun setImage(imageUrl: String){
            Picasso.get().load(imageUrl).resize(100,100).into(img)
        }

        fun bidnData(productModel: ProductModel){
            nameTxt.text = productModel.name
            priceTxt.text = productModel.price.toString()
            var imgUrl: String = productModel.imageUrl.toString()
            setImage(imgUrl)
            qtnTxt.text = productModel.qtn.toString()
        }

        override fun onClick(p0: View?) {
            onItemClickListener.OnItemClick(adapterPosition)
        }
    }

    interface OnItemClickListener{
        fun OnItemClick(position: Int)
    }

}