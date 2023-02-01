package viewer;

import controller.CustomerController;
import model.CustomerDTO;
import model.StaffDTO;
import util.ScannerUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerViewer {
    Scanner SCANNER;
    Connection connection;
    StaffDTO logIn;
    public CustomerViewer(Scanner scanner, Connection connection, StaffDTO logIn){
        this.SCANNER = scanner;
        this.connection = connection;
        this.logIn = logIn;
    }
    public void showIndex(){
        String message = "1. 회원 등록 2. 회원수정 3. 회원탈퇴 0. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 3);
        if(userChoice == 1){
            register();
        }else if(userChoice == 2){
            updateIndex();
        }else if(userChoice == 3){
            deleteIndex();
        }else if(userChoice == 0){

        }
    }
    private void register(){
        // `first_name`, `last_name`, `eMail`, `address_id`
        CustomerDTO customerDTO = new CustomerDTO();
        String message = "first_name을 입력해주세요.";
        String first_name = ScannerUtil.nextLine(SCANNER, message);
        message = "last_name을 입력해주세요.";
        String last_name = ScannerUtil.nextLine(SCANNER, message);
        message = "email을 입력해주세요.";
        String email = ScannerUtil.nextLine(SCANNER, message);

        customerDTO.setFirst_name(first_name);
        customerDTO.setLast_name(last_name);
        customerDTO.seteMail(email);

        AddressViewer addressViewer = new AddressViewer(SCANNER, connection);
        int address_id = addressViewer.returnAddressId();
        if(address_id == -1){

        }else{
            customerDTO.setAddress_id(address_id);
            CustomerController customerController = new CustomerController(connection);
            customerController.insert(customerDTO);
            System.out.println("회원등록 성공!!!");
        }
    }
    private void updateIndex(){
        CustomerDTO customerDTO = null;
        String message = "1. 회원번호로 검색 2. 회원이름으로 검색 0. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 2);
        if(userChoice == 1){
            customerDTO = customerFindById();
            update(customerDTO);
        }else if(userChoice == 2){
            customerDTO = printCustomerByName();
            update(customerDTO);
        }else if(userChoice == 0){

        }
    }
    private void update(CustomerDTO customerDTO){
        //`fist_name` = ?, `last_name`=?, `email`=?, `address_id` = ? 
        CustomerDTO c = new CustomerDTO();
        String message = "변경하실 first_name을 입력해주세요.";
        String first_name = ScannerUtil.nextLine(SCANNER, message);
        message = "변경하실 last_name을 입력해주세요.";
        String last_name = ScannerUtil.nextLine(SCANNER, message);
        message = "변경하실 email을 입력해주세요.";
        String email = ScannerUtil.nextLine(SCANNER, message);

        AddressViewer addressViewer = new AddressViewer(SCANNER, connection);
        int address_id = addressViewer.returnAddressId();
        if(address_id == -1){

        }else{
            customerDTO.setFirst_name(first_name);
            customerDTO.setLast_name(last_name);
            customerDTO.seteMail(email);
            customerDTO.setAddress_id(address_id);

            CustomerController customerController = new CustomerController(connection);
            customerController.update(customerDTO);
            System.out.println("회원수정 성공!!!");
        }
    }
    private void deleteIndex(){
        CustomerDTO customerDTO = null;
        String message = "1. 회원번호로 검색 2. 회원이름으로 검색 0. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 2);
        if(userChoice == 1){
            customerDTO = customerFindById();
            delete(customerDTO);
        }else if(userChoice == 2){
            customerDTO = printCustomerByName();
            delete(customerDTO);
        }else if(userChoice == 0){

        }
    }
    private void delete(CustomerDTO customerDTO){
        CustomerController customerController = new CustomerController(connection);
        customerController.delete(customerDTO.getCustomer_id());
        System.out.println("회원 삭제 성공!!!");
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
}





















