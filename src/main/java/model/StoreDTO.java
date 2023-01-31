package model;

import java.util.ArrayList;

public class StoreDTO {
    // store, inventory, address, city
    // 비디오번호, 스태프

    int id;
    int manager_staff_Id;
    int address_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManager_staff_Id() {
        return manager_staff_Id;
    }

    public void setManager_staff_Id(int manager_staff_Id) {
        this.manager_staff_Id = manager_staff_Id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
}
