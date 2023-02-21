

class Person {
    
    constructor(_id, name, surname, idNumber, email, phoneNumber) {
        this._id = _id;
        this.name = name;
        this.surname = surname;
        this.idNumber = idNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    getObjectId() {
        return this._id;
    }

    getName() {
        return this.name;
    }

    getSurname() {
        return this.surname;
    }

    getidNumber() {
        return this.idNumber;
    }

    getEmail() {
        return this.email;
    }

    getPhoneNumber() {
        return this.phoneNumber;
    }

}

class Booking {

    constructor(_id, email, bookingdate, checkInDate, checkOutDate, roomNumber, visitSpecification, amount){
        this._id = id;
        this.email = email;
        this.bookingdate = bookingdate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNumber = roomNumber;
        this.visitSpecification = visitSpecification;
        this.amount = amount;
    }

    getObjectId() {
        return this._id;
    }

    getEmail() {
        return this.email;
    }

    getBookingDate() {
        return this.bookingdate;
    }

    getCheckInDate(){
        return this.checkInDate;
    }

    getCheckOutDate() {
        return this.checkOutDate;
    }

    getRoomNumber() {
        return this.roomNumber;
    }

    getVisitSpec() {
        return this.visitSpecification;
    }

    getAmount() {
        return this.amount;
    }

}