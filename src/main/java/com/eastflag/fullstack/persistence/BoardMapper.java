package com.eastflag.fullstack.persistence;

import com.eastflag.fullstack.domain.BoardVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

//control 레이어에서 request 를 받은 다음 바로 응답을 주는 것이라 DB를 조회해야 하므로
//persistence 레이어를 생성.
@Mapper // xml 없이 자바에서 MyBatis 구문 사용하기 위한 어노테이션
        // 스프링이 구동시 아래 인터페이스가 인스턴스로 스프링에 등록됨.
public interface BoardMapper {
    @Insert({"<script>",
            "INSERT INTO board(title, content)",
            "VALUES(#{title}, #{content})",//content와 title은 BoardVO 객체의 변수와 이름이 같아야 함!!!
            "</script>"})//body에 title, content 담아서 DB에 삽입
    int insertBoard(BoardVO boardVO);



    @Select({"<script>",
            "SELECT * from board",
            "where id = #{id}",
            "</script>"})//id값을 받아서 해당 id 값을 가진 튜플 출력
    BoardVO findOneBoard(int id);

    @Select({"<script>",
            "SELECT * from board",
            "order by id desc",
            "<if test='offset != null and page_size != null'>",
            "LIMIT #{offset}, #{page_size}", //LIMIT => offset부터 page_size반큼 선택
            "</if>",
            "</script>"})
    List<BoardVO> findBoard(Integer offset, Integer page_size); //객체들을 담아야 하므로리턴 타입은 리스트트

    @Select({"<script>",
            "SELECT count(*) from board",
            "</script>"})
    Integer countBoard(); //board의 튜플 수를 리턴턴

    @Update({"<script>",//trim 구문을 사용해서 set을 맨앞에 위치 시키고 맨뒤 ,를 없앰.
            "UPDATE board",
            "<trim prefix='set' suffixOverrides=','>",
            "<if test='title != null'>title = #{title},</if>",
            "<if test='content != null'>content = #{content},</if>",
            "</trim>",
            "WHERE id = #{id}",
            "</script>"})
    int updateBoard(BoardVO boardVO);
    @Delete({"<script>",
            "DELETE FROM board",
            "WHERE id = #{id}",
            "</script>"})
    int deleteBoard(int id);

}