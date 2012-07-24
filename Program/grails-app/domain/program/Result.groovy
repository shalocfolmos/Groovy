package program

class Result {

    String consumeName
    String contact
    String creditCard
    String phone
    String address
    String postCode
    String postName
    Date saleDate
    String enterprise
    String productType
    String productNumber
    Double price
    String receiptNumber
    String dealerName
    String organizationCOde
    Person person


    static constraints = {
        consumeName maxSize: 20, blank: false, nullable: false
        contact maxSize: 15, blank: false,nullable: false
        creditCard maxSize: 18, blank: false,nullable: false
        saleDate blank:false,nullable: false
        phone blank: false,nullable: false
        address maxSize: 60, blank: false,nullable: false
        postCode maxSize: 20, blank: false,nullable: false
        postName maxSize: 60, blank: false,nullable: false
        enterprise maxSize: 50, blank: false,nullable: false
        productType maxSize: 30, blank: false,nullable: false
        productNumber maxSize: 30, blank: false,nullable: false
        price blank: false,nullable: false
        receiptNumber maxSize: 30, blank: false,nullable: false
        dealerName maxSize: 50, blank: false,nullable: false
        organizationCOde maxSize: 30, blank: false,nullable: false
    }

}
