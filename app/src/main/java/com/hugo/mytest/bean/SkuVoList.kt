/**
 * Copyright 2019 bejson.com
 */
package com.hugo.mytest.bean

import com.hugo.mytest.viewModel.DetailsViewModel
import java.io.Serializable

/**
 * Auto-generated: 2019-01-04 23:36:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
open class SkuVoList : Serializable {

    var id: Int = 0
    var productCode: String? = null
    var productId: Int = 0
    var price: Int = 0
    var quantity: Int = 0
    var barcode: String? = null
    var goodsNub: String? = null
    var colour: String? = null
    var theSize: String? = null
    var isSelector : DetailsViewModel.TextColor = DetailsViewModel.TextColor.NO_SELECTED

    var isTitle :Boolean =false

}