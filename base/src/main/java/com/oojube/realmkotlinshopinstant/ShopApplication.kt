package com.oojube.realmkotlinshopinstant

import android.app.Application
import io.realm.Realm

/**
 * Created by Saeed on 9/6/17.
 */
class ShopApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}