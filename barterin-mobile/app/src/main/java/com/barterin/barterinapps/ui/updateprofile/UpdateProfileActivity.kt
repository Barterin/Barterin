package com.barterin.barterinapps.ui.updateprofile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.barterin.barterinapps.R
import com.barterin.barterinapps.data.local.preference.SharedPreferenceClass
import com.barterin.barterinapps.databinding.ActivityUpdateProfileBinding
import com.barterin.barterinapps.ui.cameraactivity.CameraActivity
import com.barterin.barterinapps.ui.login.LoginViewModel
import com.barterin.barterinapps.utils.reduceFileImage
import com.barterin.barterinapps.utils.rotateBitmap
import com.barterin.barterinapps.utils.uriToFile
import com.barterin.barterinapps.viewmodel.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import com.barterin.barterinapps.data.Result
import com.barterin.barterinapps.ui.bottomnavigation.HomeActivity
import com.barterin.barterinapps.ui.emailverification.EmailVerificationActivity
import com.barterin.barterinapps.ui.itemmanagement.ItemManagementActivity
import com.bumptech.glide.Glide

class UpdateProfileActivity : AppCompatActivity() {

    private var _binding: ActivityUpdateProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedpref: SharedPreferenceClass

    private var getFile: File? = null

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

        _binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        setupView()


        binding.btnChooseImage.setOnClickListener {
            registerForContextMenu(binding.btnChooseImage)
        }

        binding.btnMyitems.setOnClickListener {
            startActivity(Intent(this@UpdateProfileActivity, ItemManagementActivity::class.java))
        }

        getData()

        binding.btnUpdate.setOnClickListener {
            uploadImage()
        }

    }

    private fun uploadImage() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[UpdateProfileViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        if (getFile != null) {
            val file = reduceFileImage(getFile as File)

            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requestImageFile
            )

            viewModel.uploadProfilePhoto(sharedpref.getToken(), imageMultipart).observe(this) {result ->

                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            binding.progressBar6.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            Toast.makeText(
                                this,
                                "Success",
                                Toast.LENGTH_SHORT
                            ).show()

                            uploadData()

                        }
                        is Result.Error -> {
                            binding.progressBar6.visibility = View.INVISIBLE
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

    private fun uploadData() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[UpdateProfileViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        val name = binding.edtName.text.toString()
        val phoneNumber = binding.edtPhoneNumber.text.toString()
        val born = binding.edtBorn.text.toString()
        val gender = binding.edtGender.text.toString()

        viewModel.updateDataProfile(sharedpref.getToken(), name, phoneNumber, born, gender).observe(this) {result ->

            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar6.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        Toast.makeText(
                            this,
                            "Success",
                            Toast.LENGTH_SHORT
                        ).show()

                        updatePreferences()

                    }
                    is Result.Error -> {
                        binding.progressBar6.visibility = View.INVISIBLE
                        Toast.makeText(
                            this,
                            "upload data error" + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }



    }

    private fun updatePreferences() {

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[UpdateProfileViewModel::class.java]
        sharedpref = SharedPreferenceClass(this)

        viewModel.getDataUser(sharedpref.getToken()).observe(this) { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding.progressBar6.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar6.visibility = View.GONE

                        val userId = result.data.id
                        val username = result.data.username
                        val email = result.data.email
                        val fullName = result.data.fullname
                        val verifiedEmail = result.data.verified_email
                        val verifiedAccount = result.data.verified_account
                        val phone = result.data.phone
                        val born = result.data.born
                        val profilePicture = result.data.profile_picture
                        val gender = result.data.gender

                        sharedpref = SharedPreferenceClass(this)
                        sharedpref.saveDataUser(userId, username, email, fullName, verifiedEmail, verifiedAccount, phone, born, profilePicture, gender, true)

                        binding.progressBar6.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "Data Berhasil Di Update",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                    is Result.Error -> {
                        binding.progressBar6.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "getdataerror" + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

    }


    private fun getData() {
        sharedpref = SharedPreferenceClass(this)

        Glide.with(this)
            .load("https://api.barterin.tech/uploads/images/profiles/${sharedpref.getProfilePicture()}")
            .placeholder(R.drawable.placeholder)
            .into(binding.imgProfile)

        binding.edtName.setText(sharedpref.getName())
        binding.edtPhoneNumber.setText(sharedpref.getPhoneNumber())
        binding.edtBorn.setText(sharedpref.getBorn())
        binding.edtGender.setText(sharedpref.getGender())

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.floating_camera_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.item_camera -> intentCameraX()
            R.id.item_galery -> intentGallery()
        }
        return super.onContextItemSelected(item)
    }

    private fun intentCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            getFile = myFile
            val result = BitmapFactory.decodeFile(getFile?.path)
            binding.imgProfile.setImageBitmap(result)
        }
    }

    private fun intentGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri

            val myFile = uriToFile(selectedImg, this@UpdateProfileActivity)

            getFile = myFile
            val resultImage = BitmapFactory.decodeFile(getFile?.path)
            binding.imgProfile.setImageBitmap(resultImage)
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

