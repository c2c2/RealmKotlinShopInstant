package com.oojube.realmkotlinshopinstant.main.model

import com.oojube.realmkotlinshopinstant.main.model.Product
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Saeed on 9/6/17.
 */
open class Order : RealmObject() {
    @PrimaryKey
    var id : String = UUID.randomUUID().toString()
    var date : Date = Date()
    var products : RealmList<Product>? = null
}