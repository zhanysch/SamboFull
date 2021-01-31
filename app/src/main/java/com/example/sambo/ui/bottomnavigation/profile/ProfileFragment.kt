package com.example.sambo.ui.bottomnavigation.profile

import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.sambo.R
import com.example.sambo.data.local.PreferenceHelper
import com.example.sambo.ui.registration.ForFragmentActivity
import com.example.sambo.utils.ext.cleanLaunchActivity
import com.example.sambo.utils.ext.setCornerRadius
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.File

class ProfileFragment: BaseUserPhotoFragment() {

    override fun resID() = R.layout.fragment_profile

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
           PreferenceHelper.setIsLogetIn(false)
            cleanLaunchActivity<ForFragmentActivity>()
        }
    }

    override fun showPhoto(file: File) {
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        image.setImageBitmap(bitmap)
    }

    private fun makeroundImage() { // метод для обработки image чтоб углы закгруглялись  // ViewExt
        val radius = resources.getDimension(R.dimen.imageRadius)
        image.setCornerRadius(
            topLeft = radius,
            topRight = radius,
            bottomLeft = radius,
            bottomRight = radius
        )
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