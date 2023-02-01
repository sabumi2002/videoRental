package viewer;

import controller.*;
import model.*;
import util.ScannerUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreViewer {
    Scanner SCANNER;
    Connection connection;
    StaffDTO logIn;
    StoreDTO store;


    StoreViewer(Scanner scanner, Connection connection, StaffDTO logIn) {
        this.connection = connection;
        this.SCANNER = scanner;
        this.logIn = logIn;
    }

    public void showMenu() {
        StoreController storeController = new StoreController(connection);
        store = storeController.selectOne(logIn.getStore_id());

        printStoreInfo(logIn.getStore_id());


        int userChoice = -1;

        while (userChoice != 0) {
            String meesage = "1. 비디오검색 2. 회원검색(반납, 내역) 3. 회원관리(등록,수정,삭제) 0. 뒤로가기";
            userChoice = ScannerUtil.nextInt(SCANNER, meesage, 0, 3);
            if (userChoice == 1) {
                showVideoMenu();
            } else if (userChoice == 2) {
                RentalViewer rentalViewer = new RentalViewer(SCANNER, connection, logIn, store);
                rentalViewer.showCustomerInfo();
            } else if (userChoice == 3) {   //회원관리
                CustomerViewer customerViewer = new CustomerViewer(SCANNER, connection, logIn);
                customerViewer.showIndex();
            }
        }
    }

    public void showVideoMenu() {
        System.out.println("< 비디오 검색 >");
        String meesage = "1. 번호 검색 2. 제목 검색 3. 카테고리 검색 0. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, meesage, 0, 3);
        if (userChoice == 1) {
            printListById();
        } else if (userChoice == 2) {
            printListByTitle();
        } else if (userChoice == 3) {
            printCategory();
        } else if (userChoice == 0) {

        }
    }

    public void printListById() {
        A_filmController filmController = new A_filmController(connection);
        ArrayList<A_filmDTO> list = filmController.selectAll(store.getStore_id());
        if (list.isEmpty()) {
            System.out.println("등록된 영화가 없습니다.");
            showVideoMenu();
        } else {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < i + 10; j++) {
                    if (j >= list.size()) {
                        String message = "영화번호를 입력해주세요.";
                        // 영화번호입력

                        i = list.size();
                        j = i + 10;

                        scanById();
                        break;
                    } else {
                        A_filmDTO f = list.get(j);
                        System.out.printf("영화번호: %d번 제목: %s 개봉연도: %d\n", f.getFilm_id(), f.getTitle(), f.getLength());
                    }
                }
                System.out.println("==========================================================");
                String message = "1. 이전 2. 다음 3. 영화번호입력 0. 뒤로가기";
                int userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 3);
                if (userChoice == 1) {
                    if (i < 10) {
                        System.out.println("첫페이지 입니다.");
                        i = -1;
                    } else {
                        i -= 11;
                    }
                } else if (userChoice == 2) {
                    i += 9;
                } else if (userChoice == 3) {
                    scanById();
                    break;
                } else if (userChoice == 0) {
                    showVideoMenu();
                    break;
                }
            }
        }
    }

    private void scanById() {
        A_filmController filmController = new A_filmController(connection);
        ArrayList<A_filmDTO> list = filmController.selectAll(store.getStore_id());
        String message = "번호를 입력하시거나 뒤로 가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);

        while (userChoice != 0 && !list.contains(new A_filmDTO(userChoice))) {
            System.out.println("잘못 입력하셨습니다.");
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }

        if (userChoice != 0) {
            FilmViewer filmViewer = new FilmViewer(SCANNER, connection, logIn, store);
            filmViewer.printById(userChoice);
        }
    }

    public void printListByTitle() {
        String message = "제목에 관련된 단어를 입력해주세요.";
        String title = ScannerUtil.nextLine(SCANNER, message);

        A_filmController filmController = new A_filmController(connection);
        ArrayList<A_filmDTO> list = filmController.selectByTitle(title, store.getStore_id());
        if (list.isEmpty()) {
            System.out.println("관련된 영화가 없습니다.");
            showVideoMenu();
        } else {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < i + 10; j++) {
                    if (j >= list.size()) {
                        message = "영화번호를 입력해주세요.";
                        // 영화번호입력

                        i = list.size();
                        j = i + 10;
                    } else {
                        A_filmDTO f = list.get(j);
                        System.out.printf("영화번호: %d번 제목: %s 개봉연도: %d\n", f.getFilm_id(), f.getTitle(), f.getLength());
                    }
                }
                System.out.println("==========================================================");
                message = "1. 이전 2. 다음 3. 영화번호입력 0. 뒤로가기";
                int userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 3);
                if (userChoice == 1) {
                    if (i < 10) {
                        System.out.println("첫페이지 입니다.");
                        i = -1;
                    } else {
                        i -= 11;
                    }
                } else if (userChoice == 2) {
                    i += 9;
                } else if (userChoice == 3) {
                    scanByTitle(title);
                    break;
                } else if (userChoice == 0) {
                    showVideoMenu();
                    break;
                }
            }
        }
    }

    public void scanByTitle(String title) {
        A_filmController filmController = new A_filmController(connection);
        ArrayList<A_filmDTO> list = filmController.selectByTitle(title, store.getStore_id());
        String message = "번호를 입력하시거나 뒤로 가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);

        while (userChoice != 0 && !list.contains(new A_filmDTO(userChoice))) {
            System.out.println("잘못 입력하셨습니다.");
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }

        if (userChoice != 0) {
            FilmViewer filmViewer = new FilmViewer(SCANNER, connection, logIn, store);
            filmViewer.printById(userChoice);
        }
    }

    public void printCategory() {
        CategoryController categoryController = new CategoryController(connection);
        ArrayList<CategoryDTO> list = categoryController.selectAll();
        for (CategoryDTO c : list) {
            System.out.printf("카테고리: %d. %s\n", c.getCategory_id(), c.getName());
        }

        String message = "번호를 입력하시거나 뒤로 가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);

        while (userChoice != 0 && !list.contains(new CategoryDTO(userChoice))) {
            System.out.println("잘못 입력하셨습니다.");
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }

        if (userChoice != 0) {
            printListByCategory(userChoice);
        }


    }

    public void printListByCategory(int category_id) {
        A_filmController filmController = new A_filmController(connection);
        ArrayList<A_filmDTO> list = filmController.selectByCategory(category_id, store.getStore_id());
        if (list.isEmpty()) {
            System.out.println("관련된 영화가 없습니다.");
            showVideoMenu();
        } else {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < i + 10; j++) {
                    if (j >= list.size()) {
                        String message = "영화번호를 입력해주세요.";
                        i = list.size();
                        j = i + 10;

                        scanByCategory(list);
                        break;
                    } else {
                        A_filmDTO f = list.get(j);
                        System.out.printf("영화번호: %d번, 제목: %s, 카테고리: %s, 개봉연도: %d\n", f.getFilm_id(), f.getTitle(), f.getCategory(), f.getLength());
                    }
                }
                if (i >= list.size()) {
                    break;
                }
                System.out.println("==========================================================");
                String message = "1. 이전 2. 다음 3. 영화번호입력 0. 뒤로가기";
                int userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 3);
                if (userChoice == 1) {
                    if (i < 10) {
                        System.out.println("첫페이지 입니다.");
                        i = -1;
                    } else {
                        i -= 11;
                    }
                } else if (userChoice == 2) {
                    i += 9;
                } else if (userChoice == 3) {
                    scanByCategory(list);
                    break;
                } else if (userChoice == 0) {
                    showVideoMenu();
                    break;
                }
            }
        }
    }

    public void scanByCategory(ArrayList<A_filmDTO> categoryList) {
        ArrayList<A_filmDTO> list = categoryList;
        String message = "번호를 입력하시거나 뒤로 가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);

        while (userChoice != 0 && !list.contains(new A_filmDTO(userChoice))) {
            System.out.println("잘못 입력하셨습니다.");
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }

        if (userChoice != 0) {
            FilmViewer filmViewer = new FilmViewer(SCANNER, connection, logIn, store);
            filmViewer.printById(userChoice);
        }
    }

    public void printStoreInfo(int store_id) {
        AddressController addressController = new AddressController(connection);
        StoreController storeController = new StoreController(connection);

        StoreDTO storeDTO = storeController.selectOne(store_id);
        AddressDTO addressDTO = addressController.selectOne(storeDTO.getAddress_id());

        System.out.printf("상점번호: %d. 위치: %s, 도시: %s, 나라: %s\n",
                storeDTO.getStore_id(), addressDTO.getAddress(), addressDTO.getCity(), addressDTO.getCountry());

    }

    public void storeIndex() {
        printStoreInfo(logIn.getStore_id());
        String message = "1. 상점추가 2. 상점수정 3. 상점삭제 0. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 3);
        if (userChoice == 1) {
            storeInsert();
        } else if (userChoice == 2){

        } else if (userChoice == 3){

        } else if (userChoice == 0) {

        }
    }

    public void storeInsert() {
//        `manager_staff_Id`, `address_id`
        StaffController staffController = new StaffController(connection);
        AddressController addressController = new AddressController(connection);
        ArrayList<StaffDTO> staffList = staffController.selectAll();

        StoreDTO storeDTO = new StoreDTO();

        for (StaffDTO s : staffList) {
            AddressDTO a = addressController.selectOne(s.getAddress_id());
            // 번호 첫이름 끝이름 상점번호 주소
            System.out.printf("직원번호: %d. 이름: %s %s, 상점번호: %d, 주소: %s\n", s.getStaff_id(), s.getFirst_name(), s.getLast_name(), s.getStore_id(), a.getAddress());
        }
        String message = "관리자 번호를 입력하시거나 뒤로가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        while (userChoice != 0 && !staffList.contains(new StaffDTO(userChoice))) {
            message = "관리자 번호를 입력하시거나 뒤로가실려면 0을 입력해주세요.";
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }
        int manager_staff_id = userChoice;
        if(userChoice!=0){
            AddressViewer addressViewer = new AddressViewer(SCANNER, connection);
            int address_id = addressViewer.returnAddressId();
            if(address_id == -1){

            }else{
                storeDTO.setManager_staff_Id(manager_staff_id);
                storeDTO.setAddress_id(address_id);
                StoreController storeController = new StoreController(connection);
                storeController.insert(storeDTO);
            }
        }
    }

//    public void storeUpdate() {
//        StaffController staffController = new StaffController(connection);
//        AddressController addressController = new AddressController(connection);
//        ArrayList<StaffDTO> staffList = staffController.selectAll();
//        StoreController storeController = new StoreController(connection);
//        ArrayList<StoreDTO> storeList = storeController.selectAll();
//
//
//        StoreDTO storeDTO = new StoreDTO();
//
//        for (StaffDTO s : staffList) {
//            AddressDTO a = addressController.selectOne(s.getAddress_id());
//            // 번호 첫이름 끝이름 상점번호 주소
//            System.out.printf("직원번호: %d. 이름: %s %s, 상점번호: %d, 주소: %s\n", s.getStaff_id(), s.getFirst_name(), s.getLast_name(), s.getStore_id(), a.getAddress());
//        }
//        String message = "관리자 번호를 입력하시거나 뒤로가실려면 0을 입력해주세요.";
//        int userChoice = ScannerUtil.nextInt(SCANNER, message);
//        while (userChoice != 0 && !staffList.contains(new StaffDTO(userChoice))) {
//            message = "관리자 번호를 입력하시거나 뒤로가실려면 0을 입력해주세요.";
//            userChoice = ScannerUtil.nextInt(SCANNER, message);
//        }
//        int manager_staff_id = userChoice;
//        if(userChoice!=0){
//            AddressViewer addressViewer = new AddressViewer(SCANNER, connection);
//            int address_id = addressViewer.returnAddressId();
//            if(address_id == -1){
//
//            }else{
//                storeDTO.setManager_staff_Id(manager_staff_id);
//                storeDTO.setAddress_id(address_id);
//                storeController.insert(storeDTO);
//            }
//        }
//    }

    public void storeDelete() {
    }

}


















