import java.sql.*; // Connection, Statement, ResultSet을 한 번에!
import java.util.*; // Scanner나 List 등을 쓸 때를 대비

public class BookList {
    public static void main(String[] args) {
        String sql = "SELECT * FROM books";

        // try-with-resources 문법으로 close() 생략 & 가독성 업!
        try (Connection conn = DBconn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== 도서 목록 ===");
            while (rs.next()) {
                // 컬럼명으로 데이터 가져오기
                System.out.printf("ID: %d | 제목: %s | 저자: %s\n",
                        rs.getInt("book_id"), rs.getString("title"), rs.getString("author"));
            }

        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
    }
}