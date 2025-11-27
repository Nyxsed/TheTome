package ru.nyxsed.thetome.core.domain.usecase

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import javax.inject.Inject

class GenerateQrUseCase @Inject constructor() {
    operator fun invoke(link: String, size: Int = 512): Bitmap? {
        return try {
            val barcodeEncoder = BarcodeEncoder()
            barcodeEncoder.encodeBitmap(link, BarcodeFormat.QR_CODE, size, size)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}