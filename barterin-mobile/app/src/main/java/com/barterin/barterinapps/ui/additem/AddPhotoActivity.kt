package com.barterin.barterinapps.ui.additem

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityAddPhotoBinding
import com.barterin.barterinapps.ui.bottomnavigation.HomeActivity
import com.barterin.barterinapps.utils.reduceFileImage
import com.barterin.barterinapps.utils.uriToFile
import com.barterin.barterinapps.viewmodel.ViewModelFactory
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
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
                    resources.getString(R.string.text_not_permission),
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

        val category = intent.getStringExtra("type")

        Toast.makeText(this@AddPhotoActivity, category, Toast.LENGTH_SHORT).show()


        binding?.image1?.setOnClickListener {
            openGalery1()
        }

        binding?.image2?.setOnClickListener {
            openGalery2()
        }

        binding?.image3?.setOnClickListener {
            openGalery3()
        }

        binding?.btnNextUpload?.setOnClickListener {
            uploadData()
        }


    }


    private fun uploadData() {

        val sharedPreference =  getSharedPreferences("id_preference", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.clear().commit()

        val type = intent.getStringExtra("type")
        val address = intent.getStringExtra("address")
        val itemName = intent.getStringExtra("itemName")
        val itemDescription = intent.getStringExtra("itemDescription")
        val usedTime = intent.getStringExtra("usedTime")
        val priceRange = intent.getStringExtra("priceRange")

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AddItemViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        if (getFile1 == null) {
            Toast.makeText(this, resources.getString(R.string.text_image_input1), Toast.LENGTH_SHORT).show()
        } else if (getFile2 == null) {
            Toast.makeText(this, resources.getString(R.string.text_image_input2), Toast.LENGTH_SHORT).show()
        } else if (getFile3 == null) {
            Toast.makeText(this, resources.getString(R.string.text_image_input3), Toast.LENGTH_SHORT).show()
        } else {

            val file = reduceFileImage(getFile1 as File)
            val file2 = reduceFileImage(getFile2 as File)
            val file3 = reduceFileImage(getFile3 as File)

            val typeRequest = type?.toRequestBody("text/plain".toMediaType())
            val addressRequest = address?.toRequestBody("text/plain".toMediaType())
            val itemNameRequest = itemName?.toRequestBody("text/plain".toMediaType())
            val itemDescriptionRequest = itemDescription?.toRequestBody("text/plain".toMediaType())
            val usedTimeRequest = usedTime?.toRequestBody("text/plain".toMediaType())
            val dateUnitRequest = "3".toRequestBody("text/plain".toMediaType())
            val priceRangeRequest = priceRange?.toRequestBody("text/plain".toMediaType())
            val itemForRequest = "0".toRequestBody("text/plain".toMediaType())

            val requestImageFile1 = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultiPart1: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo[]",
                file.name,
                requestImageFile1,
            )

            val requestImageFile2 = file2.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultiPart2: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo[]",
                file.name,
                requestImageFile2
            )

            val requestImageFile3 = file3.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultiPart3: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo[]",
                file.name,
                requestImageFile3
            )

            val listOfImages = arrayOf(imageMultiPart1, imageMultiPart2, imageMultiPart3)

            viewModel.uploadItem(
                sharedpref.getToken(),
                typeRequest!!,
                addressRequest!!,
                itemNameRequest!!,
                itemDescriptionRequest!!,
                usedTimeRequest!!,
                dateUnitRequest,
                priceRangeRequest!!,
                itemForRequest,
                listOfImages
            ).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is com.barterin.barterinapps.data.Result.Loading -> {
                            binding?.progressBar8?.visibility = View.VISIBLE
                        }
                        is com.barterin.barterinapps.data.Result.Success -> {
                            binding?.progressBar8?.visibility = View.GONE
                            Toast.makeText(
                                this,
                                "Success",
                                Toast.LENGTH_SHORT
                            ).show()
                            Intent(this, HomeActivity::class.java).also {
                                startActivity(it)
                            }
                        }
                        is com.barterin.barterinapps.data.Result.Error -> {
                            binding?.progressBar8?.visibility = View.GONE
                            Toast.makeText(
                                this,
                                resources.getString(R.string.text_error) + result.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
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
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

}
