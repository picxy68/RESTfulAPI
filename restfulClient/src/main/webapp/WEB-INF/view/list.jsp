<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<title>Insert title here</title>
<section>
<br>

<div align="center">
<h2> <a href="/list">게시판 목록보기</a> </h2>
1.페이지 사이즈: ${pageSize} &emsp; 2.페이지 list사이즈: ${pageListSize} &emsp; 
3.전체레코드 수: ${totalCount} &emsp; 4.총 페이지 수: ${totalPage} &emsp; <br>
5.현재레코드: ${start} &emsp; 6.현재페이지: ${currentPage} &emsp;
7.하단 가로 시작: ${listStartPage} &emsp; 8.하단 가로 끝: ${listEndPage} &emsp;
</div>

<table border=1  width=700 >
<tr  align="center"> 
  <td> rownum </td> <td> rnum </td> <td>이름 </td><td> 나이 </td>
  <td> 메모 </td><td> 날짜(String형) </td>
</tr>

<tr align="center">
<c:forEach var="m" items="${li}" />
  <td>${m.rownum}</td>
  <td>${m.rnum}</td>
  <td>${m.name}</td>
  <td>${m.age}</td>
  <td>${m.memo}</td>
  <td>${m.regdate}</td>
</tr>
</table>

<form action="/list">
<input type=hidden name=ch21 value='%'>
<select name=ch1>
	<option value="name">이름</option>
	<option value="memo">메모</option>
</select>
<input type=text name=ch2> <input type="submit" value="검색">
</form>

<a href="/guestbook/list(start=1,ch1=${ch1},ch2=${ch2})">처음 페이지</a> &emsp;

<c:if test="${listStartPage > pageListSize}"
		th:with="start=${(listStartPage - pageListSize -1) * pageSize +1}">
<a href="/guestbook/list(start=${start}, ch1=${ch1}, ch2=${ch2})">이전 10페이지</a>
</c:if>

<c:if test="${listStartPage <= pageListSize}">이전 10페이지</c:if> &nbsp;

<c:forEach=var=i items= "${#numbers.sequence(listStartPage, listEndPage)}">
	<th:block th:with="start=${(i-1) * pageSize + 1}">
		<th:block th:if="${i<=totalPage}">
			<a th:href="@{/guestbook/list(start=${start}, ch1=${ch1}, ch2=${ch2})}" th:text="[+${i}+]"></a> &nbsp;
		</th:block>
	</th:block>
</c:forEach>

<th:block th:if="${listEndPage < totalPage}"
		th:with="start=${listEndPage * pageSize + 1}">
	<a th:href="@{guestbook/list(start=${start}, ch1=${ch1}, ch2=${ch2})}" th:text="'다음 10페이지'"></a>&nbsp;
</th:block>

<th:block th:if="${listEndPage >= totalPage}">다음 10페이지</th:block>

<th:block th:with="endPage=${totalPage}">
	<a th:href="@{/guestbook/list(start=${endPage}, ch1=${ch1}, ch2=${ch2})}" th:text="'마지막 페이지'"></a>
</th:block>

</div>


<br>
</section>
</html>
