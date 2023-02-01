package viewer;

import controller.A_filmController;
import controller.InventoryController;
import controller.RentalController;
import model.*;
import util.ScannerUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class FilmViewer {
    Scanner SCANNER;
    Connection connection;
    StaffDTO logIn;
    StoreDTO store;
    FilmViewer(Scanner scanner, Connection connection, StaffDTO logIn, StoreDTO store) {
        this.connection = connection;
        this.SCANNER = scanner;
        this.logIn = logIn;
        this.store = store;
    }

    public void printById(int film_id ){
        A_filmController filmController = new A_filmController(connection);
        A_filmDTO f = filmController.selectOne(film_id, store.getStore_id());
        InventoryController inventoryController = new InventoryController(connection);
        ArrayList<InventoryDTO> inventoryList = inventoryController.selectByFilmStore(film_id, store.getStore_id());
        RentalController rentalController = new RentalController(connection);

        for(int i =0; i< inventoryList.size();i++){
            InventoryDTO inventoryDTO = inventoryList.get(i);
            RentalDTO rentalDTO = rentalController.isNullByInventory(inventoryDTO.getInventory_id());
            if(rentalDTO.getRental_id()!=0){
                inventoryList.remove(inventoryDTO);
            }
        }


        System.out.println("==========================================================");
        System.out.printf("영화번호: %d번 제목: %s 개봉연도: %d\n", f.getFilm_id(), f.getTitle(), f.getRelease_year());
        System.out.printf("개봉언어: %s 카테고리: %s 상영길이: %d분\n", f.getLanguage(), f.getCategory(), f.getLength());
        System.out.printf("특징: %s\n", f.getSpecial_features());
        System.out.println("----------------------------------------------------------");
        System.out.printf("줄거리: %s\n", f.getDescription());
        System.out.println("----------------------------------------------------------");
        System.out.printf("현재 남은 비디오 개수: %d개\n", inventoryList.size());
        System.out.println("==========================================================");
        String message = "1. 대여하기 0. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 0, 1);
        if(userChoice ==1){
            RentalViewer rentalViewer = new RentalViewer(SCANNER, connection, logIn, store);
            rentalViewer.printRentalByFilm(film_id);
        } else if (userChoice ==0) {

        }
    }



}

















