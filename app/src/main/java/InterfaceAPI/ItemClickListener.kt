package InterfaceAPI

import Model.ProductModel

interface ItemClickListener {
    fun minus(textData:ProductModel, position:Int)
    fun add(textData:ProductModel, position:Int)
}