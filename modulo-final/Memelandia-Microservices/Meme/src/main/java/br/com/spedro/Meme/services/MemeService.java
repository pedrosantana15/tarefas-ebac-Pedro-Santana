package br.com.spedro.Meme.services;

import br.com.spedro.Meme.dtos.MemeRequestDTO;
import br.com.spedro.Meme.dtos.MemeResponseDTO;
import br.com.spedro.Meme.entities.Meme;
import br.com.spedro.Meme.feign.CategoryFeign;
import br.com.spedro.Meme.feign.UserFeign;
import br.com.spedro.Meme.repositories.IMemeRepository;
import br.com.spedro.Meme.utils.MemeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class MemeService {

    private final IMemeRepository memeRepository;
    private final UserFeign userFeign;
    private final CategoryFeign categoryFeign;

    @Autowired
    public MemeService(IMemeRepository memeRepository, UserFeign userFeign,
                       CategoryFeign categoryFeign) {
        this.memeRepository = memeRepository;
        this.userFeign = userFeign;
        this.categoryFeign = categoryFeign;
    }

    public MemeResponseDTO createMeme(MemeRequestDTO memeDto) {
        validUser(memeDto.getIdUser());
        validCategory(memeDto.getIdCategory());

        Meme meme = MemeUtils.convertRequestDtoToEntity(memeDto);

        meme.setMemeOwner(userFeign.findUserById(memeDto.getIdUser()));
        meme.setMemeCategory(categoryFeign.findCategoryById(memeDto.getIdCategory()));
        memeRepository.insert(meme);

        return MemeUtils.convertEntityToResponseDto(meme);
    }

    public List<MemeResponseDTO> findAllMemes() {
        List<Meme> memes = memeRepository.findAll();
        return MemeUtils.convertEntityListToResponseDtoList(memes);
    }

    public MemeResponseDTO findMemeById(String id) {
        Meme meme = memeRepository.findById(id).orElseThrow(() -> new RuntimeException("Meme not found"));
        return MemeUtils.convertEntityToResponseDto(meme);
    }

    public ResponseEntity<String> deleteMeme(String id) {
        memeRepository.deleteById(id);
        return ResponseEntity.ok("Meme deleted successfully");
    }

    public MemeResponseDTO dailyMeme() {
        List<Meme> memes = memeRepository.findAll();

        if (memes.isEmpty()) {
            throw new RuntimeException("Meme not found");
        }

        Random random = new Random();
        Meme randomMeme = memes.get(random.nextInt(memes.size()));

        return MemeUtils.convertEntityToResponseDto(randomMeme);
    }

    public void validCategory(String id) {
        Boolean isCategoryRegistered = categoryFeign.isCategoryRegistered(id);
        if (!isCategoryRegistered) {
            throw new IllegalArgumentException("This Category is not registered");
        }
    }

    public void validUser(String id) {
        Boolean isUserRegistered = userFeign.isUserRegistered(id);
        if (!isUserRegistered) {
            throw new IllegalArgumentException("This Category is not registered");
        }
    }

}
