package viewer;

import controller.AddressController;
import model.AddressDTO;
import util.ScannerUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressViewer {
    Scanner SCANNER;
    Connection connection;

    public AddressViewer(Scanner scanner, Connection connection){
        this.SCANNER = scanner;
        this.connection = connection;
    }

    public int returnAddressId(){
        AddressController addressController = new AddressController(connection);
        ArrayList<AddressDTO> list =  addressController.selectAll();

        for(AddressDTO a : list){
            System.out.printf("주소번호: %d. 주소: %s, 도시: %s, 국가: %s, 우편번호: %s\n",
                    a.getAddress_id(), a.getAddress(), a.getCity(), a.getCountry(), a.getPostal_code());
        }
        String message = "주소번호를 입력하거나 뒤로가시려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        while (userChoice !=0 && list.contains(new AddressDTO(userChoice))){
            message = "주소번호를 입력하거나 뒤로가시려면 0을 입력해주세요.";
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }
        if(userChoice !=0){
            return userChoice;
        }
        return -1;
    }







}













