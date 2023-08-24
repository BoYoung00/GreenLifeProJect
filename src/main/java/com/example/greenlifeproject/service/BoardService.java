package com.example.greenlifeproject.service;

import com.example.greenlifeproject.dto.BoardDTO;
import com.example.greenlifeproject.dto.MemberDTO;
import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.entity.boardEntitys.BoardEntity;
import com.example.greenlifeproject.entity.boardEntitys.FileEntity;
import com.example.greenlifeproject.repository.BoardFileRepository;
import com.example.greenlifeproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final BoardFileRepository fileRepository;
    public void saveNewBoardPost(BoardDTO boardDTO) throws IOException {
        BoardEntity boardEntity;

        if (boardDTO.getBoardFile().isEmpty()){
            boardEntity = BoardEntity.convertToBoardEntity(boardDTO);
            boardRepository.save(boardEntity);
        }
        else{
            MultipartFile boardFile = boardDTO.getBoardFile();

            String originalFileName=boardFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" +originalFileName;
            //가짜 이름 만들어주기

            String savePath= "C:\\SpringBoot\\GreenLifeProJect\\src\\main\\resources\\boardFile/"+storedFileName;//내가 이 위치에 폴더 만들기! 이곳에 저장될 예정
            boardFile.transferTo(new File(savePath)); //이 부분에서 실제로 저장되는 부분! ->이 부분에서 예외처리!

            boardEntity = BoardEntity.convertFileToBoardEntity(boardDTO);

            Long saveId = boardRepository.save(boardEntity).getId();
            //저장한 아이디의 값을 가지고오기

            BoardEntity board=boardRepository.findById(saveId).get();

            FileEntity file = FileEntity.toBoardFileEntity(board,originalFileName,storedFileName);

            fileRepository.save(file);
        }
    }

    public Page<BoardDTO> showBoardPostList(Pageable pageable){

        Page<BoardEntity> boardPage = boardRepository.findAll(pageable);
        Page<BoardDTO> boardDTOPage = boardPage.map(BoardDTO::convertToBoardDTO);

        return boardDTOPage;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findBoardDTOById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            return BoardDTO.convertToBoardDTO(boardEntity);
        }
        return null;
    }
    public BoardEntity findBoardEntityById(Long id){
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);

        return optionalBoardEntity.orElse(null);
    }
    public int getUserPostCount(MemberEntity member) {

        return boardRepository.countByMember(member);
    }

    public int findByPostHits(Long id) {
        BoardEntity boardEntity = boardRepository.findBoardEntityById(id);

        return boardEntity.getBoardHits();
    }
}
