package viewer;

import controller.*;
import model.*;
import util.ScannerUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalViewer {
    private Scanner SCANNER;
    private Connection connection;
    private StaffDTO logIn;
    private StoreDTO store;

    RentalViewer(Scanner scanner, Connection connection, StaffDTO logIn, StoreDTO store) {
        this.connection = connection;
        this.SCANNER = scanner;
        this.logIn = logIn;
        this.store = store;
    }

    public void printRentalByFilm(int film_id) { // 비디오 대여
        InventoryController inventoryController = new InventoryController(connection);
        ArrayList<InventoryDTO> inventoryList = inventoryController.selectByFilmStore(film_id, store.getStore_id());
        RentalController rentalController = new RentalController(connection);
        for (int i = 0; i < inventoryList.size(); i++) {
            InventoryDTO inventoryDTO = inventoryList.get(i);
            RentalDTO rentalDTO = rentalController.isNullByInventory(inventoryDTO.getInventory_id());
            if (rentalDTO.getRental_id() != 0) {
                inventoryList.remove(inventoryDTO);
            }
        }
        FilmController filmController = new FilmController(connection);
        FilmDTO f = filmController.selectOne(film_id);

        for (InventoryDTO i : inventoryList) {
            System.out.printf("비디오번호: %d, 영화이름: %s, 대여비용: %d\n", i.getInventory_id(), f.getTitle(), f.getRental_rate());
        }
        String message = "비디호번호를 입력하시거나 뒤로 가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);

        while (userChoice != 0 && !inventoryList.contains(new InventoryDTO(userChoice))) {
            System.out.println("잘못 입력하셨습니다.");
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }

        if (userChoice != 0) {
            rentalVideo(userChoice, film_id);    // 인벤토리id
        }


    }

    public void rentalVideo(int inventory_id, int film_id) {
        System.out.println("대여할 회원 선택하기.");
        CustomerDTO customerDTO = null;
        String message = "1. 회원번호로 검색 2. 회원이름으로 검색 0. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 3);
        if (userChoice == 1) {
            customerDTO = customerFindById();
        } else if (userChoice == 2) {
            customerDTO = printCustomerByName();
        } else if (userChoice == 0) {

        }
        if (customerDTO == null) {
            System.out.println("회원선택 실패.");
            printRentalByFilm(film_id);
        } else {
            // 회원찾은상태, inventory_id로 비디오 대여해야함.
            // rentalDTO : inventory_id, customer_id, staff_id
            RentalDTO r = new RentalDTO();
            r.setInventory_id(inventory_id);
            r.setCustomer_id(customerDTO.getCustomer_id());
            r.setStaff_id(logIn.getStaff_id());
            RentalController rentalController = new RentalController(connection);
            rentalController.insert(r);     // 비디오 대여, rental Table에 데이터 삽입

            r = rentalController.isNullByInventory(inventory_id);   // 비디오 대여한 데이터
            FilmController filmController = new FilmController(connection);
            FilmDTO f = filmController.selectOne(film_id); // 영화정보
            PaymentDTO p = new PaymentDTO();
            p.setCustomer_id(customerDTO.getCustomer_id());
            p.setStaff_id(logIn.getStaff_id());
            p.setRental_id(r.getRental_id());
            p.setAmount(f.getRental_rate());
            PaymentController paymentController = new PaymentController(connection);
            paymentController.insert(p);        // payment Table에 데이터 삽입
            System.out.println("비디오 대여 완료!!!");
        }
    }

    public CustomerDTO customerFindById() {
        CustomerController customerController = new CustomerController(connection);
        ArrayList<CustomerDTO> list = customerController.selectAll();
        String message = "회원번호를 입력하시거나 뒤로가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        while (userChoice != 0 && !list.contains(new CustomerDTO(userChoice))) {
            message = "회원번호를 입력하시거나 뒤로가실려면 0을 입력해주세요.";
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }
        if (userChoice != 0) {
            CustomerDTO customerDTO = customerController.selectOne(userChoice);
            return customerDTO;
        }
        return null;
    }

    public CustomerDTO printCustomerByName() {
        CustomerController customerController = new CustomerController(connection);
        String message = "회원 이름을 입력해주세요.";
        String customer_name = ScannerUtil.nextLine(SCANNER, message);
        ArrayList<CustomerDTO> list = customerController.selectByName(customer_name);

        if (list.isEmpty()) {
            System.out.println("해당하는 이름은 존재하지않습니다.");
            return null;
        } else {
            for (CustomerDTO c : list) {
                System.out.printf("회원번호: %d. 이름: %s %s\n", c.getCustomer_id(), c.getFirst_name(), c.getLast_name());
            }
            return customerFindByName(list);
        }

    }

    public CustomerDTO customerFindByName(ArrayList<CustomerDTO> customerList) {
        ArrayList<CustomerDTO> list = customerList;
        String message = "회원번호를 입력하시거나 뒤로 가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);

        while (userChoice != 0 && !list.contains(new CustomerDTO(userChoice))) {
            System.out.println("잘못 입력하셨습니다.");
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }

        if (userChoice != 0) {
            CustomerController customerController = new CustomerController(connection);
            return customerController.selectOne(userChoice);
        }
        return null;
    }
    /////////////////////

    public void showCustomerInfo() {

        CustomerDTO customerDTO = null;
        String message = "1. 회원번호로 검색 2. 회원이름으로 검색 0. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 3);
        if (userChoice == 1) {
            customerDTO = customerFindById();
        } else if (userChoice == 2) {
            customerDTO = printCustomerByName();
        } else if (userChoice == 0) {

        }
        if (customerDTO == null) {
            System.out.println("회원선택 실패.");
        } else {
            userChoice = -1;
            while(userChoice !=0) {
                message = "1. 비디오 반납 2. 대여내역 보기 0. 뒤로가기";
                userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 2);

                if (userChoice == 1) {
                    returnVideo(customerDTO);   // 비디오 반납
                } else if (userChoice == 2) {
                    showRentalList(customerDTO);   //대여내역보기
                } else if (userChoice == 0) {

                }
            }
        }
    }

    public void returnVideo(CustomerDTO customerDTO) {
        ArrayList<RentalDTO> list = printRentalInfo(customerDTO);
        String message = "반납할 대여번호를 입력해주시거나 뒤로가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        while (userChoice != 0 && !list.contains(new RentalDTO(userChoice))) {
            message = "반납할 대여번호를 입력해주시거나 뒤로가실려면 0을 입력해주세요.";
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }
        if (userChoice != 0) {
            RentalController rentalController = new RentalController(connection);
            rentalController.update(new RentalDTO(userChoice));
            System.out.println("반납 성공!!!");
        }

    }

    public ArrayList<RentalDTO> printRentalInfo(CustomerDTO customerDTO) {
        RentalController rentalController = new RentalController(connection);
        ArrayList<RentalDTO> list = rentalController.isNullByCustomer(customerDTO.getCustomer_id());
        InventoryController inventoryController = new InventoryController(connection);
        FilmController filmController = new FilmController(connection);
        if(list.isEmpty()){
            System.out.println("대여한 비디오가 없습니다.");
        }else {
            for (RentalDTO r : list) {
                InventoryDTO i = inventoryController.selectOne(r.getInventory_id());
                FilmDTO f = filmController.selectOne(i.getFilm_id());
                System.out.printf("대여번호: %d. 비디오번호: %d, 영화: %s 대여날짜: %s\n", r.getRental_id(), i.getInventory_id(), f.getTitle(), r.getRental_date());
            }
        }
        return list;

    }

    public void showRentalList(CustomerDTO customerDTO) {
        RentalController rentalController = new RentalController(connection);
        ArrayList<RentalDTO> list = rentalController.selectAllById(customerDTO.getCustomer_id());
        InventoryController inventoryController = new InventoryController(connection);
        FilmController filmController = new FilmController(connection);

        if (list.isEmpty()) {
            System.out.println("빌린내역이 존재하지않습니다.");
        } else {
            System.out.println("================================================================================");
            for (RentalDTO r : list) {
                InventoryDTO i = inventoryController.selectOne(r.getInventory_id());
                FilmDTO f = filmController.selectOne(i.getFilm_id());
                System.out.printf("대여번호: %d. 비디오번호: %d, 영화: %s\n", r.getRental_id(), i.getInventory_id(), f.getTitle());
                System.out.printf("대여비용: %d, 대여날짜: %s, 반환날짜: %s\n",f.getRental_rate(), r.getRental_date(), r.getReturn_date());
                System.out.println("----------------------------------------------------------------------------");
            }
            PaymentController paymentController = new PaymentController(connection);
            double sumPay = paymentController.sumPaymentById(customerDTO.getCustomer_id());
            System.out.printf("총 지불금액 : %f\n", sumPay);
        }
    }
}























