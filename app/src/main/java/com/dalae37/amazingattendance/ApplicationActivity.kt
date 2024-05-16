package com.dalae37.amazingattendance

import android.content.pm.PackageInfo
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class ApplicationActivity : AppCompatActivity() {

    val editor = getSharedPreferences("AmazingAttendance", MODE_PRIVATE).edit()

    val registration_list = findViewById<RecyclerView>(R.id.registration_list)
    val application_list = findViewById<RecyclerView>(R.id.application_list)

    lateinit var registrationItem : ArrayList<ApplicationData>
    val applicationItems = ArrayList<ApplicationData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_application)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.application)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val packages: List<PackageInfo> = packageManager.getInstalledPackages(0)

        for (app in packages) {
            val packageName = app.packageName
            val appName = app.applicationInfo.loadLabel(packageManager).toString()
            val appIcon = packageManager.getApplicationIcon(packageName)

            val appData: ApplicationData = ApplicationData(appName, packageName, appIcon)
            applicationItems.add(ApplicationData(appName, packageName, appIcon))
        }
    }

    override fun onPause() {
        super.onPause()
        editor.remove("app")
        editor.putString("app", registrationItem.toString())
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        editor.commit()
    }
}