package com.alquieventos.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;



public class QrCodeGenerator {

     public static void generarQRCode(String texto, String rutaArchivo, int ancho, int alto) throws IOException, WriterException {
        // Crear la carpeta qrcodes si no existe
        File directorio = new File("qrcodes");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, ancho, alto);

        Path path = FileSystems.getDefault().getPath(rutaArchivo);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
