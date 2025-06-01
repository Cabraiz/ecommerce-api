package br.com.peer.ecommerce.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PagamentoService {

    private static final String NOME_RECEBEDOR = "Mateus Cardoso Cabral";
    private static final String CIDADE_RECEBEDOR = "SAO PAULO";

    public String processarPagamentoPix() {
        System.out.println("💸 Processando pagamento via Pix...");
        return "Pagamento via Pix processado com sucesso!";
    }

    public String processarPagamentoCartao() {
        System.out.println("💳 Processando pagamento com cartão...");
        return "Pagamento com cartão processado com sucesso!";
    }

    public String gerarPayloadPix(String chavePix, double valor, String idTransacaoOpcional) {
        String valorFormatado = String.format("%.2f", valor).replace(",", ".");
        String nomeFormatado = NOME_RECEBEDOR.length() > 25 ? NOME_RECEBEDOR.substring(0, 25) : NOME_RECEBEDOR;
        String cidadeFormatada = CIDADE_RECEBEDOR.length() > 15 ? CIDADE_RECEBEDOR.substring(0, 15) : CIDADE_RECEBEDOR;

        // Gera ID transação se não for informado
        String idTransacao = (idTransacaoOpcional == null || idTransacaoOpcional.isBlank())
                ? UUID.randomUUID().toString().substring(0, 14)
                : idTransacaoOpcional;

        // --- Merchant Account Info (campo 26) ---
        String campo26 = "0014BR.GOV.BCB.PIX" +
                "01" + String.format("%02d", chavePix.length()) + chavePix;
        String merchantAccount = "26" + String.format("%02d", campo26.length()) + campo26;

        // --- Adicional (campo 62 com ID) ---
        String campoInfoAdicional = "05" + String.format("%02d", idTransacao.length()) + idTransacao;
        String campo62 = "62" + String.format("%02d", campoInfoAdicional.length()) + campoInfoAdicional;

        // --- Payload sem CRC ---
        String payloadSemCRC =
                "000201" +
                        merchantAccount +
                        "52040000" +
                        "5303986" +
                        "54" + String.format("%02d", valorFormatado.length()) + valorFormatado +
                        "5802BR" +
                        "59" + String.format("%02d", nomeFormatado.length()) + nomeFormatado +
                        "60" + String.format("%02d", cidadeFormatada.length()) + cidadeFormatada +
                        campo62 +
                        "6304";

        // --- Retorna payload completo com CRC ---
        return payloadSemCRC + calcularCRC16(payloadSemCRC);
    }

    private String calcularCRC16(String str) {
        int polinomio = 0x1021;
        int resultado = 0xFFFF;

        for (int i = 0; i < str.length(); i++) {
            resultado ^= (str.charAt(i) << 8);
            for (int j = 0; j < 8; j++) {
                if ((resultado & 0x8000) != 0) {
                    resultado = (resultado << 1) ^ polinomio;
                } else {
                    resultado <<= 1;
                }
            }
        }

        resultado &= 0xFFFF;
        return String.format("%04X", resultado).toUpperCase();
    }
}
