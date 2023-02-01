package model;

import java.util.Date;

public class RentalDTO {
    // payment, rental, inventory, film
    // rental_id, payment_amount, rental_date, return_date, customer_id, film_id, store_id, staff_id
    int rental_id;
    Date rental_date;
    int inventory_id;
    int customer_id;
    Date return_date;
    int staff_id;

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public Date getRental_date() {
        return rental_date;
    }

    public void setRental_date(Date rental_date) {
        this.rental_date = rental_date;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public RentalDTO(){}
    public RentalDTO(int rental_id){
        this.rental_id = rental_id;
    }
    @Override
    public boolean equals(Object o) {
        if(o instanceof RentalDTO){
            RentalDTO r = (RentalDTO) o;
            return rental_id == r.getRental_id();
        }
        return false;
    }
}
