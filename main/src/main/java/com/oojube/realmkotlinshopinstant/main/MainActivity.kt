package com.oojube.realmkotlinshopinstant.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.oojube.realmkotlinshopinstant.main.model.Category
import com.oojube.realmkotlinshopinstant.main.model.Order
import com.oojube.realmkotlinshopinstant.main.model.Product
import io.realm.*

import kotlinx.android.synthetic.main.activity_main.*
import io.realm.annotations.RealmModule
import io.realm.log.RealmLog


class MainActivity : AppCompatActivity(), SyncUser.Callback<SyncUser> {

    val REALM_AUTH_URL = "http://" + BuildConfig.OBJECT_SERVER_IP + ":9080/auth";
    val REALM_URL_SHOP = "realm://" + BuildConfig.OBJECT_SERVER_IP + ":9080/~/test_shop"
    val REALM_URL_ORDER = "realm://" + BuildConfig.OBJECT_SERVER_IP + ":9080/~/test_order"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        RealmLog.setLevel(Log.VERBOSE);
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Do Realm GetInstance()", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            val myCredentials = SyncCredentials.usernamePassword("c3", "123654")
            SyncUser.loginAsync(myCredentials, REALM_AUTH_URL, this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onError(error: ObjectServerError?) {
        Log.d("Connection", "Error to connect!!")
    }

    override fun onSuccess(result: SyncUser?) {
        val configShop = SyncConfiguration.Builder(result, REALM_URL_SHOP)
                .modules(ShopModule())
                .build()
        var realmShop = Realm.getInstance(configShop)

        val configOrder = SyncConfiguration.Builder(result, REALM_URL_ORDER)
                .modules(OrderModule())
                .build()
        var realmOrder = Realm.getInstance(configOrder)
    }

    @RealmModule(classes = arrayOf(Order::class,  Product::class))
    private class OrderModule

    @RealmModule(classes = arrayOf(Category::class, Product::class))
    private class ShopModule
}
