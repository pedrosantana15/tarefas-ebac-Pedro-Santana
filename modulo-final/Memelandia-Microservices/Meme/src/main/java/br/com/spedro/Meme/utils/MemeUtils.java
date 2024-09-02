package br.com.spedro.Meme.utils;

import br.com.spedro.Meme.dtos.MemeRequestDTO;
import br.com.spedro.Meme.dtos.MemeResponseDTO;
import br.com.spedro.Meme.entities.Meme;

import java.util.ArrayList;
import java.util.List;

public class MemeUtils {

    public static MemeResponseDTO convertEntityToResponseDto(Meme meme) {
        MemeResponseDTO memeResponseDTO = new MemeResponseDTO();
        memeResponseDTO.setTitle(meme.getTitle());
        memeResponseDTO.setDescription(meme.getDescription());
        memeResponseDTO.setUrl(meme.getUrl());
        memeResponseDTO.setCategoryName(meme.getMemeCategory().getName());
        memeResponseDTO.setUserName(meme.getMemeOwner().getUsername());

        return memeResponseDTO;
    }

    public static Meme convertRequestDtoToEntity(MemeRequestDTO memeDto) {
        Meme meme = new Meme();
        meme.setTitle(memeDto.getTitle());
        meme.setDescription(memeDto.getDescription());
        meme.setUrl(memeDto.getUrl());

        return meme;
    }

    public static Meme convertResponseDtoToEntity(MemeResponseDTO memeDto) {
        Meme meme = new Meme();
        meme.setTitle(memeDto.getTitle());
        meme.setDescription(memeDto.getDescription());
        meme.setUrl(memeDto.getUrl());

        return meme;
    }

    public static List<MemeResponseDTO> convertEntityListToResponseDtoList(List<Meme> memes) {
        List<MemeResponseDTO> memeResponseDTOList = new ArrayList<>();
        memes.forEach(meme -> memeResponseDTOList.add(convertEntityToResponseDto(meme)));

        return memeResponseDTOList;
    }

}
