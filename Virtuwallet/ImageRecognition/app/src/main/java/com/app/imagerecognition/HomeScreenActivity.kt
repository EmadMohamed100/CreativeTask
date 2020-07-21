package com.app.imagerecognition

import android.Manifest
import android.app.Fragment
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.hardware.Camera
import android.hardware.Camera.PreviewCallback
import android.media.ImageReader
import android.media.ImageReader.OnImageAvailableListener
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Size
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class HomeScreenActivity : AppCompatActivity(), OnImageAvailableListener, PreviewCallback {

    private val MODEL_PATH = "poppy.tflite"
    private val QUANT = true
    private val LABEL_PATH = "poppylabels.txt"
    private val INPUT_SIZE = 224

    private var classifier: Classifier? = null

    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val btnDetectObject: ImageView? get() = mobile_iv
    private val imageViewResult: ImageView? get() = image_view_result
//    private val cameraView: CameraView get() = camera_view
    private var handler: Handler? = null
    private var handlerThread: HandlerThread? = null
    private val rgbFrameBitmap: Bitmap? = null
    private var rgbBytes: IntArray? = null
    private var previewWidth = 0
    private var previewHeight = 0
    private val sensorOrientation: Int? = null
    private val PERMISSIONS_REQUEST = 1
    private val PERMISSION_CAMERA = Manifest.permission.CAMERA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (hasPermission()) {
            setUpScanner()
        } else {
            requestPermission()
        }

    }

    private fun setUpScanner() {
        val cameraId: String = chooseCamera()

        val fragment: Fragment
        if (useCamera2API) {
            val camera2Fragment: CameraConnectionFragment = CameraConnectionFragment.newInstance(
                object : ConnectionCallback() {
                    fun onPreviewSizeChosen(
                        size: Size,
                        rotation: Int
                    ) {
                        previewHeight = size.height
                        previewWidth = size.width
                        this@HomeScreenActivity.onPreviewSizeChosen(size, rotation)
                    }
                },
                this,
                getLayoutId(),
                getDesiredPreviewFrameSize()
            )
            camera2Fragment.setCamera(cameraId)
            fragment = camera2Fragment
        } else {
            fragment =
                LegacyCameraConnectionFragment(this, getLayoutId(), getDesiredPreviewFrameSize())
        }

        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    private fun hasPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkSelfPermission(PERMISSION_CAMERA) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (shouldShowRequestPermissionRationale(PERMISSION_CAMERA)) {
                Toast.makeText(
                        this@HomeScreenActivity,
                        "Camera permission is required for this demo",
                        Toast.LENGTH_LONG
                    )
                    .show()
            }
            requestPermissions(
                arrayOf(PERMISSION_CAMERA),
                PERMISSIONS_REQUEST
            )
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        handlerThread!!.quitSafely()
        try {
            handlerThread!!.join()
            handlerThread = null
            handler = null
        } catch (e: InterruptedException) {
        }
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.execute { classifier!!.close() }
    }

    private fun initTensorFlowAndLoadModel() {
        executor.execute {
            try {
                classifier = TensorFlowImageClassifier.create(
                    assets,
                    MODEL_PATH,
                    LABEL_PATH,
                    INPUT_SIZE,
                    QUANT
                )
                makeButtonVisible()
            } catch (e: Exception) {
                throw RuntimeException("Error initializing TensorFlow!", e)
            }
        }
    }

    private fun makeButtonVisible() {
        runOnUiThread { btnDetectObject!!.visibility = View.VISIBLE }
    }

    override fun onImageAvailable(reader: ImageReader?) {
        processImage()
    }


    private fun processImage() {
        runInBackground(
            Runnable {
                if (classifier != null) {
                }
                Log.d("recognition_camera", "start")
//                readyForNextImage()
            })
    }

    @Synchronized
    protected fun runInBackground(r: Runnable?) {
        if (handler != null) {
            handler!!.post(r)
        }
    }

    override fun onPreviewFrame(data: ByteArray?, camera: Camera?) {
        processImage()
        Log.d("recognition_camera", "review")
    }
}
