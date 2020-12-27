package com.example.sambo.ui.bottomnavigation.profilehelper

import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.sambo.R
import com.example.sambo.data.local.PreferenceHelper
import com.example.sambo.ui.main.ForFragmentActivity
import com.example.sambo.ui.main.MainActivity
import com.example.sambo.ui.main.RegistrationOneFragment
import com.example.sambo.utils.cleanLaunchActivity
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
            alertDialog()
        }

        ext_text.setOnClickListener {
            PreferenceHelper.clear()
            cleanLaunchActivity<ForFragmentActivity>()
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

    private fun alertDialog(){
        val dialog = AlertDialog.Builder(android.view.ContextThemeWrapper(requireContext(),R.style.myDialog))
        dialog.setTitle("Выбор загрузки фото")
        dialog.setPositiveButton("камера", object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                shootPhotoWithPermissionCheck()
            }
        })

        dialog.setNegativeButton("галерея",object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                pickPhotofromGalerryWithPermissionCheck()
            }
        })
        dialog.show()
    }
}