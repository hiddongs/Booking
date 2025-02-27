package com.booking.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;

import com.booking.member.User;
import com.dbutil.DBUtil;

public class CashDAO {
    static UserDAO userDAO;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 현금 충전
    public void chargeCash(String ID, int cash, BufferedReader br) {
        Connection conn = null;
        PreparedStatement pstmtU = null;
        String sqlU = null;

        try {
            conn = DBUtil.getConnection();
            sqlU = "UPDATE \"USER\" SET CASH = CASH + ? WHERE USER_ID=?";
            pstmtU = conn.prepareStatement(sqlU);
            pstmtU.setInt(1, cash);
            pstmtU.setString(2, ID);
            int update = pstmtU.executeUpdate();

            if (update == 1) {
                conn.commit();
                System.out.println("충전 성공 !");
            } else {
                conn.rollback();
                System.err.println("충전 실패 !");
            }
        } catch (NumberFormatException | InputMismatchException | ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
            System.err.println("⚠ 숫자만 입력하세요.");
        } finally {
            DBUtil.executeClose(null, pstmtU, conn);
        }
    }

    // 사용자 보유 금액 조회
    public void showCash(String ID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT USER_ID, CASH FROM \"USER\" WHERE USER_ID=?";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("============================================================");
                System.out.println("👤 사용자 ID : " + rs.getString("USER_ID"));
                System.out.println("💰 보유 금액 : " + rs.getInt("CASH") + "원");
                System.out.println("============================================================");
            } else {
                System.err.println("⚠ 사용자 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.executeClose(rs, pstmt, conn);
        }
    }

    // 사용자 포인트 조회
    public int showPoint(String ID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int point = -1;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT POINT FROM \"USER\" WHERE USER_ID=?");
            pstmt.setString(1, ID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                point = rs.getInt("POINT");
                System.out.println("============================================================");
                System.out.println("사용자 ID : " + ID);
                System.out.println("보유 포인트 : " + point + "점");
                System.out.println("============================================================");
            } else {
                System.err.println("사용자 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.executeClose(rs, pstmt, conn);
        }
        return point;
    }

    // 포인트 적립
    public void updatePoint(String ID) {
        Connection conn = null;
        PreparedStatement pstmtU = null;

        try {
            int point = new Random().nextInt(1501) + 500;
            conn = DBUtil.getConnection();
            String sqlU = "UPDATE \"USER\" SET POINT = POINT + ? WHERE USER_ID=?";
            pstmtU = conn.prepareStatement(sqlU);
            pstmtU.setInt(1, point);
            pstmtU.setString(2, ID);
            int update = pstmtU.executeUpdate();

            if (update == 1) {
                conn.commit();
                System.out.println(point + " 포인트 적립 성공!");
            } else {
                conn.rollback();
                System.err.println("포인트 적립 실패!");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            DBUtil.executeClose(null, pstmtU, conn);
        }
    }

    // 포인트 사용
    public void usePoint(String ID, int point) {
        Connection conn = null;
        PreparedStatement pstmtU = null;

        try {
            conn = DBUtil.getConnection();
            String sqlU = "UPDATE \"USER\" SET POINT = POINT - ? WHERE USER_ID=?";
            pstmtU = conn.prepareStatement(sqlU);
            pstmtU.setInt(1, point);
            pstmtU.setString(2, ID);
            int update = pstmtU.executeUpdate();

            if (update == 1) {
                conn.commit();
                System.out.println(point + " 포인트 사용 완료!");
            } else {
                conn.rollback();
                System.err.println("포인트 사용 실패!");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            DBUtil.executeClose(null, pstmtU, conn);
        }
    }

    // 현금 사용
    public void useCash(String ID, int cash) {
        Connection conn = null;
        PreparedStatement pstmtU = null;

        try {
            conn = DBUtil.getConnection();
            String sqlU = "UPDATE \"USER\" SET CASH = CASH - ? WHERE USER_ID=?";
            pstmtU = conn.prepareStatement(sqlU);
            pstmtU.setInt(1, cash);
            pstmtU.setString(2, ID);
            int update = pstmtU.executeUpdate();

            if (update == 1) {
                conn.commit();
                System.out.println(cash + "원 사용 완료!");
            } else {
                conn.rollback();
                System.err.println("원화 사용 실패!");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            DBUtil.executeClose(null, pstmtU, conn);
        }
    }

    // 결제 여부 확인
    public boolean checkPay(int reservation_ID) {
        Connection conn = null;
        PreparedStatement pstmtS = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT RESERVATION_ID FROM PAYMENT WHERE RESERVATION_ID=?";
            pstmtS = conn.prepareStatement(sql);
            pstmtS.setInt(1, reservation_ID);
            rs = pstmtS.executeQuery();

            if (rs.next()) {
                System.out.println(" 결제 완료된 예약입니다.");
                return true;
            } else {
                System.out.println(" 미결제 예약입니다.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.executeClose(rs, pstmtS, conn);
        }
        return false;
    }
}

