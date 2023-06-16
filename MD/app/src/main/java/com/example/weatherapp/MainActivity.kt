package com.example.weatherapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.firebase.ml.modeldownloader.CustomModel
import com.google.firebase.ml.modeldownloader.CustomModelDownloadConditions
import com.google.firebase.ml.modeldownloader.DownloadType
import com.google.firebase.ml.modeldownloader.FirebaseModelDownloader
import dagger.hilt.android.AndroidEntryPoint
import org.tensorflow.lite.Interpreter

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var interpreter: Interpreter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val conditions = CustomModelDownloadConditions.Builder()
//            .requireWifi()  // Also possible: .requireWifi() .requireCharging() and .requireDeviceIdle()
            .build()
        FirebaseModelDownloader.getInstance()
            .getModel(
                "rain_rate", DownloadType.LOCAL_MODEL_UPDATE_IN_BACKGROUND,
                conditions
            )
            .addOnSuccessListener { model: CustomModel? ->
                val modelFile = model?.file
                Log.i("MainActivity", "model file : ${modelFile?.absolutePath}")
                if (modelFile != null) {
//                    val modelBuffer = loadModelFile(modelFile)
//                    val interpreter = Interpreter(modelBuffer)

                    interpreter = Interpreter(modelFile)

                    Log.i("MainActivity", "interpreter : ${interpreter.outputTensorCount}")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("MainActivity", "Exception downloading model", exception)
            }

//        val remoteModel = FirebaseCustomRemoteModel.Builder("rain_rate").build()
//
//        val conditions = FirebaseModelDownloadConditions.Builder()
////            .requireWifi()
//            .build()
//        val localModel = FirebaseCustomLocalModel.Builder()
//            .setAssetFilePath("rain_rate.tflite")
//            .build()
//
//        FirebaseModelManager.getInstance().download(remoteModel, conditions)
//            .addOnCompleteListener {
//                if(it.isSuccessful){
//                    val options = FirebaseModelInterpreterOptions.Builder(localModel).build()
//                    val interpreter = FirebaseModelInterpreter.getInstance(options)
//
//                }
//            }
//
//        FirebaseModelManager.getInstance().isModelDownloaded(remoteModel)
//            .addOnSuccessListener { isDownloaded ->
//                val options =
//                    if (isDownloaded) {
//                        FirebaseModelInterpreterOptions.Builder(remoteModel).build()
//                    } else {
//                        FirebaseModelInterpreterOptions.Builder(localModel).build()
//                    }
//                val interpreter = FirebaseModelInterpreter.getInstance(options)
//                Log.i("MainActivity", "interpreter : $interpreter")
//            }

        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WeatherApp()
                }
            }
        }
    }
}