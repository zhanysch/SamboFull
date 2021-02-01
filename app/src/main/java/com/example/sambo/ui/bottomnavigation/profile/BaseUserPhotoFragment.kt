package com.example.sambo.ui.bottomnavigation.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import com.example.sambo.data.common.BaseFragment
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*

@RuntimePermissions
abstract  class BaseUserPhotoFragment : BaseFragment(){

    abstract fun showPhoto(file: File)
    private var filename: String? = null

     @NeedsPermission(android.Manifest.permission.CAMERA)
    fun shootPhoto(){


         filename = System.nanoTime().toString()
         val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
         //val context = SamboApp.applicationContext()
         val uri = getCaptureImageOutputUri(requireContext(), filename!!)

         if (uri !=null ){
             val file = File(uri.path)
             if (Build.VERSION.SDK_INT >= 24){
                 intent.putExtra(
                         MediaStore.EXTRA_OUTPUT,
                         FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.provider", file)
                 )
                 intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                 intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
             } else intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

             startActivityForResult(intent, RESULT_CAMERA)
         }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ( resultCode == Activity.RESULT_OK){
            when(requestCode){
                RESULT_CAMERA -> {

                     val uri = getImageFromCameraUri(data,filename!!)
                    val uriFile = getNormalizedUri(uri = uri)
                    val file = File(uriFile?.path)
                    file?.let {
                        showPhoto(it) }
                    Log.d("fgsdgsd","fssdg")
                }
                RESULT_GALLERY -> {
                    if (data !=null && data.data !=null){

                        val fileName = getImagePathFromInputStreamUri(requireContext(), data.data!!)
                        val file = File(fileName)
                        showPhoto(file)

                    }

                }
            }
        }
    }

    private fun getImageFromCameraUri(data: Intent?, filname: String): Uri? {
       var isCamera = true

        if (data != null && data.data != null){
            val action = data.action

            isCamera = action !=null && action == MediaStore.ACTION_IMAGE_CAPTURE
        }
        return if( isCamera || data!!.data == null)
            getCaptureImageOutputUri(requireContext(), filname)
        else
            data.data
    }

    @NeedsPermission( android.Manifest.permission.READ_EXTERNAL_STORAGE)
     fun pickPhotofromGalerry(){
        val intent = Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, RESULT_GALLERY)



    }

    private fun getCaptureImageOutputUri(context: Context, filName : String): Uri? {
        var outputFileUri : Uri? = null
        val getImage = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (getImage !=null){
            outputFileUri = Uri.fromFile(File(getImage.path, "$filName.jpeg"))
        }
        return outputFileUri
    }

    fun getNormalizedUri(  uri: Uri?): Uri? {

        return  if (uri !=null && uri.toString().contains("content:"))
            Uri.fromFile(getPath(requireContext() , uri, MediaStore.Images.Media.DATA))
        else uri
    }

    private fun getPath(context: Context , uri: Uri, column : String): File? {
        val columns = arrayOf(column)
        val cursor = context.contentResolver.query(uri , columns , null,null,null) ?: return null
        val columnIndex = cursor.getColumnIndexOrThrow(column)
        cursor.moveToFirst()
        val path = cursor.getString(columnIndex)
        cursor.close()
        return  File(path)
    }


    fun getImagePathFromInputStreamUri(context: Context, uri: Uri): String? {
        var inputStream: InputStream? = null
        var filePath: String? = null

        if (uri.authority != null) {
            try {
                inputStream = context.contentResolver.openInputStream(uri)
                val photoFile = createTemporalFileFrom(requireContext(), inputStream)
                filePath = photoFile!!.path
            } finally {
                try {
                    inputStream!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return filePath
    }

    @Throws(IOException::class)
    private fun createTemporalFileFrom(context: Context, inputStream: InputStream?): File? {
        var targetFile: File? = null

        if (inputStream != null) {
            var read: Int
            val buffer = ByteArray(8 * 1024)

            targetFile = createTemporalFile(context)
            val outputStream = FileOutputStream(targetFile)

            while (true) {
                read = inputStream.read(buffer)
                if (read == -1)
                    break
                outputStream.write(buffer, 0, read)
            }
            outputStream.flush()

            try {
                outputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return targetFile
    }
    private fun createTemporalFile(
            context: Context,
            filePath: String = Calendar.getInstance().timeInMillis.toString()
    ): File {
        return File(context.externalCacheDir, "$filePath.jpg") // context needed
    }


    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    companion object {
        private const val  RESULT_CAMERA = 101
        private const val  RESULT_GALLERY = 102
        private var filename: String? = null
    }
}