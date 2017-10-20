package com.oojube.realmkotlinshopinstant.main.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Saeed on 9/6/17.
 */
open class Product : RealmObject() {
    @PrimaryKey
    var id : String = UUID.randomUUID().toString()
    var title : String? = null
    var price : Double? = null
}