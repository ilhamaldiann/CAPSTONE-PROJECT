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
import java.io.File
import java.nio.ByteBuffer

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var interpreter: Interpreter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val modelsName =
            listOf("rain_rate", "temp_min", "temp_max", "temp_avg", "hum_avg", "windspeed_avg")
        val modelFiles = mutableListOf<File>()

        val conditions = CustomModelDownloadConditions.Builder()
//            .requireWifi()  // Also possible: .requireWifi() .requireCharging() and .requireDeviceIdle()
            .build()

        modelsName.forEach { modelName ->
            FirebaseModelDownloader.getInstance()
                .getModel(
                    modelName, DownloadType.LOCAL_MODEL_UPDATE_IN_BACKGROUND,
                    conditions
                )
                .addOnSuccessListener { model: CustomModel? ->
                    val modelFile = model?.file
                    Log.i("MainActivity", "model file : ${modelFile?.absolutePath}")
                    if (modelFile != null) {

//                        tipe data FLOAT32
                        modelFiles.add(modelFile)
                        interpreter = Interpreter(modelFile)

// Persiapan data input
                        val inputShape = interpreter.getInputTensor(0).shape() // Mendapatkan shape input tensor
                        val inputBuffer = ByteBuffer.allocateDirect(inputShape[0] * inputShape[1] * 4) // Allokasikan ByteBuffer sesuai dengan ukuran input (4 bytes untuk FLOAT32)

// Contoh input: Nilai tunggal
                        val inputValue: Float = 100f // Ubah sesuai dengan input yang diinginkan

// Isi inputBuffer dengan data input yang sesuai
                        inputBuffer.putFloat(inputValue)
                        inputBuffer.rewind()

// Lakukan prediksi menggunakan Interpreter
                        val outputShape = interpreter.getOutputTensor(0).shape() // Mendapatkan shape output tensor
                        val outputBuffer = ByteBuffer.allocateDirect(outputShape[0] * outputShape[1] * 4) // Allokasikan ByteBuffer sesuai dengan ukuran output (4 bytes untuk FLOAT32)

                        interpreter.run(null ,outputBuffer)

// Proses output dari model
                        outputBuffer.rewind()

// Mendapatkan nilai output tunggal
                        val outputValue = outputBuffer.float

// Gunakan outputValue sesuai kebutuhan aplikasi Anda

                        Log.i(
                            "MainActivity",
                            "output $modelName: $outputValue"
                        )


//                        val inputString = "10" // String input yang ingin diprediksi
//                        val inputArray = inputString.toFloatArray() // Ubah input string menjadi array float
//                        val inputBuffer = FloatBuffer.allocate(inputArray.size)
//                        inputBuffer.put(inputArray)
//                        inputBuffer.rewind()
//
//                        val outputSize = 10
//                        val outputBuffer = FloatBuffer.allocate(outputSize)
//
//                        interpreter.run(inputBuffer, outputBuffer)
//
//                        outputBuffer.rewind()
//                        val outputArray = FloatArray(outputSize)
//                        outputBuffer.get(outputArray)
//                        val outputString = outputArray.joinToString(", ")
//
//                        Log.i("MainActivity", "output $modelName: $outputString")

//                        Log.i(
//                            "MainActivity",
//                            "interpreter $modelName : ${interpreter.outputTensorCount}"
//                        )
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("MainActivity", "Exception downloading model", exception)
                }
        }

//        if (modelFiles.size == modelsName.size){
//            interpreter = Interpreter(modelFiles[0])
//
//            val input = 100
//            val output = FloatArray(1)
//
//            interpreter.run(input, output)
//
//            Log.i("MainActivity", "output : $output")
//        }


//        FirebaseModelDownloader.getInstance()
//            .getModel(
//                "rain_rate", DownloadType.LOCAL_MODEL_UPDATE_IN_BACKGROUND,
//                conditions
//            )
//            .addOnSuccessListener { model: CustomModel? ->
//                val modelFile = model?.file
//                Log.i("MainActivity", "model file : ${modelFile?.absolutePath}")
//                if (modelFile != null) {
////                    val modelBuffer = loadModelFile(modelFile)
////                    val interpreter = Interpreter(modelBuffer)
//
//                    interpreter = Interpreter(modelFile)
//
//                    Log.i("MainActivity", "interpreter : ${interpreter.outputTensorCount}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.e("MainActivity", "Exception downloading model", exception)
//            }

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