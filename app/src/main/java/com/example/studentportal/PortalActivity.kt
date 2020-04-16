package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_portal.*

const val ADD_PORTAL_REQUEST_CODE = 100

class PortalActivity : AppCompatActivity() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { startAddActivity() }

        initViews()
    }

    /**
     *Initialiseren start layout
     */

    private fun initViews() {
        rvPortals.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvPortals.adapter = portalAdapter
    }

    /**
     * methode om activity te starten wanneer er result verwacht wordt
     *
     */
    private fun startAddActivity() {
        val intent = Intent(this, AddPortalActivity::class.java)
        startActivityForResult(intent, ADD_PORTAL_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORTAL_REQUEST_CODE -> {
                    var portal = data!!.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    portals.add(portal)
                    portalAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    /**
     * methode om items toe te voegen aan de action bar als ze er zijn
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
