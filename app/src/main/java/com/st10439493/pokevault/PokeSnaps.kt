package com.st10439493.pokevault

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.st10439493.pokevault.databinding.ActivityPokeSnapsBinding


class PokeSnaps : AppCompatActivity() {
    private lateinit var binding: ActivityPokeSnapsBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
            setContentView(R.layout.activity_poke_snaps)
            binding = ActivityPokeSnapsBinding.inflate(layoutInflater)
            setContentView(binding.root)

        //camera permissions
        val cameraProviderResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                var bitmap = it.data!!.extras?.get("data") as Bitmap
                binding.imgSavedPhoto.setImageBitmap(bitmap)
            }
        }

        //code for button to take photo
        binding.fabPhoto.setOnClickListener() {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraProviderResult.launch(intent)
        }

        }
    }
