package com.example;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.example.domain.Board;

public class JPAClient {
	public static void main(String[] args) {
//		insertBoard();
		searchBoard();
	}
	
	// ============ 등록 기능 테스트 ==================
	static void insertBoard() {
		// EntityManagerFactory생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ch01-JPABasic");
		// EntityManager생성
		EntityManager em = emf.createEntityManager();
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();

		try {
			// Transaction 시작
			tx.begin();

			// DB에저장할객체생성
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글등록잘되네요2");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			// 글등록
			em.persist(board);

			// Transaction commit
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Transaction rollback
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
	
	// ============ 검색 기능 테스트 ==================
	static void searchBoard() {
		// EntityManagerFactory생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ch01-JPABasic");
		// EntityManager생성
		EntityManager em = emf.createEntityManager();
		try {
			// 글 상세 조회
			Board searchBoard = em.find(Board.class, 1L);
			System.out.println("---> " + searchBoard.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
}
