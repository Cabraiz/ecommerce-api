package br.com.peer.ecommerce.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Service
public class PagamentoService {

    public String processarPagamentoPix() {
        System.out.println("💸 Processando pagamento via Pix...");
        return "Pagamento via Pix processado com sucesso!";
    }

    public String processarPagamentoCartao() {
        System.out.println("💳 Processando pagamento com cartão...");
        return "Pagamento com cartão processado com sucesso!";
    }

    public String gerarQRCodePix(String chavePix, double valor, String descricao) {
        try {
            // Simulação de payload Pix básico (não é um payload oficial EMV ainda)
            String payloadPix = "pix.chave:" + chavePix + "|valor:" + valor + "|desc:" + descricao;

            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(payloadPix, BarcodeFormat.QR_CODE, 300, 300);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
