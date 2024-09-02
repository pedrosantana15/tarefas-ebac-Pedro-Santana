package br.com.spedro.Meme;

import br.com.spedro.Meme.controller.MemeController;
import br.com.spedro.Meme.dtos.MemeRequestDTO;
import br.com.spedro.Meme.dtos.MemeResponseDTO;
import br.com.spedro.Meme.services.MemeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MemeControllerTest {

    @InjectMocks
    private MemeController memeController;

    @Mock
    private MemeService memeService;

    public MemeRequestDTO buildMemeRequest() {
        MemeRequestDTO meme = new MemeRequestDTO();
        meme.setTitle("Animals Memes");
        meme.setDescription("Memes");
        meme.setUrl("https://memes.com");
        meme.setIdCategory("C1");
        meme.setIdUser("U1");
        return meme;
    }

    /**
    The method "createMeme" uses URI Servlet and requires an HTTP context. We can't simulate this context in a
    unit test. That's why this method can't work.

    @Test
    public void testCreateMeme_HappyPath() {
        MemeRequestDTO memeDto = buildMemeRequest();

        MemeResponseDTO responseDto = new MemeResponseDTO();
        responseDto.setTitle(memeDto.getTitle());
        responseDto.setDescription(memeDto.getDescription());
        responseDto.setUrl(memeDto.getUrl());
        responseDto.setCategoryName("Funny");
        responseDto.setUserName("john_doe");

        Mockito.when(memeService.createMeme(memeDto)).thenReturn(responseDto);

        ResponseEntity<MemeResponseDTO> createdMeme = memeController.createMeme(memeDto);

        assertNotNull(createdMeme);
        assertEquals(HttpStatus.CREATED, createdMeme.getStatusCode());
        assertNull(createdMeme.getHeaders().getLocation());
        assertNotNull(createdMeme.getBody());
        assertEquals(memeDto.getTitle(), createdMeme.getBody().getTitle());
        assertEquals(memeDto.getDescription(), createdMeme.getBody().getDescription());
        assertEquals(memeDto.getUrl(), createdMeme.getBody().getUrl());
        assertEquals("Funny", createdMeme.getBody().getCategoryName());
        assertEquals("john_doe", createdMeme.getBody().getUserName());
    }

     @deprecated
    **/


    @Test
    public void testFindMemeById_HappyPath() {
        MemeResponseDTO responseDto = new MemeResponseDTO();
        responseDto.setTitle("Animals Memes");
        responseDto.setDescription("Memes");
        responseDto.setUrl("https://memes.com");
        responseDto.setCategoryName("Funny");
        responseDto.setUserName("john_doe");

        Mockito.when(memeService.findMemeById("M1")).thenReturn(responseDto);

        ResponseEntity<MemeResponseDTO> foundMeme = memeController.findMemeById("M1");

        assertNotNull(foundMeme);
        assertEquals("Animals Memes", foundMeme.getBody().getTitle());
        assertEquals("Memes", foundMeme.getBody().getDescription());
        assertEquals("Funny", foundMeme.getBody().getCategoryName());
        assertEquals("john_doe", foundMeme.getBody().getUserName());
    }

    @Test
    public void testFindAllMemes_HappyPath() {
        MemeResponseDTO responseDto = new MemeResponseDTO();
        responseDto.setTitle("Animals Memes");
        responseDto.setDescription("Memes");
        responseDto.setUrl("https://memes.com");
        responseDto.setCategoryName("Funny");
        responseDto.setUserName("john_doe");

        List<MemeResponseDTO> memeList = new ArrayList<>();
        memeList.add(responseDto);

        Mockito.when(memeService.findAllMemes()).thenReturn(memeList);

        ResponseEntity<Iterable<MemeResponseDTO>> memes = memeController.findAllMemes();

        assertNotNull(memes);
        assertTrue(memes.getBody().iterator().hasNext());
        MemeResponseDTO foundMeme = memes.getBody().iterator().next();
        assertEquals("Animals Memes", foundMeme.getTitle());
        assertEquals("Funny", foundMeme.getCategoryName());
        assertEquals("john_doe", foundMeme.getUserName());
    }

    @Test
    public void testDeleteMeme_HappyPath() {
        ResponseEntity<String> message = ResponseEntity.ok("Meme deleted successfully");
        Mockito.when(memeService.deleteMeme("M1")).thenReturn(message);

        ResponseEntity<String> response = memeController.deleteMeme("M1");

        assertNotNull(response);
        assertEquals(response.getBody(), message.getBody());
    }

}
