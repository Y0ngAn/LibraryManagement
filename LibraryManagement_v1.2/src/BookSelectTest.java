import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookSelectTest {

    public static void main(String[] args) {
        // 1. DB 연결 가져오기
        Connection conn = DBconn.getConnection();

        // 쿼리문 작성
        String sql = "SELECT book_id, title, author FROM books";

        // try-with-resources 구문을 사용하여 자원 자동 반납 (Java 7 이상)
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            System.out.println("----------------------------------------------");
            System.out.println("ID\t| 제목\t\t\t| 저자");
            System.out.println("----------------------------------------------");

            // 2. 결과 집합(ResultSet) 순회
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String title = rs.getString("title");
                String author = rs.getString("author");

                System.out.printf("%d\t| %-15s\t| %s\n", id, title, author);
            }
            System.out.println("----------------------------------------------");

        } catch (SQLException e) {
            System.err.println("[조회 오류] " + e.getMessage());
        } finally {
            // 3. 연결 종료 (중요)
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("[시스템] DB 연결 해제 완료");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}