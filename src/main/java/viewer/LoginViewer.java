package viewer;

import controller.CustomerController;
import controller.StaffController;
import dbConn.ConnectionMaker;
import model.StaffDTO;
import model.StoreDTO;
import util.ScannerUtil;

import java.sql.Connection;
import java.util.Scanner;

public class LoginViewer {
    private final Scanner SCANNER;
    private Connection connection;
    private StaffDTO logIn;

    public LoginViewer(ConnectionMaker connectionMaker) {
        SCANNER = new Scanner(System.in);
        connection = connectionMaker.makeConnection();
    }

    public void showIndex() {
        String message = "1. 로그인 2. 직원등록 0. 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 2);
            if (userChoice == 1) {
                auth();
                if (logIn != null) {
                    showMenu();
                }else{
                    System.out.println("로그인 인증 실패");
                }
            } else if (userChoice == 2) {
                //register();
            } else if (userChoice == 0) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void auth() {
        String message = "username을 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message);
        message = "password를 입력해주세요.";
        String password = ScannerUtil.nextLine(SCANNER, message);

        StaffDTO s = new StaffDTO();
        s.setUsername(username);
        s.setPassword(password);

        StaffController staffController = new StaffController(connection);
        logIn = staffController.auth(s);
    }

    private void showMenu() {
        String message = "1. 상점목록 2. 직원정보관리 3. 상점관리 0. 로그아웃";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                StoreViewer storeViewer = new StoreViewer(SCANNER, connection, logIn);
                storeViewer.showMenu(); // 상점목록
            } else if (userChoice == 2) {
                //printOne();
            } else if (userChoice == 3) {
                StoreViewer storeViewer = new StoreViewer(SCANNER, connection, logIn);
                storeViewer.storeIndex();   // 상점관리
            } else if (userChoice == 0) {
                logIn = null;
                System.out.println("정상적으로 로그아웃되었습니다.");
            }
        }
    }

    private void printOne() {
        System.out.println("직원 번호: " + logIn.getStaff_id());
        System.out.printf("직원 이름: %s %s", logIn.getFirst_name(), logIn.getLast_name());
        System.out.println("email: " + logIn.getEmail());
        System.out.println("근무지 번호: " + logIn.getStore_id());
        String message = "1. 수정 2. 탈퇴 0. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 2);
        if (userChoice == 1) {
            //update();
        } else if (userChoice == 2) {
            //delete();
        }
    }


}





























