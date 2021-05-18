package com.eastflag.fullstack.controller;

import com.eastflag.fullstack.domain.BoardVO;
import com.eastflag.fullstack.domain.ResultVO;
import com.eastflag.fullstack.persistence.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor//생성자 주입
@RestController
@RequestMapping("/api")
public class BoardController {
    private final BoardMapper boardMapper;

    @PostMapping("/board")
    public ResultVO addBoard(@RequestBody BoardVO boardVO) {//body에 BoardVO 객체를 넣어서 board 테이블에 삽입.
        int result = boardMapper.insertBoard(boardVO);
        if (result > 0) {
            System.out.print(boardVO);
            return new ResultVO(0, "success");
        } else {
            return new ResultVO(100, "fail");
        }
    }
    @GetMapping("/board/{id}")//쿼리 파라미터를 이용해 같은 id를 가진 튜플 조회
    public BoardVO findOne(@PathVariable int id) {
        return boardMapper.findOneBoard(id);
    }

    @GetMapping("/boards") //page_number와 page_size로 offset을 구하고 해당 페이지에 있는 게시물 리턴 = 우리가 아는 페이지
    public List<BoardVO> findAllBoard(@RequestParam @Nullable Integer page_number,
                                      @RequestParam @Nullable Integer page_size) {//쿼리파라미터 안넘기면 board의 모든 내용가져옴
        Integer offset = null; //Wrapper 클래스(객체), null값 처리할 수 있기 때문에 SQL과 연동하기 좋음
                               //DB에서 자료형이 정수형이지만 null 값이 필요한 경우 VO에서 Integer를 사용할 수 있음.

        if (page_number != null && page_size != null) {
            offset = (page_number - 1) * page_size;
        }
        System.out.println(offset);
        System.out.println(page_size);
        return boardMapper.findBoard(offset, page_size);
    }
    @GetMapping("/board/count")//board 튜플 개수 리턴
    public Integer countBoard() {
        return boardMapper.countBoard();
    }

    @PutMapping("/board")//body에 변경할 내용의 id와, 변경할 title, content을 보낸다. 수정할 때는 put을 관례적으로 사용
    public ResultVO modifyBoard(@RequestBody BoardVO boardVO) {
        int result = boardMapper.updateBoard(boardVO);
        if ( result > 0) {
            return new ResultVO(0, "success");
        } else {
            return new ResultVO(100, "fail");
        }
    }

    @DeleteMapping("/board") //VO삭제
    public ResultVO removeBoard(@RequestParam int id) {
        int result = boardMapper.deleteBoard(id);
        if (result > 0) {
            return new ResultVO(0, "success");
        } else {
            return new ResultVO(100, "fail");
        }
    }
}