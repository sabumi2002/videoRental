package model;

public class InventoryDTO {
    int inventory_id;
    int film_id;
    int store_id;

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public InventoryDTO(){}
    public InventoryDTO(int inventory_id){
        this.inventory_id = inventory_id;
    }

    public boolean equals(Object o) {
        if(o instanceof InventoryDTO){
            InventoryDTO i = (InventoryDTO) o;
            return inventory_id == i.inventory_id;

        }

        return false;
    }
}
