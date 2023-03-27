package com.example.testapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testapplication.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.common.Barcode
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import com.google.mlkit.vision.barcode.BarcodeScanning
import androidx.camera.view.CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED
import androidx.camera.mlkit.vision.MlKitAnalyzer

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var barcodeScanner: BarcodeScanner

//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Request camera permissions
//        if (allPermissionsGranted()) {
//            Log.d("TAG", "permissions granted")
//            startCamera()
//        } else {
//            Log.d("TAG2", "permissions NOT granted")
//            ActivityCompat.requestPermissions(
//                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
//            )
//        }
//
//        val navView: BottomNavigationView = binding.navView
//
////        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
//        val navController = navHostFragment.navController
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_history, R.id.navigation_favorites
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//
////        cameraExecutor = Executors.newSingleThreadExecutor()
//
//    }
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(viewBinding.root)

    // Request camera permissions
    if (allPermissionsGranted()) {
        startCamera()
    } else {
//        ActivityCompat.requestPermissions(
//            this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
//        )
        ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
//        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);

    }
}

    private fun startCamera() {
        Log.d("", "STARTING CAMERA Yeet")
        var cameraController = LifecycleCameraController(baseContext)
        val previewView: PreviewView = viewBinding.viewFinder

        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_UPC_E, Barcode.FORMAT_UPC_A)
            .build()
        barcodeScanner = BarcodeScanning.getClient(options)

        cameraController.setImageAnalysisAnalyzer(
            ContextCompat.getMainExecutor(this),
            MlKitAnalyzer(
                listOf(barcodeScanner),
                COORDINATE_SYSTEM_VIEW_REFERENCED,
                ContextCompat.getMainExecutor(this)
            ) { result: MlKitAnalyzer.Result? ->
                val barcodeResults = result?.getValue(barcodeScanner)
                if ((barcodeResults == null) ||
                    (barcodeResults.size == 0) ||
                    (barcodeResults.first() == null)
                ) {
                    previewView.overlay.clear()
                    previewView.setOnTouchListener { _, _ -> false } //no-op
                    return@MlKitAnalyzer
                }

                val qrCodeViewModel = QrCodeViewModel(barcodeResults[0]) // only needed for displaying qr code
                // Log is filled with E/BLASTBufferQueue errors so it is hard to see this printout
                // click the Logcat tab instead of the Run tab and add "-tag~:BLASTBufferQueue" to the
                // Logcat filter after "package:mine"
                Log.d("Barcode Results", qrCodeViewModel.qrContent)
//                val qrCodeDrawable = QrCodeDrawable(qrCodeViewModel)

                previewView.setOnTouchListener(qrCodeViewModel.qrCodeTouchCallback)
                previewView.overlay.clear()
//                previewView.overlay.add(qrCodeDrawable)
            }
        )

        cameraController.bindToLifecycle(this)
        previewView.controller = cameraController
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
        barcodeScanner.close()
    }

    companion object {
        private const val TAG = "CameraX-MLKit"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf (
                Manifest.permission.CAMERA
            ).toTypedArray()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                Log.d("", "STARTING CAMERA")
                startCamera()
            } else {
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}