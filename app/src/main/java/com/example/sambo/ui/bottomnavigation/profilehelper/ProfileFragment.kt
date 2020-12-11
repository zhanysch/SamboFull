package com.example.sambo.ui.bottomnavigation.profilehelper

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sambo.R
import com.example.sambo.ui.bottomnavigation.profilehelper.BaseUserPhotoFragment
import com.example.sambo.ui.bottomnavigation.profilehelper.pickPhotofromGalerryWithPermissionCheck
import com.google.android.material.shape.CornerFamily
import kotlinx.android.synthetic.main.profile_fragment.*
import java.io.File

class ProfileFragment: BaseUserPhotoFragment() {

    override fun resID() = R.layout.profile_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        makeroundImage()
    }

    private fun setupListeners() {
        changePhoto.setOnClickListener {
            pickPhotofromGalerryWithPermissionCheck()
        }

        ext_text.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_registrationOneFragment)
        }
    }

    override fun showPhoto(file: File) {
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        image.setImageBitmap(bitmap)
    }

    private fun makeroundImage() { // метод для обработки image чтоб углы закгруглялись
        val radius = resources.getDimension(R.dimen.imageRadius)
        val shape = image.shapeAppearanceModel.toBuilder()
            .setTopLeftCorner(CornerFamily.ROUNDED, radius)
            .setTopRightCorner(CornerFamily.ROUNDED, radius)
            .setBottomLeftCorner(CornerFamily.ROUNDED, radius)
            .setBottomRightCorner(CornerFamily.ROUNDED, radius)

            .build()

        image.shapeAppearanceModel = shape
    }

}