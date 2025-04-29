package br.com.peer.ecommerce.service;

import br.com.peer.ecommerce.model.ImagemProduto;
import br.com.peer.ecommerce.repository.ImagemProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImagemProdutoService {

    private final ImagemProdutoRepository repository;

    public ImagemProdutoService(ImagemProdutoRepository repository) {
        this.repository = repository;
    }

    public List<ImagemProduto> listar() {
        return repository.findAll();
    }

    public ImagemProduto salvar(ImagemProduto imagem) {
        return repository.save(imagem);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // ✅ NOVO: Atualização da imagem com MultipartFile
    public boolean atualizarImagem(Long id, MultipartFile imagem) {
        Optional<ImagemProduto> existente = repository.findById(id);
        if (existente.isPresent()) {
            try {
                // 🔍 Debug da imagem recebida
                System.out.println("Imagem recebida - tipo: " + imagem.getContentType());
                System.out.println("Tamanho (bytes): " + imagem.getSize());
                System.out.println("Bytes[]: " + imagem.getBytes().length);

                ImagemProduto imagemProduto = existente.get();
                byte[] dadosImagem = imagem.getBytes();
                System.out.println(">>>>> Classe do objeto que vai para setDados(): " + dadosImagem.getClass().getName());
                System.out.println(">>>>> Primeiro byte (debug): " + dadosImagem[0]);

                imagemProduto.setDados(imagem.getBytes());
                imagemProduto.setTipo(imagem.getContentType());

                // 👇 ADICIONE ESSE LOG
                System.out.println("⚠️ Conteúdo de getDados antes do save: " + imagemProduto.getDados());

                repository.save(imagemProduto);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public Optional<ImagemProduto> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
