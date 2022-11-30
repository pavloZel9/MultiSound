package com.expert.multisound.activity.login

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.expert.multisound.R
import com.expert.multisound.activity.server.ServerActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission


class Main : AppCompatActivity() {

    var permissionlistener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
        }

        override fun onPermissionDenied(deniedPermissions: List<String>) {
           finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setStyle()

        TedPermission.create()
            .setPermissionListener(permissionlistener)
            .setDeniedMessage("Попробуйте дати права через настройки")
            .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO)
            .check();

    }

    fun setStyle() {
        window.navigationBarColor = resources.getColor(R.color.color1)
    }


    override fun onBackPressed() {}
    fun create_server(view: View) {
        val intent = Intent(this, ServerActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.outfade, R.anim.fade)
    }

}