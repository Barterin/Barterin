package com.barterin.barterinapps.ui.additem

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityAddItemBinding
import com.barterin.barterinapps.databinding.ActivityAddPhotoBinding
import com.barterin.barterinapps.ui.updateprofile.UpdateProfileActivity
import com.barterin.barterinapps.utils.uriToFile
import java.io.File

class AddPhotoActivity : AppCompatActivity() {

    private var _binding: ActivityAddPhotoBinding? = null
    private val binding get() = _binding
    private lateinit var sharedpref: SharedPreferenceClass
    private var getFile1: File? = null
    private var getFile2: File? = null
    private var getFile3: File? = null

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddPhotoBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupView()

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }


        binding?.image1?.setOnClickListener {
            openGalery1()
        }

        binding?.image2?.setOnClickListener {
            openGalery2()
        }

        binding?.image3?.setOnClickListener {
            openGalery3()
        }


    }

    private fun openGalery3() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery3.launch(chooser)
    }

    private val launcherIntentGallery3 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri

            val myFile = uriToFile(selectedImg, this@AddPhotoActivity)

            getFile3 = myFile
            val resultImage = BitmapFactory.decodeFile(getFile3?.path)
            binding?.image3?.setImageBitmap(resultImage)
        }
    }

    private fun openGalery2() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery2.launch(chooser)
    }

    private val launcherIntentGallery2 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri

            val myFile = uriToFile(selectedImg, this@AddPhotoActivity)

            getFile2 = myFile
            val resultImage = BitmapFactory.decodeFile(getFile2?.path)
            binding?.image2?.setImageBitmap(resultImage)
        }
    }

    private fun openGalery1() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery1.launch(chooser)
    }

    private val launcherIntentGallery1 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri

            val myFile = uriToFile(selectedImg, this@AddPhotoActivity)

            getFile1 = myFile
            val resultImage = BitmapFactory.decodeFile(getFile1?.path)
            binding?.image1?.setImageBitmap(resultImage)
        }
    }


    private fun setupView() {

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    companion object {
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

}
